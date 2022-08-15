/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.PostDBContext;
import dal.ProductDBContext;
import dal.SliderDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import model.Product;
import model.Setting;
import model.Slider;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HomepageController", urlPatterns = {"/Homepage"})
public class HomepageController extends HttpServlet {

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
            out.println("<title>Servlet HomepageController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomepageController at " + request.getContextPath() + "</h1>");
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
      //Slider
        SliderDBContext sdb = new SliderDBContext();
        ArrayList<Slider> sliders = sdb.getSliders();
        
        //Post
        PostDBContext podb = new PostDBContext();
        ArrayList<Post> posts = podb.getHotPost();
        ArrayList<Post> lastestPosts = podb.getLastestPost();
        
        //Product
        ProductDBContext pdb = new ProductDBContext();
        
        int pageSize = 12;
        String page = request.getParameter("page");
        //Set origin page is 1
        if(page ==null || page.trim().length() ==0)
        {
            page = "1";
        }
        int currentPage = Integer.parseInt(page);     
        ArrayList<Product> products = pdb.getFeatureProduct();
         ArrayList<Product> catThumbnail = pdb.getCategoryThumbnail();
        ArrayList<Product> showProducts = new ArrayList();
        
        //Select 12product/time to show on page
        for (int i = currentPage*12-12; i < currentPage*12; i++){
            if (i < products.size()){
                showProducts.add(products.get(i));
            }
        }
        
        int count = pdb.getProductTotal();
        int totalPage = (count%pageSize==0)?(count/pageSize):(count/pageSize)+1;
        
        ArrayList<Setting> st = pdb.getCategory();
        
        ArrayList<Setting> cate = pdb.getCategory();
        ArrayList<Product> latestProduct = pdb.getLastestProduct();
        request.getSession().setAttribute("cate", cate);
        request.getSession().setAttribute("latestProduct", latestProduct);
        request.setAttribute("sliders", sliders);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("page", currentPage);
        request.getSession().setAttribute("st", st);
        request.setAttribute("products", showProducts);
        request.setAttribute("posts", posts);
        request.setAttribute("lposts", lastestPosts);      
        request.setAttribute("catThumbnail", catThumbnail);
        request.getRequestDispatcher("/View/Public/HomePage.jsp").forward(request, response);
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
