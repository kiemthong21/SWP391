/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.authentication.BaseAuthController;
import dal.ResUserDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class UserListController extends BaseAuthController {

    @Override
    protected boolean isPermission(HttpServletRequest request) {
        UserDBContext userDB = new UserDBContext();
        User user = (User)request.getSession().getAttribute("user");
        int numRead = userDB.hasPermission(user.getUserID(), "Admin");
        return numRead >= 1;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResUserDBContext db = new ResUserDBContext();
        ArrayList<User> users = new ArrayList();
//        UserDBContext userDB = new UserDBContext();
//        ArrayList<Setting> status = userDB.getUserStatus();
//        request.setAttribute("status", status);
//        request.setAttribute("users", users);

        String service = request.getParameter("service");

        Date date;
        String sortBy = "";

//
        if (service.equals("filt")) {
            String keyword = request.getParameter("keyword");
            String by = request.getParameter("searchBy");
            String raw_gender = request.getParameter("gender");
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            String raw_date = request.getParameter("date");

            //String sort = request.getParameter("sort");
            //String by = request.getParameter("by");
            //if(!sort.equals("-1")){
            //     sortBy = " ORDER BY " + sort + " " + by;
            //}
            //response.getWriter().print(sortBy);
//           int roleID;
//           int statusID;
//           boolean gender;

            if (keyword == null) {
                keyword = "";
            }
            if(by == null){
                by = "Name";
            }
            if (role == null) {
                role = "";
            }
            if (raw_gender == null) {
                raw_gender = "";
            } else {
                if (raw_gender.equals("Male")) {
                    raw_gender = "1";
                } else if (raw_gender.equals("Female")) {
                    raw_gender = "0";
                } else {
                    raw_gender = "-1";
                }
            }
            if (status == null) {
                status = "";
            }
            if (raw_date == null) {
                date = Date.valueOf(LocalDate.now());
            } else {
                date = Date.valueOf(raw_date);
            }

            users = db.getUserByCondition(keyword, by, raw_gender, role, status, sortBy);
            request.setAttribute("gender", raw_gender);
            request.setAttribute("role", role);
            request.setAttribute("stt", status);
            request.setAttribute("keyword", keyword);
            request.setAttribute("searchBy", by);
            // request.setAttribute("sort", sort);
            // request.setAttribute("by", by);
            //response.getWriter().print(by);
        } else {
            date = Date.valueOf(LocalDate.now());
            users = db.getUserByCondition("", "Name", "", "", "", sortBy);
            response.getWriter().print(users.size());

            request.setAttribute("gender", -1);
            request.setAttribute("role", -1);
            request.setAttribute("stt", -1);
            request.setAttribute("keyword", null);
            //request.setAttribute("sort", -1);
            //request.setAttribute("by", "ASC");
        }

        //response.getWriter().print(users.size());

        //request.setAttribute("date", date);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/View/Admin/newuserlist.jsp").forward(request, response);
        //request.getRequestDispatcher("/View/marketing/Customers.jsp").forward(request, response);
        //response.getWriter().print(users.size());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDBContext db = new UserDBContext();

        String email = request.getParameter("email");
        String name = request.getParameter("newname");
        boolean gender = request.getParameter("newgender").equals("male");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
        int role = Integer.parseInt(request.getParameter("newrole"));

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz1234567890!@#$%^&*()";
        StringBuilder randomPass = new StringBuilder();
        Random rnd = new Random();
        while (randomPass.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            randomPass.append(SALTCHARS.charAt(index));
        }

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setGender(gender);
        u.setPhone(phone);
        u.setAddress(address);
        u.setDob(dob);
        Setting s = new Setting();
        s.setSettingID(role);
        u.setRole(s);
        u.setRegisteredAt(Date.valueOf(LocalDate.now()));
        Setting st = new Setting();
        st.setSettingID(7);
        u.setStatus(st);
        u.setPassword(randomPass.toString());

        db.CreateUser(u);
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("thongnkhe150031@fpt.edu.vn", "thongav2001");
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("thongnkhe150031@fpt.edu.vn"));
            InternetAddress[] toAddresses = {new InternetAddress(email)};
            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Confirm email", "UTF-8");
            msg.setSentDate(new java.util.Date());
            msg.setContent("\nAccount registered successfully!\n "
                    + "Here is your new password: " + randomPass, "text/html; charset=UTF-8");
            // sends the e-mail
            Transport.send(msg);
            request.setAttribute("mess", "<div class=\"alert alert-success\" role=\"alert\">\n"
                    + "                            \n"
                    + "                            <h4 class=\"alert-heading\"><i class=\"bi bi-check-circle\"></i> SUCCESS!</h4>\n"
                    + "                            <p>Account registered successfully!! </p>\n"
                    + "                            <button type=\"button\" class=\"btn btn-outline-success\"><a href=\"UserList?service=accessPage\" class=\"text-success\">Back to UserList -></a> </button>\n"
                    + "                        </div> ");
            request.getRequestDispatcher("../View/component/regisersuccess.jsp").forward(request, response);
            //response.sendRedirect("UserList?service=accessPage");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            response.sendRedirect("UserList?service=accessPage");
        }
        //response.getWriter().print(randomString);
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
