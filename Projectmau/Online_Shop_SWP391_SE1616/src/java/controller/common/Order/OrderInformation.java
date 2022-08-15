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
public class OrderInformation extends HttpServlet {

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
            out.println("<title>Servlet OrderInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderInformation at " + request.getContextPath() + "</h1>");
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
        String orderID = request.getParameter("orderID");
        int status = Integer.parseInt(request.getParameter("status"));

        Order o = db.getOrderInformation(Integer.parseInt(orderID));
        o.setListProduct(db.getProductInOrder(Integer.parseInt(orderID)));

        for (int j = 0; j < o.getListProduct().size(); j++) {
            o.setTotalBill(o.getTotalBill() + o.getListProduct().get(j).getProduct().getPrice() * o.getListProduct().get(j).getQuantity());
            o.setTotalProduct(o.getTotalProduct() + o.getListProduct().get(j).getQuantity());
        }
        Setting s = new Setting();
        s.setSettingID(status);
        o.setStatus(s);
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Setting> cate = pdb.getCategory();
        ArrayList<Product> latestProduct = pdb.getLastestProduct();

        request.setAttribute("cate", cate);
        request.setAttribute("latestProduct", latestProduct);
        request.setAttribute("order", o);
        //response.getWriter().print(o.getCusID().getStatus().getSettingID());
        request.getRequestDispatcher("/View/Public/OrderInfor.jsp").forward(request, response);
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
        String orderID = request.getParameter("orderID");

        //db.deleteOrderDetail(Integer.parseInt(orderID));
        db.deleteOrder(Integer.parseInt(orderID));
        

        User customer = (User) request.getSession().getAttribute("customer");

        String page = "1";
        
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
            
            String alert = "<div class=\"alert alert-danger\">\n"
                    + "                        Your order has been cancelled!\n"
                    + "                    </div>";
            request.setAttribute("alert", alert);
            request.getRequestDispatcher("/View/Public/MyOrder.jsp").forward(request, response);
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
