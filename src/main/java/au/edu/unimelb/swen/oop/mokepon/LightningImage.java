package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LightningImage extends Image {

    private static LightningImage instance = null;

    private LightningImage(InputStream lightningStream) {
        super(lightningStream);
    }

    public static LightningImage getInstance() {
        if (instance == null) {
            InputStream lightningStream = null;
            try {
                lightningStream = new FileInputStream("assets/sprite_lightning.png");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            instance = new LightningImage(lightningStream);
        }
        return instance;
    }
}
