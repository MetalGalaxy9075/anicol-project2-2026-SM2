package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class BattleScreenBar extends GameBar {

    public BattleScreenBar(Runnable runAway_e, Runnable mainMenu_e) {
        super();

        runAway.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        mainMenu.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));

        runAway.setOnAction(e -> {
            runAway_e.run();
            ScreenManager.switchToOverworld();
        });
        mainMenu.setOnAction(e -> {
            mainMenu_e.run();
            ScreenManager.resartFromMainMenu();
        });

    }
}
