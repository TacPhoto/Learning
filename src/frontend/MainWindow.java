package frontend;

import backend.Employee.Employee;

import javax.swing.*;
import java.awt.*;
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

        List<Employee> employeeList = null;//todo: implement creating new list and reading ne from file
        EmployeeTable staffTableModel = new EmployeeTable(employeeList); //table with staff data, editable
        JTable staffTable = new JTable(staffTableModel);
        JScrollPane tablePane = new JScrollPane(staffTable);
        getContentPane().add(BorderLayout.SOUTH, tablePane);

    }

}
