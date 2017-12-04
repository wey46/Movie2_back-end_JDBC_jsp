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
public class MovieDB {
    private static HashMap<String, Movie> ourMovies;
    private static Connection conn = DBManager.getInstance().getConnection();
    
    private static void initialize() throws SQLException {
        if (ourMovies == null) {
            ourMovies = new HashMap<String,Movie>();
            loadMovies();
        }
    }
    
    private static void loadMovies() throws SQLException{
        String sql = "SELECT MovieId,Name FROM Movies";
	ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try (
            //Connection conn = DBManager.getInstance().getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql)
	){
	    rs = stmt.executeQuery();
	    while (rs.next()) {
		Movie movie = new Movie(rs.getString("MovieId"),rs.getString("Name"));
		movies.add(movie);
	    }
            for (Movie m : movies) {
                ourMovies.put(m.getID(), m);
            }
	} catch (SQLException e) {
	    //System.err.println(e);
            e.printStackTrace();
	} finally {
	    if (rs != null) {
	        rs.close();
	    }
	}
        //DBManager.getInstance().close();
    }
    
    public static Movie getMovie(String id) throws SQLException {
        initialize();
        return ourMovies.get(id);
    }
    
    public static boolean containsID(String id) throws SQLException {
        initialize();
        return ourMovies.containsKey(id);
    }
    
    public static String getTitle(String id) throws SQLException {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static int size() {
        return ourMovies.size();
    }
    
    /*public static int getYear(String id) throws SQLException {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) throws SQLException {
        initialize();
        return ourMovies.get(id).getGenres();
    }
    
    public static String getPoster(String id) throws SQLException {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id) throws SQLException {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) throws SQLException {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) throws SQLException {
        initialize();
        return ourMovies.get(id).getDirector();
    }*/

    

    public static ArrayList<String> filterBy(Filter f) throws SQLException {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }
    
}

