package org.shikhar.dropwizard.db;

import org.shikhar.dropwizard.api.Employee;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    @SqlQuery("SELECT * FROM employees WHERE id = :id")
    Optional<Employee> findById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM employees")
    List<Employee> findAll();

    @SqlUpdate("INSERT INTO employees (firstName, lastName, email) VALUES (:firstName, :lastName, :email)")
    @GetGeneratedKeys
    int insert(@BindBean Employee person);

    @SqlUpdate("UPDATE employees SET firstName = :firstName, lastName = :lastName, email = :email WHERE id = :id")
    int update(@BindBean Employee person);

    @SqlUpdate("DELETE FROM employees WHERE id = :id")
    int delete(@Bind("id") int id);
}
