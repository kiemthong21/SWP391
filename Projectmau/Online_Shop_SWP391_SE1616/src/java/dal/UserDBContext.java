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
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDBContext extends DBContext {

    MD5 encode = new MD5();
    
    public User getUser(String email, String password) {
        String md5 = encode.getMd5(password);
        try {
            String sql = "SELECT [UserID]\n"
                    + "  FROM [User]"
                    + " WHERE [Email] = ? and [Password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, md5);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setEmail(email);
                user.setPassword(password);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User loginCustomer(String email, String pass) {
        String md5 = encode.getMd5(pass);
        try {
            String sql = "select [UserID], [Phone], us.[Name], Gender, [Password], [Email], [Address], us.RoleID, se.[Name] as RoleName , Registered_At,\n"
                    + "Last_Login, Avatar, DOB, us.[Status] from [User] us\n"
                    + "left join Setting se on us.RoleID = se.Setting_ID \n"
                    + "where [Email] = ? and [Password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, md5);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUserID(rs.getInt("UserID"));
                s.setName(rs.getString("Name"));
                s.setEmail(rs.getString("Email"));
                s.setPassword(rs.getString("Password"));
                s.setGender(rs.getBoolean("Gender"));
                s.setPhone(rs.getString("Phone"));
                s.setAvatar(rs.getString("Avatar"));
                s.setAddress(rs.getString("Address"));
                Setting set = new Setting();
                set.setSettingID(rs.getInt("RoleID"));
                set.setName(rs.getString("RoleName"));
                s.setRole(set);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                s.setDob(rs.getDate("DOB"));
                s.setStatus(status);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean CheckExistEmail(String email) {

        try {
            String sql = "SELECT Email FROM [User] WHERE Email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void CreateUser(User u) {
        try {
            String sql = "INSERT [User]\n"
                    + "           ([Phone]\n"
                    + "           ,[Name]\n"
                    + "           ,[Gender]\n"
                    + "           ,[Password]\n"
                    + "           ,[Email]\n"
                    + "           ,[Address]\n"
                    + "           ,[RoleID]\n"
                    + "           ,[Registered_At]\n"
                    + "           ,[Last_Login]\n"
                    + "           ,[Avatar]\n"
                    + "           ,[DOB]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           ( ?\n"
                    + "           , ?\n"
                    + "           , ?\n"
                    + "           , ?\n"
                    + "           , ?\n"
                    + "           , ?\n"
                    + "           , 1\n"
                    + "           ,  GETDATE()\n"
                    + "           ,  GETDATE()\n"
                    + "           , Null\n"
                    + "           , ?\n"
                    + "           ,7)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, u.getPhone());
            stm.setString(2, u.getName());
            stm.setBoolean(3, u.isGender());
            stm.setString(4, encode.getMd5(u.getPassword()));
            stm.setString(5, u.getEmail());
            stm.setString(6, u.getAddress());
            stm.setDate(7, u.getDob());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT [UserID]\n"
                + "      ,[Phone]\n"
                + "      ,[Name]\n"
                + "      ,[Gender]\n"
                + "      ,[Password]\n"
                + "      ,[Email]\n"
                + "      ,[Address]\n"
                + "      ,[RoleID]\n"
                + "      ,[Registered_At]\n"
                + "      ,[Last_Login]\n"
                + "      ,[Avatar]\n"
                + "      ,[DOB]\n"
                + "      ,[Status]\n"
                + "  FROM [User]"
                + " WHERE [User].[UserID] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(id);
                user.setPhone(rs.getString("Phone"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUserById1(int id) {
        String sql = "SELECT [UserID]\n"
                + "      ,[Phone]\n"
                + "      ,[Name]\n"
                + "      ,[Gender]\n"
                + "      ,[Password]\n"
                + "      ,[Email]\n"
                + "      ,[Address]\n"
                + "      ,[RoleID]\n"
                + "      ,[Registered_At]\n"
                + "      ,[Last_Login]\n"
                + "      ,[Avatar]\n"
                + "      ,[DOB]\n"
                + "      ,[Status]\n"
                + "  FROM [User]"
                + " WHERE [User].[UserID] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setName(rs.getString("Name"));
                user.setPhone(rs.getString("Phone"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setDob(rs.getDate("DOB"));
                user.setAvatar(rs.getString("Avatar"));
                user.setRegisteredAt(rs.getDate("Registered_At"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT [UserID]\n"
                + "      ,[Phone]\n"
                + "      ,[Name]\n"
                + "      ,[Gender]\n"
                + "      ,[Password]\n"
                + "      ,[Email]\n"
                + "      ,[Address]\n"
                + "      ,[RoleID]\n"
                + "      ,[Registered_At]\n"
                + "      ,[Last_Login]\n"
                + "      ,[Avatar]\n"
                + "      ,[DOB]\n"
                + "      ,[Status]\n"
                + "  FROM [User]"
                + " WHERE [User].[Email] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setPhone(rs.getString("Phone"));
                user.setGender(rs.getBoolean("Gender"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void changePassword(User user) {

        String sql = "UPDATE [User]\n"
                + "   SET [Password] = ?\n"
                + " WHERE [User].[UserID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, encode.getMd5(user.getPassword()));
            stm.setInt(2, user.getUserID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String getUserbyEmailtocheckpass(User user) {
        try {
            String sql = "select Password from [User] where Email = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user.getEmail());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String oldpass;
                oldpass = rs.getString("Password");

                return oldpass;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateprofile(String name, String phone, boolean gender, String adrdress, Date DOB, String Avatar, int id, String email) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Phone] =?,\n"
                    + "      [Name] = ?,\n"
                    + "      [Gender] = ?,\n"
                    + "      [Address] = ?,\n"
                    + "      [Email] = ?,\n"
                    + "      [DOB] = ?,\n"
                    + "	  [Avatar] = ?\n"
                    + " WHERE [User].UserID =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, phone);
            stm.setString(2, name);
            stm.setBoolean(3, gender);
            stm.setString(4, adrdress);
            stm.setString(5, email);
            stm.setDate(6, DOB);
            stm.setString(7, Avatar);
            stm.setInt(8, id);
            stm.executeUpdate();
        } catch (Exception e) {

        }

    }

    //test code
    public static void main(String[] args) {

        UserDBContext userDB = new UserDBContext();
        User user = new User();
        Setting setting = new Setting();
        setting.setSettingID(7);
        user.setName("aaa");
        user.setUserID(5);
        user.setPhone("222222222");
        user.setEmail("abc@aaa");
        user.setStatus(setting);
        user.setGender(false);
        userDB.updateUser(user);
        System.out.println();
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> user = new ArrayList<>();
        String sql = "SELECT [UserID]\n"
                + "                      ,[Phone]\n"
                + "                      ,u.[Name]\n"
                + "                      ,[Gender]\n"
                + "                     ,[Password]\n"
                + "                     ,[Email]\n"
                + "                      ,[Address]\n"
                + "                     ,[RoleID]\n"
                + "                      ,[Registered_At]\n"
                + "                      ,[Last_Login]\n"
                + "                      ,[Avatar]\n"
                + "                      ,[DOB]\n"
                + "                     ,u.[Status]\n"
                + "                	  ,s.[Name] as RoleName\n"
                + "                	  ,st.[Name] as StatusName\n"
                + "			  ,st.Setting_ID\n"
                + "                  FROM [dbo].[User] u \n"
                + "                  left join Setting s\n"
                + "                  on u.RoleID = s.Setting_ID\n"
                + "                  left join Setting st\n"
                + "                  on u.Status = st.Setting_ID";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setAddress(rs.getString("Address"));
                u.setAvatar(rs.getString("Avatar"));
                u.setDob(rs.getDate("DOB"));
                u.setEmail(rs.getString("Email"));
                u.setGender(rs.getBoolean("Gender"));
                u.setLastLogin(rs.getDate("Last_Login"));
                u.setName(rs.getString("Name"));
                u.setPassword(rs.getString("Password"));
                u.setPhone(rs.getString("Phone"));
                u.setRegisteredAt(rs.getDate("Registered_At"));
                Setting set = new Setting();
                set.setName(rs.getString("RoleName"));
                u.setRole(set);
                Setting sett = new Setting();
                sett.setSettingID(rs.getInt("Setting_ID"));
                sett.setName(rs.getString("StatusName"));
                u.setStatus(sett);
                u.setUserID(rs.getInt("UserID"));
                user.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public void updateUser(User user) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [Phone] = ?\n"
                + "      ,[Name] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Status] = ?\n"
                + "WHERE [User].UserID =?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setString(1, user.getPhone());
            stm.setString(2, user.getName());
            stm.setBoolean(3, user.isGender());
            stm.setString(4, user.getEmail());
            stm.setInt(5, user.getStatus().getSettingID());
            stm.setInt(6, user.getUserID());
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

    public void updateUserChangeStatus(User user) {
        String sql = "UPDATE [dbo].[User]\n"
                + "                  SET              \n"
                + "                     [Status] = ?\n"
                + "                WHERE [User].UserID =?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, user.getStatus().getSettingID());
            stm.setInt(2, user.getUserID());
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

    public User getUserByIdMarketing(String id) {
        String sql = "SELECT [UserID]\n"
                + "                                    ,[Phone]\n"
                + "                                     ,u.[Name]\n"
                + "                                    ,[Gender]\n"
                + "                                     ,[Password]\n"
                + "                                    ,[Email]\n"
                + "                                    ,[Address]\n"
                + "                                    ,[RoleID]\n"
                + "                                     ,[Registered_At]\n"
                + "                                     ,[Last_Login]\n"
                + "                                    ,[Avatar]\n"
                + "                                     ,[DOB]\n"
                + "                                     ,u.[Status]\n"
                + "                                ,s.[Name] as RoleName\n"
                + "                              	  ,st.[Name] as StatusName\n"
                + "								  ,st.Setting_ID\n"
                + "                               FROM [dbo].[User] u \n"
                + "                                left join Setting s\n"
                + "                                on u.RoleID = s.Setting_ID\n"
                + "                                left join Setting st\n"
                + "                               on u.Status = st.Setting_ID\n"
                + "                			 where u.UserID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setAddress(rs.getString("Address"));
                u.setAvatar(rs.getString("Avatar"));
                u.setDob(rs.getDate("DOB"));
                u.setEmail(rs.getString("Email"));
                u.setGender(rs.getBoolean("Gender"));
                u.setLastLogin(rs.getDate("Last_Login"));
                u.setName(rs.getString("Name"));
                u.setPassword(rs.getString("Password"));
                u.setPhone(rs.getString("Phone"));
                u.setRegisteredAt(rs.getDate("Registered_At"));
                Setting set = new Setting();
                set.setName(rs.getString("RoleName"));
                u.setRole(set);
                Setting sett = new Setting();
                sett.setSettingID(rs.getInt("Setting_ID"));
                sett.setName(rs.getString("StatusName"));
                u.setStatus(sett);
                u.setUserID(rs.getInt("UserID"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Setting> getUserStatus() {
        ArrayList<Setting> st = new ArrayList();
        try {
            String sql = "SELECT [Setting_ID]\n"
                    + "      ,[Group]\n"
                    + "      ,[Name]\n"
                    + "      ,[Order]\n"
                    + "      ,[Status]\n"
                    + "  FROM [Setting] WHERE [Group] = 'User Status' OR [Group] = 'User Status'";
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

    public User insert(User user) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([Phone]\n"
                + "           ,[Name]\n"
                + "           ,[Gender]\n"
                + "           ,[Password]\n"
                + "           ,[Email]\n"
                + "           ,[Address]\n"
                + "           ,[RoleID]\n"
                + "           ,[Registered_At]\n"
                + "           ,[Last_Login]\n"
                + "           ,[Avatar]\n"
                + "           ,[DOB]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,'123'\n"
                + "           ,?\n"
                + "           ,NULL\n"
                + "           ,1\n"
                + "           ,GETDATE()\n"
                + "           ,GETDATE()\n"
                + "           ,NULL\n"
                + "           ,GETDATE()\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, user.getPhone());
            stm.setString(2, user.getName());
            stm.setBoolean(3, user.isGender());
            stm.setString(4, user.getEmail());
            stm.setInt(5, user.getStatus().getSettingID());
            stm.executeUpdate();
            return user;
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
    
    public int hasPermission(int id, String feature) {
        String sql = "SELECT COUNT(*) AS 'total'\n"
                + "FROM [User] INNER JOIN [Setting]\n"
                + "ON [User].RoleID = Setting.[Setting_ID]\n"
                + "WHERE [User].UserID =?\n"
                + "AND [Setting].[Name] like ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, feature);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
}
