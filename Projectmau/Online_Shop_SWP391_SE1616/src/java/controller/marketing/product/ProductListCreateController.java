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
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Product;
import model.Setting;
import model.User;
import utils.FileUtility;

/**
 *
 * @author Vu Dai Luong
 */
@WebServlet(name = "ProductListCreateController", urlPatterns = {"/create"})
@MultipartConfig
public class ProductListCreateController extends BaseAuthController {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductListCreateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductListCreateController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDBContext settingDB = new SettingDBContext();
        request.setAttribute("categories", settingDB.getByTypes("Product Category"));
        request.setAttribute("status", settingDB.getByTypes("Product Status"));
        request.getRequestDispatcher("/View/marketing/productCreate.jsp").forward(request, response);
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

        try {
            String title = request.getParameter("title");
            String categoryParam = request.getParameter("category");
            String statusParam = request.getParameter("status");
            String summary = request.getParameter("summary");
            String priceParam = request.getParameter("price");
            String discountParam = request.getParameter("discount");
            String quantityParam = request.getParameter("quantity");
            ProductDBContext DB = new ProductDBContext();
            Product p = new Product();
            p.setTitle(title);
            p.setSummary(summary);
            
            int idStatus = 0;
            try {
                idStatus = Integer.parseInt(statusParam);
            } catch (Exception e) {
                throw new Exception("Error set field status");
            }
            int idCategory = 0;
            try {
                idCategory = Integer.parseInt(categoryParam);
            } catch (Exception e) {
                throw new Exception("Error set field category");
            }
            double price = 0;
            try {
                price = Double.parseDouble(priceParam);
            } catch (Exception e) {
                throw new Exception("Error set field price");
            }
            double discount = 0;
            try {
                discount = Double.parseDouble(discountParam);
            } catch (Exception e) {
                throw new Exception("Error set field discount");
            }
            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityParam);
            } catch (Exception e) {
                throw new Exception("Error set field quantity");
            }
            
            p.setPrice(price);
            p.setQuantity(quantity);
            p.setDiscount(discount);
            
            Setting status = new Setting();
            status.setSettingID(idStatus);

            Setting category = new Setting();
            category.setSettingID(idCategory);

            p.setStatus(status);
            p.setCategory(category);

            Part part = request.getPart("thumbnail");
            FileUtility fileUtility = new FileUtility();

            String folder = request.getServletContext().getRealPath("/View/Img");
            String filename = null;

            if (part.getSize() != 0) {
                if(p.getThumbnail()!=null && !p.getThumbnail().trim().isEmpty()){
                    fileUtility.delete(p.getThumbnail(), folder);
                }
                filename = fileUtility.upLoad(part, folder);
            } 

            if (filename != null) {
                p.setThumbnail(filename);
            }
            User user = new User();
            user.setUserID(1);
            p.setAuthor(user);
            p.setUpdateAt(new Date(System.currentTimeMillis()));
            DB.insert1(p);
            System.out.println(p.toString());
            response.sendRedirect(request.getContextPath() + "/ProductMarketing?service=list");
        } catch (Exception e) {
            SettingDBContext settingDB = new SettingDBContext();
            request.setAttribute("status", settingDB.getByTypes("Product Status"));
            request.setAttribute("categories", settingDB.getByTypes("Product Category"));
            request.setAttribute("error", e.getMessage());
            
            request.getRequestDispatcher("/View/marketing/productCreate.jsp").forward(request, response);
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