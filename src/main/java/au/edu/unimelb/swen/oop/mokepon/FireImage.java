package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FireImage extends Image {

    private static FireImage instance = null;

    private FireImage(InputStream fireStream) {
        super(fireStream);
    }

    public static FireImage getInstance() {
       // singleton instance of the image for the fire type mokepon
        if (instance == null) {
            InputStream fireStream = null;
            try {
                fireStream = new FileInputStream("assets/sprite_fire.png");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            instance = new FireImage(fireStream);
        }
        return instance;
    }
}
