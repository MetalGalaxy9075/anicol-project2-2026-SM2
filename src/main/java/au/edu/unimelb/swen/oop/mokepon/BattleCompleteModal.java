package au.edu.unimelb.swen.oop.mokepon;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BattleCompleteModal extends Popup {

    private final Stage ownerStage;
    private final Text modalTitle;
    private final Button action1;
    private final Button action2;
    private final HBox actions;
    private final Scene rootScene;

    public BattleCompleteModal(Stage ownerStage, Scene rootScene) {

        VBox root = new VBox(10);
        root.setMinWidth(100);
        root.setMinHeight(150);
        root.setAlignment(Pos.CENTER);

        InputStream grenzeFontStream = null;
        try {
            grenzeFontStream = new FileInputStream(MokeponConstants.mokeponGrenzeFontFilepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenzeFont = Font.loadFont(grenzeFontStream, 12);
        Font buttonFont = new Font("System Regular", 12);

        Border buttonBorder = new Border(new BorderStroke(
                Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(32),
                new BorderWidths(2)));

        modalTitle = new Text("Encountered a Cindrake");
        modalTitle.setFont(grenzeFont);
        root.getChildren().add(modalTitle);

        Region spacer = new Region();
        spacer.setPrefHeight(20);
        root.getChildren().add(spacer);

        action1 = new Button();
        action1.setFont(buttonFont);
        action2 = new Button();
        action2.setFont(buttonFont);
        action1.setStyle("-fx-background-color: #d3d3d3;");
        action1.setStyle("-fx-background-radius: 20px;");
        action1.setFocusTraversable(false);
        action1.setBorder(buttonBorder);
        action2.setStyle("-fx-background-color: #d3d3d3;");
        action2.setStyle("-fx-background-radius: 20px;");
        action2.setBorder(buttonBorder);
        action2.setFocusTraversable(false);

        actions = new HBox(30);
        actions.setAlignment(Pos.CENTER);
        root.getChildren().add(actions);

        this.getContent().add(root);
        root.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-border-color: black;");
        this.ownerStage = ownerStage;
        this.rootScene = rootScene;
    }

    public void activate(MokeponConstants.WinState win, Runnable winRun, Runnable exitRun,
                         Runnable mainscreenRun, BattleScreen bScreen) {

        actions.getChildren().clear();

        //win case, set modal as specified in project breif
        if (win == MokeponConstants.WinState.WIN) {
            modalTitle.setText("You Win");
            action1.setText("Back to it!");
            action2.setText("");

            action1.setOnAction(e -> {
                close();
                winRun.run();
            });

            action2.setOnAction(e -> {
                close();
            });

            actions.getChildren().add(action1);

        } else { //lose case, set modal as specified in project breif
            modalTitle.setText("You Lose");
            action1.setText("Return to Main Menu");
            action2.setText("Quit");

            action1.setOnAction(e -> {
                close();
                mainscreenRun.run();
            });

            action2.setOnAction(e -> {
                close();
                exitRun.run();
            });

            actions.getChildren().addAll(action1, action2);
        }

        //Dimming the background using ColorAdjust
        if (ownerStage != null && ownerStage.isShowing()) {

            ColorAdjust dimEffect = new ColorAdjust();
            dimEffect.setBrightness(-0.5);
            bScreen.setEffect(dimEffect);


            this.show(ownerStage, ownerStage.getX() + (ownerStage.getWidth() - this.getWidth()) / 2,
                    ownerStage.getY() + (ownerStage.getHeight() - this.getHeight()) / 2);

        } else {
            System.err.println("Error: Cannot activate popup because the main Stage is not showing yet!");
        }

        this.show(ownerStage);
    }

    public void close() {

        ColorAdjust dimEffect = new ColorAdjust();
        dimEffect.setBrightness(0);

        if (ownerStage != null && ownerStage.getScene() != null) {
            ownerStage.getScene().getRoot().setEffect(dimEffect);
        }

        this.hide();
    }
}
