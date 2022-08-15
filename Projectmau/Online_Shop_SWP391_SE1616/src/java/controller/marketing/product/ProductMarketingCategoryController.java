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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Vu Dai Luong
 */
@WebServlet(name = "ProductMarketingCategoryController", urlPatterns = {"/ProductMarketingCategory"})
public class ProductMarketingCategoryController extends BaseAuthController {

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
            ProductDBContext PDB = new ProductDBContext();
            ArrayList<Setting> CATE = PDB.getCategoryMarketing();
            int cateId = Integer.parseInt(request.getParameter("cateID"));
            ArrayList<Product> productcategory = PDB.getProductByCategoryMarketing(cateId);
            request.setAttribute("CATE", CATE);
            request.setAttribute("ProductMarketing", productcategory);
            
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
