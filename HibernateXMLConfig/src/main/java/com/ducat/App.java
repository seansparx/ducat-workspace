package com.ducat;

import java.util.List;

public class App {

    static EmployeeDAO employeeDAO;

    public static void main(String[] args) {
        // Initialize the EmployeeDAO
        employeeDAO = new EmployeeDAO();
        
        // Add Employees
        addEmployee("Sean", "Rock", "sean.rock@ducat.com");
        addEmployee("John", "Smith", "john.smith@ducat.com");

        // List Employees
        listEmployees();
        
        // Update Employee
        updateEmployee(15);

        // Delete Employee
        deleteEmployee(1);

        // List Employees
        listEmployees();

        // Shutdown Hibernate
        HibernateUtil.shutdown();
    }
    
    /**
     * Adds a new employee to the database.
     *
     * @param firstName The first name of the employee.
     * @param lastName The last name of the employee.
     * @param email The email address of the employee.
     */
    private static void addEmployee(String firstName, String lastName, String email) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        
        try {
            employeeDAO.addEmployee(employee);
            System.out.println("Employee added: " + employee);
        } catch (Exception e) {
            System.err.println("Error adding employee: " + e.getMessage());
        }
    }
    
    /**
     * Updates the email address of an existing employee.
     *
     * @param id The ID of the employee to update.
     */
    private static void updateEmployee(int id) {
        try {
            Employee employeeToUpdate = employeeDAO.getEmployee(id);
            if (employeeToUpdate != null) {
                employeeToUpdate.setEmail("john.updated@ducat.com");
                employeeDAO.updateEmployee(employeeToUpdate);
                System.out.println("Employee updated: " + employeeToUpdate);
            } else {
                System.err.println("Employee with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.err.println("Error updating employee: " + e.getMessage());
        }
    }
    
    /**
     * Deletes an employee from the database.
     *
     * @param id The ID of the employee to delete.
     */
    private static void deleteEmployee(int id) {
        try {
            employeeDAO.deleteEmployee(id);
            System.out.println("Employee with ID " + id + " deleted.");
        } catch (Exception e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }
    
    /**
     * Lists all employees in the database.
     */
    private static void listEmployees() {
        try {
            List<Employee> employees = employeeDAO.getAllEmployees();
            for (Employee employee : employees) {
                System.out.println(employee.getId() + ": " + employee.getFirstName() + " " + employee.getLastName() + " - " + employee.getEmail());
            }
        } catch (Exception e) {
            System.err.println("Error listing employees: " + e.getMessage());
        }
    }
}
