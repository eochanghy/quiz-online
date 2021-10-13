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
 * Contains a method that calls <code>dao.impl.AccountDAOImpl</code> to get
 * account from database. If the account that user input is correct then forward
 * to <code>Home.jsp</code>, else forward to <code>Login.jsp</code>. Servlet
 * will directly forward to <code>Error.jsp</code> when any error occurs.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class LoginController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method. Forward to
     * <code>Login.jsp</code>
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
        request.getRequestDispatcher("view/html/Login.jsp").forward(request, response);
    }

    /**
     * Gets parameter "user" and "password" from <code>Login.jsp</code>, calls
     * <dao.impl.AccountDAOImpl> to get account with that user name and password
     * from database. If the account returned not equal null, forward to
     * <code>Home.jsp</code>, else forward to <code>Login.jsp</code>. Servlet
     * will directly forward to <code>Error.jsp</code> when any error occurs.
     *
     * @param request stores attributes: user, password, account, error to send
     * to <code>Home.jsp</code> and <code>Error.jsp</code>. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
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
            String user = request.getParameter("user").trim().toLowerCase();
            String password = request.getParameter("password").trim().toLowerCase();
            String url = "view/html/Login.jsp";
            request.setAttribute("user", user);
            request.setAttribute("password", password);
            if (checkInput(user, password, request)) {
                AccountDAO accountDAO = new AccountDAOImpl();
                Account account = accountDAO.getAcount(user, password);
                if (account != null) {
                    request.getSession().setAttribute("account", account);
                    url = "Home";
                } else {
                    request.setAttribute("invalid", "Incorrect account or password!");
                }
            }
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("ex", "Error: " + e.getMessage());
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
     * @param password password of account. It is a
     * <code>java.lang.String</code> object.
     * @return a <code>java.lang.Boolean</code> objects.
     */
    private boolean checkInput(String user, String password, HttpServletRequest request) {
        if (user.trim().isEmpty()) {
            request.setAttribute("userFail", "Please enter your username!");
            return false;
        }
        if (user.trim().length() > 20) {
            request.setAttribute("userFail", "Max length is 20 characters");
            return false;
        }
        if (password.trim().isEmpty()) {
            request.setAttribute("passwordFail", "Please enter your Password!");
            return false;
        }
        if (password.trim().length() > 20) {
            request.setAttribute("passwordFail", "Max length is 20 characters");
            return false;
        }
        return true;
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
