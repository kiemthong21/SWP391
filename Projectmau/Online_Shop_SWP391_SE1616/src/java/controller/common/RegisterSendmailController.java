/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.common;

import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import model.User;

/**
 *
 * @author Admin
 */
public class RegisterSendmailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterSendmailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterSendmailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            boolean gender = request.getParameter("gender").equals("male");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));

            User u = new User();
            u.setEmail(email);
            u.setAddress(address);
            u.setName(name);
            u.setPassword(password);
            u.setGender(gender);
            u.setPhone(phone);
            u.setDob(dob);
            request.getSession().setAttribute("regUser", u);
            
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz1234567890!@#$%^&*()";
            StringBuilder randomString = new StringBuilder();
            Random rnd = new Random();
            while (randomString.length() < 18) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                randomString.append(SALTCHARS.charAt(index));
            }

            String uri = request.getScheme()
                    + "://"
                    + request.getServerName()
                    + ":"
                    + request.getServerPort()
                    + request.getContextPath()
                    + "/RegisterConfirmController";
            System.out.println(uri);
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
            msg.setSentDate(new Date());
            msg.setContent( "\nConfirm email\n "
                    + "<a href=\"" + uri + "\">Confirm your email here</a>", "text/html; charset=UTF-8");
            // sends the e-mail
            Transport.send(msg);
           request.setAttribute("mess", "<div class=\"alert alert-success\" role=\"alert\">\n"
                    + "                            \n"
                    + "                            <h4 class=\"alert-heading\"><i class=\"bi bi-check-circle\"></i> ONE MORE STEP!</h4>\n"
                    + "                            <p>Confirm email has send to your email, please click to the link to finish sign up your account! </p>\n"
                    + "                            <button type=\"button\" class=\"btn btn-outline-success\"><a href=\"https://mail.google.com/mail/u/0/#inbox\" class=\"text-success\">Check your mail here -></a> </button>\n"
                    + "                        </div> ");
            request.getRequestDispatcher("./View/component/regisersuccess.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("./View/Public/ResetPassword.jsp").forward(request, response);
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
