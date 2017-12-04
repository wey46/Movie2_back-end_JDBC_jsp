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

/**
 *
 * @author WY
 */
public class Rec {
    //private static Connection conn = DBManager.getInstance().getConnection();
    
    public static ArrayList<Movie> getRec(ArrayList<Rating> list, int howmany) throws SQLException{
        ArrayList<Movie> recMovie = new ArrayList<Movie>();
        String sql = "SELECT MovieId,Name,PosterUrlHori,PosterUrlVert FROM Movies WHERE MovieId = ?";
	ResultSet rs = null;
        try (
            Connection conn = DBManager.getInstance().getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql);
	){
            for(int i=0;i<howmany;i++){
	        String movieId = list.get(i).getItem();
                stmt.setString(1, movieId);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
		    Movie m = new Movie(movieId, rs.getString("Name"), rs.getString("PosterUrlHori"), rs.getString("PosterUrlVert"));
		    recMovie.add(m);
	        }
            }

	} catch (SQLException e) {
	    System.err.println(e);
	    return null;
	} finally {
	    if (rs != null) {
	        rs.close();
	    }
	}
        return  recMovie;
        
    }
}
