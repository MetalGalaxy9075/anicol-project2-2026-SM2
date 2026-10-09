package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class OverworldBar extends GameBar {
    public OverworldBar(Runnable mainMenu_e) {
        super();

        runAway.setDisable(true);
        mainMenu.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));

        mainMenu.setOnAction(e -> {
            mainMenu_e.run();
            ScreenManager.resartFromMainMenu();
        });

    }
}
