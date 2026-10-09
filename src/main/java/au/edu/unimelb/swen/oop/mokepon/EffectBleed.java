package au.edu.unimelb.swen.oop.mokepon;

public class EffectBleed extends PlayerEffect {

    TrackableDuplet<Double, Double> userStrength;
    TrackableDuplet<Double, Double> targetDefence;

    public EffectBleed(int turnsLeft, TrackableDuplet<Double, Double> userStrength,
                       TrackableDuplet<Double, Double> targetDefence) {
        super(turnsLeft);

        this.userStrength = userStrength;
        this.targetDefence = targetDefence;
    }

    @Override
    public void activate(Mokepon mokepon) {
        int damage = (int) (userStrength.getValue1() * 50 / (targetDefence.getValue1()));
        mokepon.takeDamage(damage);
    }

    @Override
    public String onEffectEnd() {
        return "The bleeding stopped.";
    }
}
