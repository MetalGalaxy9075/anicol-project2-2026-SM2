package au.edu.unimelb.swen.oop.mokepon;

import java.util.Random;

public class SkillNimble extends Skill {

    public SkillNimble() {
        this.energyCost = 3;
        this.defenseCost = 0;
    }

    @Override
    public void activateSkill(Mokepon target, Mokepon user) {
        user.takeEnergy(3);

        Random dodgeIncrease = new Random();
        int randomIncrease = dodgeIncrease.nextInt(1, 11);

        user.setDodge(user.getDodge() + randomIncrease);

        logOutput = "They raised their dodge by " + randomIncrease + " points!";
    }

    @Override
    public String getName() {
        return "Nimble";
    }
}
