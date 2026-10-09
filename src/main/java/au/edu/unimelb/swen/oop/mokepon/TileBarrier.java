package au.edu.unimelb.swen.oop.mokepon;

public class TileBarrier extends Tile {

    public TileBarrier() {
        super(MokeponConstants.Tile.BARRIER, false);
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
