package backend.fileHandling;

import backend.Employee.Employee;

import java.io.*;
import java.util.List;

public class CsvWriter {
    private List<Employee> employeeList;
    private String outputPath;
    private PrintWriter writer;
    private char delimiter;

    public CsvWriter(List<Employee> employeeList, String outputPath) throws IOException {
        this.employeeList = employeeList;
        this.outputPath = outputPath;
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(outputPath)));
        delimiter = ';';
    }

    public void serializeEmployee(Employee employee){
        writer.write(employee.getSurname() + delimiter);
        writer.write(employee.getName() + delimiter);
        writer.write(employee.getPosition().toString() + delimiter);
        writer.write(String.valueOf(employee.getSalary()) + delimiter);
        writer.write("\n");

        writer.flush();
    }

    public void saveList(){
        for(Employee employee: employeeList){
            serializeEmployee(employee);
        }
    }
}
