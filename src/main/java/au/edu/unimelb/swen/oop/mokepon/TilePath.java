package au.edu.unimelb.swen.oop.mokepon;

public class TilePath extends Tile {

    public TilePath() {
        super(MokeponConstants.Tile.PATH, true);
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
