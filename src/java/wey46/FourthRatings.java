/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wey46;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author WY
 */
public class FourthRatings {
    
    //Giving a movieId, return its average rating
    //if a movie has no enough rater-ratings, it's avg rating is 0
    public double getAverageByID(String movieId, int minRating) throws SQLException{
	double totalRatings = 0.0;
	int totalRaters = 0;
	for (Rater rater : RaterDB.getRaters()){
	    if(rater.hasRating(movieId)){
		totalRaters ++;
		totalRatings += rater.getRating(movieId);
	    }
	}
	if (totalRaters<minRating){
	    return 0.0; //return 0.0 for not enough raters
	} else {
	    return totalRatings/totalRaters; //else return average
	}
    }
    
    private double getAverageByIDSelect(String Mvid,int minRt, List<Rating> list) throws SQLException {
	double totalRatings = 0.0;
	int totalRaters = 0;
	for (int i=0;i<list.size();i++){//loop through all raters in db,
	    Rater rtr = RaterDB.getRater(list.get(i).getItem());
	    if(rtr.hasRating(Mvid)){
		totalRaters ++;
		totalRatings += rtr.getRating(Mvid)*list.get(i).getValue();
            }
	}
	if (totalRaters<minRt){
	    return 0.0;
	} else {
	    return totalRatings/totalRaters;
	}	
    }

    // get AverageRatings for every movie in DB
    public ArrayList<Rating> getAverageRatings(int minRtr) throws SQLException{
	ArrayList<String> movies = MovieDB.filterBy(new TrueFilter());
        ArrayList<Rating> ans = new ArrayList<Rating>();
	for (String movieid : movies){
    	    double avgrating = getAverageByID(movieid,minRtr);
    	    if(avgrating != 0.0){
    	    	Rating newRt = new Rating(movieid,avgrating);
    		ans.add(newRt);
    	    }
        }
	return ans;
    }   

    //get AverageRatings with applied filter(s)
    public ArrayList<Rating> getAverageRatingsByFilter(int minRtr, Filter f) throws SQLException{
	ArrayList<String> movies = MovieDB.filterBy(f);
	ArrayList<Rating> ans = new ArrayList<Rating>();
	for (String movieid : movies){
    	    double avgrating = getAverageByID(movieid,minRtr);
    	    if(avgrating != 0.0){
    	    Rating newRt = new Rating(movieid,avgrating);
    	    ans.add(newRt);
    	    }
        }
	return ans;
    }
    
    //calculate similarity of two raters
    private double dotProduct(Rater me, Rater r){
    	double ans = 0;
    	for(String movieid : me.getItemsRated()){
    	    if(r.hasRating(movieid)){
    	        double dotp = (me.getRating(movieid)-5)*(r.getRating(movieid)-5);
    	        ans += dotp;
    	    }
    	}
    	return ans;
    }
    
    
    //Rating<Item,Rating> --> return Rating <raterId, similarity> in desc order
    private ArrayList<Rating> getSimilarities(String uid) throws SQLException{
    	Rater me = RaterDB.getRater(uid);
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	for(Rater rtr:RaterDB.getRaters()){
    	    if(me==rtr) {//do nothing 
            } else{
    		if(dotProduct(me,rtr)>0){
        		    Rating rt = new Rating(rtr.getID(),dotProduct(me,rtr));
        		    list.add(rt);
        		}//<0 do nothing
    	    }	
    	}
    	Collections.sort(list,Collections.reverseOrder());
    	return list;	
    }
    
    //Rating<item,rating> --> <movieId,weightedRating>
    //numofSimilar is top n similar users
    public ArrayList<Rating> getSimilarRatings(String raterid, int numOfSimilar, int minRaters) throws SQLException{
    	ArrayList<Rating> ans = new ArrayList<Rating>();
    	List<Rating> topSimilar = getSimilarities(raterid).subList(0, numOfSimilar);
    	ArrayList<String> movies = MovieDB.filterBy(new TrueFilter());
    	for(String movieid:movies){
    	    //multiply their similarity rating by the rating they gave that movie
    	    double weightedRating = getAverageByIDSelect(movieid,minRaters,topSimilar);
            Rating rt = new Rating(movieid,weightedRating);
    	    ans.add(rt);
    	}
    	Collections.sort(ans,Collections.reverseOrder());
    	return ans;
    }
    
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterid, int numOfSimilar, int minRaters,Filter f) throws SQLException{
    	ArrayList<Rating> ans = new ArrayList<Rating>();
    	List<Rating> topSimilar = getSimilarities(raterid).subList(0, numOfSimilar);
    	ArrayList<String> movies = MovieDB.filterBy(f);
    	for(String movieid:movies){
    	    double weightedRating = getAverageByIDSelect(movieid,minRaters,topSimilar);
    	    Rating rt = new Rating(movieid,weightedRating);
    	    ans.add(rt);
    	}
    	Collections.sort(ans,Collections.reverseOrder());
    	return ans;
    }
    
}
