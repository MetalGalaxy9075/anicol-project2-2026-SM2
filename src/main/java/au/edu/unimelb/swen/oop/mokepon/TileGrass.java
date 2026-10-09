package au.edu.unimelb.swen.oop.mokepon;

import static au.edu.unimelb.swen.oop.mokepon.MokeponConstants.*;

public class TileGrass extends Tile {

    public TileGrass() {
        super(MokeponConstants.Tile.GRASS, true);
    }

    @Override
    public double returnModier(MokeponConstants.Type mokeponType) {
        if (mokeponType == MokeponConstants.Type.LIGHTNING) {
            return 1.5;
        } else if (mokeponType == MokeponConstants.Type.ICE) {
            return 0.5;
        }

        return 1;
    }

    @Override
    public String[] returnAdvantageString(MokeponConstants.Type mokeponType) {
        String[] out = new String[2];
        out[0] = " ";
        out[1] = " ";

        if (mokeponType == Type.LIGHTNING) {
            out[0] = playerAvantageString;
            out[1] = enemyAvantageString;
            return out;
        } else if (mokeponType == Type.ICE) {
            out[0] = playerDisavantageString;
            out[1] = enemyDisavantageString;
            return out;
        }

        return out;
    }
}
