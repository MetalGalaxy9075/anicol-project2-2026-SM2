package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MokeponProgressBar extends StackPane implements Updatable {

    private Text progressText;
    private ProgressBar progressBar;
    private TrackableDuplet<Double, Double> value;

    public MokeponProgressBar(String colour, TrackableDuplet<Double, Double> value)
    {
        super();
        progressBar = new ProgressBar();
        progressBar.setProgress(100);
        progressBar.setPrefHeight(25);
        progressBar.setPrefWidth(256);
        progressBar.setStyle("-fx-accent: "+colour+";");

        Font progressBarOverlay = Font.font("monospaced", FontWeight.BOLD,
                MokeponConstants.PARAGRAPH_FONT_SIZE);

        //placeholder values on creation
        progressText = new Text(String.valueOf(value.getValue1()) + " / " + String.valueOf(value.getValue2()));
        progressText.setFont(progressBarOverlay);

        this.getChildren().add(progressBar);
        this.getChildren().add(progressText);

        this.value = value;
    }

    public void setPosition(double x, double y)
    {
        this.relocate(x, y);
    }

    // recenters text based on progress par position and text size. Must be called after every move.
    private void recenterText()
    {
        progressText.relocate(progressBar.getWidth() / 2 - progressText.getX() / 2,
                progressBar.getLayoutY() + progressBar.getHeight() / 2 - progressText.getY() / 2);
    }

    private void update(){
        // 3% rule and clamping implementation for progress bars
        if ((value.getValue1() / value.getValue2()) > MokeponConstants.MINIMUM_PROGRSS_BAR_VALUE
                || value.getValue1() == 0) {
            progressBar.setProgress(value.getValue1() / value.getValue2());
        } else {
            progressBar.setProgress(MokeponConstants.MINIMUM_PROGRSS_BAR_VALUE);
        }
        progressText.setText(String.valueOf(value.getValue1()) + " / " + String.valueOf(value.getValue2()));

        recenterText();
    }

    @Override
    public void onUpdate() {
        update();
    }

    @Override
    public void onPlayerTurn() {
        update();
    }

    @Override
    public void onEnemyTurn() {
        update();
    }

    @Override
    public void onEndGame() {
        update();
    }
}
