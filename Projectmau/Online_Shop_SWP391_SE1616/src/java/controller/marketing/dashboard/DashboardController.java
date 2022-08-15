/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.dashboard;


import controller.authentication.BaseAuthController;
import dal.DashBoardDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.OrderDetail;
import model.Post;
import model.Product;
import model.User;

/**
 *
 * @author Admin
 */

@WebServlet(name = "DashboardController", urlPatterns = {"/dashboardmarketing"})
public class DashboardController extends BaseAuthController {


    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Marketing");
        return numRead >= 1;
    }
    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DashBoardDBContext db = new DashBoardDBContext();
        String rawstartD = request.getParameter("startD");
        String rawendD = request.getParameter("endD");
        
        Date startD;
        Date endD;
        
        if(rawendD == null){
            endD = Date.valueOf(LocalDate.now());
            startD = Date.valueOf(LocalDate.now().minusDays(6));
        } else {
            endD = Date.valueOf(rawendD);
            startD = Date.valueOf(rawstartD);
        }
                
        
        int totalUser = db.getTotalNewUser(startD, endD);
        int totalStar = db.getTotalStar(startD, endD);
        ArrayList<Product> totalProducts = db.getTotalProductByCategory(startD, endD);
        int catTotal[] = new int[6];
        
        for (Product p : totalProducts) {
            switch (p.getCategory().getSettingID()) {
                case 18:
                    catTotal[0] ++;
                    break;
                case 19:
                    catTotal[1] ++;
                    break;
                case 20:
                    catTotal[2] ++;
                    break;
                case 21:
                    catTotal[3] ++;
                    break;
                case 22:
                    catTotal[4] ++;
                    break;
                case 23:
                    catTotal[5] ++;
                    break;
                default:
                    break;
            }
        }
        int max = Arrays.stream(catTotal).max().getAsInt();
        
        ArrayList<Post> totalPost = db.getPostDashboard(startD, endD);
        int salePost = 0;
        int minigamePost = 0;
        int reviewPost = 0;
        int discoverPost = 0;
        int systemPost = 0;
        
        for (Post o : totalPost) {
            switch (o.getPostCategory().getSettingID()) {
                case 24:
                    salePost++;
                    break;
                case 25:
                    minigamePost++;
                    break;
                case 26:
                    reviewPost++;
                    break;
                case 27:
                    discoverPost++;
                    break;
                case 28:
                    systemPost++;
                    break;
                default:
                    break;
            }
        }

        request.setAttribute("startD", startD);
        request.setAttribute("endD", endD);
        
        request.setAttribute("totalUser", totalUser);
        request.setAttribute("totalByCat", catTotal);
        request.setAttribute("maxCat", max);

        
        request.setAttribute("salePost", salePost);
        request.setAttribute("minigamePost", minigamePost);
        request.setAttribute("reviewPost", reviewPost);
        request.setAttribute("discoverPost", discoverPost);
        request.setAttribute("systemPost", systemPost);
        
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        
        request.setAttribute("totalStar", totalStar);
        request.getRequestDispatcher("/View/marketing/mktDashboard.jsp").forward(request, response);
        
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