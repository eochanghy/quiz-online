/*
 * Copyright(C) 2021, Nguyen Thanh Dat.
 * J3.L.P0001
 * Quiz Online
 *
 * Record of change:
 * DATE            Version             AUTHOR           DESCRIPTION
 * 2021-07-07      1.0                 DatNT           First Implement
 * 2021-07-13      2.0                 DatNT           Fix Comment 
 * 2021-07-23      3.0                 DatNT           Combine 2 function insert question and answer
 */
package controller;

import dao.AccountDAO;
import dao.QuestionDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.QuestionDAOImpl;
import entity.Account;
import entity.Question;
import entity.QuestionSingle;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a method that gets parameter from <code>MakeQuiz.jsp</code>, checks
 * that input format is correct or not, then calls
 * <code>dao.impl.QuestionDAOImpl</code> to insert that question to database. If
 * insert success, forward to <code>MakeQuiz.jsp</code> then show success
 * message. Servlet will directly forward to <code>Error.jsp</code> when any
 * error occurs..
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class MakeQuizController extends HttpServlet {

    /**
     * Forward to <code>MakeQuiz.jsp</code>. After make quiz success, set
     * attribute "success".
     *
     * @param request stores attributes: success to send to
     * <code>MakeQuiz.jsp</code>. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response provide HTTP-specific functionality in sending a response
     * to client's web browser. It is a <code> javax.servlet.http.HttpServletResponse
     * </code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Account account = (Account) request.getSession().getAttribute("account");
        String message = request.getParameter("message");
        if (message != null && message.equalsIgnoreCase("success")) {
            request.setAttribute("success", "Make quiz successful!");
        }
        //request.setAttribute("account", account);
        request.getRequestDispatcher("view/html/MakeQuiz.jsp").forward(request, response);
    }

    /**
     * Gets parameter "question", "option1", "option2", "option3", "option4",
     * "cbxOption" from <code>MakeQuiz.jsp</code>, checks that input format is
     * correct or not, then calls <code>dao.impl.QuestionDAOImpl</code> to
     * insert that question, answer to database. If insert success, forward to
     * <code>MakeQuiz.jsp</code> then show success message. Servlet will
     * directly forward to <code>Error.jsp</code> when any error occurs..
     *
     * @param request stores attributes: question, op, op_empty, result error to
     * send to <code>MakeQuiz.jsp</code> and <code>Error.jsp</code>. It is a
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "view/html/MakeQuiz.jsp";
        try {
            String question = request.getParameter("question").trim();
            String op1 = request.getParameter("option1").trim();
            String op2 = request.getParameter("option2").trim();
            String op3 = request.getParameter("option3").trim();
            String op4 = request.getParameter("option4").trim();
            String correctAnswer = request.getParameter("correctAnswer").trim();
            String subject = request.getParameter("subject").trim();
            String level = request.getParameter("level").trim();
            String[] opEmpty = new String[4];
            //Check question empty
            if (question.trim().isEmpty() || question.trim().length() > 200) {
                request.setAttribute("questionEmpty", "Please enter a question! (Max length = 200)");
            }
            //Check 4 option empty
            if (op1.trim().isEmpty() || op1.trim().length() > 100) {
                opEmpty[0] = "Please enter option number 1! (Max length = 100)";
            }
            if (op2.trim().isEmpty() || op2.trim().length() > 100) {
                opEmpty[1] = "Please enter option number 2! (Max length = 100)";
            }
            if (op3.trim().isEmpty() || op3.trim().length() > 100) {
                opEmpty[2] = "Please enter option number 3! (Max length = 100)";
            }
            if (op4.trim().isEmpty() || op1.trim().length() > 100) {
                opEmpty[3] = "Please enter option number 4! (Max length = 100)";
            }

            if (!question.trim().isEmpty() && question.trim().length() <= 500
                    && !op1.trim().isEmpty() && op1.trim().length() <= 300
                    && !op2.trim().isEmpty() && op2.trim().length() <= 300
                    && !op3.trim().isEmpty() && op3.trim().length() <= 300
                    && !op4.trim().isEmpty() && op4.trim().length() <= 300) {

                //Get current date 
                LocalDate dateNow = LocalDate.now();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String strDateNow = dateFormat.format(dateNow);
//                int accountId = Integer.parseInt(request.getParameter("accountId"));
                QuestionSingle questionInsert = new QuestionSingle(String.valueOf(generateNewId()) ,question, op1, op2, op3, op4, correctAnswer, subject, level);
                QuestionDAO questionDAO = new QuestionDAOImpl();
                questionDAO.insertQuestion(questionInsert);
                response.sendRedirect("MakeQuiz?message=success");

            } else {
                request.setAttribute("question", question.trim());
                String[] op = {op1.trim(), op2.trim(), op3.trim(), op4.trim()};
                request.setAttribute("op", op);
                request.setAttribute("opEmpty", opEmpty);
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("ex", "Error: " + e.getMessage());
            request.getRequestDispatcher("view/html/Error.jsp").forward(request, response);
        }
    }

    private int generateNewId() {
        try {
            QuestionDAO qsDao = new QuestionDAOImpl();
            List<QuestionSingle> questions = qsDao.getAllQuestion();
            int id = Integer.parseInt(questions.get(questions.size() - 1).getId())  + 1;
            return id;
        } catch (Exception ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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
