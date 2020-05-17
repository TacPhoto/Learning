package frontend;

import backend.employee.Employee;
import backend.employee.EmployeeListController;
import backend.employee.Position;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * EmployeeTableModel is an AbstractTableModel designed to work with JTable
 * It can work with Employee class data
 * Should be controlled by EmployeeListController
 */
public class EmployeeTableModel extends AbstractTableModel {

    boolean isEditable;

    private EmployeeListController employeeListController;
    private final List<Employee> employeeList; //private, yet it will use reference and be the same object
    //as employeeList in main function :)

    private final String[] columnNames = new String[]{
            "name", "surname",
            "position", "seniority",
            "salary", "Delete"
    };

    private final Class[] columnClass = new Class[]{
            String.class, String.class, Position.class, Integer.class, Double.class, JButton.class
            //name        surname       position        seniority     salary        delete
    };


    public EmployeeTableModel(List<Employee> employeeList) {
        this.employeeList = employeeList;
        isEditable = true;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 5) //we return empty name for Deletion buttons column
            return "";
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        if (employeeList == null)
            return 0;

        return employeeList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee row = employeeList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getName();
        } else if (1 == columnIndex) {
            return row.getSurname();
        } else if (2 == columnIndex) {
            return row.getPosition();
        } else if (3 == columnIndex) {
            return row.getSeniority();
        } else if (4 == columnIndex) {
            return row.getSalary();
        } else if (5 == columnIndex) { //Delete button clicked
            final JButton button = new JButton(columnNames[columnIndex]);
            button.addActionListener(arg0 -> employeeListController.removeEmployee(rowIndex));
            return button;
        }

        return null;
    }

    public void setEditable(boolean mode) {
        isEditable = mode;
    }

    public void setEmployeeListController(EmployeeListController employeeListController) {
        this.employeeListController = employeeListController;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) //we force table to be always editable
    {
        if (columnIndex == 5) //disables editability of Delete button column (enabling it will not enable changing it's value but looks bad
            return false;

        return isEditable;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Employee row = employeeList.get(rowIndex);
        if (0 == columnIndex) {
            row.setName(((String) aValue).replaceAll("[^A-Za-z]+", ""));
        } else if (1 == columnIndex) {
            row.setSurname(((String) aValue).replaceAll("[^A-Za-z]+", ""));
        } else if (2 == columnIndex) {
            row.setPosition((Position) aValue); //input secured by enum combobox
        } else if (3 == columnIndex) {
            row.setSeniority(abs((Integer) aValue));
        } else if (4 == columnIndex) {
            row.setSalary(abs((Double) aValue));
        }

    }

    public static class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            //get column and row
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / table.getRowHeight();

            //validate column and row
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    ((JButton) value).doClick(); //click event
                }
            }
        }
    }

    public static class JTableButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JButton) value;
        }
    }

    private double abs(double a) {
        //noinspection ManualMinMaxCalculation
        return (a > -a) ? a : -a;
    }

    private int abs(int a) {
        //noinspection ManualMinMaxCalculation
        return (a > -a) ? a : -a;
    }
}


