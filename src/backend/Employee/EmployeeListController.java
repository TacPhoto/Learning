package backend.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListController {
    private List<Employee> employeeList;

    public EmployeeListController() {
        this.employeeList = new ArrayList<Employee>();
    }

    public void setListFromFile(String csvPath){ //todo: implement csvReader etc
        //employeeList =
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(String surname, String name, Position position, int seniority, double salary){
        employeeList.add(new Employee(surname, name, position, seniority, salary));
    }

}
