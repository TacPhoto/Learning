package backend.fileHandling;

import backend.employee.Employee;
import backend.employee.EmployeeListController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {
    private final List<Employee> employeeList;
    private String outputPath;
    private PrintWriter writer;
    private char delimiter;

    public CsvWriter(EmployeeListController employeeListController, String outputPath) throws IOException {
        this.employeeList = employeeListController.getEmployeeList();
        this.outputPath = outputPath;
        this.writer = new PrintWriter(new BufferedWriter(new FileWriter(outputPath)));
        delimiter = ';';
    }

    private void serializeEmployee(Employee employee){
        writer.write(employee.getSurname() + delimiter);
        writer.write(employee.getName() + delimiter);
        writer.write(employee.getPosition().toString() + delimiter);
        writer.write(String.valueOf(employee.getSeniority()) + delimiter);
        writer.write(String.valueOf(employee.getSalary()) + delimiter);
        writer.flush();
    }

    public void saveList(){
        for(int i = 0; i < employeeList.size(); i++){
            serializeEmployee(employeeList.get(i));
            if(i < employeeList.size() - 1)
                writer.println();
        }
    }

    public void setOutputPath(String outputPath) throws IOException {
        this.outputPath = outputPath;
        writer.close(); //to be sure that stream is closed
        writer = new PrintWriter(new BufferedWriter(new FileWriter(outputPath))); //override old writer because
    }
}
