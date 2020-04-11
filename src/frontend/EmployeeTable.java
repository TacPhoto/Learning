package frontend;

import backend.Employee.Employee;
import backend.Employee.Position;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmployeeTable extends AbstractTableModel {

    private List<Employee> employeeList;

    private final String[] columnNames = new String[] {
            "name", "surname",
            "position", "seniority",
            "salary"
    };

    private final Class[] columnClass = new Class[] {
            String.class, String.class, Position.class, Integer.class, Double.class
            //name        surname       position        serniority     salary
    };


    public EmployeeTable(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        if(employeeList == null)
            return 0;
        
        return employeeList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Employee row = employeeList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getName();
        }
        else if(1 == columnIndex) {
            return row.getSurname();
        }
        else if(2 == columnIndex) {
            return row.getPosition();
        }
        else if(3 == columnIndex) {
            return row.getSeniority();
        }
        else if(4 == columnIndex) {
            return row.getSalary();
        }
        return null;
    }
}


