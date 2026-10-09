package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.input.KeyCode;

public class MokeponActionButton extends ActionButton implements Updatable {

    private final TrackableDuplet<Double, Double> energy;
    private final TrackableProperty<Boolean> isFainted;
    private final Skill skill;

    public MokeponActionButton(Skill skill, TrackableProperty<Boolean> isFainted,
                               TrackableDuplet<Double, Double> energy, Runnable onClick) {
        this.isFainted = isFainted;
        this.energy = energy;
        this.skill = skill;

        super(skill.getName() + " ["
                + (int) skill.getEnergyCost() + "]");

        this.setOnAction(event -> {
            onClick.run();
        });

        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DIGIT1) {
                onClick.run();
            }
        });
    }

    @Override
    public void onUpdate() {
        this.Disable();

        if (energy.getValue1() > skill.getEnergyCost()) {
            this.Enable();
        }
    }

    @Override
    public void onPlayerTurn() {
        this.Disable();
        if (energy.getValue1() > skill.getEnergyCost()) {
            this.Enable();
        }
    }

    @Override
    public void onEnemyTurn() {
        this.Disable();
    }

    @Override
    public void onEndGame() {
        this.Disable();
    }
}
