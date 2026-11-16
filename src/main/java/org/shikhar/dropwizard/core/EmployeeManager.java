package org.shikhar.dropwizard.core;

import com.google.inject.Inject;
import java.util.List;
import java.util.Optional;
import org.shikhar.dropwizard.api.Employee;
import org.shikhar.dropwizard.api.ImmutableEmployee;
import org.shikhar.dropwizard.db.EmployeeDAO;

public class EmployeeManager {
  private final EmployeeDAO employeeDAO;

  @Inject
  public EmployeeManager(EmployeeDAO employeeDAO) {
    this.employeeDAO = employeeDAO;
  }

  public List<Employee> getAllEmployees() {

    return employeeDAO.findAll();
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
