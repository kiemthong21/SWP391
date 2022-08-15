/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sale.order;

import controller.authentication.BaseAuthController;
import dal.DashBoardDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.User;

/**
 *
 * @author Admin
 */
public class SaleDashboardController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Sale");
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
            out.println("<title>Servlet SaleDashboardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleDashboardController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DashBoardDBContext db = new DashBoardDBContext();
        String rawstartD = request.getParameter("startD");
        String rawendD = request.getParameter("endD");

        User u = (User) request.getSession().getAttribute("user");
        //User u = new User();
        //u.setUserID(114);

        Date startD;
        Date endD;

        if (rawendD == null) {
            endD = Date.valueOf(LocalDate.now());
            startD = Date.valueOf(LocalDate.now().minusDays(6));
        } else {
            endD = Date.valueOf(rawendD);
            startD = Date.valueOf(rawstartD);
        }

        ArrayList<Date> date = new ArrayList();

        if (endD.getMonth() == startD.getMonth()) {
            int range = endD.getDate() - startD.getDate();
            for (int i = range; i >= 0; i--) {
                date.add(Date.valueOf(LocalDate.now().minusDays(i)));
            }
        }// else{
//            int pl = 0;
//            while(startD.getMonth()<endD.getMonth()){
//                
//                date.add(Date.valueOf(startD.toLocalDate().minusDays(pl)));
//            }
//        }
        for (int i = 0; i < date.size(); i++) {
            response.getWriter().println(date.get(i));
        }

        ArrayList<Integer> totalOrder = new ArrayList();
        ArrayList<Integer> successOrder = new ArrayList();
        ArrayList<Float> revenus = new ArrayList();

        int totalOrderCount = 0;
        float totalSale = 0;

        ArrayList<Order> orders = db.getOrderBySale(startD, endD, u);
        for (Order o : orders) {
            if (o.getStatus().getSettingID() == 16) {
                totalSale += o.getTotalBill();
            }
            totalOrderCount++;
        }
        response.getWriter().println("month" + endD.toLocalDate().getMonthValue() + startD.toLocalDate().getMonthValue());
        for (int i = 0; i < date.size(); i++) {
            int tod = 0;
            int sod = 0;
            float r = 0;
            for (int j = 0; j < orders.size(); j++) {
                if (orders.get(j).getOrderDate().compareTo(date.get(i)) == 0) {
                    tod++;
                    if (orders.get(j).getStatus().getSettingID() == 16) {
                        r += orders.get(j).getTotalBill();

                        sod++;
//                    response.getWriter().println(orders.get(j).getOrderDate());
                    }
                }

            }
            totalOrder.add(tod);
            successOrder.add(sod);
            revenus.add(r);

            //response.getWriter().println(date.get(i));
            //response.getWriter().println(orders.get(i).getOrderDate());
//            response.getWriter().println(totalOrder.get(i));
//            response.getWriter().println(successOrder.get(i));
//            response.getWriter().println(revenus.get(i));
//            response.getWriter().println("--------------");
        }

        int proOrder = 0;
        int confOrder = 0;
        int canOrder = 0;
        
        for (Order o : orders) {
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
        
        //response.getWriter().print(totalSale);
        request.setAttribute("startD", startD);
        request.setAttribute("endD", endD);

        request.setAttribute("tod", totalOrder);
        request.setAttribute("sod", successOrder);
        request.setAttribute("revenus", revenus);

        request.setAttribute("totalOrder", totalOrderCount);
        request.setAttribute("totalSale", totalSale);
        request.setAttribute("processing", proOrder);
        request.setAttribute("confirmed", confOrder);
        request.setAttribute("cancel", canOrder);
        
        request.setAttribute("date", date);
        request.getRequestDispatcher("/View/sale/saledashboard.jsp").forward(request, response);
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
