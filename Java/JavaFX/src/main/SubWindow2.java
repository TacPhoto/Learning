package main;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubWindow2 implements Initializable {

    Stage window2Stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void openSubWindow2() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SubWindow2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        window2Stage = new Stage();
        window2Stage.setTitle("Window 2");
        window2Stage.initModality(Modality.APPLICATION_MODAL);
        window2Stage.setResizable(false);
        window2Stage.setScene(scene);
        window2Stage.show();
    }
}
