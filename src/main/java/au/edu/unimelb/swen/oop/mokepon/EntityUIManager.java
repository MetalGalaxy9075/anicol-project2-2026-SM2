package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class EntityUIManager extends GridPane implements OverworldUpdatable {

    ArrayList<TileEntity> entities;
    WorldUI worldUI;

    public EntityUIManager(ArrayList<TileEntity> entities, WorldUI worldUI) {
        super();

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setMinHeight(MokeponConstants.tileSize);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setMinWidth(MokeponConstants.tileSize);
        for (int i = 0; i < MokeponConstants.gridSize; i++) {
            this.getColumnConstraints().add(columnConstraints);
            this.getRowConstraints().add(rowConstraints);
        }

        // rendering entities based on x and y coordinates
        for (TileEntity entity : entities) {
            ImageView tileImage = new ImageView(entity.getImage());
            tileImage.setFitWidth(MokeponConstants.tileSize);
            tileImage.setFitHeight(MokeponConstants.tileSize);

            this.add(tileImage, entity.getX(), entity.getY());
        }

        this.entities = entities;
        this.worldUI = worldUI;
    }

    public void update() {
        this.getChildren().clear();

        //rendering entities
        for (TileEntity entity : entities) {
            ImageView tileImage = new ImageView(entity.getImage());
            tileImage.setFitWidth(MokeponConstants.tileSize);
            tileImage.setFitHeight(MokeponConstants.tileSize);

            // tinting entities
            Light.Distant entityLight = new Light.Distant();
            entityLight.setColor(entity.getColor());
            Lighting entityLighting = new Lighting();
            entityLighting.setLight(entityLight);
            entityLighting.setSurfaceScale(0.0);
            entityLighting.setDiffuseConstant(1.0);
            entityLighting.setSpecularConstant(0.0);

            tileImage.setEffect(entityLighting);
            this.add(tileImage, entity.getX(), entity.getY());
        }
    }

    public void subscribeElements(OverworldUpdateManager overworldUpdateManager) {
        for (TileEntity entity : entities) {
            overworldUpdateManager.subscribe(entity);
        }
    }

    public TileEntity getPlayer() {
        return entities.getFirst();
    }

    @Override
    public void onCreation() {
        update();
    }

    @Override
    public void onMove() {
        update();
    }

    @Override
    public void onEventStart(TileEntity caller, Tile tile, OverworldModal modal, Player onBlock) {
        update();
    }

    @Override
    public void onEventEnd(String eventMessage, TileEntity eventCaller) {
        //finding caller tile entity in entities list
        TileEntity match = entities.get(0);
        for (TileEntity entity : entities) {
            if (entity.getId() == eventCaller.getId()) {
                match = entity;
            }
        }

        entities.remove(match);
        update();

    }

    @Override
    public void onExit() {
        update();
    }

    public TileEntity getEntityAtTile(int x, int y) {
        for (TileEntity entity : entities) {
            if (entity.getX() == x && entity.getY() == y && entity.getType() !=
                    MokeponConstants.TileEntityType.PLAYER) {
                return entity;
            }
        }

        return null;
    }
}
