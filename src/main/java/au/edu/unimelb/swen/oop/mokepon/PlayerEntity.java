package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

public class PlayerEntity extends TileEntity {

    public PlayerEntity(int x, int y, Image image) {
        super(x, y, image, MokeponConstants.defaultTintColour, MokeponConstants.TileEntityType.PLAYER);
    }

    @Override
    public void onCreation() {

    }

    @Override
    public void onMove() {

    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {

    }

    @Override
    public void onEventEnd(String eventMessage, TileEntity eventCaller) {

    }

    @Override
    public void onExit() {

    }
}
