/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.customers;

import controller.authentication.BaseAuthController;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.User;

/**
 *
 * @author Vu Dai Luong
 */
public class CustomerCreateController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Marketing");
        return numRead >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
        }
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String phone = request.getParameter("Phone");
        int statusParam = Integer.parseInt(request.getParameter("status"));
        if (name.equals(" ")) {
            request.setAttribute("error", "you must enter name!");
        }
        if (email.equals(" ")){
           request.setAttribute("error", "you must enter Email!");
        }
        if (phone.equals(" ")){
        request.setAttribute("error", "you must enter phone!");
        }
        
        UserDBContext userDB = new UserDBContext();
        Setting settingstatus = new Setting();
        settingstatus.setSettingID(statusParam);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setStatus(settingstatus);
     
        userDB.insert(user);
        response.sendRedirect("http://localhost:8080/Online_Shop_SWP391_TuanVM/customers");
        

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
