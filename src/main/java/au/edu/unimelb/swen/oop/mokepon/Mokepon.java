package au.edu.unimelb.swen.oop.mokepon;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Mokepon {
    private TrackableProperty<String> name;
    private String image_url;
    private TrackableDuplet<Double, Double> hp;
    private TrackableDuplet<Double, Double> energy;
    private TrackableDuplet<Double, Double> strength;
    private TrackableDuplet<Double, Double> accuracy;
    private TrackableDuplet<Double, Double> defence;
    private TrackableDuplet<Double, Double> dodge;
    private AbilitySet abilities;
    private InputStream imageStream;
    private Image image;
    private TrackableProperty<Boolean> isFainted;
    private ArrayList<PlayerEffect> effects;
    private double damageMultiplier;
    private double hitMultiplier;

    private MokeponConstants.Type mokeponType;
    private Image mokeponTypeImage;

    public Mokepon() {
        name = new TrackableProperty<>("Name");
        hp = new TrackableDuplet<Double, Double>(0.0, 0.0);
        energy = new TrackableDuplet<Double, Double>(0.0, 0.0);
        strength = new TrackableDuplet<Double, Double>(0.0, 0.0);
        accuracy = new TrackableDuplet<Double, Double>(0.0, 0.0);
        defence = new TrackableDuplet<Double, Double>(0.0, 0.0);
        dodge = new TrackableDuplet<Double, Double>(0.0, 0.0);
        isFainted = new TrackableProperty<>(false);

        effects = new ArrayList<>();
    }

    public void init() {
        // setting "default" stats for the mokepon, and initialising the image
        try {
            imageStream = new FileInputStream("assets/"+image_url.substring(6));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        image = new Image(imageStream);

        hp.setValue2(hp.getValue1());
        energy.setValue2(energy.getValue1());
        defence.setValue2(defence.getValue1());
        strength.setValue2(MokeponConstants.STRENGTHMAX);
        accuracy.setValue2(MokeponConstants.ACCURACYMAX);
        dodge.setValue2(MokeponConstants.DODGEMAX);
        isFainted.setValue(false);
        damageMultiplier = 1;
        hitMultiplier = 1;
    }

    public void copy(Mokepon mokepon) {
        mokepon.setName(name.getValue());
        mokepon.setImage_url(image_url);
        mokepon.setHp(hp.getValue1());
        mokepon.setEnergy(energy.getValue1());
        mokepon.setStrength(strength.getValue1());
        mokepon.setAccuracy(accuracy.getValue1());
        mokepon.setDefence(defence.getValue1());
        mokepon.setDodge(dodge.getValue1());
        mokepon.setAbilities(abilities);
        mokepon.init();
    }

    public void reset() {
        this.hp.setValue1(hp.getValue2());
        this.energy.setValue1(energy.getValue2());
        this.defence.setValue1(defence.getValue2());
        this.strength.setValue1(strength.getValue2());
        this.accuracy.setValue1(accuracy.getValue2());
        this.dodge.setValue1(dodge.getValue2());
        this.isFainted.setValue(false);
        effects.clear();
    }

    public void takeDamage(double damage) {
        hp.setValue1(hp.getValue1() - damage);
        if (hp.getValue1() <= 0) {
            hp.setValue1(0.0);
        } else if (hp.getValue1() > hp.getValue2()) {
            hp.setValue1(hp.getValue2());
        }
    }

    public void takeEnergy(double energy) {
        this.energy.setValue1(this.energy.getValue1() - energy);
        if (this.energy.getValue1() <= 0) {
            this.energy.setValue1(0.0);
        } else if (this.energy.getValue1() > this.energy.getValue2()) {
            this.energy.setValue1(this.energy.getValue2());
        }
    }

    public void takeDefence(double defence) {
        this.defence.setValue1(this.defence.getValue1() - defence);
        if (this.defence.getValue1() <= 0) {
            this.defence.setValue1(0.0);
        } else if (this.defence.getValue1() > MokeponConstants.DEFENCEMAX) {
            this.defence.setValue1(MokeponConstants.DEFENCEMAX);
        }
    }

    public void faint() {
        isFainted.setValue(true);
    }

    public String toString() {
        return name + " " + hp + " " + energy + " " + strength + " " + abilities.getSkills()[1].getName();
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public double getHp() {
        return hp.getValue1();
    }

    public void setHp(double hp) {
        this.hp.setValue1(hp);

        if (this.hp.getValue1() > MokeponConstants.HPMAX) {

        }
    }

    public double getEnergy() {
        return energy.getValue1();
    }

    public void setEnergy(double energy) {
        this.energy.setValue1(energy);

        if(this.energy.getValue1() > MokeponConstants.ENERGYMAX) {
            this.energy.setValue1(MokeponConstants.ENERGYMAX);
        }
    }

    public double getStrength() {
        return strength.getValue1();
    }

    public void setStrength(double strength) {
        this.strength.setValue1(strength);

        if (this.strength.getValue1() > MokeponConstants.STRENGTHMAX) {
            this.strength.setValue1(MokeponConstants.STRENGTHMAX);
        }
    }

    public double getAccuracy() {
        return accuracy.getValue1();
    }

    public void setAccuracy(double accuracy) {
        this.accuracy.setValue1(accuracy);

        if (this.accuracy.getValue1() > MokeponConstants.ACCURACYMAX) {
            this.accuracy.setValue1(MokeponConstants.ACCURACYMAX);
        }
    }

    public double getDefence() {
        return defence.getValue1();
    }

    public void setDefence(double defence) {
        this.defence.setValue1(defence);

        if (this.defence.getValue1() > MokeponConstants.DEFENCEMAX) {
            this.defence.setValue1(MokeponConstants.DEFENCEMAX);
        }
    }

    public double getDodge() {
        return dodge.getValue1();
    }

    public void setDodge(double dodge) {
        this.dodge.setValue1(dodge);

        if (this.dodge.getValue1() > MokeponConstants.DODGEMAX) {
            this.dodge.setValue1(MokeponConstants.DODGEMAX);
        }
    }

    public AbilitySet getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilitySet abilities) {
        this.abilities = abilities;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isFainted() {
        return isFainted.getValue();
    }

    public TrackableDuplet<Double, Double> getTrackableHp()
    {
        return hp;
    }

    public TrackableDuplet<Double, Double> getTrackableEnergy()
    {
        return energy;
    }

    public TrackableDuplet<Double, Double> getTrackableDefence()
    {
        return defence;
    }

    public TrackableDuplet<Double, Double> getTrackableStrength()
    {
        return strength;
    }

    public TrackableDuplet<Double, Double> getTrackableAccuracy()
    {
        return accuracy;
    }

    public TrackableDuplet<Double, Double> getTrackableDodge()
    {
        return dodge;
    }

    public TrackableProperty<Boolean> getTrackableFainted() {
        return isFainted;
    }

    public MokeponConstants.Type getMokeponType() {
        return mokeponType;
    }

    public void setMokeponType(MokeponConstants.Type mokeponType) {
        this.mokeponType = mokeponType;
    }

    public Image getMokeponTypeImage() {
        return mokeponTypeImage;
    }

    public void setMokeponTypeImage(Image mokeponTypeImage) {
        this.mokeponTypeImage = mokeponTypeImage;
    }

    public void addEffect(PlayerEffect effect) {
        this.effects.add(effect);
    }

    public String applyEffects() {
        String logOut = "";

        // applies applied effects to player by iterating through the effects list.
        for (PlayerEffect effect : effects) {

            if(effect.getTurnsLeft() <= 1){
                effect.activate(this);
                logOut += effect.onEffectEnd();
                this.effects.remove(effect);
            }

            effect.activate(this);
        }

        return logOut;
    }

    // multiplier functions for various selections. This could have been abstracted further, though the author
    // ran out of time.
    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public double getHitMultiplier() {
        return hitMultiplier;
    }

    public void setHitMultiplier(double hitMultiplier) {
        this.hitMultiplier = hitMultiplier;
    }

    public void setOverallMultiplier(double overallMultiplier) {
        setHp(hp.getValue1() * overallMultiplier);
        setEnergy((int)(energy.getValue1() * overallMultiplier));
        setStrength((int)(strength.getValue1() * overallMultiplier));
        setAccuracy((int)(accuracy.getValue1() * overallMultiplier));
        setDefence((int)(defence.getValue1() * overallMultiplier));
        setDodge((int)(dodge.getValue1() * overallMultiplier));
    }
}
