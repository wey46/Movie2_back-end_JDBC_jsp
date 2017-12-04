/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wey46;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author WY
 */
public class RaterDB {
    private static HashMap<String,Rater> ourRaters;//raterId, rater
    private static Connection conn = DBManager.getInstance().getConnection();
    
    private static void initialize() throws SQLException {
	    // this method is only called from addRatings 
		if (ourRaters == null) {
		    ourRaters = new HashMap<String,Rater>();
                    loadRatings();
		}
	}
    
    public static Rater getRater(String RaterId) throws SQLException {
    	initialize();
    	return ourRaters.get(RaterId);
        /*String sql = "SELECT * FROM admin WHERE RaterId = ?";
	ResultSet rs = null;
	try (
	    PreparedStatement stmt = conn.prepareStatement(sql);
	){
	    stmt.setString(1, RaterId);
	    rs = stmt.executeQuery();
	    if (rs.next()) {
		Rater rater = new Rater(RaterId);
		rater.addRating(rs.getString("MovieId"), rs.getDouble("Rating"));
		return rater;
	    } else {
		return null;
	    }

	} catch (SQLException e) {
	    System.err.println(e);
	    return null;
	} finally {
	    if (rs != null) {
	        rs.close();
	    }
	}*/
    }

    private static void loadRatings() throws SQLException {
	String sql = "SELECT * FROM Ratings";
	ResultSet rs = null;
	try (
            //Connection conn = DBManager.getInstance().getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql)
	){
	    rs = stmt.executeQuery();
	    while (rs.next()) {
		String RaterId = rs.getString("RaterId");
		String MovieId = rs.getString("MovieId");
		double Rating = rs.getDouble("Rating");
		addRaterRating(RaterId,MovieId,Rating);
            }
	} catch (SQLException e) {
	    System.err.println(e);
	} finally {
	    if (rs != null) {
	    rs.close();
	    }
	}

    }
    
    public static void addRaterRating(String raterID, String movieID, double rating) throws SQLException {
        initialize(); 
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID); 
        } else { 
            rater = new Rater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    }
    
    public static ArrayList<Rater> getRaters() throws SQLException {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());   	
    	return list;
    }
    
    public static int size() {
	    return ourRaters.size();
    }
    
}
