package au.edu.unimelb.swen.oop.mokepon;

public class ItemTonic extends Item {

    public ItemTonic() {
        super();

        setItemType(MokeponConstants.MysteryType.HEALTH);
    }

    @Override
    public void activate(Mokepon mokepon) {
        mokepon.getTrackableHp().setValue1(mokepon.getTrackableHp().getValue2());

        setLogOut("\nYou used your Health Tonic on " + mokepon.getName() + ". It healed you to max HP!");
    }
}
