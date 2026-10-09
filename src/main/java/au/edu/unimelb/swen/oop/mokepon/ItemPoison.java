package au.edu.unimelb.swen.oop.mokepon;

public class ItemPoison extends Item {

    public ItemPoison() {
        super();

        setItemType(MokeponConstants.MysteryType.POISON);
    }

    @Override
    public void activate(Mokepon mokepon) {
        mokepon.getTrackableHp().setValue1(1.0);

        setLogOut("\nYou used your Poison on " + mokepon.getName() + ". It devastated them!");
    }
}
