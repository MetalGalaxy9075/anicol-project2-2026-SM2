package au.edu.unimelb.swen.oop.mokepon;

import java.util.ArrayList;

public class BattleUIManager {

    ArrayList<Updatable> toUpdate;

    public BattleUIManager() {
        toUpdate = new ArrayList<>();
    }

    public void subscribe(Updatable updatable) {
        toUpdate.add(updatable);
    }

    public void unsubscribe(Updatable updatable) {
        toUpdate.remove(updatable);
    }

    public void update() {
        for (Updatable updatable : toUpdate) {
            updatable.onUpdate();
        }
    }

    public void playerTurn() {
        for (Updatable updatable : toUpdate) {
            updatable.onPlayerTurn();
        }
    }

    public void enemyTurn() {
        for (Updatable updatable : toUpdate) {
            updatable.onEnemyTurn();
        }
    }

    public void endGame() {
        for (Updatable updatable : toUpdate) {
            updatable.onEndGame();
        }
    }

}
