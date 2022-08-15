/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.CartDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginCustomerController", urlPatterns = {"/LoginCustomer"})
public class LoginCustomerController extends HttpServlet {

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
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
//        response.getWriter().print(pass);
//        response.getWriter().print(email);
        UserDBContext userDB = new UserDBContext();
        User customer = userDB.loginCustomer(email, pass);

        if (customer.getRole().getSettingID() == 1) {
            response.sendRedirect("Homepage");
            if (customer != null) {
                CartDBContext cartDB = new CartDBContext();
                Cart cart = cartDB.getCartUser(customer.getUserID());
                if (cart == null) {
                    boolean addNewCart = cartDB.InsertCart(customer.getUserID());
                    cart = cartDB.getCartUser(customer.getUserID());
                }
                int numberInCart = cartDB.getNumberCartDetail(cart.getCartId());
                request.getSession().setAttribute("cart", cart);
                request.getSession().setAttribute("numberCart", numberInCart);
                request.getSession().setAttribute("customer", customer);
                request.getSession().setAttribute("mess", null);

            }

        } else if (customer.getRole().getSettingID() == 2) {
            request.getSession().setAttribute("user", customer);
            response.sendRedirect(request.getContextPath() + "/dashboardmarketing");
        }else if (customer.getRole().getSettingID() == 3) {
            request.getSession().setAttribute("user", customer);
            response.sendRedirect(request.getContextPath() + "/SaleDashboard");
        }else if (customer.getRole().getSettingID() == 6) {
            request.getSession().setAttribute("user", customer);
            response.sendRedirect(request.getContextPath() + "/Dashboard");
        } else {
            response.sendRedirect("Homepage");
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