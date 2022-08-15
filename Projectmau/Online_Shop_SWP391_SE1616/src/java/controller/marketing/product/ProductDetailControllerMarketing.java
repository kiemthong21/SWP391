/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.product;

import controller.authentication.BaseAuthController;
import dal.ProductDBContext;
import dal.SettingDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
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
public class ProductDetailControllerMarketing extends BaseAuthController {

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
        int id = Integer.parseInt(request.getParameter("id"));
        
        ProductDBContext productDB = new ProductDBContext();
        ArrayList<Setting> CATE = productDB.getCategoryMarketing();
        Product product = productDB.getproductbyID(id);
        request.setAttribute("product", product);
        SettingDBContext settingDB = new SettingDBContext();
        request.setAttribute("CATE", CATE);
        request.setAttribute("status", settingDB.getByTypes("Product status"));

//        request.setAttribute("categories", settingDB.getByTypes("Post Category"));
        request.getRequestDispatcher("/View/marketing/productDetail.jsp").forward(request, response);
    }


    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
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
