/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResetPasswordConfirmCotroller", urlPatterns = {"/reset/confirm/*"})
public class ResetPasswordConfirmCotroller extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> pathList = null;
        String contextPath = request.getContextPath();
        String URI = request.getRequestURI().trim().replaceFirst(contextPath, "");
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            pathList = new ArrayList<>();
            String[] paths = pathInfo.split("/");
            for (String path : paths) {
                if (!path.trim().isEmpty()) {
                    pathList.add(path);
                }
            }
        }
        try {
            if (URI.matches("^/reset/confirm/[0-9]+/[a-zA-Z0-9!@#$%^&*()]+$")) {
                int id = Integer.parseInt(pathList.get(0));
                UserDBContext userDB = new UserDBContext();
                User user = userDB.getUserById(id);
                if (user == null) {
                    response.getWriter().println("User not found!");
                    return;
                }
                request.setAttribute("user", user);
                request.setAttribute("password", pathList.get(1));
                request.getRequestDispatcher("/View/Public/ConfimResetPassword.jsp").forward(request, response);
            } else {
                response.getWriter().println("Not found url");
            }
        } catch (Exception e) {
            response.getWriter().println("Not found url");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<String> pathList = null;
        String contextPath = request.getContextPath();
        String URI = request.getRequestURI().trim().replaceFirst(contextPath, "");
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && !pathInfo.isEmpty()) {
            pathList = new ArrayList<>();
            String[] paths = pathInfo.split("/");
            for (String path : paths) {
                if (!path.trim().isEmpty()) {
                    pathList.add(path);
                }
            }
        }
        try {
            System.out.println(URI);
            if (URI.matches("^/reset/confirm/[0-9]+/[a-zA-Z0-9!@#$%^&*()]+$")) {
                int id = Integer.parseInt(pathList.get(0));
                UserDBContext userDB = new UserDBContext();
                User user = userDB.getUserById(id);
                if (user == null) {
                    response.getWriter().println("User not found!");
                    return;
                }
                String newPass = request.getParameter("new-password");
                user.setPassword(newPass);
                userDB.changePassword(user);
                response.sendRedirect("../../../Homepage");
            } else {
                response.getWriter().println("Not found url");
            }
        } catch (Exception e) {
            response.getWriter().println("Not found url");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
