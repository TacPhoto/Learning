package backend.fileHandling;

import backend.Employee.Employee;
import backend.Employee.EmployeeListController;
import frontend.EmployeeTable;

import java.io.*;
import java.util.List;

public class CsvWriter {
    private List<Employee> employeeList;
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

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
