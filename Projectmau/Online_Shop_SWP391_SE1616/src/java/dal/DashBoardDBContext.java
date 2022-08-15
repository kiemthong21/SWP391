/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.OrderDetail;
import model.Post;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class DashBoardDBContext extends DBContext {

    public float getTotalSales(Date start, Date end) {
        float total = 0;
        try {
            String sql = "SELECT  SUM([Total_Bill]) as Total\n"
                    + "  FROM [Order] WHERE StatusID = 16 AND Order_Date >= ? AND Order_Date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getFloat("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getTotalOrder(Date start, Date end) {
        int total = 0;
        try {
            String sql = "SELECT  COUNT(OrderID) as TotalOrder\n"
                    + "  FROM [Order] WHERE Order_Date >= ? AND Order_Date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt("TotalOrder");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getTotalNewUser(Date start, Date end) {
        int total = 0;
        try {
            String sql = "SELECT COUNT(UserID) as TotalUser\n"
                    + "  FROM [User] WHERE Registered_At >= ? AND Registered_At <= ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt("TotalUser");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public int getTotalStar(Date start, Date end) {
        int total = 0;
        try {
            String sql = "SELECT \n"
                    + "      SUM([Rating]) as total\n"
                    + "  FROM [Product_Feedback] WHERE [Create_At] >= ? AND [Create_At] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public ArrayList<Product> getTotalProductByCategory(Date start, Date end) {
        ArrayList<Product> total = new ArrayList();
        try {
            String sql = "SELECT Product.ProductID, Setting.Setting_ID\n"
                    + "FROM Product INNER JOIN Setting \n"
                    + "ON Product.CategoryID = Setting.Setting_ID WHERE Created_At >= ? AND Created_At <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product o = new Product();
                o.setProductID(rs.getInt("ProductID"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Setting_ID"));
                o.setCategory(s);
                total.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public ArrayList<OrderDetail> getTotalSaleByCategory(Date start, Date end) {
        ArrayList<OrderDetail> total = new ArrayList();
        try {
            String sql = "SELECT o.[Price_Order]\n"
                    + "       ,s.[Setting_ID]\n"
                    + "FROM [Order_Detail] o INNER JOIN [Product] p ON o.[ProductID] = p.[ProductID]\n"
                    + "                      INNER JOIN [Setting] s ON p.[CategoryID] = s.[Setting_ID]"
                    + "                      INNER JOIN [Order] od ON o.[OrderID] = od.[OrderID] WHERE od.Order_Date >= ? AND od.Order_Date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                OrderDetail o = new OrderDetail();
                o.setPrice(rs.getFloat("Price_Order"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Setting_ID"));
                Product p = new Product();
                p.setCategory(s);
                o.setProduct(p);
                total.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public ArrayList<Order> getOrderCount(Date start, Date end) {
        ArrayList<Order> total = new ArrayList();
        try {
            String sql = "SELECT [StatusID]\n"
                    + ",[Order_Date]\n"
                    + ",[Total_Bill]\n"
                    + "  FROM [Order] WHERE Order_Date >= ? AND Order_Date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderDate(rs.getDate("Order_Date"));
                o.setTotalBill(rs.getFloat("Total_Bill"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("StatusID"));
                o.setStatus(s);
                total.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public ArrayList<Order> getOrderBySale(Date start, Date end, User u) {
        ArrayList<Order> total = new ArrayList();
        try {
            String sql = "SELECT o.[OrderID]\n"
                    + "      ,o.[Order_Date]\n"
                    + "      ,o.[Total_Bill]\n"
                    + "      ,o.[StatusID]\n"
                    + "	  ,p.[Author]\n"
                    + "  FROM [Order] o INNER JOIN [Order_Detail] od ON o.OrderID = od.OrderID\n"
                    + "				 INNER JOIN [Product] p ON p.ProductID = od.ProductID\n"
                    + "				 WHERE o.Order_Date  >= ? AND o.Order_Date <= ? AND p.Author = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            stm.setInt(3, u.getUserID());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderDate(rs.getDate("Order_Date"));
                o.setTotalBill(rs.getFloat("Total_Bill"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("StatusID"));
                o.setStatus(s);
                total.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    public ArrayList<Post> getPostDashboard(Date start, Date end) {
        ArrayList<Post> total = new ArrayList();
        try {
            String sql = "SELECT TOP (1000) [Setting_ID]\n"
                    + "  FROM [Setting] inner join Post \n"
                    + "  on Setting.Setting_ID = Post.Post_Category\n"
                    + "  WHERE Post_Date >= ? AND Post_Date <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, start);
            stm.setDate(2, end);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post o = new Post();
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Setting_ID"));
                o.setPostCategory(s);
                total.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
