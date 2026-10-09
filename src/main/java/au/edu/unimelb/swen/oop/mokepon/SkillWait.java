package au.edu.unimelb.swen.oop.mokepon;

import java.util.Random;

public class SkillWait extends Skill {
    public SkillWait() {
        this.energyCost = -2;
        this.damage = 0;
        this.defenseCost = 0;
    }

    @Override
    public String getName() {
        return "Wait";
    }

    @Override
    public void activateSkill(Mokepon target, Mokepon user) {
        Random logResult = new Random();
        int logState = logResult.nextInt(3);

        if (logState == 0) {
            logOutput = "They get a little more bored.";
        } else if (logState == 1) {
            logOutput = "They wonder if this is a good strategy.";
        } else {
            logOutput = "They think about what they will have for dinner";
        }

    }
}
