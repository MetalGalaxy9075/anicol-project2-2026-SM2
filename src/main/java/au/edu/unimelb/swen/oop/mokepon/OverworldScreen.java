package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static au.edu.unimelb.swen.oop.mokepon.MokeponConstants.statusBarWidth;

public class OverworldScreen extends Pane {

    //Mokepon player, Runnable mainMenu
    public OverworldScreen(Tile[][] tileMap, ArrayList<TileEntity> entities, Player player, OverworldModal modal) {
        OverworldUpdateManager.init(modal, player);

        //------------------------------------------------
        //   Center Column
        //------------------------------------------------
        WorldUI world = new WorldUI(tileMap);
        world.setLayoutX((MokeponConstants.SCREEN_WIDTH / 2) -
                (MokeponConstants.gridSize / 2 * MokeponConstants.tileSize));
        world.setLayoutY(40);
        this.getChildren().add(world);

        EntityUIManager entityUI = new EntityUIManager(entities, world);
        entityUI.setLayoutX((MokeponConstants.SCREEN_WIDTH / 2) -
                (MokeponConstants.gridSize / 2 * MokeponConstants.tileSize));
        entityUI.setLayoutY(40);
        OverworldUpdateManager.getInstance().subscribe(entityUI);
        this.getChildren().add(entityUI);
        entityUI.update();

        StatusBar status = new StatusBar(player.getStatusTrackable());
        status.setLayoutX((MokeponConstants.SCREEN_WIDTH / 2) - statusBarWidth / 2);
        status.setLayoutY(670);
        OverworldUpdateManager.getInstance().subscribe(status);
        this.getChildren().add(status);

        //------------------------------------------------
        //   Background, Game-bar and Style Definitions
        //------------------------------------------------
        try {
            InputStream bgStream = new FileInputStream("assets/bg.png");
            Image image = new Image(bgStream);
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                    false, false, true, true));
            this.setBackground(new Background(backgroundImage));
        } catch (Exception e) {
            System.err.println("Error loading background image");
        }

        OverworldBar gameBar = new OverworldBar(new Runnable() {
            @Override
            public void run() {
                modal.activate("Test", "test", "test", "test",
                        "close", () -> {
                }, () -> {
                    modal.close();
                });
            }
        });
        this.getChildren().add(gameBar);
        gameBar.prefWidthProperty().bind(this.widthProperty());


        //------------------------------------------------
        //   Right Column
        //------------------------------------------------

        TrackableProperty<Boolean> tinted = new TrackableProperty<Boolean>(false);
        MokeponBackground statsBG = new MokeponBackground(tinted);
        statsBG.setPosition(970, 40);
        statsBG.setSize(240, 300);
        this.getChildren().add(statsBG);

        OverworldPlayerStats playerStats = new OverworldPlayerStats(player);
        this.getChildren().addAll(playerStats);
        OverworldUpdateManager.getInstance().subscribe(playerStats);

        MovementButtonUnit movement = new MovementButtonUnit();
        MovementManager movementManager = new MovementManager(world, entityUI,
                movement, OverworldUpdateManager.getInstance());
        movement.setOnAction(movementManager.returnOnMove(MokeponConstants.up),
                movementManager.returnOnMove(MokeponConstants.down),
                movementManager.returnOnMove(MokeponConstants.left),
                movementManager.returnOnMove(MokeponConstants.right));
        movement.setLayoutX(1000);
        movement.setLayoutY(600);
        this.getChildren().add(movement);
        OverworldUpdateManager.getInstance().subscribe(movementManager);
        entityUI.subscribeElements(OverworldUpdateManager.getInstance());

    }

}
