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
import javax.servlet.http.HttpSession;

/**
 *
 * @author WY
 */
@WebServlet(name = "recPageRec", urlPatterns = {"/recPageRec"})
public class recPageRec extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String uid = (String) session.getAttribute("uid");
        ArrayList<Rating> list;
        boolean openedDB = false;
        if (session.getAttribute("simlist") == null) {
            FourthRatings fr = new FourthRatings();
            list = fr.getSimilarRatings(uid, 10, 1);
            openedDB = true;
        } else {
            list = (ArrayList<Rating>) session.getAttribute("simlist");
        }
        ArrayList<Movie> recMovies = Rec.getRec(list,28);
        response.setContentType("text/xml;charset=UTF-8"); 
        PrintWriter out = response.getWriter();
        out.println("<RecMovieInfo>");
        for(int i=0;i<recMovies.size();i++){
            out.print("<movie>");
            out.print("<movieid>"+recMovies.get(i).getID()+"</movieid>");
            out.print("<title>"+recMovies.get(i).getTitle()+"</title>");
            out.print("<year>"+recMovies.get(i).getReleaseDate()+"</year>");
            out.print("<posterh>"+recMovies.get(i).getPoster()[0]+"</posterh>");
            out.print("<posterv>"+recMovies.get(i).getPoster()[1]+"</posterv>");
            out.print("<duration>"+recMovies.get(i).getMinutes()+"</duration>");
            out.print("<overview>"+recMovies.get(i).getOverview()+"</overview>");
            out.print("</movie>");
        }
        out.println("</RecMovieInfo>");
        if(openedDB){
            DBManager.getInstance().close();  
        }
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
            Logger.getLogger(recPageRec.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(recPageRec.class.getName()).log(Level.SEVERE, null, ex);
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
