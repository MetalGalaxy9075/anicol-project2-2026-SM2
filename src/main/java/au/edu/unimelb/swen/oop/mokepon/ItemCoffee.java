package au.edu.unimelb.swen.oop.mokepon;

public class ItemCoffee extends Item {

    public ItemCoffee() {
        super();

        setItemType(MokeponConstants.MysteryType.COFFEE);
    }

    @Override
    public void activate(Mokepon mokepon) {
        mokepon.getTrackableEnergy().setValue1(mokepon.getTrackableEnergy().getValue2());

        setLogOut("\nYou used your Coffee on " + mokepon.getName() + ". It restored all your energy!");
    }

}
