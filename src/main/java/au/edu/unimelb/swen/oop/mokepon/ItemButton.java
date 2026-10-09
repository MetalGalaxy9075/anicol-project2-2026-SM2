package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ItemButton extends MokeponButton implements Updatable {
    ItemCase items;
    MokeponConstants.MysteryType itemType;

    public ItemButton(String text, Image itemImage, Runnable onClick, ItemCase items,
                      MokeponConstants.MysteryType itemType) {
        super(text);

        Border buttonBorder = new Border(new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(40),
                new BorderWidths(2)));

        InputStream grenzeStream = null;
        try {
            grenzeStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font grenze = Font.loadFont(grenzeStream, 24);

        ImageView itemView = new ImageView(itemImage);
        this.setGraphic(itemView);
        this.setPrefWidth(20);
        this.setPrefHeight(100);
        this.setStyle("-fx-background-radius: 40;");
        this.setContentDisplay(ContentDisplay.TOP);
        this.setBorder(buttonBorder);
        this.setFont(grenze);


        this.setOnAction(e -> {
            onClick.run();
        });

        this.items = items;
        this.itemType = itemType;
    }

    private void UpdateText(String newText) {
        this.setText(newText);
    }

    @Override
    public void onUpdate() {
        // enable / disable based on number of items
        if (items.getItemCount(itemType) > 0) {
            enable();
        } else {
            disable();
        }

        UpdateText(String.valueOf(items.getItemCount(itemType)));
    }

    @Override
    public void onPlayerTurn() {
        if (items.getItemCount(itemType) > 0) {
            enable();
        } else {
            disable();
        }

        UpdateText(String.valueOf(items.getItemCount(itemType)));
    }

    @Override
    public void onEnemyTurn() {
        disable();
    }

    @Override
    public void onEndGame() {
        disable();
    }
}
