/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Vu Dai Luong
 */
@WebServlet(name = "ProfileCustomerController", urlPatterns = {"/ProfileCustomer"})
public class ProfileCustomerController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileCustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileCustomerController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        int id = Integer.parseInt(request.getParameter("sid"));
        String imagine = request.getParameter("imagine");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Date dob = Date.valueOf(request.getParameter("dob"));

        UserDBContext usd = new UserDBContext();
        usd.updateprofile(name, phone, gender, address, dob, imagine, id, email);
//        User customer = (User) request.getSession().getAttribute("customer");
        //User customer = usd.getUserByEmail(email);
        //response.getWriter().println(customer.getEmail());
//       response.getWriter().println(id);
//       response.getWriter().println(imagine);
//       response.getWriter().println(gender);
//       response.getWriter().println(name);
//       response.getWriter().println(email);
//       response.getWriter().println(phone);
//       response.getWriter().println(address);
//       response.getWriter().println(dob);
        User nCustomer = usd.getUserById1(id);
        request.getSession().setAttribute("customer", nCustomer);
        response.sendRedirect("Homepage");

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
