package au.edu.unimelb.swen.oop.mokepon;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * ActionButton class, extends the javaFX Button class. Sets style and hover actions while providing
 * disable and enable functions to prevent button activation.
 */


public class ActionButton extends Button {
    private boolean enabled;

    /**
     * Instantiates a new ActionButton.
     * Automatically sets button style and size, and implements opacity changes on mouse hover.
     */
    public ActionButton(String text) {
        super(text);
        this.enabled = false;

        InputStream grenzeStream = null;
        try {
            grenzeStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenze = Font.loadFont(grenzeStream, 15);

        Border buttonBorder = new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID,
                new CornerRadii(20), new BorderWidths(2)));

        this.setFont(grenze);
        this.setOpacity(0.20);
        this.setStyle("-fx-background-color: #d3d3d3;");
        this.setStyle("-fx-background-radius: 20;");
        this.setBorder(buttonBorder);
        this.setPrefWidth(160);
        this.setPrefHeight(40);
        this.setAlignment(Pos.CENTER);

        if (enabled) {
            this.setOnMousePressed(event -> this.setOpacity(0.75));
            this.setOnMouseReleased(event -> this.setOpacity(0.5));
        }
    }


    public void Enable() {
        this.setOpacity(0.5);
        this.setDisabled(false);
        this.enabled = true;
    }

    public void Disable() {
        this.setOpacity(0.2);
        this.setDisabled(true);
        this.enabled = false;
    }

}
