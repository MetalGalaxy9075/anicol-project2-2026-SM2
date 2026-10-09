package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.io.InputStream;

// a default image used to create image classes where a specific image may not have been defined yet.
public class NormalImage extends Image {

    private static Image instance = null;

    private NormalImage(InputStream Stream) {
        super(Stream);
    }

    public static Image getInstance() {
        if (instance == null) {
            instance = new WritableImage(1, 1);
        }
        return instance;
    }
}
