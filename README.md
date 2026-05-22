# Employee Payroll Management System

A CLI-based Java application to manage employee payroll operations. Designed using Object-Oriented Programming (OOP) principles and file handling for data persistence.

## Features
- **Add Employee Details**: Add new employees with their ID, Name, Department, and Basic Salary.
- **Generate Salary Slips**: Automatically calculates deductions like Tax (10%) and Provident Fund (12%), and calculates Net Salary.
- **View All Employees**: Display a neatly formatted tabular view of all stored employees and their net salaries.
- **Data Persistence**: Saves and loads all employee records locally using a `data/employees.csv` file, ensuring data isn't lost when the application closes.

## Project Structure
```text
src/
└── com/payrollsystem/
    ├── models/
    │   ├── Employee.java (Abstract Base Class)
    │   └── PermanentEmployee.java (Concrete Subclass)
    ├── services/
    │   ├── FileStorageService.java (Handles CSV Reading/Writing)
    │   └── PayrollManager.java (Business Logic & State)
    └── Main.java (CLI Application Entry Point)
```

## How to Run

### Windows
Double click the `run.bat` file, or open Command Prompt and execute:
```cmd
run.bat
```

### Manual Compilation
Alternatively, you can compile and run using standard Java commands:
```bash
# Compile
javac -d bin src/com/payrollsystem/models/*.java src/com/payrollsystem/services/*.java src/com/payrollsystem/Main.java

# Run
java -cp bin com.payrollsystem.Main
```
