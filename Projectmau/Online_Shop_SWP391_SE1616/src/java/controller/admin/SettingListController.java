/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.authentication.BaseAuthController;
import dal.SettingDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class SettingListController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Admin");
        return numRead >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SettingListController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDBContext db = new SettingDBContext();
        ArrayList<Setting> settings = db.getSetting();
        
        request.setAttribute("settings", settings);
        request.getRequestDispatcher("/View/Admin/settingList.jsp").forward(request, response);
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
        String type = request.getParameter("group");
        String raw_order = request.getParameter("order");
        String name = request.getParameter("name");
        boolean status = request.getParameter("status").equals("7");
        SettingDBContext db = new SettingDBContext();
        if (type == null || raw_order == null || name == null || type.isEmpty() || raw_order.isEmpty() || name.isEmpty()) {

        
            request.setAttribute("mess", "null");
        } else {
            int order = Integer.parseInt(raw_order);
            Setting s = new Setting();
            s.setGroup(type);
            s.setName(name);
            s.setOrder(order);
            s.setStatus(status);
            db.createSetting(s);

            //response.getWriter().print(s.getGroup() + s.getName() + s.getOrder() + s.isStatus());
            request.setAttribute("mess", "success");        
        }
             
        ArrayList<Setting> settings = db.getSetting();
        
        request.setAttribute("settings", settings);
        request.getRequestDispatcher("/View/Admin/settingList.jsp").forward(request, response);
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
