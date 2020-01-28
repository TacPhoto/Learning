package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    Stage stage;
    public Stage getStage() {return stage; }
    void setStage(Stage stage){this.stage=stage;}

    @FXML
    Label labelText;
    @FXML
    Button button1;
    @FXML
   TextField textField1;
    @FXML
    RadioButton radioOne, radioTwo;

    static ToggleGroup tg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelText.setText("Program is on");
        button1.setGraphic(new ImageView("main/icon.png"));

        List<RadioButton> rbList = new ArrayList<>();
        rbList.add(radioOne);
        rbList.add(radioTwo);
        MainWindowController.prepareRadioButtons(rbList);
    }

    public void buttonClicked(){
        String txtFromTextField = textField1.getText();
        labelText.setText(txtFromTextField);
        button1.setText(txtFromTextField);
        textField1.setText("");
    }

    private static void prepareRadioButtons(List<RadioButton> rbList)
    {
        tg = new ToggleGroup();
        for(RadioButton rb : rbList)
        {
            rb.setToggleGroup(tg);
        }
    }

    @FXML
    private void radioClicked()
    {
        RadioButton selectedRB = (RadioButton) tg.getSelectedToggle();
        String clickedId = selectedRB.getId();
        String clickedText = selectedRB.getText();

        textField1.setText("Id: " + clickedId + " Name: " + clickedText);
    }

    public static void closeProgram(){
        Platform.exit();
        System.exit(0);
    }
}