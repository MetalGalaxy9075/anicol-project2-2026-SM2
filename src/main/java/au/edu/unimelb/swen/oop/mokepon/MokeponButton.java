package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MokeponButton extends Button {

    public MokeponButton(String text) {
        super(text);

        InputStream grenzeStream = null;
        try {
            grenzeStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenze = Font.loadFont(grenzeStream, 20);

        Border buttonBorder = new Border(new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20),
                new BorderWidths(2)));

        this.setFont(grenze);
        this.setOpacity(0.5);
        this.setStyle("-fx-background-color: #d3d3d3;");
        this.setStyle("-fx-background-radius: 20;");
        this.setBorder(buttonBorder);
        this.setPrefWidth(280);
    }

    public void setSelect() {
        this.setOpacity(1);
    }

    public void setUnselect() {
        this.setOpacity(0.75);
    }

    public void disable() {
        this.setOpacity(0.5);
        this.setDisable(true);
    }

    public void enable() {
        this.setOpacity(0.80);
        this.setDisable(false);
    }
}
