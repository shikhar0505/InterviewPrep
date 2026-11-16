package org.shikhar.dropwizard.resources;

import jakarta.validation.ConstraintViolation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.Validator;
import jakarta.ws.rs.core.Response;
import org.shikhar.dropwizard.api.Employee;
import org.shikhar.dropwizard.api.ImmutableEmployee;
import org.shikhar.dropwizard.core.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Path("employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private final Validator validator;
    private final EmployeeService employeeService;

    public EmployeeResource(Validator validator, EmployeeService employeeService) {
        this.validator = validator;
        this.employeeService = employeeService;
    }

    @GET
    public Response getAllEmployees() {
        return Response.ok(employeeService.getAllEmployees()).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") int id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return Response.ok(employee.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createEmployee(Employee employee) {
        List<String> validationMessages = validateEmployee(employee);
        if (!validationMessages.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }

        Employee createdEmployee = employeeService.createEmployee(employee);
        return Response.status(Response.Status.CREATED).entity(createdEmployee).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") int id, Employee employee) {
        ArrayList<String> validationMessages = validateEmployee(employee);
        if (!validationMessages.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();
        }

        employee.setEmployeeId(id); // Ensure the ID is set for the update
        boolean isUpdated = employeeService.updateEmployee(employee);

        if (isUpdated) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Employee with ID " + id + " not found.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    private ArrayList<String> validateEmployee(Employee employee) {
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        ArrayList<String> validationMessages = new ArrayList<>();
        for (ConstraintViolation<Employee> violation : violations) {
            validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
        }
        return validationMessages;
    }
}
