/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.publicc;

import dal.CartDBContext;
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
import model.Setting;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/Cart"})
public class CartController extends HttpServlet {

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
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        String action = request.getParameter("action");
        //sider
        ProductDBContext productDBContext = new ProductDBContext();
        ArrayList<Setting> cate = productDBContext.getCategory();
        ArrayList<Product> latestProduct = productDBContext.getLastestProduct();
        //sider atribute
        request.getSession().setAttribute("cate", cate);
        request.getSession().setAttribute("latestProduct", latestProduct);
        //list car
        CartDBContext cartDBContext = new CartDBContext();
        ArrayList<CartDetail> cd = cartDBContext.getAllProductInCart(cart.getCartId());
        request.setAttribute("listCartDetail", cd);
        if (action.equals("detail")) {
            request.getRequestDispatcher("View/Public/CartDetail.jsp").forward(request, response);
        }
        if (action.equals("contact")) {
            double amount = 0;
            for (CartDetail cartDetail : cd) {
                amount = amount + cartDetail.getQuantity()*(cartDetail.getProduct().getPrice()*((100-cartDetail.getProduct().getDiscount())/100));
            }
            request.setAttribute("amount", amount);
            request.getRequestDispatcher("View/Public/CartContact.jsp").forward(request, response);
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
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //sider
        ProductDBContext productDBContext = new ProductDBContext();
        ArrayList<Setting> cate = productDBContext.getCategory();
        ArrayList<Product> latestProduct = productDBContext.getLastestProduct();
        //sider atribute
        request.getSession().setAttribute("cate", cate);
        request.getSession().setAttribute("latestProduct", latestProduct);
        //list cart
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        Receiver receiver = new Receiver(name, phone, address, note);
        CartDBContext cartDBContext = new CartDBContext();
        ArrayList<CartDetail> cd = cartDBContext.getAllProductInCart(cart.getCartId());
        request.setAttribute("listCartDetail", cd);
        request.getSession().setAttribute("receiver", receiver);
        request.getRequestDispatcher("View/Public/CartContact.jsp").forward(request, response);
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
