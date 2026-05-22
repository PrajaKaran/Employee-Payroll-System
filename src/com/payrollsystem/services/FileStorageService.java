package com.payrollsystem.services;

import com.payrollsystem.models.Employee;
import com.payrollsystem.models.PermanentEmployee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all file-based persistence operations.
 * Reads from and writes to a CSV file.
 */
public class FileStorageService {
    // We store data in a simple CSV format.
    private static final String DATA_DIR = "data";
    private static final String FILE_NAME = DATA_DIR + "/employees.csv";

    public FileStorageService() {
        // Ensure data directory exists
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Saves the list of employees to a file, overwriting the previous contents.
     * @param employees List of Employee objects to save.
     */
    public void saveEmployees(List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employees) {
                writer.write(emp.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving records to CSV: " + e.getMessage());
        }
    }

    /**
     * Loads the list of employees from the file system.
     * @return List of parsed Employee objects.
     */
    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(FILE_NAME);
        
        // If file doesn't exist, we just return an empty list
        if (!file.exists()) {
            return employees;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                PermanentEmployee emp = PermanentEmployee.fromCSV(line);
                if (emp != null) {
                    employees.add(emp);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading records from CSV: " + e.getMessage());
        }
        return employees;
    }
}
