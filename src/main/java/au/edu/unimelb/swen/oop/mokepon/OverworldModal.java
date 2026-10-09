package au.edu.unimelb.swen.oop.mokepon;

import javafx.geometry.Pos;
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

public class OverworldModal extends Popup {
    private final Stage ownerStage;
    private final Text modalTitle;
    private final Text sub1;
    private final Text sub2;
    private final Button action1;
    private final Button action2;

    public OverworldModal(Stage ownerStage) {

        VBox root = new VBox(10);
        root.setMinWidth(300);
        root.setMinHeight(150);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lightgray; -fx-padding: 10px; -fx-border-color: black;");

        InputStream grenzeFontStream = null;
        try {
            grenzeFontStream = new FileInputStream(MokeponConstants.mokeponGrenzeFontFilepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenzeFont = Font.loadFont(grenzeFontStream, 20);
        Font buttonFont = new Font("System Regular", 12);

        modalTitle = new Text("Encountered a Cindrake");
        modalTitle.setFont(grenzeFont);
        root.getChildren().add(modalTitle);

        Border buttonBorder = new Border(new BorderStroke(
                Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(32),
                new BorderWidths(2)));

        Region spacer = new Region();
        spacer.setPrefHeight(20);
        root.getChildren().add(spacer);

        sub1 = new Text();
        sub2 = new Text();
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

        root.getChildren().add(sub1);
        root.getChildren().add(sub2);

        HBox actions = new HBox(30);
        actions.setAlignment(Pos.CENTER);
        actions.getChildren().addAll(action1, action2);
        root.getChildren().add(actions);

        this.getContent().add(root);
        this.ownerStage = ownerStage;
    }

    // adding dynamic modal buttons, and showing the modal
    public void activate(String title, String sub1text, String sub2text, String but1Text, String but2Text,
                         Runnable action1ToRun, Runnable action2ToRun) {
        modalTitle.setText(title);
        sub1.setText(sub1text);
        sub2.setText(sub2text);
        action1.setText(but1Text);
        action2.setText(but2Text);
        action1.setOnAction(e -> {
            close();
            action1ToRun.run();
        });
        action2.setOnAction(e -> {
            close();
            action2ToRun.run();
        });

        //dimming the background
        if (ownerStage != null && ownerStage.isShowing()) {

            ColorAdjust dimEffect = new ColorAdjust();
            dimEffect.setBrightness(-0.5);

            if (ownerStage != null && ownerStage.getScene() != null) {
                ownerStage.getScene().getRoot().setEffect(dimEffect);
            }

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
