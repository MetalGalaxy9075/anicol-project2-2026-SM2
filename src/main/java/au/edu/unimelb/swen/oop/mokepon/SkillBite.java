package au.edu.unimelb.swen.oop.mokepon;

public final class SkillBite extends Skill {

    public SkillBite() {
        this.energyCost = 1;
        this.defenseCost = 0;
    }

    @Override
    public String getName() {
        return "Bite";
    }

    @Override
    public void activateSkill(Mokepon target, Mokepon user) {
        hitProbability = ((Math.pow(target.getAccuracy(), 2)) / (Math.pow(target.getAccuracy(), 2)
                + Math.pow(target.getDodge(), 2))) * user.getHitMultiplier();
        double hitResult = Math.random();

        if ((hitResult) < hitProbability) {
            //cast to int for truncation
            damage = (int) (0.5 * user.getStrength() * 50 / (target.getDefence()) * user.getDamageMultiplier());
            logOutput = "Hit " + target.getName() + " for " + damage + " damage!";
        } else {
            damage = 0;
            logOutput = "But they missed.";
        }

    }
}
