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
public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String posterv;
    private String posterh;
    private int minutes;

    public Movie (String MovieId, String Name) {
        //testCase, only id and name
        id = MovieId.trim();
        title = Name.trim();
    }
    
    public Movie (String MovieId, String Name, String urlh, String urlv) {
        //testCase, only id and name
        id = MovieId.trim();
        title = Name.trim();
        posterh = urlh;
        posterv = urlv;
    }

    public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector,
    String aCountry, String Posterv, String Posterh, int theMinutes) {
        // ideal movie database
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        posterv = Posterv;
        posterh = Posterh;
        minutes = theMinutes;
    }

    // Returns ID associated with this item
    public String getID () {
        return id;
    }

    // Returns title of this item
    public String getTitle () {
        return title;
    }

    // Returns year in which this item was published
    public int getYear () {
        return year;
    }

    // Returns genres associated with this item
    public String getGenres () {
        return genres;
    }

    public String getCountry(){
        return country;
    }

    public String getDirector(){
        return director;
    }

    public String[] getPoster(){
        return new String[]{posterh,posterv};
    }

    public int getMinutes(){
        return minutes;
    }

    // Returns a string of the item's information
    public String toString () {
        String result = "Movie [id=" + id + ", title=" + title + ", year=" + year;
        result += ", genres= " + genres + "]";
        return result;
    }
}
