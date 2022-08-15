/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing.post;

import controller.authentication.BaseAuthController;
import utils.FileUtility;
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

/**
 *
 * @author Hieuhihi
 */
@MultipartConfig
public class PostCreateController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User) request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Marketing");
        return numRead >= 1;
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDBContext settingDB = new SettingDBContext();
        request.setAttribute("status", settingDB.getByTypes("Post status"));
        request.setAttribute("categories", settingDB.getByTypes("Post Category"));
        request.getRequestDispatcher("/View/marketing/postCreate.jsp").forward(request, response);
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
            Post post = new Post();
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
                filename = fileUtility.upLoad(part, folder);
            } else {
                throw new Exception("Thumbnail is required!");
            }

            if (filename != null) {
                post.setThumbnail(filename);
            }
            UserDBContext userDB = new UserDBContext();
            User user = (User) request.getSession().getAttribute("user");
            post.setAuthor(user);
            post.setPostDate(new Date(System.currentTimeMillis()));
            post.setUpdateDate(new Date(System.currentTimeMillis()));
            PostDBContext postDB = new PostDBContext();
            postDB.insert(post);
            response.sendRedirect(request.getContextPath() + "/postmarketing");
        } catch (Exception e) {
            SettingDBContext settingDB = new SettingDBContext();
            request.setAttribute("status", settingDB.getByTypes("Post status"));
            request.setAttribute("categories", settingDB.getByTypes("Post Category"));
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/View/marketing/postCreate.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
