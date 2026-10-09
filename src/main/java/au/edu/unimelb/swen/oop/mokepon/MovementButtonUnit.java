package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import static au.edu.unimelb.swen.oop.mokepon.MokeponConstants.movementButtonWidth;

public class MovementButtonUnit extends Pane implements OverworldUpdatable {
    Boolean[] walkable;
    MovementButton upButton;
    MovementButton downButton;
    MovementButton leftButton;
    MovementButton rightButton;

    public MovementButtonUnit() {
        super();
        this.setWidth(200);
        this.setHeight(150);

    }

    public void setOnAction(Runnable up, Runnable down, Runnable left, Runnable right) {
        upButton = new MovementButton(up, "assets/sprite_up.png", KeyCode.UP);
        downButton = new MovementButton(down, "assets/sprite_down.png", KeyCode.DOWN);
        leftButton = new MovementButton(left, "assets/sprite_left.png", KeyCode.LEFT);
        rightButton = new MovementButton(right, "assets/sprite_right.png", KeyCode.RIGHT);

        this.getChildren().addAll(upButton, downButton, leftButton, rightButton);

        //setting relative position based on constants
        upButton.setLayoutX(movementButtonWidth);
        upButton.setLayoutY(0);

        downButton.setLayoutX(movementButtonWidth);
        downButton.setLayoutY(movementButtonWidth);

        leftButton.setLayoutX(0);
        leftButton.setLayoutY(movementButtonWidth);

        rightButton.setLayoutX(2 * movementButtonWidth);
        rightButton.setLayoutY(movementButtonWidth);
    }

    public void updateEnabled(Boolean[] walkable) {

        //checking if adjacent tiles are walkable. The order is [up, down, left, right]
        this.walkable = walkable;
        upButton.disable();
        downButton.disable();
        leftButton.disable();
        rightButton.disable();

        if (walkable[0]) {
            upButton.enable();
        }
        if (walkable[1]) {
            downButton.enable();
        }
        if (walkable[2]) {
            leftButton.enable();
        }
        if (walkable[3]) {
            rightButton.enable();
        }
    }


    @Override
    public void onCreation() {
        updateEnabled(walkable);
    }

    @Override
    public void onMove() {
        updateEnabled(walkable);
    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {
        upButton.disable();
        downButton.disable();
        leftButton.disable();
        rightButton.disable();
    }

    @Override
    public void onEventEnd(String eventMessage, TileEntity eventCaller) {
        updateEnabled(walkable);
    }

    @Override
    public void onExit() {
        upButton.disable();
        downButton.disable();
        leftButton.disable();
        rightButton.disable();
    }
}
