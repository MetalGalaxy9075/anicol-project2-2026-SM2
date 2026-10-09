package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class TileMapParser {

    public static Tile[][] parseTileMap(Properties gameFile) {
        Tile[][] tileMap = new Tile[MokeponConstants.gridSize][MokeponConstants.gridSize];

        for (int i = 0; i < MokeponConstants.gridSize; i++) {
            int x = 0;

            // test each letter against the current tile list.
            for (char letter : gameFile.getProperty("map.tiles." + i).toCharArray()) {
                if (letter == 'B') {
                    tileMap[i][x] = new TileBarrier();
                } else if (letter == 'F') {
                    tileMap[i][x] = new TileForest();
                } else if (letter == 'G') {
                    tileMap[i][x] = new TileGrass();
                } else if (letter == 'O') {
                    tileMap[i][x] = new TileOcean();
                } else if (letter == 'P') {
                    tileMap[i][x] = new TilePath();
                } else if (letter == 'R') {
                    tileMap[i][x] = new TileRocks();
                } else if (letter == 'W') {
                    tileMap[i][x] = new TileWater();
                } else {
                    tileMap[i][x] = new TileWater();
                }

                InputStream tileImageStream = null;
                try {
                    tileImageStream = new FileInputStream(gameFile.getProperty("map.tiles.images."
                            + tileMap[i][x].getTileType().toString().toLowerCase()));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Image tileImage = new Image(tileImageStream);
                tileMap[i][x].setTileImage(tileImage);

                x++;
            }
        }
        return tileMap;
    }

    public static ArrayList<TileEntity> parseTileEntities(Properties gameFile) {

        ArrayList<TileEntity> tileEntities = new ArrayList<>();

        InputStream playerImageStream = null;
        try {
            playerImageStream = new FileInputStream(gameFile.getProperty("map.player.image"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image playerImage = new Image(playerImageStream);
        String[] coords = gameFile.getProperty("map.player.startingPos").split(",");
        PlayerEntity player = new PlayerEntity(Integer.valueOf(coords[0]), Integer.valueOf(coords[1]), playerImage);
        tileEntities.add(player);

        // random markers
        InputStream randomInputStream = null;
        try {
            randomInputStream = new FileInputStream(gameFile.getProperty("map.mokeponRandom.image"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image randomImage = new Image(randomInputStream);
        Color randomColor = Color.web(gameFile.getProperty("map.mokeponRandom.colour"));

        int i = 0;
        while (gameFile.get("map.mokeponRandom." + i) != null) {
            String[] coordsRandom = gameFile.getProperty("map.mokeponRandom." + i).split(",");
            RandomMarker random = new RandomMarker(Integer.valueOf(coordsRandom[0]), Integer.valueOf(coordsRandom[1]),
                    randomImage, randomColor);
            tileEntities.add(random);
            i++;
        }

        // strong markers
        InputStream strongInputStream = null;
        try {
            strongInputStream = new FileInputStream(gameFile.getProperty("map.mokeponStrong.image"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image strongimage = new Image(strongInputStream);
        Color strongColor = Color.web(gameFile.getProperty("map.mokeponStrong.colour"));

        i = 0;
        while (gameFile.get("map.mokeponStrong." + i) != null) {
            String[] coordsStrong = gameFile.getProperty("map.mokeponStrong." + i).split(",");
            StrongMarker strong = new StrongMarker(Integer.valueOf(coordsStrong[0]), Integer.valueOf(coordsStrong[1]),
                    strongimage, strongColor);
            tileEntities.add(strong);
            i++;
        }

        // weak markers
        InputStream weakInputStream = null;
        try {
            weakInputStream = new FileInputStream(gameFile.getProperty("map.mokeponWeak.image"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image weakImage = new Image(weakInputStream);
        Color weakColour = Color.web(gameFile.getProperty("map.mokeponWeak.colour"));

        i = 0;
        while (gameFile.get("map.mokeponWeak." + i) != null) {
            String[] coordsWeak = gameFile.getProperty("map.mokeponWeak." + i).split(",");
            WeakMarker weak = new WeakMarker(Integer.valueOf(coordsWeak[0]), Integer.valueOf(coordsWeak[1]),
                    weakImage, weakColour);
            tileEntities.add(weak);
            i++;
        }

        // mystery boxes
        InputStream mysteryInputStream = null;
        try {
            mysteryInputStream = new FileInputStream(gameFile.getProperty("map.items.image"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image mysteryImage = new Image(mysteryInputStream);

        i = 0;
        while (gameFile.get("map.items." + i + ".type") != null) {
            String[] coordsMystery = gameFile.getProperty("map.items." + i + ".location").split(",");
            MysteryBox mystery = new MysteryBox(Integer.valueOf(coordsMystery[0]), Integer.valueOf(coordsMystery[1]),
                    mysteryImage, MokeponConstants.MysteryType.valueOf(gameFile.getProperty("map.items." + i + ".type")));
            tileEntities.add(mystery);
            i++;
        }

        // exit
        InputStream exitStream = null;
        try {
            exitStream = new FileInputStream(gameFile.getProperty("map.exit.image"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image exitImage = new Image(exitStream);
        Color exitColour = Color.web(gameFile.getProperty("map.exit.colour"));
        String[] coordsExit = gameFile.getProperty("map.exit.location").split(",");
        ExitEntity exit = new ExitEntity(Integer.valueOf(coordsExit[0]), Integer.valueOf(coordsExit[1]),
                exitImage, exitColour);
        tileEntities.add(exit);

        return tileEntities;
    }
}
