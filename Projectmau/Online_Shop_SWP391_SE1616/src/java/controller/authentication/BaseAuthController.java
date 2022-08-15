/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author hieu2
 */
public abstract class BaseAuthController extends HttpServlet {

    protected abstract boolean isPermission(HttpServletRequest request);
    
    private boolean isAuth(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return false;
        } else {
            return isPermission(request);
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuth(request)) {
            //business
            processGet(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/Online_Shop_SWP391_TuanVM/View/accessdeny.jsp");
            

        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (isAuth(request)) {
            //business
            processPost(request, response);
        } else {
            response.sendRedirect("http://localhost:8080/Online_Shop_SWP391_TuanVM/View/accessdeny.jsp");
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