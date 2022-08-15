/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.publicc;

import dal.CartDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ActionCartController", urlPatterns = {"/ActionCart"})
public class ActionCartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @thrws ServletException if a servlet-specific error occurs
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
            out.println("<title>Servlet DeleteCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteCartController at " + request.getContextPath() + "</h1>");
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
        int pid = Integer.parseInt(request.getParameter("pid"));
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String action = request.getParameter("action");
        CartDBContext cartDB = new CartDBContext();
        if (action.equals("delete")) {
            boolean isDelete = cartDB.deleteCart(pid, cart.getCartId());
            int numberInCart = cartDB.getNumberCartDetail(cart.getCartId());
            request.getSession().setAttribute("numberCart", numberInCart);
            response.sendRedirect("Cart?action=detail");
        }
        if (action.equals("updateQuantity")) {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (quantity <= 0) {
                boolean isUpdateQuantity = cartDB.updateQuantityToCart(pid, 1, cart.getCartId());
            } else {
                boolean isUpdateQuantity = cartDB.updateQuantityToCart(pid, quantity, cart.getCartId());
            }
            int numberInCart = cartDB.getNumberCartDetail(cart.getCartId());
            request.getSession().setAttribute("numberCart", numberInCart);
            response.sendRedirect("Cart?action=detail");
        }
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
