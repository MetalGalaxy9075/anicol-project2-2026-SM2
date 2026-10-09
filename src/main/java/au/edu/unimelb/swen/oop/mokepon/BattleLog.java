package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * BattleLog class, extends JavaFX TextArea. Implements a text area of set size and location with extended logging
 * functionality.
 */
public class BattleLog extends TextArea {

    /**
     * Instantiates a new TextArea with set size and style
     */
    public BattleLog() {
        super();
        this.setPrefWidth(600);
        this.setPrefHeight(220);
        this.setLayoutX(630);
        this.setLayoutY(500);
        this.setEditable(false);

        Font monospaceFont = Font.font("monospaced", FontWeight.BOLD, MokeponConstants.PARAGRAPH_FONT_SIZE);
        this.setFont(monospaceFont);
    }

    /**
     * Appends a new string to the TextArea and moves to the next line.
     *
     * @param text text to be appended to the TextArea
     */
    public void logln(String text) {
        this.appendText(text + "\n");
    }

    /**
     * Appends a new string to the TextArea following a tab space and moves to the next line.
     *
     * @param text text to be appended to the TextArea
     */
    public void loglntb(String text) {
        this.appendText("\t" + text + "\n");
    }

    /**
     * Clears the TextArea and prints a new start of game message using the names of the player and opponent.
     *
     * @param player   Player mokepon of the current game
     * @param opponent Opponent mokepon of the current game
     */
    public void reset(Mokepon player, Mokepon opponent) {
        this.clear();
        this.logln("Player chose " + player.getName() + ". Began fight against enemy " + opponent.getName() + "!");
    }
}
