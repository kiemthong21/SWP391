/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;


import dal.MD5;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
public class ChangePassController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        UserDBContext userDB = new UserDBContext();
//        User user = userDB.getUserById(1);
//        request.getSession().setAttribute("user", user);
        User user = (User) request.getSession().getAttribute("customer");
        if(user==null){
            response.sendRedirect("./Homepage");
            return;
        }
        request.getRequestDispatcher("./View/Public/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MD5 encode = new MD5();
            String oldPass = request.getParameter("password");
            String newPass = request.getParameter("new-password");
            String confirm = request.getParameter("confirm-password");
            UserDBContext userDB = new UserDBContext();
            User user = (User) request.getSession().getAttribute("customer");
            user = userDB.getUserById(user.getUserID());
            String md5 = encode.getMd5(oldPass);
            String newPassMD5 = encode.getMd5(newPass);
            response.getWriter().print(user.getPassword() + oldPass);
            if (!md5.equals(user.getPassword())) {
                request.setAttribute("error", "Old pass wrong!");
                request.getRequestDispatcher("./View/Public/changePassError.jsp").forward(request, response);
                return;
            }

            if (md5.equals(newPassMD5)) {
                request.setAttribute("error", "New pass same old pass!");
                request.getRequestDispatcher("./View/Public/changePassError.jsp").forward(request, response);
                return;
            }
            if (!newPass.equals(confirm)) {
                request.setAttribute("error", "Password not match!");
                request.getRequestDispatcher("./View/Public/changePassError.jsp").forward(request, response);
                return;
            }
            user.setPassword(newPass);
            userDB.changePassword(user);
            response.sendRedirect("./Homepage");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("./View/Public/HomePage.jsp").forward(request, response);
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
