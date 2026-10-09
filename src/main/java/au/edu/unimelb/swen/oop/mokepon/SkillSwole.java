package au.edu.unimelb.swen.oop.mokepon;

import java.util.Random;

public class SkillSwole extends Skill {

    public SkillSwole() {
        this.energyCost = 3;
        this.defenseCost = 0;
    }

    @Override
    public void activateSkill(Mokepon target, Mokepon user) {
        user.takeEnergy(3);

        Random strengthIncrease = new Random();
        int randomIncrease = strengthIncrease.nextInt(1, 11);

        user.setStrength(user.getStrength() + randomIncrease);

        logOutput = "They raised their strength by " + randomIncrease + " points!";
    }

    @Override
    public String getName() {
        return "Swole";
    }
}
