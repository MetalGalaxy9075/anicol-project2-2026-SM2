package au.edu.unimelb.swen.oop.mokepon;

import java.util.Random;

public class SkillAim extends Skill {

    public SkillAim() {
        this.energyCost = 3;
        this.defenseCost = 0;
    }

    @Override
    public void activateSkill(Mokepon target, Mokepon user) {
        user.takeEnergy(3);

        Random accuracyIncrease = new Random();
        int randomIncrease = accuracyIncrease.nextInt(1, 11);
        user.setAccuracy(user.getAccuracy() + randomIncrease);

        logOutput = "They raised their accuracy by " + randomIncrease + " points!";
    }

    @Override
    public String getName() {
        return "Aim";
    }
}
