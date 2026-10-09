package au.edu.unimelb.swen.oop.mokepon;

import java.util.ArrayList;

public class OverworldUpdateManager {
    private static ArrayList<OverworldUpdatable> updatables;
    private static OverworldModal modal;
    private static OverworldUpdateManager instance;
    private static Player playerLocal;

    private OverworldUpdateManager(OverworldModal modal, Player player) {
        updatables = new ArrayList<>();
        OverworldUpdateManager.modal = modal;
        playerLocal = player;
    }

    public static void init(OverworldModal modal, Player player) {
        if (instance == null) {
            updatables = new ArrayList<>();
            instance = new OverworldUpdateManager(modal, player);
        }
    }

    public static OverworldUpdateManager getInstance() {
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public void subscribe(OverworldUpdatable updatable) {
        updatables.add(updatable);
    }

    public void start() {
        for (OverworldUpdatable updatable : updatables) {
            updatable.onCreation();
        }
    }

    public void move() {
        for (OverworldUpdatable updatable : updatables) {
            updatable.onMove();
        }
    }

    public void event(TileEntity caller, Tile tile) {
        for (OverworldUpdatable updatable : updatables) {
            updatable.onEventStart(caller, tile, modal, playerLocal);
        }
    }

    public void eventEnd(String eventMessage, TileEntity eventCaller) {
        for (OverworldUpdatable updatable : updatables) {
            updatable.onEventEnd(eventMessage, eventCaller);
        }
    }

    public void exit() {
        for (OverworldUpdatable updatable : updatables) {
            updatable.onExit();
        }
    }
}
