package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SpriteManager {
    static SpriteManager instance;
    Image effect1;
    Image effect2;
    Image effect3;

    //simple 3 image sprite manager. Can be extended to increase image count.
    private SpriteManager(String effect1URL, String effect2URL, String effect3URL) {
        InputStream effect1Stream = null;
        try {
            effect1Stream = new FileInputStream(effect1URL);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        effect1 = new Image(effect1Stream);

        InputStream effect2Stream = null;
        try {
            effect2Stream = new FileInputStream(effect2URL);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        effect2 = new Image(effect2Stream);

        InputStream effect3Stream = null;
        try {
            effect3Stream = new FileInputStream(effect3URL);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        effect3 = new Image(effect3Stream);
    }

    public static void initInstance(String effect1URL, String effect2URL, String effect3URL) {
        instance = new SpriteManager(effect1URL, effect2URL, effect3URL);
    }

    public static SpriteManager getInstance() {
        if (instance == null) {
            System.err.println("SpriteManager not initialized");
        }
        return instance;
    }

    public static Image getEffect1() {
        return getInstance().effect1;
    }

    public static Image getEffect2() {
        return getInstance().effect2;
    }

    public static Image getEffect3() {
        return getInstance().effect3;
    }
}
