package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

// Game bar class is the generic menu bar class across all three major screens
public class GameBar extends MenuBar {

    protected Menu game;
    protected MenuItem runAway;
    protected MenuItem mainMenu;

    public GameBar() {
        final Menu game = new Menu("Game");
        this.getMenus().addAll(game);

        MenuItem exit = new MenuItem("Exit");
        runAway = new MenuItem("Run away");
        mainMenu = new MenuItem("Main menu");

        exit.setOnAction(event ->
        {
            System.exit(0);
        });
        exit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));

        game.getItems().addAll(runAway, mainMenu, exit);
    }

}
