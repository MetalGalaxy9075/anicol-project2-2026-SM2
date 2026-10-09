package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class WaterImage extends Image {

    private static WaterImage instance = null;

    private WaterImage(InputStream waterStream) {
        super(waterStream);
    }

    public static WaterImage getInstance() {
        if (instance == null) {
            InputStream waterStream = null;
            try {
                waterStream = new FileInputStream("assets/sprite_water.png");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            instance = new WaterImage(waterStream);
        }
        return instance;
    }
}
