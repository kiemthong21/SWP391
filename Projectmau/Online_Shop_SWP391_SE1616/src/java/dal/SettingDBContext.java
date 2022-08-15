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
import model.Setting;

/**
 *
 * @author Hieuhihi
 */
public class SettingDBContext extends DBContext {

    public ArrayList<Setting> getSetting() {
        ArrayList<Setting> settings = new ArrayList<>();
        String sql = "SELECT [Setting_ID]\n"
                + "      ,[Group]\n"
                + "      ,[Name]\n"
                + "      ,[Order]\n"
                + "      ,[Status]\n"
                + "  FROM [Online_Shop].[dbo].[Setting]";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setSettingID(rs.getInt("Setting_ID"));
                setting.setGroup(rs.getString("Group"));
                setting.setName(rs.getString("Name"));
                setting.setOrder(rs.getInt("Order"));
                setting.setStatus(rs.getBoolean("Status"));
                settings.add(setting);
            }
            return settings;
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Setting getSettingDetail(int sid) {
        Setting setting = new Setting();
        String sql = "SELECT [Setting_ID]\n"
                + "      ,[Group]\n"
                + "      ,[Name]\n"
                + "      ,[Order]\n"
                + "      ,[Status]\n"
                + "  FROM [Online_Shop].[dbo].[Setting] WHERE [Setting_ID] = ? ";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                setting.setSettingID(rs.getInt("Setting_ID"));
                setting.setGroup(rs.getString("Group"));
                setting.setName(rs.getString("Name"));
                setting.setOrder(rs.getInt("Order"));
                setting.setStatus(rs.getBoolean("Status"));
            }
            return setting;
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Setting> getByTypes(String type) {
        ArrayList<Setting> settings = new ArrayList<>();
        String sql = "SELECT [Setting_ID]\n"
                + "      ,[Group]\n"
                + "      ,[Name]\n"
                + "      ,[Order]\n"
                + "      ,[Status]\n"
                + "  FROM [Online_Shop].[dbo].[Setting]"
                + "  WHERE UPPER([setting].[Group]) = UPPER(?)"
                + "  ORDER BY [setting].[Order] ASC";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, type);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setSettingID(rs.getInt("Setting_ID"));
                setting.setGroup(rs.getString("Group"));
                setting.setName(rs.getString("Name"));
                setting.setOrder(rs.getInt("Order"));
                setting.setStatus(rs.getBoolean("Status"));
                settings.add(setting);
            }
            return settings;
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Setting getById(int status) {
        String sql = "SELECT [Setting_ID]\n"
                + "      ,[Group]\n"
                + "      ,[Name]\n"
                + "      ,[Order]\n"
                + "      ,[Status]\n"
                + "  FROM [Online_Shop].[dbo].[Setting]"
                + " WHERE [Setting_ID] = ?";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting();
                setting.setSettingID(rs.getInt("Setting_ID"));
                setting.setGroup(rs.getString("Group"));
                setting.setName(rs.getString("Name"));
                setting.setOrder(rs.getInt("Order"));
                setting.setStatus(rs.getBoolean("Status"));
                return setting;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createSetting(Setting s) {
        String sql = "INSERT INTO [dbo].[Setting]\n"
                + "           ([Group]\n"
                + "           ,[Name]\n"
                + "           ,[Order]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, s.getGroup());
            stm.setString(2, s.getName());
            stm.setInt(3, s.getOrder());
            stm.setBoolean(4, s.isStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateSetting(Setting s, int sid) {
        String sql = "UPDATE [Setting]\n"
                + "   SET [Group] = ?\n"
                + "      ,[Name] = ?\n"
                + "      ,[Order] = ?\n"
                + "      ,[Status] = ?\n"
                + " WHERE Setting_ID = ?";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, s.getGroup());
            stm.setString(2, s.getName());
            stm.setInt(3, s.getOrder());
            stm.setBoolean(4, s.isStatus());
            stm.setInt(5, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SettingDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
