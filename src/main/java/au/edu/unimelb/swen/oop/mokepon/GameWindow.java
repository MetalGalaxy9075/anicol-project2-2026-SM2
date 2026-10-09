package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameWindow extends Stage {

    public GameWindow() {
        setTitle("Mokepon");
        setResizable(false);
        setScene(new Scene(new Pane(), 1280, 768, Color.LIGHTGREY));
    }
}
