package au.edu.unimelb.swen.oop.mokepon;


abstract public class Skill {
    protected double energyCost;
    protected String logOutput;
    protected double defenseCost;
    protected double damage;
    protected double hitProbability;

    abstract public void activateSkill(Mokepon target, Mokepon user);

    abstract public String getName();

    public final double getDamage() {
        return damage;
    }

    public final double getEnergyCost() {
        return energyCost;
    }

    public final String toString() {
        return logOutput;
    }

    public final double getDefenseCost() {
        return defenseCost;
    }
}
