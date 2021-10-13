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

import dao.QuestionDAO;
import dao.impl.QuestionDAOImpl;
import entity.Account;
import entity.Quiz;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a method that gets parameter "number" from
 * <code>ChooseQuestion.jsp</code>, checks that input format is correct or not.
 * If input format is correct, forward to <code>TakeQuiz.jsp</code> else forward
 * to <code>ChooseQuestion.jsp</code> and show error message. Servlet will
 * directly forward to <code>Error.jsp</code> when any error occurs.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class ChooseQuestionController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method. Forward to
     * <code>ChooseQuestion.jsp</code>.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        request.setAttribute("account", account);
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        if (quiz != null) {
            request.getSession().removeAttribute("quiz");
        }
        request.getRequestDispatcher("view/html/ChooseQuestion.jsp").forward(request, response);

    }

    /**
     * Gets parameter "number" from <code>ChooseQuestion.jsp</code>, checks that
     * input format is correct or not. If input format is correct, forward to
     * <code>TakeQuiz.jsp</code> else forward to <code>ChooseQuestion.jsp</code>
     * and show error message. Servlet will directly forward to
     * <code>Error.jsp</code> when any error occurs.
     *
     * @param request stores attributes: invalid, ex to send to
     * <code>ChooseQuestion.jsp</code> and <code>Error.jsp</code>. It is a
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
            String strNumQuestion = request.getParameter("number").trim();
            int numQuestion = 0;
            String url = "view/html/ChooseQuestion.jsp";
            QuestionDAO questionDAO = new QuestionDAOImpl();
            //check input is empty or not number
            if (!checkInputNumber(strNumQuestion)) {
                request.setAttribute("invalid", "Please enter a number!");
                request.getRequestDispatcher(url).forward(request, response);
            } else {

                numQuestion = Integer.parseInt(strNumQuestion);
                if (numQuestion < 1 || numQuestion > questionDAO.countAllQuestion()) {
                    request.setAttribute("invalid", "Number of questions must be"
                            + " greater than 1 and less than " + questionDAO.countAllQuestion() + "!");
                    request.setAttribute("numQues", strNumQuestion);
                    request.getRequestDispatcher(url).forward(request, response);
                } else {
                    response.sendRedirect("TakeQuiz?number=" + numQuestion);
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(ChooseQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("ex", "Error: " + ex.getMessage());
            request.getRequestDispatcher("view/html/Error.jsp").forward(request, response);
        }

    }

    /**
     * Check input is number and is empty or not. If the conditions are
     * satisfied, return true else return false.
     *
     * @param input input will be checked. It is a <code>java.lang.String</code>
     * object.
     * @return a <code>java.lang.Boolean</code> objects.
     */
    private boolean checkInputNumber(String input) {
        if (input.trim().isEmpty()) {
            return false;
        }
        try {
            int check = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
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
