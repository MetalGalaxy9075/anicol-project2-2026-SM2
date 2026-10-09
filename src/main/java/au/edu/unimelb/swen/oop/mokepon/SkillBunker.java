package au.edu.unimelb.swen.oop.mokepon;

import java.util.Random;

public final class SkillBunker extends Skill {
    public SkillBunker() {
        this.energyCost = 3;
        this.damage = 0;
    }

    @Override
    public String getName() {
        return "Bunker";
    }

    @Override
    public void activateSkill(Mokepon target, Mokepon user) {
        Random defenseResult = new Random();
        defenseCost = -1 * defenseResult.nextInt(11);
        logOutput = "They raised their defence by " + -1 * defenseCost + " points!";
    }
}
