package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelText.setText("Program is on");
        button1.setGraphic((new ImageView("main/icon.png")));
    }

    public void buttonClicked(){
        String txtFromTextField = textField1.getText();
        labelText.setText(txtFromTextField);
        button1.setText(txtFromTextField);
        textField1.setText("");
    }

    public static void closeProgram(){
        Platform.exit();
        System.exit(0);
    }
}