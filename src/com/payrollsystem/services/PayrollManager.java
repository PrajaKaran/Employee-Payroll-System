package com.payrollsystem.services;

import com.payrollsystem.models.Employee;
import com.payrollsystem.models.PermanentEmployee;

import java.util.List;
import java.util.Optional;

/**
 * Service class that manages all business logic and state for the Payroll System.
 */
public class PayrollManager {
    private List<Employee> employees;
    private FileStorageService fileService;

    public PayrollManager() {
        fileService = new FileStorageService();
        // Load initial records from the CSV file
        employees = fileService.loadEmployees();
    }

    /**
     * Adds a new employee to the system and persists the change.
     */
    public void addEmployee(String id, String name, String department, double salary) {
        // Check for duplicates
        if (findEmployeeById(id).isPresent()) {
            System.out.println("Error: An employee with ID '" + id + "' already exists!");
            return;
        }
        
        Employee newEmployee = new PermanentEmployee(id, name, department, salary);
        employees.add(newEmployee);
        fileService.saveEmployees(employees);
        System.out.println("Success: Employee '" + name + "' added successfully!");
    }

    /**
     * Displays a tabular view of all employees.
     */
    public void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found in the system.");
            return;
        }
        
        System.out.printf("%-10s | %-20s | %-15s | %-10s | %-10s%n", "ID", "Name", "Department", "Basic", "Net Salary");
        System.out.println("-------------------------------------------------------------------------------");
        for (Employee emp : employees) {
            System.out.printf("%-10s | %-20s | %-15s | ₹%-9.2f | ₹%-9.2f%n", 
                emp.getId(), emp.getName(), emp.getDepartment(), emp.getBasicSalary(), emp.calculateNetSalary());
        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Total Employees: " + employees.size());
    }

    /**
     * Searches for an employee by ID and prints their salary slip.
     */
    public void searchAndGenerateSlip(String id) {
        Optional<Employee> employee = findEmployeeById(id);
        if (employee.isPresent()) {
            employee.get().printSalarySlip();
        } else {
            System.out.println("Error: Employee with ID '" + id + "' not found.");
        }
    }

    /**
     * Helper method to find an employee by their ID (case-insensitive).
     */
    private Optional<Employee> findEmployeeById(String id) {
        return employees.stream()
                .filter(e -> e.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}
