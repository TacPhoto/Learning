package backend.employee;

import frontend.EmployeeTable;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListController {
    private final List<Employee> employeeList;
    private EmployeeTable staffTableModel;

    public EmployeeListController() {
        this.employeeList = new ArrayList<Employee>();
    }

    private void updateList() {
        if (staffTableModel != null)
            staffTableModel.fireTableDataChanged();
    }

    public void setStaffTableModel(EmployeeTable staffTableModel) {
        this.staffTableModel = staffTableModel;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void addEmployee(String surname, String name, Position position, int seniority, double salary) {
        employeeList.add(new Employee(surname, name, position, seniority, salary));
        updateList();
    }

    public void removeEmployee(int index) {
        employeeList.remove(index);
        updateList();
    }

    public void clearList() {
        employeeList.clear();
        updateList();
    }

    private boolean isEmployeeValid(Employee employee) {
        /*
          employee properties are secured by EmployeeTable inputs however
          name and surname can be left as empty. If so, employee record is
          considered as invalid. It should be used to prevent saving
          an incomplete list
         */
        return employee.getSurname() != null && !employee.getSurname().equals("") &&
                employee.getName() != null && !employee.getName().equals("");
    }

    public boolean isListValid() {
        if(employeeList.size() == 0)
            return false;

        if(employeeList.get(0) == null)
            return false;

        for (Employee employee : employeeList) {
            if (!isEmployeeValid(employee))
                return false;
        }

        return true;
    }
}
