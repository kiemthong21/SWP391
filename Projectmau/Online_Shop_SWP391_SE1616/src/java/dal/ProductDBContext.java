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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class ProductDBContext extends DBContext {

    OrderBy order = new OrderBy();

    public ArrayList<Product> getFeatureProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT [ProductID]\n"
                    + "      ,[Author]\n"
                    + "      ,[Title]\n"
                    + "      ,[Summary]\n"
                    + "      ,[CategoryID]\n"
                    + "      ,[Price]\n"
                    + "      ,[Discount]\n"
                    + "      ,[Quantity]\n"
                    + "      ,[Created_At]\n"
                    + "      ,[Update_At]\n"
                    + "      ,[Status]\n"
                    + "      ,[thumbnail]\n"
                    + "      ,[View]\n"
                    + "  FROM Product\n"
                    + "	ORDER BY [View] DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("CategoryID"));
                s.setStatus(rs.getBoolean("Status"));
                p.setCategory(s);
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setView(rs.getInt("View"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Product getProductDetail(int pid) {
        Product p = new Product();
        try {
            String sql = "SELECT p.[ProductID]\n"
                    + "      ,p.[Author]\n"
                    + "      ,p.[Title]\n"
                    + "      ,p.[Summary]\n"
                    + "      ,p.[CategoryID]\n"
                    + "      ,p.[Price]\n"
                    + "      ,p.[Discount]\n"
                    + "      ,p.[Quantity]\n"
                    + "      ,p.[Created_At]\n"
                    + "      ,p.[Update_At]\n"
                    + "      ,p.[Status]\n"
                    + "      ,p.[thumbnail]\n"
                    + "      ,p.[View]\n"
                    + "      ,p.[Rate]\n"
                    + "	  ,u.[Email]\n"
                    + "	  ,u.[Name]\n"
                    + "	  ,u.[Phone]\n"
                    + "  FROM [Product] p INNER JOIN [User] u ON p.[Author] = u.[UserID] WHERE [ProductID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                u.setName(rs.getString("Name"));
                u.setEmail(rs.getString("Email"));
                u.setPhone(rs.getString("Phone"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("CategoryID"));
                s.setStatus(rs.getBoolean("Status"));
                p.setCategory(s);
                p.setPrice(rs.getDouble("Price"));
                p.setSummary(rs.getString("Summary"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("View"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public int getProductTotal() {
        try {
            String sql = "SELECT COUNT(*) as Total FROM Product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Setting> getCategory() {
        ArrayList<Setting> st = new ArrayList();
        try {
            String sql = "SELECT [Setting_ID]\n"
                    + "      ,[Group]\n"
                    + "      ,[Name]\n"
                    + "      ,[Order]\n"
                    + "      ,[Status]\n"
                    + "  FROM [Setting] WHERE [Group] = 'Product Category' OR [Group] = 'Product Categort'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Setting_ID"));
                s.setGroup(rs.getString("Group"));
                s.setName(rs.getString("Name"));
                s.setOrder(rs.getInt("Order"));
                s.setStatus(rs.getBoolean("Status"));
                st.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }

    public ArrayList<Product> getProductByCategory(int categoryID) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "Select ProductID, Author, us.[Name] as AuthorName, Title, Summary, CategoryID, se.[Name] as Category, \n"
                    + "Price, Discount, Quantity, Created_At, Update_At, pro.[Status], sett.[Name] as StatusName, thumbnail, [View] from Product pro \n"
                    + "left join [User] us on pro.Author = us.UserID\n"
                    + "left join [Setting] se on pro.CategoryID = se.Setting_ID\n"
                    + "left join [Setting] sett on pro.[Status] = sett.Setting_ID\n"
                    + "where CategoryID = ?"
                    + "	ORDER BY [View] DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                u.setName(rs.getString("AuthorName"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting cate = new Setting();
                cate.setSettingID(rs.getInt("CategoryID"));
                cate.setName(rs.getString("Category"));
                p.setCategory(cate);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("StatusName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setView(rs.getInt("View"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public int getProductTotalByCategory(int category) {
        try {
            String sql = "SELECT COUNT(*) as Total FROM Product where CategoryID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, category);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Product> getAllProduct(String chooseOrder) {
        String orderProduct = order.OrderByProduct(chooseOrder);
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "Select ProductID, Author, us.[Name] as AuthorName, Title, Summary, CategoryID, se.[Name] as Category, \n"
                    + "Price, Discount, Quantity, Created_At, Update_At, pro.[Status], sett.[Name] as StatusName, thumbnail, [View] from Product pro \n"
                    + "left join [User] us on pro.Author = us.UserID\n"
                    + "left join [Setting] se on pro.CategoryID = se.Setting_ID\n"
                    + "left join [Setting] sett on pro.[Status] = sett.Setting_ID\n"
                    + orderProduct;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                u.setName(rs.getString("AuthorName"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting cate = new Setting();
                cate.setSettingID(rs.getInt("CategoryID"));
                cate.setName(rs.getString("Category"));
                p.setCategory(cate);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("StatusName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setView(rs.getInt("View"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;

    }

    public ArrayList<Product> getAllProductByCondition(String orderBy, String keyword, String category) {
        String orderProduct = order.OrderByProduct(orderBy);
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "Select ProductID, Author, us.[Name] as AuthorName, Title, Summary, CategoryID, se.[Name] as Category, \n"
                    + "Price, Discount, Quantity, Created_At, Update_At, pro.[Status], sett.[Name] as StatusName, thumbnail, [View] from Product pro \n"
                    + " left join [User] us on pro.Author = us.UserID\n"
                    + " left join [Setting] se on pro.CategoryID = se.Setting_ID\n"
                    + " left join [Setting] sett on pro.[Status] = sett.Setting_ID\n"
                    + "where CategoryID like ? and Title like ?"
                    + orderProduct;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + category + "%");
            stm.setString(2, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                u.setName(rs.getString("AuthorName"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting cate = new Setting();
                cate.setSettingID(rs.getInt("CategoryID"));
                cate.setName(rs.getString("Category"));
                p.setCategory(cate);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("StatusName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setView(rs.getInt("View"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public int getProductTotalByCondition(String category, String keyword) {
        try {
            String sql = "SELECT COUNT(*) as Total FROM Product where CategoryID like ? and Title like ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + category + "%");
            stm.setString(2, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Product> getLastestProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "select top 4 * from Product order by Created_At desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting cate = new Setting();
                cate.setSettingID(rs.getInt("CategoryID"));
                p.setCategory(cate);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setView(rs.getInt("View"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public ArrayList<Product> getProductMarketingdedtail() {

        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT pr.ProductID,pr.Title,pr.Summary,Pr.Price,Pr.Discount,Pr.thumbnail,Pr.Quantity,Pr.[Status], s.[Name], st.[Name] as Statusname FROM [Product] pr \n"
                    + "inner join Setting s\n"
                    + "on pr.CategoryID = s.Setting_ID\n"
                    + "inner join Setting st\n"
                    + "on pr.[Status] = st.Setting_ID ";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setTitle(rs.getString("Title"));
                p.setSummary(rs.getString("Summary"));
                Setting s = new Setting();
                s.setName(rs.getString("Name"));
                p.setCategory(s);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("Statusname"));
                p.setStatus(status);
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setQuantity(rs.getInt("Quantity"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public ArrayList<Product> getProductMarketing(String orderBy) {
        String orderProduct = order.OrderByProductMarketing(orderBy);
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT pr.ProductID,pr.Title,pr.Summary,Pr.Price,Pr.Discount,Pr.thumbnail,Pr.Quantity,Pr.[Status], s.[Name], st.[Name] as Statusname FROM [Product] pr \n"
                    + "                    inner join Setting s\n"
                    + "                    on pr.CategoryID = s.Setting_ID\n"
                    + "                    inner join Setting st\n"
                    + "                    on pr.[Status] = st.Setting_ID\n "
                    + orderProduct;

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setTitle(rs.getString("Title"));
                p.setSummary(rs.getString("Summary"));
                Setting s = new Setting();
                s.setName(rs.getString("Name"));
                p.setCategory(s);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("Statusname"));
                p.setStatus(status);
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setQuantity(rs.getInt("Quantity"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public ArrayList<Setting> getCategoryMarketing() {
        ArrayList<Setting> st = new ArrayList();
        try {
            String sql = "SELECT [Setting_ID]\n"
                    + "      ,[Group]\n"
                    + "      ,[Name]\n"
                    + "      ,[Order]\n"
                    + "      ,[Status]\n"
                    + "  FROM [Setting] WHERE [Group] = 'Product Category' OR [Group] = 'Product Categort'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Setting_ID"));
                s.setGroup(rs.getString("Group"));
                s.setName(rs.getString("Name"));
                s.setOrder(rs.getInt("Order"));
                s.setStatus(rs.getBoolean("Status"));
                st.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }

    public ArrayList<Product> getProductByCategoryMarketing(int cateId) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT pr.ProductID,pr.Title,pr.Summary,Pr.Price,Pr.Discount,Pr.thumbnail,Pr.Quantity,Pr.[Status], s.[Name], st.[Name] as Statusname FROM [Product] pr \n"
                    + "inner join Setting s\n"
                    + "on pr.CategoryID = s.Setting_ID\n"
                    + "inner join Setting st\n"
                    + "on pr.[Status] = st.Setting_ID \n"
                    + "where pr.CategoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setTitle(rs.getString("Title"));
                p.setSummary(rs.getString("Summary"));
                Setting s = new Setting();
                s.setName(rs.getString("Name"));
                p.setCategory(s);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("Statusname"));
                p.setStatus(status);
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Product insert(Product product) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([Author]\n"
                + "           ,[Title]\n"
                + "           ,[Summary]\n"
                + "           ,[CategoryID]\n"
                + "           ,[Price]\n"
                + "           ,[Discount]\n"
                + "           ,[Quantity]\n"
                + "           ,[Created_At]\n"
                + "           ,[Update_At]\n"
                + "           ,[Status]\n"
                + "           ,[thumbnail]\n"
                + "           ,[View]\n"
                + "           ,[Rate])\n"
                + "     VALUES\n"
                + "           ( NULL\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,GETDATE()\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,0\n"
                + "           ,0)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, product.getTitle());
            stm.setString(2, product.getSummary());
            stm.setInt(3, product.getCategory().getSettingID());
            stm.setDouble(4, product.getPrice());
            stm.setDouble(5, product.getDiscount());
            stm.setInt(6, product.getQuantity());
            stm.setInt(7, product.getStatus().getSettingID());
            stm.setString(8, product.getThumbnail());
            stm.executeUpdate();
            return product;
        } catch (SQLException e) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return null;
    }

    public ArrayList<Setting> getStatus() {
        ArrayList<Setting> st = new ArrayList();
        try {
            String sql = "SELECT [Setting_ID]\n"
                    + "      ,[Group]\n"
                    + "      ,[Name]\n"
                    + "      ,[Order]\n"
                    + "      ,[Status]\n"
                    + "  FROM [Setting] WHERE [Group] = 'Product Status' OR [Group] = 'Product Status'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Setting_ID"));
                s.setGroup(rs.getString("Group"));
                s.setName(rs.getString("Name"));
                s.setOrder(rs.getInt("Order"));
                s.setStatus(rs.getBoolean("Status"));
                st.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }

    public Product getproductbyID(int id) {
        try {
            String sql = "SELECT pr.ProductID,pr.Title,pr.Summary,Pr.Price,Pr.Discount,Pr.thumbnail,Pr.Quantity,st.[Status],s.[Name] as Categoryname, st.[Name] as Statusname FROM [Product] pr \n"
                    + "                                     inner join Setting s\n"
                    + "                                       on pr.CategoryID = s.Setting_ID\n"
                    + "                                       inner join Setting st\n"
                    + "                                       on pr.[Status] = st.Setting_ID \n"
                    + "                                       where pr.ProductID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setTitle(rs.getString("Title"));
                p.setSummary(rs.getString("Summary"));
                Setting s = new Setting();
                s.setName(rs.getString("Categoryname"));
                p.setCategory(s);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("Statusname"));
//                status.setGroup("Group");
//                status.setOrder(rs.getInt("Order"));
//                status.setStatus(rs.getBoolean("Status"));
                p.setStatus(status);
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setQuantity(rs.getInt("Quantity"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateproduct(int id, String Title, String summary, int CategoryID, double price,
            double Discount, int quantity, int status, String thumbnail) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET "
                + "      [Title] = ?\n" //1
                + "      ,[Summary] = ?\n" //2
                + "      ,[CategoryID] = ?\n" //3
                + "      ,[Price] = ?\n" //4
                + "      ,[Discount] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[thumbnail] = ?\n"
                + "      \n"
                + " WHERE Product.ProductID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, Title);
            stm.setString(2, summary);
            stm.setInt(3, CategoryID);
            stm.setDouble(4, price);
            stm.setDouble(5, Discount);
            stm.setInt(6, quantity);
            stm.setInt(7, status);
            stm.setString(8, thumbnail);
            stm.setInt(9, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteproduct(String id) {
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE Product.ProductID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Product product) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET "
                + "      [Status] = ?\n"
                + " WHERE Product.ProductID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, product.getStatus().getSettingID());
            stm.setInt(2, product.getProductID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "Select ProductID, Author, us.[Name] as AuthorName, Title, Summary, CategoryID, se.[Name] as Category, \n"
                    + "Price, Discount, Quantity, Created_At, Update_At, pro.[Status], sett.[Name] as StatusName, thumbnail, [View] from Product pro \n"
                    + "left join [User] us on pro.Author = us.UserID\n"
                    + "left join [Setting] se on pro.CategoryID = se.Setting_ID\n"
                    + "left join [Setting] sett on pro.[Status] = sett.Setting_ID\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                u.setName(rs.getString("AuthorName"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting cate = new Setting();
                cate.setSettingID(rs.getInt("CategoryID"));
                cate.setName(rs.getString("Category"));
                p.setCategory(cate);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                status.setName(rs.getString("StatusName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setView(rs.getInt("View"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;

    }

    public boolean updateQuantityAvailable(int quantity, int productId) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [Quantity] = ?\n"
                    + " WHERE ProductID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, productId);
            stm.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Product> getCategoryThumbnail() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT TOP (6) [ProductID]\n"
                    + "      ,[Author]\n"
                    + "      ,[Title]\n"
                    + "      ,[Summary]\n"
                    + "      ,[CategoryID]\n"
                    + "      ,[Price]\n"
                    + "      ,[Discount]\n"
                    + "      ,[Quantity]\n"
                    + "      ,[Created_At]\n"
                    + "      ,[Update_At]\n"
                    + "      ,[Status]\n"
                    + "      ,[thumbnail]\n"
                    + "      ,[View]\n"
                    + "      ,[Rate]\n"
                    + "  FROM [online_shop].[dbo].[Product]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("CategoryID"));
                s.setStatus(rs.getBoolean("Status"));
                p.setCategory(s);
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setView(rs.getInt("View"));
                p.setThumbnail(rs.getString("thumbnail"));
                products.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Product insert1(Product product) {
        String sql = "INSERT INTO [Product]\n"
                + "           ([Author]\n"//1
                + "           ,[Title]\n"//2
                + "           ,[Summary]\n"//3
                + "           ,[CategoryID]\n"//4
                + "           ,[Price]\n"//5
                + "           ,[Discount]\n"//6
                + "           ,[Quantity]\n"//7
                + "           ,[Created_At]\n"//8
                + "           ,[Update_At]\n"//9
                + "           ,[Status]\n"//10
                + "           ,[thumbnail]\n"//11
                + "           ,[View]\n"//12
                + "           ,[Rate])\n"//13
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,0\n"
                + "           ,0)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, product.getAuthor().getUserID());
            stm.setString(2, product.getTitle());
            stm.setString(3, product.getSummary());
            stm.setInt(4, product.getCategory().getSettingID());
            stm.setDouble(5, product.getPrice());
            stm.setDouble(6, product.getDiscount());
            stm.setInt(7, product.getQuantity());
            stm.setDate(8, product.getCreateAt());
            stm.setDate(9, product.getUpdateAt());
            stm.setInt(10, product.getStatus().getSettingID());
            stm.setString(11, product.getThumbnail());
            stm.executeUpdate();
            return product;
        } catch (SQLException e) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return null;
    }
    public Product getProductDetail1(int pid) {
        Product p = new Product();
        try {
            String sql = "SELECT p.[ProductID]\n"
                    + "      ,p.[Author]\n"
                    + "      ,p.[Title]\n"
                    + "      ,p.[Summary]\n"
                    + "      ,p.[CategoryID]\n"
                    + "      ,p.[Price]\n"
                    + "      ,p.[Discount]\n"
                    + "      ,p.[Quantity]\n"
                    + "      ,p.[Created_At]\n"
                    + "      ,p.[Update_At]\n"
                    + "      ,p.[Status]\n"
                    + "      ,p.[thumbnail]\n"
                    + "      ,p.[View]\n"
                    + "      ,p.[Rate]\n"
                    + "	  ,u.[Email]\n"
                    + "	  ,u.[Name]\n"
                    + "	  ,u.[Phone]\n"
                    + "  FROM [Product] p INNER JOIN [User] u ON p.[Author] = u.[UserID] WHERE [ProductID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                p.setProductID(rs.getInt("ProductID"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                u.setName(rs.getString("Name"));
                u.setEmail(rs.getString("Email"));
                u.setPhone(rs.getString("Phone"));
                p.setAuthor(u);
                p.setTitle(rs.getString("Title"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("CategoryID"));
                Setting s1 = new Setting();
                s1.setSettingID(rs.getInt("Status"));
                p.setStatus(s1);
                p.setCategory(s);
                p.setPrice(rs.getDouble("Price"));
                p.setSummary(rs.getString("Summary"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCreateAt(rs.getDate("Created_At"));
                p.setUpdateAt(rs.getDate("Update_At"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("View"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    public void update1(Product p) {
        String sql = "UPDATE [Product]\n"
                + "   SET [Title] = ?\n"//1
                + "      ,[Summary] = ?\n"//2
                + "      ,[CategoryID] = ?\n"//3
                + "      ,[Price] = ?\n"//4
                + "      ,[Discount] = ?\n"//5
                + "      ,[Quantity] = ?\n"//6
                + "      ,[Update_At] = ?\n"//7
                + "      ,[Status] = ?\n"//8
                + "      ,[thumbnail] = ?\n"//9
                + " WHERE Product.ProductID = ?";//10
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getTitle());
            stm.setString(2, p.getSummary());
            stm.setInt(3, p.getCategory().getSettingID());
            stm.setDouble(4, p.getPrice());
            stm.setDouble(5, p.getDiscount());
            stm.setInt(6, p.getQuantity());
            stm.setDate(7, p.getUpdateAt());
            stm.setInt(8, p.getStatus().getSettingID());
            stm.setString(9, p.getThumbnail());
            stm.setInt(10, p.getProductID());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        ProductDBContext db = new ProductDBContext();
        Product product = db.getproductbyID(102);
        System.out.println(product);

    }

}
