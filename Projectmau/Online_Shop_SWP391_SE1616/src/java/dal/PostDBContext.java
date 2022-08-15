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
import model.Post;
import model.Setting;
import model.User;
import model.general.Pageable;
import model.general.Pagination;
import model.general.ResultPageable;

/**
 *
 * @author Admin
 */
public class PostDBContext extends DBContext {

    public ArrayList<Post> getHotPost() {
        ArrayList<Post> posts = new ArrayList();
        try {
            String sql = "SELECT [PostID]\n"
                    + "      ,[Post_Title]\n"
                    + "      ,[Post_Context]\n"
                    + "      ,[Post_Author]\n"
                    + "      ,[Post_Date]\n"
                    + "      ,[Update_Date]\n"
                    + "      ,[thumbnail]\n"
                    + "      ,[view]\n"
                    + "      ,[Post_Category]\n"
                    + "      ,[Post_Status]\n"
                    + "      ,[Summary]\n"
                    + "  FROM [Post]\n"
                    + "  ORDER BY [view] DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPostId(rs.getInt("PostID"));
                p.setPostTitle(rs.getString("Post_Title"));
                p.setPostContent(rs.getString("Post_Context"));
                User u = new User();
                u.setUserID(rs.getInt("Post_Author"));
                p.setAuthor(u);
                p.setPostDate(rs.getDate("Post_Date"));
                p.setUpdateDate(rs.getDate("Update_Date"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("view"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Post_Category"));
                s.setStatus(rs.getBoolean("Post_Status"));
                p.setPostStatus(s);
                p.setPostCategory(s);
                posts.add(p);
                //Get 4 hot post
                if (posts.size() == 4) {
                    return posts;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public ArrayList<Post> getLastestPost() {
        ArrayList<Post> posts = new ArrayList();
        try {
            String sql = "SELECT [PostID]\n"
                    + "      ,[Post_Title]\n"
                    + "      ,[Post_Context]\n"
                    + "      ,[Post_Author]\n"
                    + "      ,[Post_Date]\n"
                    + "      ,[Update_Date]\n"
                    + "      ,[thumbnail]\n"
                    + "      ,[view]\n"
                    + "      ,[Post_Category]\n"
                    + "      ,[Post_Status]\n"
                    + "      ,[Summary]\n"
                    + "  FROM [Post]\n"
                    + "  ORDER BY [Post_Date] DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPostId(rs.getInt("PostID"));
                p.setPostTitle(rs.getString("Post_Title"));
                p.setPostContent(rs.getString("Post_Context"));
                User u = new User();
                u.setUserID(rs.getInt("Post_Author"));
                p.setAuthor(u);
                p.setPostDate(rs.getDate("Post_Date"));
                p.setUpdateDate(rs.getDate("Update_Date"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("view"));
                Setting s = new Setting();
                s.setSettingID(rs.getInt("Post_Category"));
                s.setStatus(rs.getBoolean("Post_Status"));
                p.setPostStatus(s);
                p.setPostCategory(s);
                posts.add(p);
                //Get 4 hot post
                if (posts.size() == 5) {
                    return posts;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public ArrayList<Post> getAllBlogWithPagging(int page, int PAGE_SIZE) {
        ArrayList<Post> post = new ArrayList<>();
        try {
            String sql = "select p.PostID , p.Post_Title , p.Summary, p.Post_Date , p.Update_Date , p.thumbnail , p.[view] , u.[Name] from Post p inner join [User] u\n"
                    + "                    on p.Post_Author = u.UserID\n"
                    + "                    order by Post_Date DESC, PostID\n"
                    + "					offset (?-1)*? row fetch next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, PAGE_SIZE);
            stm.setInt(3, PAGE_SIZE);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Post p = new Post();
                p.setPostId(rs.getInt("PostID"));
                p.setPostTitle(rs.getString("Post_Title"));
                p.setSummary(rs.getString("Summary"));
                User u = new User();
                u.setName(rs.getString("Name"));
                p.setAuthor(u);
                p.setPostDate(rs.getDate("Post_Date"));
                p.setUpdateDate(rs.getDate("Update_Date"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("view"));
                post.add(p);
            }
        } catch (Exception e) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return post;

    }

    public int countTotalPost() {
        ArrayList<Post> post = new ArrayList<>();
        try {
            String sql = "select COUNT(p.PostID) from Post p inner join [User] u\n"
                    + "on p.Post_Author = u.UserID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return 0;

    }

    public Post getBlogById(int blogId) {
        try {
            String sql = "select p.PostID , p.Post_Title , p.Summary, p.Post_Date , p.Update_Date , p.thumbnail , p.[view] ,p.Post_Context, u.[Name] from Post p inner join [User] u\n"
                    + "                                       on p.Post_Author = u.UserID\n"
                    + "                    			   Where p.PostID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, blogId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Post p = new Post();
                p.setPostId(rs.getInt("PostID"));
                p.setPostTitle(rs.getString("Post_Title"));
                p.setSummary(rs.getString("Summary"));
                p.setPostContent(rs.getString("Post_Context"));
                User u = new User();
                u.setName(rs.getString("Name"));
                p.setAuthor(u);
                p.setPostDate(rs.getDate("Post_Date"));
                p.setUpdateDate(rs.getDate("Update_Date"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("view"));

                return p;
            }
        } catch (Exception e) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public ArrayList<Setting> getCategory() {
        ArrayList<Setting> st = new ArrayList();
        try {
            String sql = " SELECT [Setting_ID]\n"
                    + "                                          ,[Group]\n"
                    + "                                     ,[Name]\n"
                    + "                                           ,[Order]\n"
                    + "                                          ,[Status]\n"
                    + "                                         FROM [Setting] WHERE [Group] = 'Post Category' OR [Group] = 'Post Category'";
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

    public ArrayList<Post> getPosttByCategory(int categoryID) {
        ArrayList<Post> post = new ArrayList<>();
        try {
            String sql = "select p.PostID , p.Post_Title , p.Summary, p.Post_Date , p.Update_Date , p.thumbnail , p.[view] , u.[Name] from Post p inner join [User] u\n"
                    + "                    on p.Post_Author = u.UserID\n"
                    + "                    inner join Setting s\n"
                    + "                    on p.Post_Status = s.Setting_ID\n"
                    + "                    where Post_Category = ?\n"
                    + "					order by Post_Date DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPostId(rs.getInt("PostID"));
                p.setPostTitle(rs.getString("Post_Title"));
                p.setSummary(rs.getString("summary"));
                User u = new User();
                u.setName(rs.getString("Name"));
                p.setAuthor(u);
                p.setPostDate(rs.getDate("Post_Date"));
                p.setUpdateDate(rs.getDate("Update_Date"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("view"));
                post.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

    public ArrayList<Post> Search(String keyword) {
        ArrayList<Post> post = new ArrayList<>();
        try {
            String sql = "select p.PostID , p.Post_Title , p.Summary, p.Post_Date , p.Update_Date , p.thumbnail , p.[view] , u.[Name] from Post p inner join [User] u\n"
                    + "                    on p.Post_Author = u.UserID\n"
                    + "                    inner join Setting s\n"
                    + "                    on p.Post_Status = s.Setting_ID\n"
                    + "                    where Post_Title like ?\n"
                    + "					order by Post_Date DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post p = new Post();
                p.setPostId(rs.getInt("PostID"));
                p.setPostTitle(rs.getString("Post_Title"));
                p.setSummary(rs.getString("Summary"));
                User u = new User();
                u.setName(rs.getString("Name"));
                p.setAuthor(u);
                p.setPostDate(rs.getDate("Post_Date"));
                p.setUpdateDate(rs.getDate("Update_Date"));
                p.setThumbnail(rs.getString("thumbnail"));
                p.setView(rs.getInt("view"));
                post.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

    public void list() {
        String sql = "SELECT [Post].[PostID]\n"
                + "      ,[Post].[Post_Title]\n"
                + "      ,[Post].[Post_Context]\n"
                + "      ,[Post].[Post_Author]\n"
                + "      ,[Post].[Post_Date]\n"
                + "      ,[Post].[Update_Date]\n"
                + "      ,[Post].[thumbnail]\n"
                + "      ,[Post].[view]\n"
                + "      ,[Post].[Post_Category]\n"
                + "      ,[Post].[Post_Status]\n"
                + "      ,[Post].[Summary]\n"
                + "	  ,[status].[Setting_ID] as 'status_id'\n"
                + "      ,[status].[Group] as 'status_group'\n"
                + "      ,[status].[Name] as 'status_name'\n"
                + "      ,[status].[Order] as 'status_order'\n"
                + "      ,[status].[Status] as 'status_status'\n"
                + "	  ,[category].[Setting_ID] 'category_id'\n"
                + "      ,[category].[Group] 'category_group'\n"
                + "      ,[category].[Name] 'category_name'\n"
                + "      ,[category].[Order] 'category_order'\n"
                + "      ,[category].[Status] 'category_status'\n"
                + "	  ,[User].[UserID] as 'user_id'\n"
                + "      ,[User].[Phone]  as 'user_phone'\n"
                + "      ,[User].[Name]  as 'user_name'\n"
                + "      ,[User].[Gender]  as 'user_gender'\n"
                + "      ,[User].[Email]  as 'user_email'\n"
                + "      ,[User].[Address]  as 'user_address'\n"
                + "      ,[User].[RoleID]  as 'user_role'\n"
                + "      ,[User].[Avatar] as 'user_avatar'\n"
                + "  FROM [Post]\n"
                + "  INNER JOIN [Setting] as [status] on [status].[Setting_ID] = [Post].[Post_Status]\n"
                + "  INNER JOIN [Setting] as [category] on [category].[Setting_ID] = [Post].[Post_Category]\n"
                + "  INNER JOIN [User] on [User].[UserID] =  [Post].[Post_Author]";
    }
    
    public Post getById(int id) {
        String sql = "SELECT [Post].[PostID]\n"
                + "      ,[Post].[Post_Title]\n"
                + "      ,[Post].[Post_Context]\n"
                + "      ,[Post].[Post_Author]\n"
                + "      ,[Post].[Post_Date]\n"
                + "      ,[Post].[Update_Date]\n"
                + "      ,[Post].[thumbnail]\n"
                + "      ,[Post].[view]\n"
                + "      ,[Post].[Post_Category]\n"
                + "      ,[Post].[Post_Status]\n"
                + "      ,[Post].[Summary]\n"
                + "	  ,[status].[Setting_ID] as 'status_id'\n"
                + "      ,[status].[Group] as 'status_group'\n"
                + "      ,[status].[Name] as 'status_name'\n"
                + "      ,[status].[Order] as 'status_order'\n"
                + "      ,[status].[Status] as 'status_status'\n"
                + "	  ,[category].[Setting_ID] 'category_id'\n"
                + "      ,[category].[Group] 'category_group'\n"
                + "      ,[category].[Name] 'category_name'\n"
                + "      ,[category].[Order] 'category_order'\n"
                + "      ,[category].[Status] 'category_status'\n"
                + "	  ,[User].[UserID] as 'user_id'\n"
                + "      ,[User].[Phone]  as 'user_phone'\n"
                + "      ,[User].[Name]  as 'user_name'\n"
                + "      ,[User].[Gender]  as 'user_gender'\n"
                + "      ,[User].[Email]  as 'user_email'\n"
                + "      ,[User].[Address]  as 'user_address'\n"
                + "      ,[User].[RoleID]  as 'user_role'\n"
                + "      ,[User].[Avatar] as 'user_avatar'\n"
                + "  FROM [Post]\n"
                + "  INNER JOIN [Setting] as [status] on [status].[Setting_ID] = [Post].[Post_Status]\n"
                + "  INNER JOIN [Setting] as [category] on [category].[Setting_ID] = [Post].[Post_Category]\n"
                + "  INNER JOIN [User] on [User].[UserID] =  [Post].[Post_Author]"
                + "  WHERE [Post].[PostID] = ? ";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostID"));
                post.setPostTitle(rs.getString("Post_Title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setPostContent(rs.getString("Post_Context"));
                post.setUpdateDate(rs.getDate("Update_Date"));
                post.setPostDate(rs.getDate("Post_Date"));
                post.setSummary(rs.getString("Summary"));
                post.setView(rs.getInt("view"));

                Setting status = new Setting();
                status.setSettingID(rs.getInt("status_id"));
                status.setGroup(rs.getString("status_group"));
                status.setName(rs.getString("status_name"));
                status.setOrder(rs.getInt("status_order"));
                status.setStatus(rs.getBoolean("status_status"));
                post.setPostStatus(status);

                Setting category = new Setting();
                category.setSettingID(rs.getInt("category_id"));
                category.setGroup(rs.getString("category_group"));
                category.setName(rs.getString("category_name"));
                category.setOrder(rs.getInt("category_order"));
                category.setStatus(rs.getBoolean("category_status"));
                post.setPostCategory(category);

                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));
                user.setName(rs.getString("user_name"));
                user.setAvatar(rs.getString("user_avatar"));
                user.setGender(rs.getBoolean("user_gender"));
                Setting role = new Setting();
                role.setSettingID(rs.getInt("user_role"));
                post.setAuthor(user);
                return post;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Post insert(Post model) {
        String sql = "INSERT INTO [Post]\n"
                + "           ([Post_Title]\n"
                + "           ,[Post_Context]\n"
                + "           ,[Post_Author]\n"
                + "           ,[Post_Date]\n"
                + "           ,[Update_Date]\n"
                + "           ,[thumbnail]\n"
                + "           ,[view]\n"
                + "           ,[Post_Category]\n"
                + "           ,[Post_Status]\n"
                + "           ,[Summary])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getPostTitle());
            stm.setString(2, model.getPostContent());
            stm.setInt(3, model.getAuthor().getUserID());
            stm.setDate(4, model.getPostDate());
            stm.setDate(5, model.getUpdateDate());
            stm.setString(6, model.getThumbnail());
            stm.setInt(7, 0);
            stm.setInt(8, model.getPostCategory().getSettingID());
            stm.setInt(9, model.getPostStatus().getSettingID());
            stm.setString(10, model.getSummary());
            stm.executeUpdate();
            return model;
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
        return null;
    }

    public Post delete(int id) {
        String sql = "DELETE FROM [dbo].[Post]\n"
                + "      WHERE Post.PostID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
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
        return null;
    }

    public ResultPageable<Post> searchAndFilterAndSort(String search, Pageable pageable) {
        if (pageable.getFilters() == null) {
            pageable.setFilters(new HashMap<String, ArrayList<String>>());
        }
        ResultPageable<Post> resultPageable = new ResultPageable<>();
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<String> authors = pageable.getFilters().get("author");

        try {
            String sql_query_data = "SELECT * FROM (SELECT [Post].[PostID]\n"
                    + "      ,[Post].[Post_Title]\n"
                    + "      ,[Post].[Post_Context]\n"
                    + "      ,[Post].[Post_Author]\n"
                    + "      ,[Post].[Post_Date]\n"
                    + "      ,[Post].[Update_Date]\n"
                    + "      ,[Post].[thumbnail]\n"
                    + "      ,[Post].[view]\n"
                    + "      ,[Post].[Post_Category]\n"
                    + "      ,[Post].[Post_Status]\n"
                    + "      ,[Post].[Summary]\n"
                    + "	     ,[status].[Setting_ID] as 'status_id'\n"
                    + "      ,[status].[Group] as 'status_group'\n"
                    + "      ,[status].[Name] as 'status_name'\n"
                    + "      ,[status].[Order] as 'status_order'\n"
                    + "      ,[status].[Status] as 'status_status'\n"
                    + "	  ,[category].[Setting_ID] 'category_id'\n"
                    + "      ,[category].[Group] 'category_group'\n"
                    + "      ,[category].[Name] 'category_name'\n"
                    + "      ,[category].[Order] 'category_order'\n"
                    + "      ,[category].[Status] 'category_status'\n"
                    + "	  ,[User].[UserID] as 'user_id'\n"
                    + "      ,[User].[Phone]  as 'user_phone'\n"
                    + "      ,[User].[Name]  as 'user_name'\n"
                    + "      ,[User].[Gender]  as 'user_gender'\n"
                    + "      ,[User].[Email]  as 'user_email'\n"
                    + "      ,[User].[Address]  as 'user_address'\n"
                    + "      ,[User].[RoleID]  as 'user_role'\n"
                    + "      ,[User].[Avatar] as 'user_avatar'\n";

            sql_query_data += " ,ROW_NUMBER() OVER (ORDER BY ";
            if (pageable.getOrderings() != null && !pageable.getOrderings().isEmpty()) {
                for (Map.Entry<String, String> en : pageable.getOrderings().entrySet()) {
                    String key = en.getKey();
                    String val = en.getValue();
                    key = key.split("[.]")[1];
                    if (key.equalsIgnoreCase("author")) {
                        sql_query_data += " [User].[Name] " + val + ",";
                    } else if (key.equalsIgnoreCase("category")) {
                        sql_query_data += " [category].[Name] " + val + ",";
                    } else if (key.equalsIgnoreCase("created")) {
                        sql_query_data += " [Post].[Post_Date] " + val + ",";
                    } else if (key.equalsIgnoreCase("updated")) {
                        sql_query_data += " [Post].[Update_Date] " + val + ",";
                    } else if (key.equalsIgnoreCase("title")) {
                        sql_query_data += " [Post].[Post_Title] " + val + ",";
                    }
                }
                sql_query_data = sql_query_data.substring(0, sql_query_data.length() - 1);
            } else {
                sql_query_data += " [Post].[PostID] DESC";
            }
            sql_query_data += " ) as row_index\n";

            sql_query_data += "  FROM [Post]\n"
                    + "  INNER JOIN [Setting] as [status] on [status].[Setting_ID] = [Post].[Post_Status]\n"
                    + "  INNER JOIN [Setting] as [category] on [category].[Setting_ID] = [Post].[Post_Category]\n"
                    + "  INNER JOIN [User] on [User].[UserID] =  [Post].[Post_Author] ";

            sql_query_data += "  WHERE [Post].[Post_Title] LIKE ? ";

            for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                String key = entry.getKey();
                ArrayList<String> val = entry.getValue();
                sql_query_data += " AND ( ";
                if (key.equalsIgnoreCase("author")) {
                    key = " [User].[RoleID] ";
                } else if (key.equalsIgnoreCase("category")) {
                    key = " [category].[Setting_ID] ";
                } else if (key.equalsIgnoreCase("author")) {
                    key = " [User].[RoleID] ";
                } else if (key.equalsIgnoreCase("status")) {
                    key = " [status].[Setting_ID] ";
                }
                sql_query_data += key + " in (?";
                for (int i = 1; i < val.size(); i++) {
                    sql_query_data += ",?";
                }
                sql_query_data += ") ) ";
            }

            sql_query_data += " ) [Post]"
                    + "  WHERE row_index >= (? - 1) * ? + 1 AND row_index <= ? * ?";

            String sql_count_data = "SELECT COUNT([Post].[PostID]) as size "
                    + "  FROM [Post]\n"
                    + "  INNER JOIN [Setting] as [status] on [status].[Setting_ID] = [Post].[Post_Status]\n"
                    + "  INNER JOIN [Setting] as [category] on [category].[Setting_ID] = [Post].[Post_Category]\n"
                    + "  INNER JOIN [User] on [User].[UserID] =  [Post].[Post_Author] ";

            sql_count_data += " WHERE [Post].[Post_Title] LIKE ? ";

            for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                String key = entry.getKey();
                ArrayList<String> val = entry.getValue();
                sql_count_data += " AND ( ";
                if (key.equalsIgnoreCase("author")) {
                    key = " [User].[RoleID] ";
                } else if (key.equalsIgnoreCase("category")) {
                    key = " [category].[Setting_ID] ";
                } else if (key.equalsIgnoreCase("author")) {
                    key = " [User].[RoleID] ";
                } else if (key.equalsIgnoreCase("status")) {
                    key = " [status].[Setting_ID] ";
                }
                sql_count_data += key + " in (?";
                for (int i = 1; i < val.size(); i++) {
                    sql_count_data += ",?";
                }
                sql_count_data += ") ) ";
            }

            System.out.println(sql_query_data);

            PreparedStatement stm = connection.prepareStatement(sql_query_data);

            stm.setString(1, "%" + search + "%");

            if (pageable.getFilters() != null && !pageable.getFilters().isEmpty()) {
                int count = 0;
                for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                    String key = entry.getKey();
                    ArrayList<String> val = entry.getValue();
                    for (int i = 0; i < val.size(); i++) {
                        stm.setString(count + 2, val.get(i));
                        count++;
                    }
                }
                stm.setInt(count - 1 + 3, pageable.getPageIndex());
                stm.setInt(count - 1 + 4, pageable.getPageSize());
                stm.setInt(count - 1 + 5, pageable.getPageIndex());
                stm.setInt(count - 1 + 6, pageable.getPageSize());
            } else {
                stm.setInt(2, pageable.getPageIndex());
                stm.setInt(3, pageable.getPageSize());
                stm.setInt(4, pageable.getPageIndex());
                stm.setInt(5, pageable.getPageSize());
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setPostId(rs.getInt("PostID"));
                post.setPostTitle(rs.getString("Post_Title"));
                post.setThumbnail(rs.getString("thumbnail"));
                post.setPostContent(rs.getString("Post_Context"));
                post.setUpdateDate(rs.getDate("Update_Date"));
                post.setPostDate(rs.getDate("Post_Date"));
                post.setSummary(rs.getString("Summary"));
                post.setView(rs.getInt("view"));

                Setting status = new Setting();
                status.setSettingID(rs.getInt("status_id"));
                status.setGroup(rs.getString("status_group"));
                status.setName(rs.getString("status_name"));
                status.setOrder(rs.getInt("status_order"));
                status.setStatus(rs.getBoolean("status_status"));
                post.setPostStatus(status);

                Setting category = new Setting();
                category.setSettingID(rs.getInt("category_id"));
                category.setGroup(rs.getString("category_group"));
                category.setName(rs.getString("category_name"));
                category.setOrder(rs.getInt("category_order"));
                category.setStatus(rs.getBoolean("category_status"));
                post.setPostCategory(category);

                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setEmail(rs.getString("user_email"));
                user.setPhone(rs.getString("user_phone"));
                user.setName(rs.getString("user_name"));
                user.setAvatar(rs.getString("user_avatar"));
                user.setGender(rs.getBoolean("user_gender"));
                Setting role = new Setting();
                role.setSettingID(rs.getInt("user_role"));
                post.setAuthor(user);
                posts.add(post);
            }
            stm = connection.prepareCall(sql_count_data);
            stm.setString(1, "%" + search + "%");
            int count = 0;
            for (Map.Entry<String, ArrayList<String>> entry : pageable.getFilters().entrySet()) {
                String key = entry.getKey();
                ArrayList<String> val = entry.getValue();
                for (int i = 0; i < val.size(); i++) {
                    stm.setString(count + 2, val.get(i));
                    count++;
                }
            }

            rs = stm.executeQuery();
            int size = 0;
            if (rs.next()) {
                size = rs.getInt("size");
            }
            resultPageable.setList(posts);
            resultPageable.setPagination(new Pagination(pageable.getPageIndex(), pageable.getPageSize(), size));
            return resultPageable;
        } catch (SQLException ex) {
            Logger.getLogger(PostDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update(Post model) {
        String sql = "UPDATE [Post]\n"
                + "          SET [Post_Title] = ?\n"
                + "           ,[Post_Context] = ?\n"
                + "           ,[Post_Author] = ?\n"
                + "           ,[Post_Date] = ?\n"
                + "           ,[Update_Date] = ?\n"
                + "           ,[thumbnail] = ?\n"
                + "           ,[view] = ?\n"
                + "           ,[Post_Category] = ?\n"
                + "           ,[Post_Status] = ?\n"
                + "           ,[Summary]  = ?\n"
                + "     WHERE [Post].[PostID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, model.getPostTitle());
            stm.setString(2, model.getPostContent());
            stm.setInt(3, model.getAuthor().getUserID());
            stm.setDate(4, model.getPostDate());
            stm.setDate(5, model.getUpdateDate());
            stm.setString(6, model.getThumbnail());
            stm.setInt(7, model.getView());
            stm.setInt(8, model.getPostCategory().getSettingID());
            stm.setInt(9, model.getPostStatus().getSettingID());
            stm.setString(10, model.getSummary());
            stm.setInt(11, model.getPostId());
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
}
