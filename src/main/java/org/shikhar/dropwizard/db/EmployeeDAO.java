package org.shikhar.dropwizard.db;

import com.hubspot.rosetta.jdbi3.BindWithRosetta;
import java.util.List;
import java.util.Optional;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.shikhar.dropwizard.api.Employee;

public interface EmployeeDAO {
  @SqlQuery("SELECT id, first_name, last_name, email FROM employees WHERE id = :id")
  Optional<Employee> findById(@Bind("id") int id);

  @SqlQuery(
      "SELECT id, first_name, last_name, email FROM employees "
          + "WHERE id > :afterId ORDER BY id ASC LIMIT :limit")
  List<Employee> findPage(@Bind("afterId") int afterId, @Bind("limit") int limit);

  @SqlUpdate(
      "INSERT INTO employees (first_name, last_name, email) VALUES (:first_name, :last_name, :email)")
  @GetGeneratedKeys
  int insert(@BindWithRosetta Employee person);

  @SqlUpdate(
      "UPDATE employees SET first_name = :first_name, last_name = :last_name, email = :email WHERE id = :id")
  int update(@BindWithRosetta Employee person);

  @SqlUpdate("DELETE FROM employees WHERE id = :id")
  int delete(@Bind("id") int id);
}
