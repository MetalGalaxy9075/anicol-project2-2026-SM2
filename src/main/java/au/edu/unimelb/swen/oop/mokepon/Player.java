package au.edu.unimelb.swen.oop.mokepon;

public class Player extends Mokepon {
    private final ItemCase itemCase;
    private int numbFights;
    private final TrackableProperty<String> status;

    public Player() {
        numbFights = 0;
        itemCase = new ItemCase();
        status = new TrackableProperty<String>("");
        super();
    }

    public void setFromMokepon(Mokepon mokepon) {
        // useful function to define a player from a mokepon. Allows player selection from main mokepon list.
        this.setImage_url(mokepon.getImage_url());
        this.setHp(mokepon.getHp());
        this.setEnergy(mokepon.getEnergy());
        this.setStrength(mokepon.getStrength());
        this.setAccuracy(mokepon.getAccuracy());
        this.setDefence(mokepon.getDefence());
        this.setDodge(mokepon.getDodge());
        this.setName(mokepon.getName());
        this.setImage(mokepon.getImage());
        this.setAbilities(mokepon.getAbilities());
        this.init();
    }

    public int getNumbFights() {
        return numbFights;
    }

    public void incNumbFights() {
        numbFights = numbFights + 1;
    }

    public String getStatus() {
        return status.getValue();
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public TrackableProperty<String> getStatusTrackable() {
        return status;
    }

    public ItemCase getItems() {
        return itemCase;
    }

}
