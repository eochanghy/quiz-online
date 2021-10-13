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

import dao.QuestionDAO;
import dao.impl.QuestionDAOImpl;
import entity.Question;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            String[] results = request.getParameterValues("cbxOption");
            ArrayList<String> listAnswer = new ArrayList<>();
            for (String result : results) {
                listAnswer.add(result);
            }
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
            //Check answer empty
            if (results == null) {
                request.setAttribute("resultFail", "Please enter answers!");
            } else if (results.length == 4) {//Check choose 4 answer
                request.setAttribute("resultFail", "Do not choose all 4 answers!");
            }
            if (!question.trim().isEmpty() && question.trim().length() <= 200
                    && !op1.trim().isEmpty() && op1.trim().length() <= 100
                    && !op2.trim().isEmpty() && op2.trim().length() <= 100
                    && !op3.trim().isEmpty() && op3.trim().length() <= 100
                    && !op4.trim().isEmpty() && op4.trim().length() <= 100
                    && results != null && results.length != 4) {

                //Get current date 
                LocalDate dateNow = LocalDate.now();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String strDateNow = dateFormat.format(dateNow);
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                Question questionInsert = new Question(question, op1, op2, op3, op4, strDateNow, accountId);
                QuestionDAO questionDAO = new QuestionDAOImpl();
                questionDAO.insertQuestion(questionInsert, listAnswer);
                response.sendRedirect("MakeQuiz?message=success");

            } else {
                request.setAttribute("question", question.trim());
                String[] op = {op1.trim(), op2.trim(), op3.trim(), op4.trim()};
                request.setAttribute("op", op);
                request.setAttribute("opEmpty", opEmpty);
                request.setAttribute("result", results);
                request.getRequestDispatcher(url).forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("ex", "Error: " + e.getMessage());
            request.getRequestDispatcher("view/html/Error.jsp").forward(request, response);
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
