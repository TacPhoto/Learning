package backend.fileHandling;

import backend.employee.Employee;
import backend.employee.EmployeeListController;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * CsvWriter is meant to save EmployeeTable data to CSV file
 * It does not provide any kind of validation nor safety checks
 * Use EmployeeListController to check if the list is valid before using CsvWriter
 */
public class CsvWriter {
    private final List<Employee> employeeList;
    private PrintWriter writer;
    private char delimiter;

    public CsvWriter(@NotNull EmployeeListController employeeListController, String outputPath) throws IOException {
        this.employeeList = employeeListController.getEmployeeList();
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(outputPath)));
        delimiter = ';';
    }

    private void serializeEmployee(@NotNull Employee employee) {
        writer.write(employee.getSurname() + delimiter);
        writer.write(employee.getName() + delimiter);
        writer.write(employee.getPosition().toString() + delimiter);
        writer.write(String.valueOf(employee.getSeniority()) + delimiter);
        writer.write(String.valueOf(employee.getSalary()) + delimiter);
        writer.flush();
    }

    public void saveList() {
        for (int i = 0; i < employeeList.size(); i++) {
            serializeEmployee(employeeList.get(i));
            if (i < employeeList.size() - 1)
                writer.println();
        }
    }

}
