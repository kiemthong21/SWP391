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
public class ResUserDBContext extends DBContext {

    public ArrayList<User> getUserByCondition(String keyword, String by, String gender, String role, String statusI, String sortBy) {
        ArrayList<User> users = new ArrayList();
        if (gender.equals("-1")) {
            gender = "";
        }
        if (role.equals("-1")) {
            role = "";
        }
        if (statusI.equals("-1")) {
            statusI = "";
        }
        try {
            String sql = "SELECT u.[UserID]\n"
                    + "      ,u.[Phone]\n"
                    + "      ,u.[Name]\n"
                    + "      ,u.[Gender]\n"
                    + "      ,u.[Password]\n"
                    + "      ,u.[Email]\n"
                    + "      ,u.[Address]\n"
                    + "      ,u.[RoleID]\n"
                    + "	  ,s.[Name] AS RoleName\n"
                    + "      ,u.[Registered_At]\n"
                    + "      ,u.[Last_Login]\n"
                    + "      ,u.[Avatar]\n"
                    + "      ,u.[DOB]\n"
                    + "      ,u.[Status]\n"
                    + "  FROM [User] u LEFT JOIN [Setting] s ON u.RoleID = s.Setting_ID WHERE u.[" + by + "] Like ? AND u.Gender Like ? AND u.RoleID like ? "
                    + "AND u.Status LIKE ? ";// + sortBy;
            // + "?";
//                    + "OFFSET (1-1)*5 ROWS\n"
//                    + "FETCH NEXT 5 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setString(2, "%" + gender + "%");
            stm.setString(3, "%" + role + "%");
            stm.setString(4, "%" + statusI + "%");
            //stm.setString(6, sortBy);
            //stm.setInt(6, currentPage);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
                users.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    public ArrayList<User> getUser(){
        ArrayList<User> users = new ArrayList();
        try {
            String sql = "SELECT u.[UserID]\n"
                    + "      ,u.[Phone]\n"
                    + "      ,u.[Name]\n"
                    + "      ,u.[Gender]\n"
                    + "      ,u.[Password]\n"
                    + "      ,u.[Email]\n"
                    + "      ,u.[Address]\n"
                    + "      ,u.[RoleID]\n"
                    + "	  ,s.[Name] AS RoleName\n"
                    + "      ,u.[Registered_At]\n"
                    + "      ,u.[Last_Login]\n"
                    + "      ,u.[Avatar]\n"
                    + "      ,u.[DOB]\n"
                    + "      ,u.[Status]\n"
                    + "  FROM [User] u LEFT JOIN [Setting] s ON u.RoleID = s.Setting_ID ";// + sortBy;
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
                users.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public User getUserDetail(int uid) {
        User u = new User();
        try {
            String sql = "SELECT u.[UserID]\n"
                    + "      ,u.[Phone]\n"
                    + "      ,u.[Name]\n"
                    + "      ,u.[Gender]\n"
                    + "      ,u.[Password]\n"
                    + "      ,u.[Email]\n"
                    + "      ,u.[Address]\n"
                    + "      ,u.[RoleID]\n"
                    + "	  ,s.[Name] AS RoleName\n"
                    + "      ,u.[Registered_At]\n"
                    + "      ,u.[Last_Login]\n"
                    + "      ,u.[Avatar]\n"
                    + "      ,u.[DOB]\n"
                    + "      ,u.[Status]\n"
                    + "  FROM [User] u LEFT JOIN [Setting] s ON u.RoleID = s.Setting_ID WHERE u.[UserID] = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                u.setUserID(rs.getInt("UserID"));
                u.setName(rs.getString("Name"));
                u.setEmail(rs.getString("Email"));
                u.setPassword(rs.getString("Password"));
                u.setGender(rs.getBoolean("Gender"));
                u.setPhone(rs.getString("Phone"));
                u.setAvatar(rs.getString("Avatar"));
                u.setAddress(rs.getString("Address"));
                Setting set = new Setting();
                set.setSettingID(rs.getInt("RoleID"));
                set.setName(rs.getString("RoleName"));
                u.setRole(set);
                Setting status = new Setting();
                status.setSettingID(rs.getInt("Status"));
                u.setDob(rs.getDate("DOB"));
                u.setStatus(status);
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateUser(int uid, int role, int status) {
        try {
            String sql = "UPDATE [User]\n"
                    + "   SET \n"
                    + "      [RoleID] = ?\n"
                    + "      ,[Status] = ?\n"
                    + " WHERE [UserID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, role);
            stm.setInt(2, status);
            stm.setInt(3, uid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DashBoardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
