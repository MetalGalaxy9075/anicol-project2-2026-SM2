package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * BattleScreen class, extends JavaFX Pane. Instantiates a pane preloaded with all elements from the battle screen
 * specification. Also defines the battle sequence.
 */
public final class BattleScreen extends Pane {

    private final Mokepon player;
    private final Mokepon opponent;


    /**
     * Instantiates a new pane preloaded with all battle screen elements. Also sets action events for buttons,
     * keys and the gameBar. All elements are positioned with absolute coordinates, and are designed to fit on
     * a 1280x768 screen.
     *
     * @param player   Player Mokepon for this battle.
     * @param opponent Opponent Mokepon for this battle.
     * @param reset    Runnable event to occur when the reset GameBar item is selected.
     */
    public BattleScreen(Player player, Mokepon opponent, Runnable reset, Runnable onWin,
                        Runnable onLose, BattleCompleteModal modal) {
        this.player = player;
        this.opponent = opponent;

        player.reset();

        //------------------------------------------
        //   Logging and management
        //------------------------------------------

        BattleLog fightInformation = new BattleLog();
        this.getChildren().add(fightInformation);
        fightInformation.logln("Player chose " + player.getName() + ". Began fight against enemy "
                + opponent.getName() + "!");

        BattleUIManager battleUIManager = new BattleUIManager();

        // adding functionality to onwin modals using objects accessible in this layer
        Runnable onWinModal = () -> {
            modal.activate(MokeponConstants.WinState.WIN, () -> {
                onWin.run();
            }, () -> {
            }, () -> {
            }, this);
        };
        Runnable onLoseModal = () -> {
            modal.activate(MokeponConstants.WinState.LOSE, () -> {
            }, () -> {
                ScreenManager.quitGame();
            }, () -> {
                onLose.run();
                ScreenManager.resartFromMainMenu();
            }, this);
        };
        BattleMokeponManager battleMokeponManager = new BattleMokeponManager(battleUIManager, player,
                opponent, fightInformation, onWinModal, onLoseModal);

        //------------------------------------------
        //   Background and Style Definitions
        //------------------------------------------
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

        Color faintOverlayColor = Color.web("#b41e1e", 0.35);

        InputStream battleHeadingStream = null;
        try {
            battleHeadingStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font battleHeading = Font.loadFont(battleHeadingStream, MokeponConstants.HEADING_ONE_FONT_SIZE);

        InputStream actionHeadingStream = null;
        try {
            actionHeadingStream = new FileInputStream("assets/GrenzeGotisch-Black.ttf");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Font actionHeading = Font.loadFont(actionHeadingStream, MokeponConstants.HEADING_TWO_FONT_SIZE);

        //------------------------------------------
        //             Player Elements
        //------------------------------------------

        MokeponBackground playerBG = new MokeponBackground(player.getTrackableFainted());
        playerBG.setSize(550, 550);
        playerBG.setPosition(70, 728 - playerBG.getFitHeight());
        this.getChildren().add(playerBG);
        battleUIManager.subscribe(playerBG);

        BattleScreenBar gameBar = new BattleScreenBar(reset, () -> {
            battleMokeponManager.reset();
        });
        this.getChildren().add(gameBar);
        gameBar.prefWidthProperty().bind(this.widthProperty());

        CharacterImage playerMokeponImage = new CharacterImage(player.getImage());
        playerMokeponImage.setX(100);
        playerMokeponImage.setY(768 / 2.0 - 50);
        this.getChildren().add(playerMokeponImage);

        Text playerTitle = new Text(player.getName());
        playerTitle.setFont(battleHeading);
        playerTitle.setX(270);
        playerTitle.setY(768 / 2.0 - 80);
        this.getChildren().add(playerTitle);

        MokeponProgressBar playerHpBar = new MokeponProgressBar("limegreen", player.getTrackableHp());
        playerHpBar.setPosition(100, 600);
        this.getChildren().add(playerHpBar);
        battleUIManager.subscribe(playerHpBar);

        MokeponProgressBar playerEnergyBar = new MokeponProgressBar("royalblue", player.getTrackableEnergy());
        playerEnergyBar.setPosition(100, 635);
        this.getChildren().add(playerEnergyBar);
        battleUIManager.subscribe(playerEnergyBar);

        Text actionTitle = new Text("Actions");
        actionTitle.setFont(actionHeading);
        actionTitle.setX(470);
        actionTitle.setY(768 / 2.0 - 80 + 50);
        this.getChildren().add(actionTitle);

        // Creating skill buttons dynamically
        int y = 374;
        for (Skill skill : player.getAbilities().getSkills()) {
            if (skill != null) {
                MokeponActionButton skillButton = new MokeponActionButton(skill, player.getTrackableFainted(),
                        player.getTrackableEnergy(), battleMokeponManager.getMokeponAction(skill));
                skillButton.setLayoutX(416);
                skillButton.setLayoutY(y);
                battleUIManager.subscribe(skillButton);
                this.getChildren().add(skillButton);

                y += 50;
            }
        }

        //Item button definitions
        ItemButton coffee = new ItemButton("0", SpriteManager.getEffect1(), () -> {
            // If player has an item, use that item and update the log / update manager
            if (player.getItems().hasItem(MokeponConstants.MysteryType.COFFEE)) {
                Item item = player.getItems().getItem(MokeponConstants.MysteryType.COFFEE);
                player.getItems().useItem(item, player);
                fightInformation.logln(item.getText());
                battleUIManager.update();
            }
        }, player.getItems(), MokeponConstants.MysteryType.COFFEE);
        coffee.setLayoutX(200);
        coffee.setLayoutY(80);
        battleUIManager.subscribe(coffee);
        this.getChildren().add(coffee);

        ItemButton poison = new ItemButton("0", SpriteManager.getEffect3(), () -> {
            if (player.getItems().hasItem(MokeponConstants.MysteryType.POISON)) {
                Item item = player.getItems().getItem(MokeponConstants.MysteryType.POISON);
                player.getItems().useItem(item, opponent);
                fightInformation.logln(item.getText());
                battleUIManager.update();
            }
        }, player.getItems(), MokeponConstants.MysteryType.POISON);
        poison.setLayoutX(280);
        poison.setLayoutY(80);
        battleUIManager.subscribe(poison);
        this.getChildren().add(poison);

        ItemButton tonic = new ItemButton("0", SpriteManager.getEffect2(), () -> {
            if (player.getItems().hasItem(MokeponConstants.MysteryType.HEALTH)) {
                Item item = player.getItems().getItem(MokeponConstants.MysteryType.HEALTH);
                player.getItems().useItem(item, player);
                fightInformation.logln(item.getText());
                battleUIManager.update();
            }
        }, player.getItems(), MokeponConstants.MysteryType.HEALTH);
        tonic.setLayoutX(360);
        tonic.setLayoutY(80);
        battleUIManager.subscribe(tonic);
        this.getChildren().add(tonic);

        //------------------------------------------
        //             Enemy Elements
        //------------------------------------------

        MokeponBackground enemyBG = new MokeponBackground(opponent.getTrackableFainted());
        enemyBG.setSize(300, 450);
        enemyBG.setPosition(940, 40);
        this.getChildren().add(enemyBG);
        battleUIManager.subscribe(enemyBG);

        CharacterImage enemyMokeponImage = new CharacterImage(opponent.getImage());
        enemyMokeponImage.setX(960);
        enemyMokeponImage.setY(120);
        this.getChildren().add(enemyMokeponImage);

        Text enemyTitle = new Text(opponent.getName());
        enemyTitle.setFont(battleHeading);
        enemyTitle.setX(1280 - 240);
        enemyTitle.setY(110);
        this.getChildren().add(enemyTitle);

        MokeponProgressBar enemyHpBar = new MokeponProgressBar("limegreen",
                opponent.getTrackableHp());
        enemyHpBar.setPosition(965, 370);
        this.getChildren().add(enemyHpBar);
        battleUIManager.subscribe(enemyHpBar);

        MokeponProgressBar enemyEnergyBar = new MokeponProgressBar("royalblue",
                opponent.getTrackableEnergy());
        enemyEnergyBar.setPosition(965, 405);
        this.getChildren().add(enemyEnergyBar);
        battleUIManager.subscribe(enemyEnergyBar);


        // begin game logic
        battleUIManager.update();

    }


}
