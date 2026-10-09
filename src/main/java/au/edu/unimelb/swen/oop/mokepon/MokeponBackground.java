package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MokeponBackground extends ImageView implements Updatable {

    TrackableProperty<Boolean> isFainted;
    private Blend faintBlend;

    public MokeponBackground(TrackableProperty<Boolean> isFainted) {
        InputStream bgStream = null;
        try {
            bgStream = new FileInputStream("assets/scroll.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image bgIMG = new Image(bgStream);
        super(bgIMG);

        this.isFainted = isFainted;

        initFaintBlend();
    }

    public void setSize(double width, double height) {
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

    //must be called after position or size is changed
    public void initFaintBlend() {
        //initialising the red faint overlay that occurs in faint condition
        ColorInput playerFaintColourInput = new ColorInput();
        playerFaintColourInput.setPaint(MokeponConstants.faintOverlayColor);
        playerFaintColourInput.widthProperty().bind(
                this.layoutBoundsProperty().map(b -> b.getWidth()));
        playerFaintColourInput.heightProperty().bind(
                this.layoutBoundsProperty().map(b -> b.getHeight()));
        playerFaintColourInput.setX(this.getLayoutX());
        playerFaintColourInput.setY(this.getLayoutY());
        faintBlend = new Blend(BlendMode.SRC_ATOP);
        faintBlend.setTopInput(playerFaintColourInput);
        faintBlend.setOpacity(0.8);

        this.setEffect(null);
    }

    public void setPosition(double x, double y) {
        this.relocate(x, y);
    }


    @Override
    public void onUpdate() {
        this.setEffect(null);
    }

    @Override
    public void onPlayerTurn() {
        this.setEffect(null);
    }

    @Override
    public void onEnemyTurn() {
        this.setEffect(null);
    }

    @Override
    public void onEndGame() {
        if (isFainted.getValue()) {
            this.setEffect(faintBlend);
        }
    }
}
