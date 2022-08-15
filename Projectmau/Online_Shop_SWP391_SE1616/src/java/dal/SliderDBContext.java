
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
import model.Slider;
import model.User;

/**
 *
 * @author Admin
 */
public class SliderDBContext extends DBContext {

    public ArrayList<Slider> getSliders() {
        ArrayList<Slider> sliders = new ArrayList();
        try {
            String sql = "SELECT [SlideID]\n"
                    + "      ,[Slide_Title]\n"
                    + "      ,[Slide_Img]\n"
                    + "      ,[Author]\n"
                    + "      ,[Slide_Content]\n"
                    + "      ,[StartDate]\n"
                    + "      ,[Status]\n"
                    + "  FROM [Slide]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSliderID(rs.getInt("SlideID"));
                s.setTitle(rs.getString("Slide_Title"));
                s.setImg(rs.getString("Slide_Img"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                s.setAuthor(u);
                s.setContent(rs.getString("Slide_Content"));
                s.setStartDate(rs.getDate("StartDate"));
                Setting se = new Setting();
                se.setStatus(rs.getBoolean("Status"));
                s.setStatus(se);
                sliders.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public Slider getSlideDetail(int sid) {
        try {
            String sql = "SELECT  [SlideID]\n"
                    + "      ,[Slide_Title]\n"
                    + "      ,[Slide_Img]\n"
                    + "      ,[Author]\n"
                    + "      ,[StartDate]\n"
                    + "      ,[Status]\n"
                    + "      ,[Slide_Content]\n"
                    + "  FROM [Slide] WHERE [SlideID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSliderID(rs.getInt("SlideID"));
                s.setTitle(rs.getString("Slide_Title"));
                s.setImg(rs.getString("Slide_Img"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                s.setAuthor(u);
                s.setContent(rs.getString("Slide_Content"));
                s.setStartDate(rs.getDate("StartDate"));
                Setting se = new Setting();
                se.setStatus(rs.getBoolean("Status"));
                s.setStatus(se);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Slider> getSlidersMarketing() {
        ArrayList<Slider> sliders = new ArrayList();
        try {
            String sql = "SELECT [SlideID]\n"
                    + "                          ,[Slide_Title]\n"
                    + "                          ,[Slide_Img]\n"
                    + "                          ,[Author]\n"
                    + "                          ,[StartDate]\n"
                    + "                          ,sl.[Status]\n"
                    + "                          ,[Slide_Content]\n"
                    + "                    	 ,st.[Name]\n"
                    + "			         ,st.Setting_ID\n"
                    + "                      FROM [dbo].[Slide] sl \n"
                    + "                      inner join Setting st\n"
                    + "                      on sl.[Status] = st.Setting_ID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSliderID(rs.getInt("SlideID"));
                s.setTitle(rs.getString("Slide_Title"));
                s.setImg(rs.getString("Slide_Img"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                s.setAuthor(u);
                s.setContent(rs.getString("Slide_Content"));
                s.setStartDate(rs.getDate("StartDate"));
                Setting se = new Setting();
                se.setName(rs.getString("Name"));
                se.setSettingID(rs.getInt("Setting_ID"));
                s.setStatus(se);
                sliders.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public ArrayList<Setting> getSliderStatus() {
        ArrayList<Setting> st = new ArrayList();
        try {
            String sql = "SELECT [Setting_ID]\n"
                    + "      ,[Group]\n"
                    + "      ,[Name]\n"
                    + "      ,[Order]\n"
                    + "      ,[Status]\n"
                    + "  FROM [Setting] WHERE [Group] = 'Slider Status' OR [Group] = 'Silder Status'";
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

    public static void main(String[] args) {
        SliderDBContext slDB = new SliderDBContext();
        Slider slider = new Slider();
        Setting set = new Setting();
        set.setSettingID(33);
        slider.setTitle("asc");
        slider.setImg("qddfad");
        slider.setStatus(set);
        slDB.insertSlider(slider);
        //       System.out.println(slDB.getslidersbyID(1));

    }

    public void insertSlider(Slider slider) {
        String sql = "INSERT INTO [dbo].[Slide]\n"
                + "                           ([Slide_Title]\n"
                + "                           ,[Slide_Img]\n"
                + "                          ,[Author]\n"
                + "                           ,[StartDate]\n"
                + "                          ,[Status]\n"
                + "                           ,[Slide_Content])\n"
                + "                     VALUES\n"
                + "                           (?\n"
                + "                          ,?\n"
                + "                           ,105\n"
                + "                           ,GETDATE()\n"
                + "                           ,?\n"
                + "                           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, slider.getTitle());
            stm.setString(2, slider.getImg());
            stm.setInt(3, slider.getStatus().getSettingID());
            stm.setString(4, slider.getContent());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Slider getslidersbyID(String id) {
        String sql = "SELECT [SlideID]\n"
                + "                                          ,[Slide_Title]\n"
                + "                                          ,[Slide_Img]\n"
                + "                                         ,[Author]\n"
                + "                                        ,[StartDate]\n"
                + "                                         ,sl.[Status]\n"
                + "                                        ,[Slide_Content]\n"
                + "                                     ,st.[Name]\n"
                + "					 ,st.Setting_ID\n"
                + "                                     FROM [dbo].[Slide] sl \n"
                + "                                    inner join Setting st\n"
                + "                                     on sl.[Status] = st.Setting_ID\n"
                + "                                where sl.SlideID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider s = new Slider();
                s.setSliderID(rs.getInt("SlideID"));
                s.setTitle(rs.getString("Slide_Title"));
                s.setImg(rs.getString("Slide_Img"));
                User u = new User();
                u.setUserID(rs.getInt("Author"));
                s.setAuthor(u);
                s.setContent(rs.getString("Slide_Content"));
                s.setStartDate(rs.getDate("StartDate"));
                Setting se = new Setting();
                se.setName(rs.getString("Name"));
                se.setSettingID(rs.getInt("Setting_ID"));
                s.setStatus(se);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void UpdateSlider(Slider slider) {
        String sql = "UPDATE [dbo].[Slide]\n"
                + "   SET [Slide_Title] = ?\n"
                + "      ,[Slide_Img] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[Slide_Content] = ?\n"
                + " WHERE SlideID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, slider.getTitle());
            stm.setString(2, slider.getImg());
            stm.setInt(3, slider.getStatus().getSettingID());
            stm.setString(4, slider.getContent());
            stm.setInt(5, slider.getSliderID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
