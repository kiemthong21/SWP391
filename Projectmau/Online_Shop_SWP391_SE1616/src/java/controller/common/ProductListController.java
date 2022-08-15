/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.Setting;

/**
 *
 * @author Admin
 */
public class ProductListController extends HttpServlet {

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

        ProductDBContext productDBContext = new ProductDBContext();
        String service = request.getParameter("service");
        ArrayList<Setting> cate = productDBContext.getCategory();
        ArrayList<Product> latestProduct = productDBContext.getLastestProduct();
        request.getSession().setAttribute("cate", cate);
        request.getSession().setAttribute("latestProduct", latestProduct);
        if (service != null) {
            if (service.equals("productList")) {
                ProductDBContext pdb = new ProductDBContext();
                ArrayList<Product> products = pdb.getAllProduct("0");
                int pageSize = 12;
                String page = request.getParameter("page");
                if (page == null || page.trim().length() == 0) {
                    page = "1";
                }
                ArrayList<Product> showProducts = new ArrayList();
                int currentPage = Integer.parseInt(page);
                for (int i = currentPage * 12 - 12; i < currentPage * 12; i++) {
                    if (i < products.size()) {
                        showProducts.add(products.get(i));
                    }
                }

                int count = pdb.getProductTotal();
                int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;

                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", currentPage);
                request.setAttribute("products", showProducts);
                request.getRequestDispatcher("/View/Public/ProductList.jsp").forward(request, response);
            }

            if (service.equals("search")) {
                request.getSession().setAttribute("alert", null);
                String keyword = request.getParameter("keyword");
                String cateId = request.getParameter("cateID");
                String order = request.getParameter("orderBy");
                String sort = request.getParameter("sortBy");
                String orderBy = null;
                if (keyword.isEmpty()) {
                    keyword = "";
                }
                if (cateId.isEmpty()) {
                    cateId = "";
                }
                if (sort.isEmpty()) {
                    sort = "c";
                }
                if (order.isEmpty()) {
                    order = "1";
                }
                orderBy = sort + order;
 //               response.getWriter().print(orderBy);
                ArrayList<Product> products = productDBContext.getAllProductByCondition(orderBy, keyword, cateId);
                int pageSize = 12;
                String page = request.getParameter("page");
                if (page == null || page.trim().length() == 0) {
                    page = "1";
                }
                ArrayList<Product> showProducts = new ArrayList();
                int currentPage = Integer.parseInt(page);
                for (int i = currentPage * 12 - 12; i < currentPage * 12; i++) {
                    if (i < products.size()) {
                        showProducts.add(products.get(i));
                    }
                }

                int count = productDBContext.getProductTotalByCondition(cateId, keyword);
                int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
                request.setAttribute("cateID", cateId);
                request.setAttribute("keyword", keyword);
                request.setAttribute("orderBy", order);
                request.setAttribute("sortBy", sort);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", currentPage);
                request.setAttribute("products", showProducts);
                request.getRequestDispatcher("/View/Public/ProductList.jsp").forward(request, response);
                return;

            }
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
