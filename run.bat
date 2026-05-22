@echo off
echo ========================================
echo Compiling Employee Payroll System...
echo ========================================

:: Create a bin directory for compiled classes
if not exist bin mkdir bin

:: Compile all Java files in the src directory structure
javac -d bin src/com/payrollsystem/models/*.java src/com/payrollsystem/services/*.java src/com/payrollsystem/Main.java

if %ERRORLEVEL% equ 0 (
    echo Compilation Successful!
    echo.
    echo Starting Application...
    echo.
    :: Run the Main class
    java -cp bin com.payrollsystem.Main
) else (
    echo.
    echo Compilation Failed. Please check the errors above.
)

pause
