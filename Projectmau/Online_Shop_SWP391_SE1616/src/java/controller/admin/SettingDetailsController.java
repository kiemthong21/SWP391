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
public class SettingDetailsController extends BaseAuthController {

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
            out.println("<title>Servlet SettingDetailsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingDetailsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sid = Integer.parseInt(request.getParameter("sid"));
        String mode = request.getParameter("mode");

        SettingDBContext db = new SettingDBContext();
        Setting setting = db.getSettingDetail(sid);

        request.setAttribute("setting", setting);
        request.setAttribute("mode", mode);
        request.getRequestDispatcher("/View/Admin/settingDetails.jsp").forward(request, response);
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
        int sid = Integer.parseInt(request.getParameter("sid"));
        boolean status = request.getParameter("status").equals("7");
        
        SettingDBContext db = new SettingDBContext();
        
        if (type == null || raw_order == null || name == null || type.isEmpty() || raw_order.isEmpty() || name.isEmpty()) {
            Setting setting = db.getSettingDetail(sid);
        
            request.setAttribute("setting", setting);
            request.setAttribute("mess", "null");
            request.setAttribute("mode", "edit");
            request.getRequestDispatcher("/View/Admin/settingDetails.jsp").forward(request, response);
        } else {
            int order = Integer.parseInt(raw_order);
            Setting s = new Setting();
            s.setGroup(type);
            s.setName(name);
            s.setOrder(order);
            s.setStatus(status);
            db.UpdateSetting(s, sid);
            Setting setting = db.getSettingDetail(sid);
        
            request.setAttribute("setting", setting);
            //response.getWriter().print(s.getGroup() + s.getName() + s.getOrder() + s.isStatus());
            request.setAttribute("mess", "success");
            request.setAttribute("mode", "view");
            request.getRequestDispatcher("/View/Admin/settingDetails.jsp").forward(request, response);
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
