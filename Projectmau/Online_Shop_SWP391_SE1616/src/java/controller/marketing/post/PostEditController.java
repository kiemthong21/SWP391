/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.post;

import controller.authentication.BaseAuthController;
import dal.PostDBContext;
import dal.SettingDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Post;
import model.Setting;
import model.User;
import utils.FileUtility;

/**
 *
 * @author Hieuhihi
 */
@MultipartConfig
public class PostEditController extends BaseAuthController {
    
    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Marketing");
        return numRead >= 1;
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PostDBContext postDB = new PostDBContext();
        Post post = postDB.getById(id);
        request.setAttribute("post", post);
        SettingDBContext settingDB = new SettingDBContext();
        request.setAttribute("status", settingDB.getByTypes("Post status"));
        request.setAttribute("categories", settingDB.getByTypes("Post Category"));
        request.getRequestDispatcher("/View/marketing/postEdit.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String categoryParam = request.getParameter("category");
            String statusParam = request.getParameter("status");
            String summary = request.getParameter("summary");
            String content = request.getParameter("content");
            int id = Integer.parseInt(request.getParameter("id"));
            PostDBContext postDB = new PostDBContext();
            Post post = postDB.getById(id);
            post.setPostTitle(title);
            post.setSummary(summary);
            post.setPostContent(content);
            int idStatus = 0;
            try {
                idStatus = Integer.parseInt(statusParam);
            } catch (Exception e) {
                throw new Exception("Error set field status");
            }
            int idCategory = 0;
            try {
                idCategory = Integer.parseInt(categoryParam);
            } catch (Exception e) {
                throw new Exception("Error set field category");
            }
            Setting status = new Setting();
            status.setSettingID(idStatus);

            Setting category = new Setting();
            category.setSettingID(idCategory);

            post.setPostStatus(status);
            post.setPostCategory(category);

            Part part = request.getPart("thumbnail");
            FileUtility fileUtility = new FileUtility();

            String folder = request.getServletContext().getRealPath("/View/imgpost");
            String filename = null;

            if (part.getSize() != 0) {
                if(post.getThumbnail()!=null && !post.getThumbnail().trim().isEmpty()){
                    fileUtility.delete(post.getThumbnail(), folder);
                }
                filename = fileUtility.upLoad(part, folder);
            } 

            if (filename != null) {
                post.setThumbnail(filename);
            }
            User user = new User();
            user.setUserID(1);
            post.setAuthor(user);
            post.setPostDate(new Date(System.currentTimeMillis()));
            post.setUpdateDate(new Date(System.currentTimeMillis()));
            postDB.update(post);
            response.sendRedirect(request.getContextPath() + "/postmarketing");
        } catch (Exception e) {
            SettingDBContext settingDB = new SettingDBContext();
            request.setAttribute("status", settingDB.getByTypes("Post status"));
            request.setAttribute("categories", settingDB.getByTypes("Post Category"));
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/View/marketing/postCreate.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
