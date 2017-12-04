/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wey46;

/**
 *
 * @author WY
 */
public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    public Rating (String aMovieId, double aRating) {
        item = aMovieId;
        value = aRating;
    }

    // Returns item being rated
    public String getItem () {
        return item;
    }

    // Returns the value of this rating (as a number so it can be used in calculations)
    public double getValue () {
        return value;
    }

    // Returns a string of all the rating information
    @Override
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    @Override
    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        
        return 0;
    }
}
