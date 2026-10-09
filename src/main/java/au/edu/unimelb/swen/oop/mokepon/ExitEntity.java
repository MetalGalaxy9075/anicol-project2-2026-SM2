package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ExitEntity extends TileEntity implements OverworldUpdatable {
    public ExitEntity(int x, int y, Image image, Color color) {
        super(x, y, image, color, MokeponConstants.TileEntityType.EXIT);
    }

    @Override
    public void onCreation() {

    }

    @Override
    public void onMove() {

    }

    // end game modal call
    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {
        if (caller == this) {
            modal.activate("You finished the game! You won " + onBlock.getNumbFights() + " fights!", "",
                    "", "Return to Main Menu", "Quit",
                    () -> {
                        ScreenManager.resartFromMainMenu();
                        modal.close();
                    }, () -> {
                        modal.close();
                        ScreenManager.quitGame();
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
