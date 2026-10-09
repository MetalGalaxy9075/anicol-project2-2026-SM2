package au.edu.unimelb.swen.oop.mokepon;

import java.util.ArrayList;
import java.util.Properties;

public class MokeponFileReader {

    private final Properties gameFile;

    public MokeponFileReader(Properties gameFile) {
        this.gameFile = gameFile;
    }

    public ArrayList<Mokepon> getMokeponsFromFile() {
        ArrayList<Mokepon> mokepons = new ArrayList<>();

        int i = 0;

        //while the next dodge value is not null = the next mokepon exists
        while (gameFile.get("mokepon." + i + ".dodge") != null) {
            Mokepon newMokepon = new Mokepon();
            newMokepon.setName(gameFile.getProperty("mokepon." + i + ".name"));
            newMokepon.setImage_url(gameFile.getProperty("mokepon." + i + ".image"));
            newMokepon.setHp(Integer.valueOf(gameFile.getProperty("mokepon." + i + ".hp")));
            newMokepon.setEnergy(Integer.valueOf(gameFile.getProperty("mokepon." + i + ".energy")));
            newMokepon.setStrength(Integer.valueOf(gameFile.getProperty("mokepon." + i + ".strength")));
            newMokepon.setAccuracy(Integer.valueOf(gameFile.getProperty("mokepon." + i + ".accuracy")));
            newMokepon.setDefence(Integer.valueOf(gameFile.getProperty("mokepon." + i + ".defence")));
            newMokepon.setDodge(Integer.valueOf(gameFile.getProperty("mokepon." + i + ".dodge")));
            AbilitySet abilities = new AbilitySet(gameFile.getProperty("mokepon." + i + ".skills"));
            newMokepon.setMokeponType(MokeponConstants.Type.valueOf(gameFile.getProperty("mokepon." + i + ".type")));

            //type handling
            if (newMokepon.getMokeponType() == MokeponConstants.Type.NORMAL) {
                newMokepon.setMokeponTypeImage(NormalImage.getInstance());
            } else if (newMokepon.getMokeponType() == MokeponConstants.Type.FIRE) {
                newMokepon.setMokeponTypeImage(FireImage.getInstance());
            } else if (newMokepon.getMokeponType() == MokeponConstants.Type.ICE) {
                newMokepon.setMokeponTypeImage(IceImage.getInstance());
            } else if (newMokepon.getMokeponType() == MokeponConstants.Type.WATER) {
                newMokepon.setMokeponTypeImage(WaterImage.getInstance());
            } else if (newMokepon.getMokeponType() == MokeponConstants.Type.LIGHTNING) {
                newMokepon.setMokeponTypeImage(LightningImage.getInstance());
            }
            newMokepon.setAbilities(abilities);
            newMokepon.init();

            mokepons.add(newMokepon);
            i = i + 1;
        }

        // sending image urls to seperate sprite manager class to handle image creation.
        SpriteManager.initInstance(gameFile.getProperty("items.coffee"), gameFile.getProperty("items.heal"),
                gameFile.getProperty("items.poison"));

        return mokepons;
    }

    public Tile[][] parseTileMap() {
        return TileMapParser.parseTileMap(gameFile);
    }

}
