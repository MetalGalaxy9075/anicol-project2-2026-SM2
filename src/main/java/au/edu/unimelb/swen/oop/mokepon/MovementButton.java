package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static au.edu.unimelb.swen.oop.mokepon.MokeponConstants.movementButtonWidth;

public class MovementButton extends Button {

    public MovementButton(Runnable onAction, String imageURL, KeyCode keyCode) {
        super();

        InputStream buttonImageStream = null;
        try {
            buttonImageStream = new FileInputStream(imageURL);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image buttonImage = new Image(buttonImageStream);
        ImageView buttonImageView = new ImageView(buttonImage);
        this.setGraphic(buttonImageView);

        this.setOnAction(event -> {
            onAction.run();
        });
        this.setOnKeyPressed(event -> {
            if (event.getCode() == keyCode && !this.isDisable()) {
                onAction.run();
            }
        });

        this.setPrefWidth(movementButtonWidth);
        this.setPrefHeight(movementButtonWidth);
        this.setStyle("-fx-background-color: #d3d3d3;");
        this.setShape(new Circle(movementButtonWidth));
        this.setStyle("-fx-background-color: #000000, grey;" + "-fx-background-insets: 0, 2;");

    }

    public void enable() {
        this.setOpacity(0.9);
        this.setDisable(false);
    }

    public void disable() {
        this.setOpacity(0.5);
        this.setDisable(true);
    }
}
