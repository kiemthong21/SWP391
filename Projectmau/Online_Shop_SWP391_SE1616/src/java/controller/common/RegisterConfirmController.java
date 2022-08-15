/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
public class RegisterConfirmController extends HttpServlet {

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
            throws ServletException, IOException {
        UserDBContext db = new UserDBContext();

        User u = (User) request.getSession().getAttribute("regUser");
        if (u == null) {
            request.setAttribute("mess", "<div class=\"alert alert-danger\" role=\"alert\">\n"
                    + "                            \n"
                    + "                            <h4 class=\"alert-heading\"><i class=\"bi bi-x-circle\"></i> FAILED!</h4>\n"
                    + "                            <p>Not found user! Make sure you are register on same web IDE or on session time! </p>\n"
                    + "                            <button type=\"button\" class=\"btn btn-outline-danger\"><a href=\"Homepage\" class=\"text-danger\">Try again!</a> </button>\n"
                    + "                        </div>");
            request.getRequestDispatcher("/View/component/regisersuccess.jsp").forward(request, response);

        } else {
            request.setAttribute("mess", "<div class=\"alert alert-success\" role=\"alert\">\n"
                    + "                            \n"
                    + "                            <h4 class=\"alert-heading\"><i class=\"bi bi-check-circle\"></i> SUCCESS!</h4>\n"
                    + "                            <p>Congratulations, you have successfully registered an account! </p>\n"
                    + "                            <button type=\"button\" class=\"btn btn-outline-success\"><a href=\"Homepage\" class=\"text-success\">Let's start shopping!</a> </button>\n"
                    + "                        </div> ");
            db.CreateUser(u);

            request.getRequestDispatcher("/View/component/regisersuccess.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
