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
import entity.Question;
import entity.Quiz;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a method that calls <code>dao.impl.QuestionDAO</code> to get random
 * question from database to create new quiz. After user lick "Next" button,
 * call method doPost to check result and get next question in quiz. Servlet
 * will directly forward to <code>Error.jsp</code> when any error occurs.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class TakeQuizController extends HttpServlet {

    /**
     * Calls <code>dao.impl.QuestionDAO</code> to get random question from
     * database to create new quiz, set timeEnd for the quiz base on number of
     * questions in the quiz. Servlet will directly forward to
     * <code>Error.jsp</code> when any error occurs.
     *
     * @param request stores attributes: question, quiz, ex to send to
     * <code>TakeQuiz.jsp</code> and <code>Error.jsp</code>. It is a
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
        try {
            Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
            //get total number of question in quiz
            int numQuestion = Integer.parseInt(request.getParameter("number"));
            //The quiz hasn't started
            if (quiz == null) {
                QuestionDAO questionDAO = new QuestionDAOImpl();
                ArrayList<Question> questions = new ArrayList<>();
                //get random question
                questions = questionDAO.getRandomQuestion(numQuestion);
                //set time end to the quiz
                long timeEnd = System.currentTimeMillis() + numQuestion * 10 * 1000;
                //create quiz
                quiz = new Quiz();
                quiz.setEndTime(timeEnd);
                quiz.setQuestions(questions);
                quiz.setCurrentQuestion(0);
                quiz.setScore(0);
                //Add the quiz to sesstion
                request.getSession().setAttribute("quiz", quiz);
            }
            request.setAttribute("question", quiz.getQuestions().get(quiz.getCurrentQuestion()));
            request.getRequestDispatcher("view/html/TakeQuiz.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("ex", "Error: " + ex.getMessage());
            request.getRequestDispatcher("view/html/Error.jsp").forward(request, response);
        }
    }

    /**
     * When user click "Next" button, this method will be called. This method
     * get parameter "currentQuestion" and "option" to check result, count score
     * and get next question in the quiz, then forward to
     * <code>TakeQuiz.jsp</code>. After last question, this method will forward
     * to <code>Result.jsp</code>. Servlet will directly forward to
     * <code>Error.jsp</code> when any error occurs.
     *
     * @param request stores attributes: question, quiz, ex to send to
     * <code>TakeQuiz.jsp</code> and <code>Error.jsp</code>. It is a
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
            //Account account = (Account) request.getSession().getAttribute("account");
            Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
            long realTime = System.currentTimeMillis();
            if (quiz != null) {
                //Get index of current question
                int currentQuestionIndex = Integer.parseInt(request.getParameter("currentQuestion"));
                String[] option = request.getParameterValues("option");
                //Count number of correct answer
                int numCorrect = 0;
                Question currentQuestion = quiz.getQuestions().get(currentQuestionIndex);
                ArrayList<Integer> resultQuestion = currentQuestion.getResult();
                //score of quiz
                double score = quiz.getScore();
                if (option != null) {
                    for (int i = 0; i < option.length; i++) {
                        for (int j = 0; j < resultQuestion.size(); j++) {
                            //All option choosed are correct with result
                            if (Integer.parseInt(option[i]) == resultQuestion.get(j)) {
                                numCorrect++;
                            } else {
                                numCorrect = -1;
                            }
                        }
                    }
                    //Calculate score of 1 question
                    double score1Question = 10 * 1 / (quiz.getQuestions().size() * 1.0);
                    //Calculate score archieved
                    if (numCorrect == resultQuestion.size()) {
                        score += score1Question;
                    }
                }
                quiz.setScore(score);
                //If time run out
                if (currentQuestionIndex == quiz.getQuestions().size() - 1 || realTime > quiz.getEndTime() + 50) {
                    //request.getSession().setAttribute("quiz", quiz);
                    response.sendRedirect("Result");
                } else {//increase index of current question by 1 (next question)
                    quiz.setCurrentQuestion(currentQuestionIndex + 1);
                    request.setAttribute("question", quiz.getQuestions().get(quiz.getCurrentQuestion()));
                    request.setAttribute("quiz", quiz);
                    //request.getSession().setAttribute("quiz", quiz);
                    request.getRequestDispatcher("view/html/TakeQuiz.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("ChooseQuestion");
            }
        } catch (Exception ex) {
            request.setAttribute("ex", "Error: " + ex.getMessage());
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
