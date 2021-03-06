package backend.fileHandling;

import backend.employee.EmployeeListController;
import backend.employee.Position;
import frontend.EmployeeTableModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * CsvReader reads EmployeeTable from CSV file
 * It has validation.
 * If it encounters invalid data, it will fallback table to empty state
 * and display an error prompt.
 */
public class CsvReader {
    private EmployeeListController employeeListController;
    private BufferedReader reader;
    private final List<String> lineList = new ArrayList<String>();
    private EmployeeTableModel staffTable;
    private String delimiter; //we use String instead of char we would have to convert it to String to use split()


    public CsvReader(String csvPath, EmployeeListController employeeListController, EmployeeTableModel staffTable) throws IOException {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvPath), StandardCharsets.UTF_8));
        this.employeeListController = employeeListController;
        this.staffTable = staffTable;
        delimiter = ";";
    }

    private void csvToStringList() {
        try {
            String line;
            while ((line = reader.readLine()) != null)
                lineList.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidLineSplit(String[] lineSplit) {
        /**
        * isValidLineSplit validates a single line of data taken from source csv file
         * it checks if it can be used to initialize single Employee object.
         * it returns false if any error is encountered..
         */
        if (lineSplit == null) //should not happen
            return false;

        if (lineSplit.length < 5)
            return false;

        for (int i = 0; i < 5; i++) //overall empty values check
            if (lineSplit[i] == null)
                return false;

        if (!lineSplit[0].chars().allMatch(Character::isLetter)) //surname check
            return false;

        if (!lineSplit[1].chars().allMatch(Character::isLetter)) //name check
            return false;

        boolean isValidEnum;

        try {
            Position.valueOf(lineSplit[2]);
            isValidEnum = true;
        } catch (Exception e) {
            System.out.println("Enum value validation error");
            return false;
        }

        if (!lineSplit[3].chars().allMatch(Character::isDigit)) //seniority check
            return false;

        if (!lineSplit[3].chars().allMatch(Character::isDigit) &&
                !lineSplit[3].matches("[-+]?[0-9]*\\.?[0-9]+")) //salary check
            return false;

        //noinspection ConstantConditions
        return isValidEnum;
    }

    private void addEmployee(@NotNull String[] lineSplit) {
        /**
         * addEmployee initializes and adds to the EmployeeTable
         * a single Employee object based on source Csv file
         */
        employeeListController.addEmployee(
                lineSplit[0]                         //surname
                , lineSplit[1]                       //name
                , Position.valueOf(lineSplit[2])     //position
                , Integer.parseInt(lineSplit[3])     //seniority
                , Double.parseDouble(lineSplit[4])   //salary
        );

    }

    private void populateTable() {
        /**
         * populateTable populates whole EmployeeTable with data from Csv file
         * if any record is invalid, it will fallback to empty state
         */
        staffTable.setEditable(false);

        for (String line : lineList) {
            String[] lineSplit = line.split(delimiter);

            if (isValidLineSplit(lineSplit))
                addEmployee(lineSplit);
            else {
                System.out.println("ERROR: DATA FROM FILE IS INVALID");
                employeeListController.clearList();

                JOptionPane.showMessageDialog(
                        new JFrame()
                        , "File validation error"
                        , "ERROR"
                        , JOptionPane.ERROR_MESSAGE);
                break;
            }
        }

        staffTable.setEditable(true);
    }


    public void readDataFromCsv() {
        csvToStringList();
        populateTable();
    }

}
