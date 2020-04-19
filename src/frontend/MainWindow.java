package frontend;

import backend.Employee.EmployeeListController;
import backend.Employee.Position;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

public class MainWindow extends JFrame{
    EmployeeListController employeeListController;
    JTable staffTable;
    EmployeeTable staffTableModel;
    int width = 1200;

    public MainWindow(){

        //Frame
        super( "Employee Manager" );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, 800));

        employeeListController = new EmployeeListController();

        //layout
        init_ui();

        //add table to employeeListController, it will let it notify table about changes
        employeeListController.setStaffTableModel(staffTableModel);

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
        JMenuItem menuItemNew = new JMenuItem("New");
        JMenuItem menuItemOpen = new JMenuItem("Open");
        JMenuItem menuItemSaveAs = new JMenuItem("Save as");

        fileMenu.add(menuItemNew);
        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSaveAs);

        //create and add HELP menu items
        JMenuItem menuItemAbout = new JMenuItem("About");

        helpMenu.add(menuItemAbout);

        //add actions to menus items

        //add menuBar to mainWindow
        getContentPane().add(BorderLayout.NORTH, menuBar);
    }

    private void initToolsPane(){
        JPanel toolsPanel = new JPanel(); //panel that contains all tools and operations

        //create tools buttons
        ToolButton saveToolButton = new ToolButton("Save", employeeListController);
        ToolButton addToolButton = new ToolButton("Add", employeeListController);

        //add tools buttons to toolsPanel
        toolsPanel.add(addToolButton);
        toolsPanel.add(saveToolButton);

        //add toolsPanel to the layout
        getContentPane().add(BorderLayout.NORTH, toolsPanel);

    }

    private void init_search(TableRowSorter sorter){{
        //create search panel
        JPanel searchPanel = new JPanel(new BorderLayout());

        //create and add elements of search panel
        JLabel label = new JLabel("Filter: ");
        searchPanel.add(BorderLayout.WEST ,label);
        final JTextField filterText = new JTextField("");
        searchPanel.add(BorderLayout.CENTER, filterText);

        //add search panel to the layout
        getContentPane().add(BorderLayout.SOUTH, searchPanel);

        //implement searching
        filterText.getDocument().addDocumentListener(new DocumentListener() {
            private String getText(){
                return filterText.getText();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                if (getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + getText()));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (getText().trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + getText()));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });}
    }

    private void initStaffTablePanel(){
        //create staff table
        staffTableModel = new EmployeeTable(employeeListController.getEmployeeList()); //table with staff data, editable
        staffTable = new JTable(staffTableModel);

        //create and add table sorter
        TableRowSorter<EmployeeTable> sorter = new TableRowSorter<EmployeeTable>(staffTableModel);
        staffTable.setRowSorter(sorter);
        sorter.setSortable(5, false); //button column is not sortable

        //create scroll pane
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

        //add staff table to the layout
        getContentPane().add(BorderLayout.CENTER, tablePane);

        init_search(sorter);
    }

    private void init_ui(){

        //initialize menu bar
        initMenuBar();
        //initialize tools panel
        initToolsPane();
        //initialize staff table panel
        initStaffTablePanel();


        //TEST
        employeeListController.addEmployee("addedLater", "second", Position.IT, 5, 2000);

        //TEST
        employeeListController.addEmployee("addedLater", "secofend", Position.IT, 5, 2000);

        //TEST
        employeeListController.addEmployee("removed", "second", Position.IT, 5, 2000);
    }
}
