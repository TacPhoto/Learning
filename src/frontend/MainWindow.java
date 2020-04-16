package frontend;

import backend.Employee.Employee;
import backend.Employee.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class MainWindow extends JFrame{
    public MainWindow(){

        //Frame
        super( "Employee Manager" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 800)); //todo: tweak min size according to tool pane size

        //layout
        init_ui();

        //show window
        setVisible(true);
    }

    private void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        //create and add main menu items
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        //create and add FILE menu items
        JMenuItem menuItemOpen = new JMenuItem("Open");
        JMenuItem menuItemSaveAs = new JMenuItem("Save as");

        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSaveAs);

        //create and add HELP menu items
        JMenuItem menuItemAbout = new JMenuItem("About");

        helpMenu.add(menuItemAbout);

        //add actions to menus items

        //add menuBar to mainWindow
        getContentPane().add(BorderLayout.NORTH, menuBar);
    }

    private void init_ui(){

        //initialize menu bar
        initMenuBar();

        //add main panels to the main window
        JPanel toolsPanel = new JPanel(); //panel that contains all tools and operations
        getContentPane().add(BorderLayout.CENTER, toolsPanel);

        List<Employee> employeeList = new ArrayList<Employee>();//todo: implement creating new list and reading ne from file

        //TEST
        Employee test1 = new Employee("first", "second", Position.IT, 5, 2000);
        employeeList.add(test1);

        //create staff table and add it to the layout
        EmployeeTable staffTableModel = new EmployeeTable(employeeList); //table with staff data, editable
        JTable staffTable = new JTable(staffTableModel);
        JScrollPane tablePane = new JScrollPane(staffTable);

        //add combobox to the table
        JComboBox comboBox = new JComboBox(backend.Employee.Position.values());
        comboBox.setEnabled(true);
        DefaultCellEditor editor = new DefaultCellEditor(comboBox);
        staffTable.getColumnModel().getColumn(2).setCellEditor(editor);

        getContentPane().add(BorderLayout.SOUTH, tablePane);

        //TEST
        Employee test2 = new Employee("addedLater", "second", Position.IT, 5, 2000);
        employeeList.add(test2);
        //TEST
        Employee test3 = new Employee("addedLater", "secofend", Position.IT, 5, 2000);
        employeeList.add(test3);
        //TEST
        Employee test4 = new Employee("removed", "second", Position.IT, 5, 2000);
        employeeList.add(test4);
        employeeList.remove(test4);
    }
}
