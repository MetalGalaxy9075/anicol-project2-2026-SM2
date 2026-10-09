package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class WeakMarker extends TileEntity implements OverworldUpdatable {
    public WeakMarker(int x, int y, Image image, Color color) {
        super(x, y, image, color, MokeponConstants.TileEntityType.WEAK);
    }

    @Override
    public void onCreation() {

    }

    @Override
    public void onMove() {
    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {

        // if this tile called the event, start a new battle against a weak mokepon.
        if (caller == this) {
            Mokepon opponent = RandomMokeponSelector.getWeakMokepon(tile);
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
