package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class StrongMarker extends TileEntity implements OverworldUpdatable {
    public StrongMarker(int x, int y, Image image, Color color) {
        super(x, y, image, color, MokeponConstants.TileEntityType.STRONG);
    }

    @Override
    public void onCreation() {

    }

    @Override
    public void onMove() {

    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {

        // if this tile called the event, start a new battle against a strong mokepon
        if (caller == this) {
            Mokepon opponent = RandomMokeponSelector.getStrongMokepon(tile);
            modal.activate("Encountered a " + opponent.getName(), tile.returnAdvantageString(PlayerInterface.getPlayerType())[0],
                    tile.returnAdvantageString(PlayerInterface.getPlayerType())[1], "Fight!", "Run Away!",
                    () -> {
                        BattleStarter.startBattle(opponent, this);
                    }, () -> {
                        modal.close();
                    });
        }
    }

    @Override
    public void onEventEnd(String eventMessage, TileEntity eventCaller) {

    }

    @Override
    public void onExit() {

    }
}
