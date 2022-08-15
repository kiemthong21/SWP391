/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.publicc;

import Ultity.SendMail;
import dal.CartDBContext;
import dal.OrderDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cart;
import model.CartDetail;
import model.Product;
import model.Receiver;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartCompletion", urlPatterns = {"/CartCompletion"})
public class CartCompletion extends HttpServlet {

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
        double total = Double.parseDouble(request.getParameter("total"));
        total = (double) Math.round(total * 100) / 100;
        CartDBContext cartDB = new CartDBContext();
        ProductDBContext productDB = new ProductDBContext();
        OrderDBContext orderDB = new OrderDBContext();

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Receiver rc = (Receiver) request.getSession().getAttribute("receiver");
        User user = (User) request.getSession().getAttribute("customer");

        if (rc == null) {
            rc = new Receiver();
            rc.setName(user.getName());
            rc.setAddress(user.getAddress());
            rc.setPhone(user.getPhone());
            rc.setNote(null);
        }

        ArrayList<CartDetail> listProductInCart = cartDB.getAllProductInCart(cart.getCartId());
        ArrayList<Product> listProductNotEnought = new ArrayList<>();
        for (CartDetail productsInCart : listProductInCart) {
            Product product = productDB.getProductDetail(productsInCart.getProduct().getProductID());
            if (product.getQuantity() <= productsInCart.getQuantity()) {
                listProductNotEnought.add(product);
            }
        }
        request.setAttribute("listProductNotEnought", listProductNotEnought);
        if (listProductNotEnought.isEmpty()) {
            Boolean submitCart = orderDB.submitCartContact(cart.getCartId(), rc, user.getUserID());
            response.getWriter().println(submitCart);

            if (submitCart == true) {
                for (CartDetail productsInCart : listProductInCart) {
                    Product product = productDB.getProductDetail(productsInCart.getProduct().getProductID());
                    Boolean updateQuantity = productDB.updateQuantityAvailable(product.getQuantity()
                            - productsInCart.getQuantity(), product.getProductID());
                }
                int deleteAllProductInCart = cartDB.deleteAllProductInCart(cart.getCartId());
                int numberInCart = cartDB.getNumberCartDetail(cart.getCartId());
                request.getSession().setAttribute("numberCart", numberInCart);
                SendMail send = new SendMail();
                String bankNumber = request.getServletContext().getInitParameter("bankNumber");
                String bankName = request.getServletContext().getInitParameter("bankName");
                String phone = request.getServletContext().getInitParameter("phoneNumber");
                String gmail = request.getServletContext().getInitParameter("gmail");
                send.mailConfirmCartCompletion(user.getEmail(), bankNumber, bankName, phone, gmail, user.getName(), total);
            } else {
                request.getRequestDispatcher("View/Public/Error.jsp").forward(request, response);
                return;
            }
        }
        request.getRequestDispatcher("View/Public/CartCompletion.jsp").forward(request, response);
        return;
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
        processRequest(request, response);
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
