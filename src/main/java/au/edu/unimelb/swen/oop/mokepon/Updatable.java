package au.edu.unimelb.swen.oop.mokepon;

// defines common events for observable objects during the battle cycle
public interface Updatable {

    void onUpdate();

    void onPlayerTurn();

    void onEnemyTurn();

    void onEndGame();
}
