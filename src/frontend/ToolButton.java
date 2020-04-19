package frontend;

import backend.Employee.EmployeeListController;
import backend.Employee.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JButton implements ActionListener {
    private String text;
    private EmployeeListController employeeListController;

    public ToolButton(String text){
        super(text);
        this.text = text;
        this.addActionListener(new ToolListener() {
        });
    }

    public ToolButton(String text, EmployeeListController employeeListController){
        super(text);
        this.text = text;
        this.employeeListController = employeeListController;
        this.addActionListener(new ToolListener() {
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    class ToolListener implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            switch (text){
                case "Add":
                    System.out.println("Add clicked");
                    employeeListController.addEmployee("","", Position.none, 0, 0);
                    break;
                case "Save":
                    System.out.println("Save");
                    break;
                default:
                    System.out.println("DEBUG: button has no action specify. It is bind by button text");
            }
        }
    }
}