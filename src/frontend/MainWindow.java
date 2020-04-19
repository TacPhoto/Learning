package frontend;

import backend.Employee.Employee;
import backend.Employee.EmployeeListController;
import backend.Employee.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.TableCellRenderer;

public class MainWindow extends JFrame{
    EmployeeListController employeeListController;

    public MainWindow(){

        //Frame
        super( "Employee Manager" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 800)); //todo: tweak min size according to tool pane size

        employeeListController = new EmployeeListController();

        //layout
        init_ui();

        //show window
        setVisible(true);

        //Test
        employeeListController.addEmployee("last", "second", Position.IT, 5, 2000);

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
        getContentPane().add(BorderLayout.NORTH, toolsPanel);

        //initialize Employee List Controller

        //create staff table and add it to the layout
        EmployeeTable staffTableModel = new EmployeeTable(employeeListController.getEmployeeList()); //table with staff data, editable
        JTable staffTable = new JTable(staffTableModel);
        JScrollPane tablePane = new JScrollPane(staffTable);


        //add combobox to the table
        JComboBox comboBox = new JComboBox(backend.Employee.Position.values());
        comboBox.setEnabled(true);
        DefaultCellEditor editor = new DefaultCellEditor(comboBox);
        staffTable.getColumnModel().getColumn(2).setCellEditor(editor);

        //add buttons to the table
        TableCellRenderer buttonRenderer = new EmployeeTable.JTableButtonRenderer();
        staffTable.getColumnModel().getColumn(5).setCellRenderer(buttonRenderer);
        //staffTable.getColumn("Delete").setCellRenderer(buttonRenderer); //this forces referring to the column by name. we use column name for button name return different one for the colum
        staffTable.addMouseListener(new EmployeeTable.JTableButtonMouseListener(staffTable));

        getContentPane().add(BorderLayout.SOUTH, tablePane);

        //TEST
        employeeListController.addEmployee("addedLater", "second", Position.IT, 5, 2000);

        //TEST
        employeeListController.addEmployee("addedLater", "secofend", Position.IT, 5, 2000);

        //TEST
        employeeListController.addEmployee("removed", "second", Position.IT, 5, 2000);
    }
}
