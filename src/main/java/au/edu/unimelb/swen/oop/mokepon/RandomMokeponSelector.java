package au.edu.unimelb.swen.oop.mokepon;

import java.util.ArrayList;
import java.util.Random;

import static au.edu.unimelb.swen.oop.mokepon.MokeponConstants.standardMokeponMultiplier;

public class RandomMokeponSelector {
    private static ArrayList<Mokepon> mokeponsLocal;

    public static void init(ArrayList<Mokepon> mokepon){
        mokeponsLocal = mokepon;
    }

    public static Mokepon getRandomMokepon(Tile tile){
        if(mokeponsLocal == null){
            System.out.println("Must initialise before use!");
            return null;
        }

        Random rand = new Random();
        int randomMokeponSelector = rand.nextInt(mokeponsLocal.size());

        Mokepon randomMokeponTemp = mokeponsLocal.get(randomMokeponSelector);
        Mokepon randomMokepon = new Mokepon();
        randomMokeponTemp.copy(randomMokepon);

        randomMokepon.setOverallMultiplier(tile.returnModier(randomMokepon.getMokeponType()));
        return randomMokepon;
    }

    public static Mokepon getStrongMokepon(Tile tile){
        if(mokeponsLocal == null){
            System.out.println("Must initialise before use!");
            return null;
        }

        ArrayList<Mokepon> eligible = new ArrayList<>();

        // create shortlist of mokepons with an advantage on the current tile
        for(Mokepon mokepon : mokeponsLocal){
            if(tile.returnModier(mokepon.getMokeponType()) > standardMokeponMultiplier){
                eligible.add(mokepon);
            }
        }

        Random rand = new Random();
        int randomMokeponSelector = rand.nextInt(eligible.size());

        Mokepon randomMokeponTemp = eligible.get(randomMokeponSelector);
        Mokepon randomMokepon = new Mokepon();
        randomMokeponTemp.copy(randomMokepon);

        randomMokepon.setOverallMultiplier(tile.returnModier(randomMokepon.getMokeponType()));
        return randomMokepon;
    }

    public static Mokepon getWeakMokepon(Tile tile){
        if(mokeponsLocal == null){
            System.out.println("Must initialise before use!");
            return null;
        }

        ArrayList<Mokepon> eligible = new ArrayList<>();

        // create shortlist of mokepons with a disadvantage on the current tile
        for(Mokepon mokepon : mokeponsLocal){
            if(tile.returnModier(mokepon.getMokeponType()) < standardMokeponMultiplier){
                eligible.add(mokepon);
            }
        }

        Random rand = new Random();
        int randomMokeponSelector = rand.nextInt(eligible.size());

        Mokepon randomMokeponTemp = eligible.get(randomMokeponSelector);
        Mokepon randomMokepon = new Mokepon();
        randomMokeponTemp.copy(randomMokepon);

        randomMokepon.setOverallMultiplier(tile.returnModier(randomMokepon.getMokeponType()));
        return randomMokepon;
    }
}
