package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacterImage extends ImageView {

    public CharacterImage(Image image) {
        super(image);
        this.setFitHeight(256);
        this.setFitWidth(256);
        this.setPreserveRatio(true);
    }
}
