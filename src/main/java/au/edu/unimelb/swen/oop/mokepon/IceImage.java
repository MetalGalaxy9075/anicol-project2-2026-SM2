package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class IceImage extends Image {

    private static IceImage instance = null;

    private IceImage(InputStream iceStream) {
        super(iceStream);
    }

    // singleton instance of the image for the ice type mokepon
    public static IceImage getInstance()
    {
        if (instance == null)
        {
            InputStream iceStream = null;
            try {
                iceStream = new FileInputStream("assets/sprite_ice.png");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            instance = new IceImage(iceStream);
        }
        return instance;
    }
}
