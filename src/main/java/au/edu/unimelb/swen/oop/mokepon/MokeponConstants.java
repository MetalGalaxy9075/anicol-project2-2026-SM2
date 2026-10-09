package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.paint.Color;

public final class MokeponConstants {
    public static final double HPMAX = 1000;
    public static final double ENERGYMAX = 100;
    public static final double STRENGTHMAX = 100;
    public static final double ACCURACYMAX = 100;
    public static final double DEFENCEMAX = 100;
    public static final double DODGEMAX = 100;
    public static final int MAXSKILLS = 4;
    public static final int TITLE_FONT_SIZE = 34;
    public static final int TITLE_TWO_FONT_SIZE = 30;
    public static final int HEADING_ONE_FONT_SIZE = 28;
    public static final int HEADING_TWO_FONT_SIZE = 24;
    public static final int PARAGRAPH_FONT_SIZE = 12;
    public static final double MINIMUM_PROGRSS_BAR_VALUE = 0.03;
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 768;
    public static final Color faintOverlayColor = Color.web("#b41e1e", 0.35);
    public static final String mokeponGrenzeFontFilepath = "assets/GrenzeGotisch-Black.ttf";
    public static final double standardMokeponMultiplier = 1.0;
    public static final Color defaultTintColour = Color.WHITE;
    public static final int tileSize = 30;
    public static final int gridSize = 20;
    public static final int up = 0;
    public static final int down = 1;
    public static final int left = 2;
    public static final int right = 3;
    public static double movementButtonWidth = 60;
    public static double statusBarWidth = 500;
    public static String playerAvantageString = "Your mokepon will be at an advantage here!";
    public static String playerDisavantageString = "Your mokepon will be at an disatvantage here!";
    public static String enemyAvantageString = "Your enemy will be at an advantage here!";
    public static String enemyDisavantageString = "Your enemy will be at an disatvantage here!";

    private MokeponConstants() {
    }

    public enum Role {
        PLAYER,
        ENEMY
    }

    public enum Type {
        NORMAL,
        FIRE,
        ICE,
        WATER,
        LIGHTNING
    }

    public enum Tile {
        BARRIER,
        FOREST,
        GRASS,
        OCEAN,
        PATH,
        ROCKS,
        WATER
    }

    public enum TileEntityType {
        PLAYER,
        RANDOM,
        WEAK,
        STRONG,
        MYSTERY,
        EXIT
    }

    //type for myster box items
    public enum MysteryType {
        HEALTH,
        COFFEE,
        POISON
    }

    public enum WinState {
        WIN,
        LOSE
    }

}
