package au.edu.unimelb.swen.oop.mokepon;

public abstract class Item {

    private String logOut;
    private MokeponConstants.MysteryType itemType;

    public Item() {

    }

    public abstract void activate(Mokepon mokepon);

    public String getText() {
        return logOut;
    }

    public void setLogOut(String logOut) {
        this.logOut = logOut;
    }

    public MokeponConstants.MysteryType getItemType() {
        return itemType;
    }

    public void setItemType(MokeponConstants.MysteryType itemType) {
        this.itemType = itemType;
    }
}
