package main.MenuUtil;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import main.MainWindowController;

public class MenuUtilities
{

    public static MenuBar prepareMenu()
    {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem menuFileClose = new MenuItem("Close");
        menuFileClose.setGraphic(new ImageView("main/icon.png"));
        menuFileClose.setOnAction((e) -> {MainWindowController.closeProgram();});

        menuFile.getItems().add(menuFileClose);
        menuBar.getMenus().add(menuFile);

        return menuBar;
    }
}
