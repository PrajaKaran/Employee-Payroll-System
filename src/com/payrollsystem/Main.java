package com.payrollsystem;

import com.payrollsystem.services.PayrollManager;

import java.util.Scanner;

/**
 * Main entry point for the CLI application.
 * Manages the user interface and menu loops.
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PayrollManager manager = new PayrollManager();

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("Initializing Employee Payroll Management System...");
        
        while (!exit) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    System.out.println("\n--- All Employees ---");
                    manager.viewAllEmployees();
                    break;
                case 3:
                    generateSalarySlip();
                    break;
                case 4:
                    System.out.println("Saving data and exiting. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
            
            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("======================================");
        System.out.println("  EMPLOYEE PAYROLL MANAGEMENT SYSTEM  ");
        System.out.println("======================================");
        System.out.println("1. Add New Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Generate Salary Slip & Search by ID");
        System.out.println("4. Exit");
        System.out.println("======================================");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // Return an invalid option to trigger default case
        }
    }

    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        
        System.out.print("Enter Employee ID (e.g. EMP01): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("ID cannot be empty. Operation cancelled.");
            return;
        }
        
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Operation cancelled.");
            return;
        }
        
        System.out.print("Enter Department (e.g. Engineering, HR, Sales): ");
        String dept = scanner.nextLine().trim();
        
        System.out.print("Enter Basic Salary: ₹");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine().trim());
            if (salary < 0) {
                System.out.println("Salary cannot be negative. Operation cancelled.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for salary. Operation cancelled.");
            return;
        }

        manager.addEmployee(id, name, dept, salary);
    }

    private static void generateSalarySlip() {
        System.out.println("\n--- Generate Salary Slip ---");
        System.out.print("Enter Employee ID to search: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("ID cannot be empty.");
            return;
        }
        manager.searchAndGenerateSlip(id);
    }
}
