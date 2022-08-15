/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.CartDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartDetail;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCart"})
public class AddToCartController extends HttpServlet {

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
            out.println("<title>Servlet AddToCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddToCartController at " + request.getContextPath() + "</h1>");
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
        int proid = Integer.parseInt(request.getParameter("pid"));
        User customer = (User) request.getSession().getAttribute("customer");

        if (customer != null) {
            CartDBContext cartDB = new CartDBContext();
            Cart cart = (Cart) request.getSession().getAttribute("cart");
//            if(cart == null){
//            boolean addNewCart = cartDB.InsertCart(customer.getUserID());
//            cart = (Cart) cartDB.getCartUser(customer.getUserID());
//            }           
            CartDetail cD = (CartDetail) cartDB.getProductInCart(proid, cart.getCartId());
            if (cD != null) {
                boolean updateQuantity = cartDB.updateQuantityToCart(cD.getCartDetailID(), cD.getQuantity());
            } else {
                boolean addNewProduct = cartDB.addNewProductToCart(cart.getCartId(), proid);
            }
            int numberInCart = cartDB.getNumberCartDetail(cart.getCartId());
            request.getSession().setAttribute("numberCart", numberInCart);
            request.getSession().setAttribute("alert", "Add To Cart Successfull");
            response.sendRedirect("ProductList?service=productList");
        } else {
            request.getSession().setAttribute("mess", "Currently, you are not logged in");
            response.sendRedirect("Homepage");
        }
    }

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
