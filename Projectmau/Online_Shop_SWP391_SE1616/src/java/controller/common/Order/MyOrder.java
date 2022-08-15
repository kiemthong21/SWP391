/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common.Order;

import dal.OrderDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class MyOrder extends HttpServlet {

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
            out.println("<title>Servlet MyOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyOrder at " + request.getContextPath() + "</h1>");
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
    OrderDBContext db = new OrderDBContext();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User customer = (User) request.getSession().getAttribute("customer");

        String page = request.getParameter("page");
        //Set origin page is 1
        if(page ==null || page.trim().length() ==0)
        {
            page = "1";
        }
        
        int currentPage = Integer.parseInt(page);
        
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Setting> cate = pdb.getCategory();
        ArrayList<Product> latestProduct = pdb.getLastestProduct();
        
        request.setAttribute("cate", cate);
        request.setAttribute("latestProduct", latestProduct);
        if (customer == null) {
            request.getRequestDispatcher("/View/Public/Homepage.jsp").forward(request, response);
        } else {

            int totalPage = db.getTotalPage(customer.getUserID());
            ArrayList<Order> orders = db.getOrder(customer.getUserID(), currentPage);
            
            if (!orders.isEmpty()) {
                for (int i = 0; i < orders.size(); i++) {
                    orders.get(i).setListProduct(db.getProductInOrder(orders.get(i).getOrderID()));
                    //response.getWriter().println(orders.get(0).getListProduct().size());
                }
                for (int i = 0; i < orders.size(); i++) {
                    for (int j = 0; j < orders.get(i).getListProduct().size(); j++) {
                        //response.getWriter().println(orders.get(i).getListProduct().get(j).getProduct().getPrice()*orders.get(i).getListProduct().get(j).getQuantity()); 
                        orders.get(i).setTotalBill(orders.get(i).getTotalBill() + orders.get(i).getListProduct().get(j).getProduct().getPrice() * orders.get(i).getListProduct().get(j).getQuantity());
                        orders.get(i).setTotalProduct(orders.get(i).getTotalProduct() + orders.get(i).getListProduct().get(j).getQuantity());

                    }
                }
                request.setAttribute("page", currentPage);
                request.setAttribute("totalPage", totalPage);
                //response.getWriter().print(totalPage);
            }

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/View/Public/MyOrder.jsp").forward(request, response);
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
