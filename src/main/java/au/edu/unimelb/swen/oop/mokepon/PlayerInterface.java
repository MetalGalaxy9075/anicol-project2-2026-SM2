package au.edu.unimelb.swen.oop.mokepon;

// used to retreive specific player data where otherwise would require major restructuring.
public class PlayerInterface {

    private static Player playerLocal;

    public static void init(Player player) {
        playerLocal = player;
    }

    public static MokeponConstants.Type getPlayerType() {
        return playerLocal.getMokeponType();
    }
}
