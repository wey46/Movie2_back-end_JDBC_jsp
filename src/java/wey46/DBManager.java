/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wey46;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WY
 */
public class DBManager {
    private static DBManager instance = null;
    private final String USERNAME = "root";
    private final String PASSWORD = "0309";
    private final String CONN_STRING = "jdbc:mysql://127.0.0.1:3306/Web_proj";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn = null;
        
    private DBManager() {}
      
    public static DBManager getInstance() {
	if (instance == null) {
            System.out.println("null instance");
	    instance = new DBManager();
	}
	    return instance;
    }
        
    private boolean openConnection() {
	try {
            Class.forName(DRIVER);
	    conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
	    return true;
	} catch (SQLException e) {
	    System.err.println(e);
	    return false;
	} catch (ClassNotFoundException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Connection getConnection(){
	if (conn == null) {
	    if (openConnection()) {
		System.out.println("Connection opened");
		return conn;
	    } else {
                System.out.println(conn);
		return null;
	    }
	}
                    System.out.println("getConnection != null");
	    return conn;
    }

    public void close() {
	System.out.println("Closing connection");

        try {
	    conn.close();
	    conn = null;
	} catch (Exception e) {}
        	//instance = null;
    }
    
}
