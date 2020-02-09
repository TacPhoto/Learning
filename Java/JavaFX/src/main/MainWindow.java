package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.MenuUtil.MenuUtilities;

public class MainWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("mainWindow.fxml"));

        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane);

        MainWindowController controller = (MainWindowController) loader.getController();
        controller.setStage(primaryStage);

        stackPane.getChildren().add(MenuUtilities.prepareMenu());
        stackPane.setAlignment(Pos.TOP_CENTER);

        MainWindowController.addControls(stackPane);
        primaryStage.getIcons().add(new Image("main/icon.png"));
        primaryStage.setOnCloseRequest((e) -> {MainWindowController.closeProgram();});
        primaryStage.setHeight(600.0);
        primaryStage.setWidth(900.0);
        primaryStage.setMinHeight(400.0);
        primaryStage.setMinHeight(300.0);
        primaryStage.setTitle("First window");
        primaryStage.setScene(scene);

        primaryStage.setResizable(true);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
