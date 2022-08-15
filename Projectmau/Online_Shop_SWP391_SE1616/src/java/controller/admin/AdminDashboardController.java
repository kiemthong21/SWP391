/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.OrderDetail;
import model.User;

/**
 *
 * @author Admin
 */
public class AdminDashboardController extends BaseAuthController {

    
    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Admin");
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

        if (rawendD == null) {
            endD = Date.valueOf(LocalDate.now());
            startD = Date.valueOf(LocalDate.now().minusDays(6));
        } else {
            endD = Date.valueOf(rawendD);
            startD = Date.valueOf(rawstartD);
        }

        float totalSale = db.getTotalSales(startD, endD);
        int totalOrder = db.getTotalOrder(startD, endD);
        int totalUser = db.getTotalNewUser(startD, endD);
        int totalStar = db.getTotalStar(startD, endD);
        ArrayList<OrderDetail> totalByCat = db.getTotalSaleByCategory(startD, endD);
        double catTotal[] = new double[6];

        ArrayList<Order> totalOrderCount = db.getOrderCount(startD, endD);
        int proOrder = 0;
        int confOrder = 0;
        int canOrder = 0;

        for (OrderDetail o : totalByCat) {
            switch (o.getProduct().getCategory().getSettingID()) {
                case 18:
                    catTotal[0] += o.getPrice();
                    break;
                case 19:
                    catTotal[1] += o.getPrice();
                    break;
                case 20:
                    catTotal[2] += o.getPrice();
                    break;
                case 21:
                    catTotal[3] += o.getPrice();
                    break;
                case 22:
                    catTotal[4] += o.getPrice();
                    break;
                case 23:
                    catTotal[5] += o.getPrice();
                    break;
                default:
                    break;
            }
        }
        double totalSales = Arrays.stream(catTotal).sum();
        double max = Arrays.stream(catTotal).max().getAsDouble();

        for (Order o : totalOrderCount) {
            switch (o.getStatus().getSettingID()) {
                case 15:
                    proOrder++;
                    break;
                case 16:
                    confOrder++;
                    break;
                case 17:
                    canOrder++;
                    break;
                default:
                    break;
            }
        }

        ArrayList<Date> date = new ArrayList();

        if (endD.getMonth() == startD.getMonth()) {
            int range = endD.getDate() - startD.getDate();
            for (int i = range; i >= 0; i--) {
                date.add(Date.valueOf(LocalDate.now().minusDays(i)));
            }
        }
        ArrayList<Integer> tOrder = new ArrayList();
        ArrayList<Integer> successOrder = new ArrayList();
        ArrayList<Float> revenus = new ArrayList();
        int totalOrderCountt = 0;

        for (Order o : totalOrderCount) {
            if (o.getStatus().getSettingID() == 16) {
                totalSale += o.getTotalBill();
            }
            totalOrderCountt++;
        }
        response.getWriter().println("month" + endD.toLocalDate().getMonthValue() + startD.toLocalDate().getMonthValue());
        for (int i = 0; i < date.size(); i++) {
            int tod = 0;
            int sod = 0;
            float r = 0;
            for (int j = 0; j < totalOrderCount.size(); j++) {
                if (totalOrderCount.get(j).getOrderDate().compareTo(date.get(i)) == 0) {
                    tod++;
                    if (totalOrderCount.get(j).getStatus().getSettingID() == 16) {
                        r += totalOrderCount.get(j).getTotalBill();

                        sod++;
//                    response.getWriter().println(orders.get(j).getOrderDate());
                    }
                }

            }
            tOrder.add(tod);
            successOrder.add(sod);
            revenus.add(r);
        }

        request.setAttribute("startD", startD);
        request.setAttribute("endD", endD);

        request.setAttribute("tod", tOrder);
        request.setAttribute("sod", successOrder);
        request.setAttribute("revenus", revenus);
        request.setAttribute("date", date);
        
        request.setAttribute("totalSale", totalSales);
        request.setAttribute("totalOrder", totalOrder);
        request.setAttribute("totalUser", totalUser);
        request.setAttribute("totalByCat", catTotal);
        request.setAttribute("maxCat", max);

        request.setAttribute("processing", proOrder);
        request.setAttribute("confirmed", confOrder);
        request.setAttribute("cancel", canOrder);
        request.setAttribute("totalStar", totalStar);
        request.getRequestDispatcher("/View/Admin/admindashboard.jsp").forward(request, response);
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
