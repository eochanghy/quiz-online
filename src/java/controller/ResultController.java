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

import dao.ResultDAO;
import dao.impl.ResultDAOImpl;
import entity.Account;
import entity.Quiz;
import entity.Result;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a method that gets parameter quiz then forward to
 * <code>Result.jsp</code> to show result of quiz, calls
 * <code>dao.impl.ResultDAOImpl</code> to insert result of quiz to database.
 * Servlet will directly forward to <code>Error.jsp</code> when any error
 * occurs.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class ResultController extends HttpServlet {

    /**
     * Gets quiz from session then forward to <code>Result.jsp</code> to show
     * result of quiz, calls <code>dao.impl.ResultDAOImpl</code> to insert
     * result of quiz to database and remove attribute quiz on session. Servlet
     * will directly forward to <code>Error.jsp</code> when any error occurs.
     *
     * @param request stores attributes: result, ex to send to
     * <code>Result.jsp</code> and <code>Error.jsp</code>. It is a
     * <code> javax.servlet.http.HttpServletRequest</code>
     * @param response provide HTTP-specific functionality in sending a response
     * to client's web browser. It is a <code> javax.servlet.http.HttpServletResponse
     * </code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
            Account account = (Account) request.getSession().getAttribute("account");
            //submit over time
            if (quiz.getEndTime() + 1000 < System.currentTimeMillis()) {
                request.setAttribute("resultFail", "Late submission of the exam");
            } else {//sunmit on time
                //Get Score and Date
                double totalScore = (double) Math.round(quiz.getScore() * 10) / 10;
                LocalDate dateNow = LocalDate.now();
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String strDateNow = dateFormat.format(dateNow);
                Result result = new Result(account.getId(), totalScore, strDateNow);
                ResultDAO resultDAO = new ResultDAOImpl();
                resultDAO.insertResult(result);
                request.setAttribute("result", totalScore);
                request.getSession().removeAttribute("quiz");
                request.getRequestDispatcher("view/html/Result.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("ex", "Error: " + ex.getMessage());
            request.getRequestDispatcher("view/html/Error.jsp").forward(request, response);
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
        processRequest(request, response);
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
