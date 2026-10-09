package au.edu.unimelb.swen.oop.mokepon;

public class SkillLance extends Skill {

    public SkillLance() {
        this.energyCost = 4;
        this.defenseCost = 0;
    }

    @Override
    public void activateSkill(Mokepon target, Mokepon user) {
        user.takeEnergy(4);

        hitProbability = ((Math.pow(target.getAccuracy(), 2)) / (Math.pow(target.getAccuracy(), 2)
                + Math.pow(target.getDodge(), 2))) * user.getHitMultiplier();

        double hitResult = Math.random();
        if ((hitResult) < hitProbability) {
            //cast to int for truncation
            damage = (int) (user.getStrength() * 50 / (target.getDefence()) * user.getDamageMultiplier());
            logOutput = "Hit " + target.getName() + " for " + damage + " damage! They started bleeding";

            target.addEffect(new EffectBleed(2, user.getTrackableStrength(), target.getTrackableDefence()));
        } else {
            damage = 0;
            logOutput = "But they missed.";
        }
    }

    @Override
    public String getName() {
        return "Lance";
    }
}
