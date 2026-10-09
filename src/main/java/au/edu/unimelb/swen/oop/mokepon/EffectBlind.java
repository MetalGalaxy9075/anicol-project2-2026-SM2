package au.edu.unimelb.swen.oop.mokepon;

public class EffectBlind extends PlayerEffect {
    Mokepon mokepon;

    public EffectBlind(int turnsLeft) {
        super(turnsLeft);
    }

    @Override
    public void activate(Mokepon mokepon) {
        this.mokepon = mokepon;

        mokepon.setHitMultiplier(mokepon.getHitMultiplier() * 0.5);
    }

    @Override
    public String onEffectEnd() {
        mokepon.setHitMultiplier(mokepon.getHitMultiplier() * 2);
        return "";
    }
}
