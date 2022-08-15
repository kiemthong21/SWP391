/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.product;

import controller.authentication.BaseAuthController;
import dal.PostDBContext;
import dal.ProductDBContext;
import dal.SettingDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Vu Dai Luong
 */
@WebServlet(name = "ProductChangeStatusController", urlPatterns = {"/product/change-status"})
public class ProductChangeStatusController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Marketing");
        return numRead >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int status = Integer.parseInt(request.getParameter("status"));
        ProductDBContext productDB = new ProductDBContext();
        Product product = productDB.getproductbyID(id);
        SettingDBContext settingDB = new SettingDBContext();
        Setting setting = settingDB.getById(status);
        if (setting.getSettingID() == 11 || setting.getSettingID() ==12) {
            setting.setSettingID(13);
        }else{
            if (product.getQuantity()>0) {
                setting.setSettingID(12);
            }else{
                setting.setSettingID(11);
            }
        }
        product.setStatus(setting);
        productDB.update(product);
        response.sendRedirect(request.getHeader("referer"));
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
