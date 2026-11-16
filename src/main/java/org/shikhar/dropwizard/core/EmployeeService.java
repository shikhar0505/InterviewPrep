package org.shikhar.dropwizard.core;

import org.shikhar.dropwizard.api.Employee;
import org.shikhar.dropwizard.db.EmployeeDAO;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
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
        employee.setId(newId);
        return employee;
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDAO.update(employee) > 0;
    }

    public boolean deleteEmployee(int id) {
        return employeeDAO.delete(id) > 0;
    }
}
