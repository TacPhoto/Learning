package frontend;

import backend.employee.EmployeeListController;
import backend.employee.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ToolButton class is meant to serve for creation of buttons which can be used in MainWindow.
 * They should work as tools meant for operating on EmployeeTable etc but should not have very
 * extensive usage.
 * Button action is specified by button text in ToolListener interface.
 */
public class ToolButton extends JButton implements ActionListener {
    private final String text;
    private final EmployeeListController employeeListController;

    public ToolButton(String text, EmployeeListController employeeListController) {
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
        @Override
        public void actionPerformed(ActionEvent e) {
            //noinspection SwitchStatementWithTooFewBranches
            switch (text) {
                case "Add":
                    employeeListController.addEmployee("", "", Position.none, 0, 0);
                    break;
                default:
                    System.out.println("DEBUG: button has no action specified. It is binded by button text"); //only for debug purposes
            }
        }
    }
}