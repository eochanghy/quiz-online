/*
 * Copyright(C) 2021, Nguyen Thanh Dat.
 * J3.L.P0001
 * Quiz Online
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-07-07      1.0                 DatNT           First Implement
 * 2021-07-13      2.0                 DatNT           Fix Comment 
 */
package controller;

import dao.AccountDAO;
import dao.impl.AccountDAOImpl;
import entity.Account;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a method that gets parameter from <code>Register.jsp</code>, checks
 * that input is correct or not, then calls <code>dao.impl.AccountDAOImpl</code>
 * to insert that account to database. If insert success, forward to
 * <code>Home.jsp</code>, else forward to <code>Register.jsp</code>. Servlet
 * will directly forward to <code>Error.jsp</code> when any error occurs.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class RegisterController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method. Forward to
     * <code>Register.jsp</code>
     *
     * @param request servlet request. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response servlet response. It is a <code> javax.servlet.http.HttpServletResponse
     * </code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/html/Register.jsp").forward(request, response);
    }

    /**
     * Gets parameter "user", "password", "email", "type" from
     * <code>Register.jsp</code>, call method <code>checkInput</code> to check
     * that input is correct or not, then calls
     * <code>dao.impl.AccountDAOImpl</code> to insert that account to database.
     * If insert success, forward to <code>Home.jsp</code>, else forward to
     * <code>Register.jsp</code>. Servlet will directly forward to
     * <code>Error.jsp</code> when any error occurs.
     *
     * @param request stores attributes: user, password, account, accountRegis
     * error to send to <code>Home.jsp</code> and <code>Error.jsp</code>. It is
     * a <code> javax.servlet.http.HttpServletRequest</code>
     * @param response provide HTTP-specific functionality in sending a response
     * to client's web browser. It is a <code> javax.servlet.http.HttpServletResponse
     * </code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String url = "view/html/Register.jsp";
            AccountDAO accountDAO = new AccountDAOImpl();
            String user = request.getParameter("user").trim().toLowerCase();
            String pass = request.getParameter("password".trim()).toLowerCase();
            String email = request.getParameter("email").trim().toLowerCase();
            String type = null;
            int typeIndex = Integer.parseInt(request.getParameter("type"));

            if (typeIndex == 1) {
                type = "teacher";
            } else {
                type = "student";
            }
            Account accountRegis = new Account();
            accountRegis.setName(user);
            accountRegis.setPassword(pass);
            accountRegis.setEmail(email);
            accountRegis.setType(type);
            request.setAttribute("accountRegis", accountRegis);
            if (checkInput(request, user, pass, email)) {
                Account account = new Account(user, pass, type, email);
                accountDAO.createAccount(account);
                request.getSession().setAttribute("account", account);
                request.setAttribute("success", "New account registration successful!");
                url = "Home";
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("ex", "Error: " + ex.getMessage());
            request.getRequestDispatcher("view/html/Error.jsp").forward(request, response);
        }
    }

    /**
     * Check length of input and check that input is empty or not. If the
     * conditions are satisfied, return true else return false.
     *
     * @param request servlet request. It is a
     * <code> javax.servlet.http.HttpServletRequest</code> object.
     * @param user userName of account. It is a <code>java.lang.String</code>
     * object.
     * @param pass password of account. It is a <code>java.lang.String</code>
     * object.
     * @param email email of account. It is a <code>java.lang.String</code>
     * object.
     * @param type
     * @return a <code>java.lang.Boolean</code> objects.
     */
    private boolean checkInput(HttpServletRequest request, String user, String pass, String email) {
        boolean check = true;
        try {

            String emailFormat = "^[A-Za-z][\\w.]{1,29}@([0-9A-Za-z]+[.][A-Za-z]{2,4}){1,2}$";
            request.setAttribute("user", user);
            request.setAttribute("pass", pass);
            request.setAttribute("email", email);
            AccountDAO accountDAO = new AccountDAOImpl();
            if (user.trim().isEmpty() || user.length() < 2 || user.trim().length() > 20) {
                request.setAttribute("usernameFail", "Username cannot be empty and the length is from 2 to 20!");
                request.removeAttribute("user");
                check = false;
            } else if (accountDAO.isExistedAccount(user)) {
                request.setAttribute("usernameFail", "Account already exists!");
                request.removeAttribute("user");
                check = false;
            }
            if (pass.trim().isEmpty() || pass.length() < 2 || pass.trim().length() > 20) {
                request.setAttribute("passwordFail", "Password cannot be empty and the length is from 2 to 20!");
                request.removeAttribute("pass");
                check = false;
            }
            if (email.trim().isEmpty() || !email.matches(emailFormat)) {
                request.setAttribute("emailFail", "Wrong format email!");
                request.removeAttribute("email");
                check = false;
            }

        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
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
