package au.edu.unimelb.swen.oop.mokepon;

import java.util.ArrayList;

// Class to hold item counts for a player
public class ItemCase {
    ArrayList<Item> items;

    public ItemCase() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int getItemCount(MokeponConstants.MysteryType itemType) {

        int count = 0;
        for (Item item : items) {
            if (item.getItemType() == itemType) {
                count++;
            }
        }

        return count;
    }

    public void useItem(Item item, Mokepon mokepon) {
        item.activate(mokepon);
        items.remove(item);
    }

    public boolean hasItem(MokeponConstants.MysteryType itemType) {
        for (Item item : items) {
            if (item.getItemType() == itemType) {
                return true;
            }
        }

        return false;
    }

    public Item getItem(MokeponConstants.MysteryType itemType) {
        for (Item item : items) {
            if (item.getItemType() == itemType) {
                return item;
            }
        }

        return null;
    }
}
