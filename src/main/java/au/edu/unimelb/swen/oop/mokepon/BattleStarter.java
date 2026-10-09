package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.Scene;

// Battle starter class is a globally callable class to open the battle screen
public class BattleStarter {
    private static BattleScreen battleScreen;
    private static Scene rootSceneLocal;
    private static Player playerLocal;
    private static BattleCompleteModal modalLocal;
    private static OverworldScreen lastScreenLocal;

    public static void initialise(Scene rootScene, Player player, BattleCompleteModal modal,
                                  OverworldScreen overWorldScreen) {
        rootSceneLocal = rootScene;
        playerLocal = player;
        modalLocal = modal;
        lastScreenLocal = overWorldScreen;
    }

    public static void startBattle(Mokepon opponent, TileEntity caller) {
        if (rootSceneLocal != null) {
            BattleScreen bScreen = new BattleScreen(playerLocal, opponent, () -> {

            }, () -> {
                // return to overworld
                rootSceneLocal.setRoot(lastScreenLocal);
                caller.setEnabled(false);
                playerLocal.incNumbFights();
                OverworldUpdateManager.getInstance().eventEnd("You beat the enemy " +
                        opponent.getName(), caller);
            }, () -> {

            }, modalLocal);
            ScreenManager.startBattle(bScreen);
        } else {
            System.out.println("Root screen null");
        }
    }

}
