package au.edu.unimelb.swen.oop.mokepon;

public class TileOcean extends Tile {

    public TileOcean() {
        super(MokeponConstants.Tile.OCEAN, false);
    }

    @Override
    public double returnModier(MokeponConstants.Type mokeponType) {
        return 1;
    }

    @Override
    public String[] returnAdvantageString(MokeponConstants.Type mokeponType) {
        String[] out = new String[2];
        out[0] = " ";
        out[1] = " ";

        return out;
    }
}
