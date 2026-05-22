package com.payrollsystem.models;

/**
 * Abstract class representing a generic Employee.
 * Demonstrates inheritance and abstraction.
 */
public abstract class Employee {
    private String id;
    private String name;
    private String department;
    private double basicSalary;

    public Employee(String id, String name, String department, double basicSalary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public double getBasicSalary() { return basicSalary; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }

    // Abstract methods to be implemented by subclasses
    public abstract double calculateTax();
    public abstract double calculatePF();
    public abstract double calculateNetSalary();

    /**
     * Generates a formatted salary slip for the employee.
     */
    public void printSalarySlip() {
        System.out.println("======================================");
        System.out.println("            SALARY SLIP               ");
        System.out.println("======================================");
        System.out.println("Employee ID   : " + id);
        System.out.println("Name          : " + name);
        System.out.println("Department    : " + department);
        System.out.println("--------------------------------------");
        System.out.printf("Basic Salary  : ₹%.2f%n", basicSalary);
        System.out.printf("Tax Deduction : ₹%.2f%n", calculateTax());
        System.out.printf("PF Deduction  : ₹%.2f%n", calculatePF());
        System.out.println("--------------------------------------");
        System.out.printf("NET SALARY    : ₹%.2f%n", calculateNetSalary());
        System.out.println("======================================");
    }
    
    // Abstract method to format data for file storage
    public abstract String toCSV();
}
