package org.shikhar.dropwizard.core;

import com.google.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.shikhar.dropwizard.api.Employee;
import org.shikhar.dropwizard.api.EmployeePage;
import org.shikhar.dropwizard.api.ImmutableEmployee;
import org.shikhar.dropwizard.api.ImmutableEmployeePage;
import org.shikhar.dropwizard.db.EmployeeDAO;

public class EmployeeManager {
  private final EmployeeDAO employeeDAO;

  @Inject
  public EmployeeManager(EmployeeDAO employeeDAO) {
    this.employeeDAO = employeeDAO;
  }

  public EmployeePage getEmployees(Optional<String> cursor, int limit) {
    int safeLimit = Math.min(Math.max(limit, 1), 100);
    int afterId = cursor.map(this::decodeCursor).orElse(0);

    // Fetch 1 extra row to check if a next page exists
    List<Employee> rows = employeeDAO.findPage(afterId, safeLimit + 1);

    boolean hasMore = rows.size() > safeLimit;
    List<Employee> pageData = hasMore ? rows.subList(0, safeLimit) : rows;

    Optional<String> nextCursor =
        hasMore
            ? pageData.get(pageData.size() - 1).getId().map(this::encodeCursor)
            : Optional.empty();

    return ImmutableEmployeePage.builder()
        .data(pageData)
        .nextCursor(nextCursor)
        .hasMore(hasMore)
        .build();
  }

  private String encodeCursor(int id) {
    return Base64.getUrlEncoder()
        .withoutPadding()
        .encodeToString(String.valueOf(id).getBytes(StandardCharsets.UTF_8));
  }

  private int decodeCursor(String cursor) {
    try {
      String decoded = new String(Base64.getUrlDecoder().decode(cursor), StandardCharsets.UTF_8);
      return Integer.parseInt(decoded);
    } catch (IllegalArgumentException e) {
      throw new BadRequestException("Invalid pagination cursor");
    }
  }

  public Optional<Employee> getEmployeeById(int id) {
    return employeeDAO.findById(id);
  }

  public Employee createEmployee(Employee employee) {

    int newId = employeeDAO.insert(employee);

    return ImmutableEmployee.builder().from(employee).id(newId).build();
  }

  public boolean updateEmployee(Employee employee) {
    return employeeDAO.update(employee) > 0;
  }

  public boolean deleteEmployee(int id) {
    return employeeDAO.delete(id) > 0;
  }
}
