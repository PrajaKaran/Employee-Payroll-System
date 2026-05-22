package com.payrollsystem.models;

/**
 * Concrete class representing a full-time permanent employee.
 * Inherits from Employee and implements payroll calculation logic.
 */
public class PermanentEmployee extends Employee {

    private static final double TAX_RATE = 0.10; // 10% tax deduction
    private static final double PF_RATE = 0.12;  // 12% Provident Fund deduction

    public PermanentEmployee(String id, String name, String department, double basicSalary) {
        super(id, name, department, basicSalary);
    }

    @Override
    public double calculateTax() {
        return getBasicSalary() * TAX_RATE;
    }

    @Override
    public double calculatePF() {
        return getBasicSalary() * PF_RATE;
    }

    @Override
    public double calculateNetSalary() {
        return getBasicSalary() - calculateTax() - calculatePF();
    }

    @Override
    public String toCSV() {
        // Format: ID,Name,Department,BasicSalary
        return String.join(",", getId(), getName(), getDepartment(), String.valueOf(getBasicSalary()));
    }
    
    /**
     * Factory method to create an Employee object from a CSV line.
     * @param csvLine comma separated string of employee details.
     * @return PermanentEmployee object or null if parsing fails.
     */
    public static PermanentEmployee fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 4) {
            try {
                return new PermanentEmployee(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
            } catch (NumberFormatException e) {
                System.err.println("Error parsing salary for employee ID: " + parts[0]);
            }
        }
        return null;
    }
}
