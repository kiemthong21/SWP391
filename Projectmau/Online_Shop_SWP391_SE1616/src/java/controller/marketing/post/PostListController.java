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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import model.User;
import model.general.Pageable;
import model.general.ResultPageable;

/**
 *
 * @author Hieuhihi
 */
public class PostListController extends BaseAuthController {
    
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
        int pageSize = 8;
        try {
            SettingDBContext settingDB = new SettingDBContext();
            request.setAttribute("categories", settingDB.getByTypes("Post Category"));
            request.setAttribute("groups", settingDB.getByTypes("User Role"));
            request.setAttribute("status", settingDB.getByTypes("Post Status"));

            String page = request.getParameter("page");
            if (page == null || page.trim().length() == 0) {
                page = "1";
            }
            int pageIndex = 1;
            try {
                pageIndex = Integer.parseInt(page);
                if (pageIndex <= 0) {
                    pageIndex = 1;
                }
            } catch (Exception e) {
                pageIndex = 1;
            }

            PostDBContext postDB = new PostDBContext();
            ResultPageable<Post> resultPageable = null;
            Pageable pageable = new Pageable();
            pageable.setPageIndex(pageIndex);
            pageable.setPageSize(pageSize);

            Map<String, String> orderMap = new HashMap<>();
            String[] sorts =request.getParameterValues("sort");
            if (sorts != null && sorts.length > 0) {
                int count = 0;
                for (String ordering : sorts) {
                    if (ordering != null && ordering.matches("^[a-zA-z]{1,}_(DESC|ASC)$")) {
                        String[] sort = ordering.split("_", 2);
                        if (sort[0].equalsIgnoreCase("id")
                                || sort[0].equalsIgnoreCase("title")
                                || sort[0].equalsIgnoreCase("category")
                                || sort[0].equalsIgnoreCase("author")
//                                || sort[0].equalsIgnoreCase("featured")
                                || sort[0].equalsIgnoreCase("created")
                                || sort[0].equalsIgnoreCase("updated")) {

                            orderMap.put(++count + "." + sort[0], sort[1]);
                        }
                    }
                }
            }
            pageable.setOrderings(orderMap);

            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }

            String[] status = request.getParameterValues("status");
            String[] categories = request.getParameterValues("category");
            String[] authors = request.getParameterValues("author");
            Map<String, ArrayList<String>> filters = new HashMap<>();
            if (status != null && status.length > 0) {
                ArrayList<String> listStatus = new ArrayList<>();
                for (String statu : status) {
                    if (statu.matches("^[0-9]+$") || statu.matches("^[0-9]+$")) {
                        listStatus.add(statu);
                    }
                }
                if (!listStatus.isEmpty()) {
                    filters.put("status", listStatus);
                }
            }
            if (categories != null && categories.length > 0) {
                ArrayList<String> listCategory = new ArrayList<>();
                for (String category : categories) {
                    if (category.matches("[0-9]+")) {
                        listCategory.add(category);
                    }
                }
                if (!listCategory.isEmpty()) {
                    filters.put("category", listCategory);
                }
            }
            if (authors != null && authors.length > 0) {
                ArrayList<String> listAuthor = new ArrayList<>();
                for (String author : authors) {
                    if (author.matches("[0-9]+")) {
                        listAuthor.add(author);
                    }
                }
                if (!listAuthor.isEmpty()) {
                    filters.put("author", listAuthor);
                }
            }
            pageable.setFilters(filters);
            resultPageable = postDB.searchAndFilterAndSort(search, pageable);
            request.setAttribute("posts", resultPageable.getList());
            request.setAttribute("page", resultPageable.getPagination());
            request.setAttribute("pageable", pageable);
            request.setAttribute("search", search);
            request.getRequestDispatcher("/View/marketing/post1.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PostListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
