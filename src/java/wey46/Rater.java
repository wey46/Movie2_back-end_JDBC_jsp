/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wey46;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author WY
 */
public class Rater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public Rater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
    	myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        Rating r = myRatings.get(item);
        return r.getValue();
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<>(myRatings.keySet());
    }
}
