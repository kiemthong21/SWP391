/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.customers;

import controller.authentication.BaseAuthController;
import dal.SettingDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.User;

/**
 *
 * @author Vu Dai Luong
 */
@WebServlet(name = "CustomerChangeStatusController", urlPatterns = {"/customer/change-status"})
public class CustomerChangeStatusController extends BaseAuthController {

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
        int status = Integer.parseInt(request.getParameter("status"));
        UserDBContext userDB = new UserDBContext();
        User user = userDB.getUserByIdMarketing(id);
        SettingDBContext settingDB = new SettingDBContext();
        Setting setting = settingDB.getById(status);
        user.setStatus(setting);
        userDB.updateUser(user);
        
//        response.getWriter().print(userDB.getUserByIdMarketing(1));
        response.sendRedirect(request.getHeader("referer"));
    }


    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
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
