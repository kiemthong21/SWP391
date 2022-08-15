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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.CartDetail;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class CartDBContext extends DBContext {

    public boolean addNewProductToCart(int cartId, int productId) {
        try {
            String sql = "INSERT INTO [dbo].[CartDetail]\n"
                    + "           ([ProductID]\n"
                    + "           ,[quantity]\n"
                    + "           ,[CartID])\n"
                    + "     VALUES\n"
                    + "           (?, 1, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productId);
            stm.setInt(2, cartId);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

//    public boolean updateQuantityProduct(int productId, int quantity) {
//        try {
//            String sql = "";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            return true;
//        } catch (Exception e) {
//            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return false;
//    }
    public Cart getCartUser(int userID) {
        try {
            String sql = "select * from Cart where [UserID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Cart c = new Cart();
                c.setCartId(rs.getInt("CartID"));
                c.setUserId(rs.getInt("UserID"));
                c.setTotalPrice(rs.getFloat("Cart_total_price"));
                return c;
            }
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Boolean InsertCart(int userID) {
        try {
            String sql = "INSERT INTO [dbo].[Cart]\n"
                    + "           ([UserID]\n"
                    + "           ,[Cart_total_price])\n"
                    + "     VALUES\n"
                    + "           (?,0)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public CartDetail getProductInCart(int productID, int cartID) {
        try {
            String sql = "select * from CartDetail where ProductID = ? and CartID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, productID);
            stm.setInt(2, cartID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                CartDetail cD = new CartDetail();
                cD.setCartDetailID(rs.getInt("CartDetailID"));
                Product pro = new Product();
                pro.setProductID(rs.getInt("ProductID"));
                cD.setProduct(pro);
                cD.setQuantity(rs.getInt("quantity"));
                return cD;
            }
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public boolean updateQuantityToCart(int cartDetailID, int quantity) {
        try {
            String sql = "UPDATE [dbo].[CartDetail]\n"
                    + "   SET [quantity] = ? \n"
                    + " WHERE CartDetailID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity + 1);
            stm.setInt(2, cartDetailID);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public boolean updateQuantityToCart(int pid, int quantity, int cartId) {
        try {
            String sql = "UPDATE [dbo].[CartDetail]\n"
                    + "   SET [quantity] = ?\n"
                    + " WHERE ProductID = ? and CartID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, pid);
            stm.setInt(3, cartId);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public int getNumberCartDetail(int cartID) {
        try {
            String sql = "select sum(quantity) as numberquantity from CartDetail where CartID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("numberquantity");
            }

        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;
    }

    public ArrayList<CartDetail> getAllProductInCart(int cartID) {
        try {
            ArrayList<CartDetail> productInCart = new ArrayList<>();
            String sql = "select CartDetailID, CartID, cd.ProductID, Title, Price, Discount, thumbnail, cd.quantity from CartDetail cd\n"
                    + "left join Product p on cd.ProductID = p.ProductID\n"
                    + "where CartID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CartDetail cd = new CartDetail();
                cd.setCartDetailID(rs.getInt("CartDetailID"));
                cd.setCartID(rs.getInt("CartID"));
                cd.setQuantity(rs.getInt("quantity"));
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setTitle(rs.getString("Title"));
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setThumbnail(rs.getString("thumbnail"));
                cd.setProduct(p);
                productInCart.add(cd);
            }
            return productInCart;
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public boolean deleteCart(int pid, int cartId) {
        try {
            String sql = "DELETE FROM [dbo].[CartDetail]\n"
                    + "      WHERE ProductID = ? and CartID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            stm.setInt(2, cartId);
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public ArrayList<Integer> getAuthorProductInCartDetail(int cartId) {
        ArrayList<Integer> listAuthor = new ArrayList<Integer>();
        try {
            String sql = "select distinct Author from CartDetail cd \n"
                    + "left join Product p on cd.ProductID = p.ProductID\n"
                    + "where CartID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listAuthor.add(rs.getInt("Author"));
            }
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return listAuthor;
    }

    public ArrayList<Product> getProductByAuthorInCart(int cartID, int AuthorID) {
        try {
            ArrayList<Product> productInCart = new ArrayList<>();
            String sql = "select cd.ProductID, Title, Price, Discount, thumbnail, cd.quantity, p.Author from CartDetail cd\n"
                    + "left join Product p on cd.ProductID = p.ProductID\n"
                    + "where CartID = ? and p.Author = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartID);
            stm.setInt(2, AuthorID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setTitle(rs.getString("Title"));
                p.setPrice(rs.getDouble("Price"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setQuantity(rs.getInt("quantity"));
                productInCart.add(p);
            }
            return productInCart;
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public int deleteAllProductInCart(int cartID) {
        try {
            String sql = "DELETE FROM [dbo].[CartDetail]\n"
                    + "      WHERE CartID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cartID);
            return stm.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return -1;
    }

    public int updateCartInProduct(ArrayList<CartDetail> product, int cartid) {
        int count = 0;
        try {
            connection.setAutoCommit(false);
            for (CartDetail cd : product) {
                String sql = "INSERT INTO [dbo].[CartDetail]\n"
                        + "           ([ProductID]\n"
                        + "           ,[quantity]\n"
                        + "           ,[CartID])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?)";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, cd.getProduct().getProductID());
                stm.setInt(2, cd.getQuantity());
                stm.setInt(3, cartid);
                stm.executeUpdate(sql);
                count = count + 1;
            }
            connection.commit();
        } catch (Exception ex) {
            Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(CartDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        ArrayList<CartDetail> listcd =  new ArrayList<>();
        CartDetail cd = new CartDetail();
        Product p = new Product();
        p.setProductID(102);
        cd.setProduct(p);
        cd.setQuantity(12);
        
        
    }

}
