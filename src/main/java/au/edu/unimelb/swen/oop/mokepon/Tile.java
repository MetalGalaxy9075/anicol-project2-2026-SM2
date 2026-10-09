package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

public abstract class Tile {
    protected MokeponConstants.Tile tileType;
    protected Image tileImage;
    protected Boolean walkable;

    public Tile(MokeponConstants.Tile tileType, Boolean walkable) {
        this.tileType = tileType;
        this.walkable = walkable;
    }

    public abstract double returnModier(MokeponConstants.Type mokeponType);

    // returns a string to display in the modal depending on the mokepon type
    public abstract String[] returnAdvantageString(MokeponConstants.Type mokeponType);

    public MokeponConstants.Tile getTileType() {
        return tileType;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
