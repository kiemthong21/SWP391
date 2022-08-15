/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.Product;
import model.Setting;
import model.User;
import model.general.Pageable;
import model.general.Pagination;
import model.general.ResultPageable;

/**
 *
 * @author Hieuhihi
 */
public class FeedbackDBContext extends DBContext {
    
    public Feedback get(int id) {
        try {
            String sql = "SELECT [Product_Feedback].[FeedbackID]\n"
                    + "      ,[Product_Feedback].[ProductID]\n"
                    + "      ,[Product_Feedback].[UserID]\n"
                    + "      ,[Product_Feedback].[Title]\n"
                    + "      ,[Product_Feedback].[Rating]\n"
                    + "      ,[Product_Feedback].[Create_At]\n"
                    + "      ,[Product_Feedback].[Content]\n"
                    + "      ,[Product_Feedback].[Like]\n"
                    + "      ,[Product_Feedback].[status] as 'Product_Feedback_status'\n"
                    + "      ,[User].[Phone] as 'User_phone'\n"
                    + "      ,[User].[Name]\n"
                    + "      ,[User].[Gender]\n"
                    + "      ,[User].[Password]\n"
                    + "      ,[User].[Email]\n"
                    + "      ,[User].[Address]\n"
                    + "      ,[User].[RoleID]\n"
                    + "      ,[User].[Registered_At]\n"
                    + "      ,[User].[Last_Login]\n"
                    + "      ,[User].[Avatar]\n"
                    + "      ,[User].[DOB]\n"
                    + "      ,[User].[Status]\n"
                    + "      ,[Product].[Author] as 'Product_author'\n"
                    + "      ,[Product].[Title] as 'Product_title'\n"
                    + "      ,[Product].[Summary]\n"
                    + "      ,[Product].[CategoryID]\n"
                    + "      ,[Product].[Price]\n"
                    + "      ,[Product].[Discount]\n"
                    + "      ,[Product].[Quantity]\n"
                    + "      ,[Product].[Created_At]\n"
                    + "      ,[Product].[Update_At]\n"
                    + "      ,[Product].[Status] as 'pStatus'\n"
                    + "      ,[Product].[thumbnail]\n"
                    + "      ,[Product].[View]\n"
                    + "      ,[Product].[Rate]\n"
                    + "  FROM [Product_Feedback]\n"
                    + "  INNER JOIN Product ON Product.ProductID = Product_Feedback.[ProductID] \n"
                    + "  INNER JOIN [User] ON [User].[UserID] = [Product_Feedback].[UserID] "
                    + "  WHERE [Product_Feedback].[FeedbackID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setProductID(rs.getInt("ProductID"));
                feedback.setUserID(rs.getInt("UserID"));
                feedback.setTitle(rs.getString("Title"));
                feedback.setRating(rs.getInt("Rating"));
                feedback.setCreate_at(rs.getDate("Create_At"));
                feedback.setContent(rs.getString("Content"));
                feedback.setLike(rs.getInt("Like"));
                feedback.setStatus(rs.getBoolean("Product_Feedback_status"));
                User user = new User();
                user.setPhone(rs.getString("User_phone"));
                user.setName(rs.getString("Name"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setRegisteredAt(rs.getDate("Registered_At"));
                user.setLastLogin(rs.getDate("Last_Login"));
                user.setAvatar(rs.getString("Avatar"));
                feedback.setUser(user);
                Product product = new Product();
                product.setTitle(rs.getString("Product_title"));
                product.setSummary(rs.getString("Summary"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setCreateAt(rs.getDate("Created_At"));
                product.setUpdateAt(rs.getDate("Update_At"));
                product.setThumbnail(rs.getString("thumbnail"));
                product.setView(rs.getInt("View"));
                product.setRate(rs.getDouble("Rate"));
                feedback.setProduct(product);
                return feedback;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Feedback> list() {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM(SELECT [Product_Feedback].[FeedbackID]\n"
                    + "      ,[Product_Feedback].[ProductID]\n"
                    + "      ,[Product_Feedback].[UserID]\n"
                    + "      ,[Product_Feedback].[Title]\n"
                    + "      ,[Product_Feedback].[Rating]\n"
                    + "      ,[Product_Feedback].[Create_At]\n"
                    + "      ,[Product_Feedback].[Content]\n"
                    + "      ,[Product_Feedback].[Like]\n"
                    + "      ,[User].[Phone] as 'User_phone'\n"
                    + "      ,[User].[Name]\n"
                    + "      ,[User].[Gender]\n"
                    + "      ,[User].[Password]\n"
                    + "      ,[User].[Email]\n"
                    + "      ,[User].[Address]\n"
                    + "      ,[User].[RoleID]\n"
                    + "      ,[User].[Registered_At]\n"
                    + "      ,[User].[Last_Login]\n"
                    + "      ,[User].[Avatar]\n"
                    + "      ,[User].[DOB]\n"
                    + "      ,[User].[Status]\n"
                    + "      ,[Product].[Author] as 'Product_author'\n"
                    + "      ,[Product].[Title] as 'Product_title'\n"
                    + "      ,[Product].[Summary]\n"
                    + "      ,[Product].[CategoryID]\n"
                    + "      ,[Product].[Price]\n"
                    + "      ,[Product].[Discount]\n"
                    + "      ,[Product].[Quantity]\n"
                    + "      ,[Product].[Created_At]\n"
                    + "      ,[Product].[Update_At]\n"
                    + "      ,[Product].[Status] as 'pStatus'\n"
                    + "      ,[Product].[thumbnail]\n"
                    + "      ,[Product].[View]\n"
                    + "      ,[Product].[Rate]\n"
                    + "  INNER JOIN Product ON Product.ProductID = Product_Feedback.[ProductID]\n"
                    + "  INNER JOIN [User] ON [User].[UserID] = [Product_Feedback].[UserID]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setProductID(rs.getInt("ProductID"));
                feedback.setUserID(rs.getInt("UserID"));
                feedback.setTitle(rs.getString("Title"));
                feedback.setRating(rs.getInt("Rating"));
                feedback.setCreate_at(rs.getDate("Create_At"));
                feedback.setContent(rs.getString("Content"));
                feedback.setLike(rs.getInt("Like"));
                User user = new User();
                user.setPhone(rs.getString("User_phone"));
                user.setName(rs.getString("Name"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setRegisteredAt(rs.getDate("Registered_At"));
                user.setLastLogin(rs.getDate("Last_Login"));
                user.setAvatar(rs.getString("Avatar"));
                feedback.setUser(user);
                Product product = new Product();
                product.setTitle(rs.getString("Product_title"));
                product.setSummary(rs.getString("Summary"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setCreateAt(rs.getDate("Created_At"));
                product.setUpdateAt(rs.getDate("Update_At"));
                product.setThumbnail(rs.getString("thumbnail"));
                product.setView(rs.getInt("View"));
                product.setRate(rs.getDouble("Rate"));
                feedback.setProduct(product);
                feedbacks.add(feedback);
            }
            return feedbacks;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultPageable<Feedback> list(String search, Pageable pageable) {
        ResultPageable<Feedback> resultPageable = new ResultPageable<>();
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            String sql_query_data = "SELECT * FROM(SELECT [Product_Feedback].[FeedbackID]\n"
                    + "      ,[Product_Feedback].[ProductID]\n"
                    + "      ,[Product_Feedback].[UserID]\n"
                    + "      ,[Product_Feedback].[Title]\n"
                    + "      ,[Product_Feedback].[Rating]\n"
                    + "      ,[Product_Feedback].[Create_At]\n"
                    + "      ,[Product_Feedback].[Content]\n"
                    + "      ,[Product_Feedback].[Like]\n"
                    + "      ,[Product_Feedback].[status] as 'Product_Feedback_status'\n"
                    + "      ,[User].[Phone] as 'User_phone'\n"
                    + "      ,[User].[Name]\n"
                    + "      ,[User].[Gender]\n"
                    + "      ,[User].[Password]\n"
                    + "      ,[User].[Email]\n"
                    + "      ,[User].[Address]\n"
                    + "      ,[User].[RoleID]\n"
                    + "      ,[User].[Registered_At]\n"
                    + "      ,[User].[Last_Login]\n"
                    + "      ,[User].[Avatar]\n"
                    + "      ,[User].[DOB]\n"
                    + "      ,[User].[Status]\n"
                    + "      ,[Product].[Author] as 'Product_author'\n"
                    + "      ,[Product].[Title] as 'Product_title'\n"
                    + "      ,[Product].[Summary]\n"
                    + "      ,[Product].[CategoryID]\n"
                    + "      ,[Product].[Price]\n"
                    + "      ,[Product].[Discount]\n"
                    + "      ,[Product].[Quantity]\n"
                    + "      ,[Product].[Created_At]\n"
                    + "      ,[Product].[Update_At]\n"
                    + "      ,[Product].[Status] as 'pStatus'\n"
                    + "      ,[Product].[thumbnail]\n"
                    + "      ,[Product].[View]\n"
                    + "      ,[Product].[Rate]\n";
            sql_query_data += " ,ROW_NUMBER() OVER (ORDER BY ";
            if (pageable.getOrderings() != null && !pageable.getOrderings().isEmpty()) {
                for (Map.Entry<String, String> en : pageable.getOrderings().entrySet()) {
                    String key = en.getKey();
                    String val = en.getValue();
                    key = key.split("[.]")[1];
                    if (key.equalsIgnoreCase("rating")) {
                        sql_query_data += " [Product_Feedback].[Rating] " + val + ",";
                    } else if (key.equalsIgnoreCase("product")) {
                        sql_query_data += " [Product].[Title] " + val + ",";
                    } else if (key.equalsIgnoreCase("status")) {
                        sql_query_data += " [Product_Feedback].[status] " + val + ",";
                    }
                }
                sql_query_data = sql_query_data.substring(0, sql_query_data.length() - 1);
            } else {
                sql_query_data += " [Product_Feedback].[FeedbackID] DESC";
            }
            sql_query_data += " ) as row_index\n";
            sql_query_data += "  FROM [Product_Feedback]\n"
                    + "  INNER JOIN Product ON Product.ProductID = Product_Feedback.[ProductID]\n"
                    + "  INNER JOIN [User] ON [User].[UserID] = [Product_Feedback].[UserID]"
                    + " WHERE ( [Product_Feedback].[Title] LIKE ? or [Product_Feedback].[Content] like ? ) ";

            for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                String key = entry.getKey();
                ArrayList<String> val = entry.getValue();
                sql_query_data += " AND ( ";
                if (key.equalsIgnoreCase("author")) {
                    key = " [User].[RoleID] ";
                } else if (key.equalsIgnoreCase("product")) {
                    key = " [Product_Feedback].[ProductID] ";
                } else if (key.equalsIgnoreCase("status")) {
                    key = " [Product].[Status] ";
                }
                sql_query_data += key + " in (?";
                for (int i = 1; i < val.size(); i++) {
                    sql_query_data += ",?";
                }
                sql_query_data += ") ) ";
            }

            sql_query_data += " ) [Feedback]"
                    + "  WHERE row_index >= (? - 1) * ? + 1 AND row_index <= ? * ?";

            System.out.println(sql_query_data);

            String sql_count_data = "SELECT COUNT([Product_Feedback].[FeedbackID]) as size "
                    + "  FROM [Product_Feedback]\n"
                    + "  INNER JOIN Product ON Product.ProductID = Product_Feedback.[ProductID]\n"
                    + "  INNER JOIN [User] ON [User].[UserID] = [Product_Feedback].[UserID]"
                    + " WHERE ( [Product_Feedback].[Title] LIKE ? or [Product_Feedback].[Content] like ? ) ";
            for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                String key = entry.getKey();
                ArrayList<String> val = entry.getValue();
                sql_count_data += " AND ( ";
                if (key.equalsIgnoreCase("author")) {
                    key = " [User].[RoleID] ";
                } else if (key.equalsIgnoreCase("product")) {
                    key = " [Product_Feedback].[ProductID] ";
                } else if (key.equalsIgnoreCase("status")) {
                    key = " [Product].[Status] ";
                }
                sql_count_data += key + " in (?";
                for (int i = 1; i < val.size(); i++) {
                    sql_count_data += ",?";
                }
                sql_count_data += ") ) ";
            }

            PreparedStatement stm = connection.prepareStatement(sql_query_data);
            stm.setString(1, "%" + search + "%");
            stm.setString(2, "%" + search + "%");
            if (pageable.getFilters() != null && !pageable.getFilters().isEmpty()) {
                int count = 0;
                for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                    String key = entry.getKey();
                    ArrayList<String> val = entry.getValue();
                    for (int i = 0; i < val.size(); i++) {
                        stm.setString(count + 3, val.get(i));
                        count++;
                    }
                }
                stm.setInt(count - 1 + 4, pageable.getPageIndex());
                stm.setInt(count - 1 + 5, pageable.getPageSize());
                stm.setInt(count - 1 + 6, pageable.getPageIndex());
                stm.setInt(count - 1 + 7, pageable.getPageSize());
            } else {
                stm.setInt(3, pageable.getPageIndex());
                stm.setInt(4, pageable.getPageSize());
                stm.setInt(5, pageable.getPageIndex());
                stm.setInt(6, pageable.getPageSize());
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setProductID(rs.getInt("ProductID"));
                feedback.setUserID(rs.getInt("UserID"));
                feedback.setTitle(rs.getString("Title"));
                feedback.setRating(rs.getInt("Rating"));
                feedback.setCreate_at(rs.getDate("Create_At"));
                feedback.setContent(rs.getString("Content"));
                feedback.setLike(rs.getInt("Like"));
                feedback.setStatus(rs.getBoolean("Product_Feedback_status"));
                User user = new User();
                user.setPhone(rs.getString("User_phone"));
                user.setName(rs.getString("Name"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setRegisteredAt(rs.getDate("Registered_At"));
                user.setLastLogin(rs.getDate("Last_Login"));
                user.setAvatar(rs.getString("Avatar"));
                feedback.setUser(user);
                Product product = new Product();
                product.setTitle(rs.getString("Product_title"));
                product.setSummary(rs.getString("Summary"));
                product.setPrice(rs.getDouble("Price"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setCreateAt(rs.getDate("Created_At"));
                product.setUpdateAt(rs.getDate("Update_At"));
                product.setThumbnail(rs.getString("thumbnail"));
                product.setView(rs.getInt("View"));
                product.setRate(rs.getDouble("Rate"));
                feedback.setProduct(product);
                feedbacks.add(feedback);
            }
            resultPageable.setList(feedbacks);

            stm = connection.prepareCall(sql_count_data);
            stm.setString(1, "%" + search + "%");
            stm.setString(2, "%" + search + "%");

            if (pageable.getFilters() != null && !pageable.getFilters().isEmpty()) {
                int count = 0;
                for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                    String key = entry.getKey();
                    ArrayList<String> val = entry.getValue();
                    for (int i = 0; i < val.size(); i++) {
                        stm.setString(count + 3, val.get(i));
                        count++;
                    }
                }
            }
            rs = stm.executeQuery();
            int size = 0;
            if (rs.next()) {
                size = rs.getInt("size");
            }
            resultPageable.setPagination(new Pagination(pageable.getPageIndex(), pageable.getPageSize(), size));
            return resultPageable;
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update(Feedback model) {
         String sql = "UPDATE [Product_Feedback]\n"
                + "          SET status = ?\n"
                + "     WHERE [Product_Feedback].[FeedbackID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setBoolean(1, model.isStatus());
            stm.setInt(2, model.getFeedbackID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FeedbackDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FeedbackDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
