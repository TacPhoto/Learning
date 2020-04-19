package backend.fileHandling;

import backend.Employee.EmployeeListController;
import backend.Employee.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private EmployeeListController employeeListController;
    private BufferedReader reader;
    private List<String> lineList = new ArrayList<String>();
    String delimiter;

    public CsvReader(String csvPath, EmployeeListController employeeListController) throws IOException{
        this.reader = Files.newBufferedReader(Paths.get(csvPath));
        this.employeeListController = employeeListController;
        delimiter = ";";
    }

    private void csvToStringList() throws IOException {
        try {
            String line;
            while((line = reader.readLine()) != null)
                lineList.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidLineSplit(String[] lineSplit){
        for(int i = 0; i < lineSplit.length; i++) //overall empty values check
            return lineSplit[i] != null;

        if(!lineSplit[0].chars().allMatch(Character::isLetter)) //surname check
            return false;

        if(!lineSplit[1].chars().allMatch(Character::isLetter)) //name check
            return false;

        boolean isValidEnum = false;
        for(Position pos : Position.values()){
            if(pos.name().equals(lineSplit[2])) {
                isValidEnum = true;
                break;
            }
        }

        if(!lineSplit[3].chars().allMatch(Character::isDigit)) //seniority check
            return false;

        if(!lineSplit[3].chars().allMatch(Character::isDigit) &&
        !lineSplit[3].matches("[-+]?[0-9]*\\.?[0-9]+")) //salarycheck
            return false;

        return isValidEnum;
    }

    private void addEmployee(String[] lineSplit){
            employeeListController.addEmployee(
                    lineSplit[0]                         //surname
                    , lineSplit[1]                       //name
                    , Position.valueOf(lineSplit[2])     //position
                    , Integer.parseInt(lineSplit[3])     //seniority
                    , Double.parseDouble(lineSplit[4])   //salary
            );

    }

    private void populateTable(){

        for(String line : lineList) {
            String[] lineSplit = line.split(delimiter);

            if (isValidLineSplit(lineSplit))
                addEmployee(lineSplit);
            else{
                System.out.println("ERROR: DATA FROM FILE IS INVALID");
                break;
            }
        }

    }

}
