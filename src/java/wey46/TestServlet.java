/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wey46;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WY
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //MovieDB.loadMovies();
    	//RaterDB.loadRatings();
    	FourthRatings fr = new FourthRatings();
    	//System.out.println("Movies: " + MovieDB.size() + "\t" + "Ratings: " + RaterDB.size());
        //ArrayList<Rating> list = fr.getSimilarRatings("10", 10, 1);
        //getSimilarRatings(String raterid, int numOfSimilar, int minRaters)
        
        ArrayList<Rating> list = fr.getSimilarRatings(request.getParameter("UserId"), 10, 1);
        System.out.println("Movies: " + MovieDB.size() + "\t" + "Ratings: " + RaterDB.size());
        /*for(int i=0;i<20;i++){
            Rating r = list.get(i);
        	    System.out.println(r.getValue() + "\t" + MovieDB.getTitle(r.getItem()));
        }*/
        System.out.println("Found "+list.size()+" movies");
        //int recNumber = request.getParameter("recNumber");
        //ArrayList<Movie> recMovies = Rec.getRec(list,recNumber);
        ArrayList<Movie> recMovies = Rec.getRec(list,20);
        response.setContentType("text/xml;charset=UTF-8"); 
        PrintWriter out = response.getWriter();
        out.println("<RecMovieInfo>");
        for(int i=0;i<recMovies.size();i++){
            out.println("<movie>");
            out.println("<movieid>"+recMovies.get(i).getID()+"</movieid>");
            out.println("<name>"+recMovies.get(i).getTitle()+"</name>");
            out.println("<posterh>"+recMovies.get(i).getPoster()[0]+"</posterh>");
            out.println("<posterv>"+recMovies.get(i).getPoster()[1]+"</posterv>");
            out.println("</movie>");
        }
        out.println("</RecMovieInfo>");
        DBManager.getInstance().close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
