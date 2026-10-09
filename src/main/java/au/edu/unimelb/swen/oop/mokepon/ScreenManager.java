package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Properties;

import static au.edu.unimelb.swen.oop.mokepon.MokeponConstants.SCREEN_HEIGHT;
import static au.edu.unimelb.swen.oop.mokepon.MokeponConstants.SCREEN_WIDTH;

public class ScreenManager {

    private static ScreenManager instance;
    private static OverworldScreen overworld;
    private static SelectScreen select;
    private static Scene localRoot;
    private static Player player;
    private static Properties props;
    private static ArrayList<Mokepon> mokepons;
    private static GameWindow gameWindowLocal;

    private ScreenManager(){

    }

    // start of game logic
    public static Stage init(Properties gameData){
        gameWindowLocal = new GameWindow();

        // reading game file and converting into map and mokepon list
        MokeponFileReader mokeponFileReader = new MokeponFileReader(gameData);
        mokepons = mokeponFileReader.getMokeponsFromFile();
        Tile[][] tileMap = mokeponFileReader.parseTileMap();
        ArrayList<TileEntity> entities = TileMapParser.parseTileEntities(gameData);

        player = new Player();
        select = new SelectScreen(mokepons, ()->{
            player.setFromMokepon(select.getSelectedMokepon());
            switchToOverworld();
        });
        OverworldModal modal = new OverworldModal(gameWindowLocal);
        localRoot = new Scene(select, SCREEN_WIDTH, SCREEN_HEIGHT);
        overworld = new OverworldScreen(tileMap, entities, player, modal);

        PlayerInterface.init(player);
        BattleStarter.initialise(localRoot, player, new BattleCompleteModal(gameWindowLocal, localRoot), overworld);
        RandomMokeponSelector.init(mokepons);

        gameWindowLocal.setScene(localRoot);
        gameWindowLocal.setTitle("Mokepon");
        gameWindowLocal.show();

        if(instance == null){
           instance =  new ScreenManager();
        }

        props =  gameData;
        return gameWindowLocal;
    }

    public static ScreenManager getInstance(){
        if(instance == null){
           System.err.println("ScreenManager instance is null");
        }

        return instance;
    }

    public static void switchToOverworld(){
        player.reset();
        localRoot.setRoot(overworld);
        OverworldUpdateManager.getInstance().start();
    }

    public static void startBattle(BattleScreen battleScreen){
        localRoot.setRoot(battleScreen);
    }

    public static void resartFromMainMenu(){
        gameWindowLocal.close();
        gameWindowLocal = null;
        OverworldUpdateManager.reset();
        init(props);
    }

    public static void quitGame(){
        System.exit(0);
    }
}
