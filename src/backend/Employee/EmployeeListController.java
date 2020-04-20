package backend.Employee;

import frontend.EmployeeTable;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListController {
    private List<Employee> employeeList;
    private EmployeeTable staffTableModel;

    public EmployeeListController() {
        this.employeeList = new ArrayList<Employee>();
    }

    public void setListFromFile(String csvPath){ //todo: implement csvReader etc
        //employeeList =
    }

    public void setStaffTableModel(EmployeeTable staffTableModel){
        this.staffTableModel = staffTableModel;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(String surname, String name, Position position, int seniority, double salary){
        employeeList.add(new Employee(surname, name, position, seniority, salary));

        if(staffTableModel != null)
            staffTableModel.fireTableDataChanged();
    }

    public void removeEmployee(int index){
        employeeList.remove(index);

        if(staffTableModel != null)
            staffTableModel.fireTableDataChanged();
    }
}
