package au.edu.unimelb.swen.oop.mokepon;

public class SkillStun extends Skill {

    public SkillStun() {
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
            target.addEffect(new EffectStun(3));
            logOutput = target.getName() + " was stunned! Their damage is halved for 3 turns.";
        } else {
            damage = 0;
            logOutput = "But they missed.";
        }
    }

    @Override
    public String getName() {
        return "Stun";
    }
}
