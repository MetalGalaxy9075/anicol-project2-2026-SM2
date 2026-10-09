package au.edu.unimelb.swen.oop.mokepon;

// abstract class for lasting effects on a mokepon
public abstract class PlayerEffect {
    private int turnsLeft;

    public PlayerEffect(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    public void onTurn(){
        turnsLeft--;
    }

    public abstract void activate(Mokepon mokepon);

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public abstract String onEffectEnd();
}
