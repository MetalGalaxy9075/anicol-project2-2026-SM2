package au.edu.unimelb.swen.oop.mokepon;

public class EffectStun extends PlayerEffect {
    Mokepon mokepon;

    public EffectStun(int turnsLeft) {
        super(turnsLeft);

    }

    @Override
    public void activate(Mokepon mokepon) {
        this.mokepon = mokepon;
        mokepon.setDamageMultiplier(mokepon.getDamageMultiplier() * 0.5);
    }

    @Override
    public String onEffectEnd() {
        mokepon.setDamageMultiplier(mokepon.getDamageMultiplier() * 2);
        return "";
    }


}
