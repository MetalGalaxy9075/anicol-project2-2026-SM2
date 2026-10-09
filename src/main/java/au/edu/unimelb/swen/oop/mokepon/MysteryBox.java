package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

public class MysteryBox extends TileEntity implements OverworldUpdatable {

    private static int boxCounter = 0;
    private final MokeponConstants.MysteryType mysteryType;
    //box number provides each mystery box a unique ID.
    private final int boxNumber;

    public MysteryBox(int x, int y, Image image, MokeponConstants.MysteryType mysteryType) {
        super(x, y, image, MokeponConstants.defaultTintColour, MokeponConstants.TileEntityType.MYSTERY);
        this.mysteryType = mysteryType;
        this.boxNumber = boxCounter;
        boxCounter++;
    }

    @Override
    public void onCreation() {

    }

    @Override
    public void onMove() {

    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {
        // test for mystery box type. In future this could be moved to an abstract class, where this functionality
        // is defined in child classes.
        if (caller == this) {
            String statusText = "";
            if (mysteryType == MokeponConstants.MysteryType.COFFEE) {
                onBlock.getItems().addItem(new ItemCoffee());
                onBlock.setStatus("You picked up a Coffee!");
                statusText = "You picked up a Coffee!";
            } else if (mysteryType == MokeponConstants.MysteryType.POISON) {
                onBlock.getItems().addItem(new ItemPoison());
                onBlock.setStatus("You picked up a Poison!");
                statusText = "You picked up a Poison!";
            } else if (mysteryType == MokeponConstants.MysteryType.HEALTH) {
                onBlock.getItems().addItem(new ItemTonic());
                onBlock.setStatus("You picked up a Health Tonic!");
                statusText = "You picked up a Health Tonic!";
            }

            OverworldUpdateManager.getInstance().eventEnd(statusText, this);
        }
    }

    @Override
    public void onEventEnd(String eventMessage, TileEntity eventCaller) {

    }

    @Override
    public void onExit() {

    }


}
