package frontend;

import backend.employee.EmployeeListController;
import backend.fileHandling.CsvReader;
import backend.fileHandling.CsvWriter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    final EmployeeListController employeeListController;
    JTable staffTable;
    EmployeeTable staffTableModel;
    final int width = 1200;
    String csvPath;
    String outputPath;
    FileNameExtensionFilter csvFilter;
    public MainWindow() {

        //Frame
        super("Employee Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, 800));

        //initialize variables and objects
        employeeListController = new EmployeeListController();
        this.csvFilter = new FileNameExtensionFilter("CSV file", "CSV", "csv");
        this.csvPath = "";

        //layout
        init_ui();

        //add table to employeeListController, it will let it notify table about changes
        connectTableAndController();

        //show window
        setVisible(true);
    }

    private void loadCsvButton() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(csvFilter);

        int i = fileChooser.showOpenDialog(null);

        //check if user selected any file
        //noinspection AccessStaticViaInstance
        if (i == fileChooser.APPROVE_OPTION) {
            employeeListController.clearList(); //prevents concatenating old and new data

            csvPath = fileChooser.getSelectedFile().getAbsolutePath();

            CsvReader csvReader = new CsvReader(csvPath, employeeListController, staffTableModel);
            csvReader.readDataFromCsv();
        }
    }

    @NotNull
    private String correctCsvPath(String inputPath){
        if(!inputPath.toLowerCase().endsWith(".csv"))
            return inputPath + ".csv";

        return inputPath;
    }

    private void validationFailDialog(){
        JOptionPane.showMessageDialog(
                new JFrame()
                , "User can save list with employees with no position, no salary or no seniority\n" +
                        "but cannot save list with employee that has no name or no surname. Please fix the list."
                , "ERROR"
                , JOptionPane.ERROR_MESSAGE);
    }

    private void saveCsv() throws IOException {
        File file = new File(outputPath);
        if( !( //could use logic laws to get rid of this  negation but it's imo easier to understand this way
                (file.canWrite() || !file.exists()) //if file exists, check if it can be written. if not, ignore that
                        && (new File(file.getParent())).canWrite())
        ){ //check if can write in the directory
            JOptionPane.showMessageDialog(
                    new JFrame()
                    , "You cannot write to this file or location." +
                            "Check if the file is not read only." +
                            "Check if you are permitted to write in this location"
                    , "ERROR"
                    , JOptionPane.ERROR_MESSAGE);

            return;
        }

        if (employeeListController.isListValid()) {
            CsvWriter csvWriter = new CsvWriter(employeeListController, outputPath);
            csvWriter.saveList();
        } else {
            validationFailDialog();
        }
    }

    private void saveCsv(boolean skipValidation) throws IOException {
        File file = new File(outputPath);
        if( !( //could use logic laws to get rid of this  negation but it's imo easier to understand this way
                (file.canWrite() || !file.exists()) //if file exists, check if it can be written. if not, ignore that
                && (new File(file.getParent())).canWrite())
        ){ //check if can write in the directory
            JOptionPane.showMessageDialog(
                    new JFrame()
                    , "You cannot write to this file or location." +
                            "Check if the file is not read only." +
                            "Check if you are permitted to write in this location"
                    , "ERROR"
                    , JOptionPane.ERROR_MESSAGE);
/*
            //DEBUG
            System.out.println("file.canWrite():                         " + file.canWrite());
            System.out.println("!file.exists():                          " + !file.exists());
            System.out.println("(file.canWrite() || !file.exists():      " + (file.canWrite() || !file.exists()));
            System.out.println("(new File(file.getParent())).canWrite(): " + (new File(file.getParent())).canWrite());
*/
            return;
        }

        if(!skipValidation) {
            if (employeeListController.isListValid()) {
                CsvWriter csvWriter = new CsvWriter(employeeListController, outputPath);
                csvWriter.saveList();
            } else {
                validationFailDialog();
            }
        }
        else{
            CsvWriter csvWriter = new CsvWriter(employeeListController, outputPath);
            csvWriter.saveList();
        }
    }

    private void saveButton() throws IOException {
        if (csvPath != null) {
            if(employeeListController.isListValid()){
                if(csvPath.equals("")){
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(csvFilter);

                    int i = fileChooser.showSaveDialog(null);

                    if (i == JFileChooser.APPROVE_OPTION) {
                        outputPath = correctCsvPath(fileChooser.getSelectedFile().getAbsolutePath());
                        saveCsv(true); //cautious with use
                        csvPath = outputPath;
                    }
                    else //failsafe if user does not choose file
                        return;
                }

            outputPath = csvPath;
            saveCsv(true); //cautious with use
            }
            else
            {
                validationFailDialog();
                //noinspection UnnecessaryReturnStatement
                return; //unnecessary but left here in case this code grows bigger in future
            }
        }
    }

    private void saveAsButton() throws IOException {
        if(employeeListController.isListValid()) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(csvFilter);

            int i = fileChooser.showSaveDialog(null);

            //check if user selected any file
            //noinspection AccessStaticViaInstance
            if (i == fileChooser.APPROVE_OPTION) {
                outputPath = correctCsvPath(fileChooser.getSelectedFile().getAbsolutePath());
                saveCsv(true); //cautious with use
                csvPath = outputPath;
            }
        }
        else {
            validationFailDialog();
        }
    }

    private void newButton(){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null
                , "Do you really want to create a new table? Remember to save your table before that."
                , "Warning"
                , dialogButton);

        if (dialogResult == JOptionPane.YES_OPTION) {
            csvPath = "";
            employeeListController.clearList();
        }
    }

    private void aboutButton(){
        JOptionPane.showMessageDialog(
                new JFrame()
                , "Karol Kowalczyk 2020\nS20240\n\n" +
                        "Use search panel to filter text values in the table\n" +
                        "To filter table members by salary use  >  and  <  characters before comparing number"
                , "About"
                , JOptionPane.INFORMATION_MESSAGE);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //create and add main menu items
        JMenu fileMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        //create and add FILE menu items
        JMenuItem menuItemNew = new JMenuItem();
        JMenuItem menuItemOpen = new JMenuItem();
        JMenuItem menuItemSave = new JMenuItem();
        JMenuItem menuItemSaveAs = new JMenuItem();

        //add actions and text to menus items
        menuItemOpen.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadCsvButton();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuItemOpen.setText("Open");

        menuItemSave.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveButton();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuItemSave.setText("Save");

        menuItemSaveAs.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveAsButton();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuItemSaveAs.setText("Save As");

        menuItemNew.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    newButton();
            }
        });
        menuItemNew.setText("New");

        //add menu items to FILE menu
        fileMenu.add(menuItemNew);
        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSave);
        fileMenu.add(menuItemSaveAs);

        //create and add About menu items
        JMenuItem menuItemAbout = new JMenuItem();

        //set actions and text for About menu items
        menuItemAbout.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    aboutButton();
            }
        });
        menuItemAbout.setText("About");

        //set shortcuts
        menuItemNew.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        menuItemOpen.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        menuItemSave.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        menuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK+InputEvent.SHIFT_DOWN_MASK));

        //add items to About menu
        aboutMenu.add(menuItemAbout);

        //add menuBar to mainWindow
        this.setJMenuBar(menuBar);

    }

    private void initToolsPane() {
        JPanel toolsPanel = new JPanel(); //panel that contains all tools and operations

        //create tools buttons
        ToolButton addToolButton = new ToolButton("Add", employeeListController);

        //add tools buttons to toolsPanel
        toolsPanel.add(addToolButton);

        //add toolsPanel to the layout
        getContentPane().add(BorderLayout.NORTH, toolsPanel);

    }

    private void init_search(TableRowSorter sorter) {
        {
            //create search panel
            JPanel searchPanel = new JPanel(new BorderLayout());

            //create and add elements of search panel
            JLabel label = new JLabel("Filter: ");
            searchPanel.add(BorderLayout.WEST, label);

            final JTextField filterText = new JTextField("");
            searchPanel.add(BorderLayout.CENTER, filterText);

            //add search panel to the layout
            getContentPane().add(BorderLayout.SOUTH, searchPanel);

            //create table sorter
            TableSorter textSorter = new TableSorter(sorter, filterText);

            //add sorter to the table
            filterText.getDocument().addDocumentListener(textSorter);
        }
    }

    private void initStaffTablePanel() {
        //staff table setup
        staffTableModel = new EmployeeTable(employeeListController.getEmployeeList()); //table with staff data, editable
        staffTable = new JTable(staffTableModel);

        //table sorter setup
        TableRowSorter<EmployeeTable> sorter = new TableRowSorter<EmployeeTable>(staffTableModel);
        staffTable.setRowSorter(sorter);
        sorter.setSortable(5, false); //5col - button column is not sortable

        //create scroll pane
        JScrollPane tablePane = new JScrollPane(staffTable);

        //combobox setup
        JComboBox<backend.employee.Position> comboBox = new JComboBox<>(backend.employee.Position.values());
        comboBox.setEnabled(true);
        DefaultCellEditor editor = new DefaultCellEditor(comboBox);
        staffTable.getColumnModel().getColumn(2).setCellEditor(editor);

        //button setup
        TableCellRenderer buttonRenderer = new EmployeeTable.JTableButtonRenderer();
        staffTable.getColumnModel().getColumn(5).setCellRenderer(buttonRenderer);
        staffTable.addMouseListener(new EmployeeTable.JTableButtonMouseListener(staffTable));

        //add staff table to the layout
        getContentPane().add(BorderLayout.CENTER, tablePane);

        init_search(sorter);
    }

    private void init_ui() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //initialize menu bar
        initMenuBar();
        //initialize tools panel
        initToolsPane();
        //initialize staff table panel
        initStaffTablePanel();

    }

    private void connectTableAndController() {
        employeeListController.setStaffTableModel(staffTableModel);
        staffTableModel.setEmployeeListController(employeeListController);
    }
}
