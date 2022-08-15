/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.product;

import controller.authentication.BaseAuthController;
import dal.ProductDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.User;

/**
 *
 * @author Vu Dai Luong
 */
@WebServlet(name = "UpdateDetailController", urlPatterns = {"/updatedetail"})
public class UpdateDetailController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Marketing");
        return numRead >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int id = Integer.parseInt(request.getParameter("sid"));
            ProductDBContext db = new ProductDBContext();
            Product product = db.getproductbyID(id);
            request.getRequestDispatcher("/View/marketing/ProductList_Marketing.jsp").forward(request, response);
        }
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Id = Integer.parseInt(request.getParameter("ID"));
        String title = request.getParameter("title");
        int category = Integer.parseInt(request.getParameter("category"));
        int statusParam = Integer.parseInt(request.getParameter("status"));
        String summary = request.getParameter("summary");
        double price = Double.parseDouble(request.getParameter("price"));
        double discount = Double.parseDouble(request.getParameter("discount"));
        int Quantity = Integer.parseInt(request.getParameter("quantity"));
        String thumbnaill = request.getParameter("thumbnaill");
        ProductDBContext dao = new ProductDBContext();
        dao.updateproduct(Id, title, summary, category, price, discount, Quantity, statusParam, thumbnaill);
        response.sendRedirect("http://localhost:8080/Online_Shop_SWP391_TuanVM/ProductDetailMarketing");
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
