package au.edu.unimelb.swen.oop.mokepon;

import java.util.ArrayList;

public class MovementManager implements OverworldUpdatable {
    WorldUI world;
    EntityUIManager entityUIManager;
    MovementButtonUnit movement;
    ArrayList<OverworldUpdatable> updatables;
    OverworldUpdateManager updater;

    public MovementManager(WorldUI world, EntityUIManager entityUIManager, MovementButtonUnit movement, OverworldUpdateManager updater) {
        this.world = world;
        this.entityUIManager = entityUIManager;
        this.movement = movement;
        this.updater = updater;

        updatables = new ArrayList<>();
    }

    public void subscribe(OverworldUpdatable updatable) {
        updatables.add(updatable);
    }

    public void updateMovementOptions(){
        movement.updateEnabled(world.getImmediatelyWalkable(entityUIManager.getPlayer().getX(), entityUIManager.getPlayer().getY()));
    }

    public Runnable returnOnMove(int direction)
    {
        if(direction == MokeponConstants.up){
            return ((Runnable) () -> {
                if(entityUIManager.getPlayer().getY() > 0){
                    entityUIManager.getPlayer().setY(entityUIManager.getPlayer().getY() - 1);
                }
                entityUIManager.update();
                updateMovementOptions();
                updater.move();
            });
        }

        if(direction == MokeponConstants.down){
            return ((Runnable) () -> {
                if(entityUIManager.getPlayer().getY() < MokeponConstants.gridSize-1){
                    entityUIManager.getPlayer().setY(entityUIManager.getPlayer().getY() + 1);
                }
                entityUIManager.update();
                updateMovementOptions();
                updater.move();
            });
        }

        if(direction == MokeponConstants.left){
            return ((Runnable) () -> {
                if(entityUIManager.getPlayer().getX() > 0){
                    entityUIManager.getPlayer().setX(entityUIManager.getPlayer().getX() - 1);
                }
                entityUIManager.update();
                updateMovementOptions();
                updater.move();
            });
        }

        if(direction == MokeponConstants.right){
            return ((Runnable) () -> {
                if(entityUIManager.getPlayer().getX() < MokeponConstants.gridSize-1){
                    entityUIManager.getPlayer().setX(entityUIManager.getPlayer().getX() + 1);
                }
                entityUIManager.update();
                updateMovementOptions();
                updater.move();
            });
        }

        return ((Runnable) () -> {
            System.out.println("Empty Runnable");
        });
    }

    @Override
    public void onCreation() {

    }

    @Override
    public void onMove() {
        if(entityUIManager.getEntityAtTile(entityUIManager.getPlayer().getX(), entityUIManager.getPlayer().getY()) != null){

            updater.event(entityUIManager.getEntityAtTile(entityUIManager.getPlayer().getX(), entityUIManager.getPlayer().getY()),
                    world.getTile(entityUIManager.getPlayer().getX(), entityUIManager.getPlayer().getY()));
        }
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
