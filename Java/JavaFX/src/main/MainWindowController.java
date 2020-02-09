package main;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    Stage stage;

    public Stage getStage() {
        return stage;
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    Label labelText;
    @FXML
    Button button1;
    @FXML
    TextField textField1;
    @FXML
    RadioButton radioOne, radioTwo;
    @FXML
    CheckBox checkBox;

    static Label lbl = new Label("Test");

    static ComboBox combo;
    static ComboBox combo2;

    static ToggleGroup tg;

    static String[] weekDays = {"Mon", " Tue", "Wed", "Thu", "Fri", "Sun", "Sat"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelText.setText("Program is on");
        button1.setGraphic(new ImageView("main/icon.png"));

        List<RadioButton> rbList = new ArrayList<>();
        rbList.add(radioOne);
        rbList.add(radioTwo);
        MainWindowController.prepareRadioButtons(rbList);

        checkBox.setSelected(true);
        checkBoxClicked();
    }

    public void buttonClicked() {
        String txtFromTextField = textField1.getText();
        labelText.setText(txtFromTextField);
        button1.setText(txtFromTextField);
        textField1.setText("");
    }

    private static void prepareRadioButtons(List<RadioButton> rbList) {
        tg = new ToggleGroup();
        for (RadioButton rb : rbList) {
            rb.setToggleGroup(tg);
        }
    }

    @FXML
    private void radioClicked() {
        RadioButton selectedRB = (RadioButton) tg.getSelectedToggle();
        String clickedId = selectedRB.getId();
        String clickedText = selectedRB.getText();

        textField1.setText("Id: " + clickedId + " Name: " + clickedText);
    }

    public void checkBoxClicked() {
        boolean checked = checkBox.isSelected();
        if (checked)
            textField1.appendText("CheckBox Selected!");
        else
            textField1.appendText("Checkbox Deselected!");
    }

    public static void closeProgram() {
        Platform.exit();
        System.exit(0);
    }

    public static void addControls(StackPane stackPane) {
        StackPane.setMargin(lbl, new Insets(260, 10, 10, 10));
        stackPane.getChildren().add(lbl);

        ObservableList<Cars> carsList = FXCollections.observableArrayList(
                new Cars("126p", "Fiat"),
                new Cars("F1", "McLaren"),
                new Cars("07", "Borewicz"),
                new Cars("Bleh", "Multipla")
        );
        ComboBox combo = new ComboBox(carsList);
        combo.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Cars>() {
                    @Override
                    public void changed(ObservableValue<? extends Cars> observableValue, Cars oldValue, Cars newValue) {
                        if (newValue != null)
                            lbl.setText(newValue.getName());
                    }

                }
        );
        StackPane.setMargin(combo, new Insets(280, 10, 10, 10));
        stackPane.getChildren().add(combo);

        ComboBox combo2 = new ComboBox(FXCollections.observableArrayList(weekDays));
        StackPane.setMargin(combo2, new Insets(310, 10, 10, 10));
        stackPane.getChildren().add(combo2);
    }
}