package au.edu.unimelb.swen.oop.mokepon;

public class SkillPocketSand extends Skill {

    public SkillPocketSand() {
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
            target.addEffect(new EffectBlind(3));
            logOutput = "Pocket sand blinded " + target.getName() + "!\n Their targeted skills may miss for 3 turns.";
        } else {
            damage = 0;
            logOutput = "But they missed.";
        }
    }

    @Override
    public String getName() {
        return "Pocket Sand";
    }
}
