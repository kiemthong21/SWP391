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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResetPasswordController", urlPatterns = {"/Resetpass"})
public class ResetPasswordController extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./View/Public/ResetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            UserDBContext userDB = new UserDBContext();
            User user = userDB.getUserByEmail(email);
            if (user == null) {
                request.setAttribute("error", "Email not found!");
                request.getRequestDispatcher("./View/Public/ResetPassword.jsp").forward(request, response);
                return;
            }
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghiklmnopqrstuvwxyz1234567890!@#$%^&*()";
            StringBuilder randomString = new StringBuilder();
            Random rnd = new Random();
            while (randomString.length() < 18) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                randomString.append(SALTCHARS.charAt(index));
            }
            //String password = randomString.toString();
            String password ="123";
            user.setPassword(password);
            userDB.changePassword(user);
            String uri = request.getScheme()
                    + "://"
                    + request.getServerName()
                    + ":"
                    + request.getServerPort()
                    + request.getContextPath()
                    + "/reset/confirm/" + user.getUserID() + "/" + password;
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
            InternetAddress[] toAddresses = {new InternetAddress(user.getEmail())};
            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Reset password", "UTF-8");
            msg.setSentDate(new Date());
            msg.setContent("Change your password"
                    + "<a href=\"" + uri + "\">Change password here</a>", "text/html; charset=UTF-8");
            // sends the e-mail
            Transport.send(msg);
            request.getRequestDispatcher("./View/Public/SendEmailSucess.jsp").forward(request, response);
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
