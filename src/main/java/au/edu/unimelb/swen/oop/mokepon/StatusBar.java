package au.edu.unimelb.swen.oop.mokepon;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StatusBar extends VBox implements OverworldUpdatable {
    Text text;
    TrackableProperty<String> status;

    public StatusBar(TrackableProperty<String> status) {
        super();
        this.setMinWidth(500);
        this.setMinHeight(40);
        this.setAlignment(Pos.CENTER);
        this.setStyle(
                "-fx-background-color: #000000, white;" +
                        "-fx-background-insets: 0, 2;"
        );

        text = new Text("Test");
        this.getChildren().add(text);

        this.status = status;
    }

    private void updateText(String statusText) {
        text.setText(statusText);
    }

    private void clear() {
        text.setText("");
    }


    @Override
    public void onCreation() {
        clear();
    }

    @Override
    public void onMove() {
        clear();
        status.setValue("");
    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {
        updateText(status.getValue());
    }

    @Override
    public void onEventEnd(String eventMessage, TileEntity eventCaller) {
        updateText(eventMessage);
    }

    @Override
    public void onExit() {
        clear();
    }
}
