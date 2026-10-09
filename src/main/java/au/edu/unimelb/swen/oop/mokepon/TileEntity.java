package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class TileEntity implements OverworldUpdatable {
    private static int idCount;
    private int x;
    private int y;
    private Image image;
    private Color color;
    private Runnable onEntry;
    private boolean enabled;
    private final MokeponConstants.TileEntityType type;
    private final ArrayList<Tile> playerSurroundingTiles;
    private final int id;

    public TileEntity(int x, int y, Image image, Color color, MokeponConstants.TileEntityType type) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.color = color;
        this.type = type;
        enabled = true;
        playerSurroundingTiles = new ArrayList<>();
        id = idCount++;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void disable() {
        this.enabled = false;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public MokeponConstants.TileEntityType getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
