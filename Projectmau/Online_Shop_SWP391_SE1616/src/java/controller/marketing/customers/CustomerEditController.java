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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CustomerEditController extends BaseAuthController {

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
        String id = request.getParameter("id");
        UserDBContext userDB = new UserDBContext();
        User user = userDB.getUserByIdMarketing(id);
        ArrayList<Setting> status = userDB.getUserStatus();
        request.setAttribute("user", user);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/View/marketing/CustomersEdit.jsp").forward(request, response);
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String phone = request.getParameter("Phone");
        int statusParam = Integer.parseInt(request.getParameter("status"));
        int idStatus = 0;
            try {
                idStatus = statusParam;
                Setting status = new Setting();
            status.setSettingID(idStatus);
            } catch (Exception e) {
            try {
                throw new Exception("Error set field status");
                
            } catch (Exception ex) {
                request.setAttribute("error", ex.getMessage());;
            }
            }
        UserDBContext userDB = new UserDBContext();
        User user = new User();
        Setting settingstatus = new Setting();
        settingstatus.setSettingID(statusParam);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setGender(gender);
        user.setStatus(settingstatus);
        userDB.updateUser(user);
        
//        response.getWriter().print(id);
//        response.getWriter().print(gender);
//        response.getWriter().print(name);
//        response.getWriter().print(email);
//        response.getWriter().print(phone);
//        response.getWriter().print(statusParam);
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
