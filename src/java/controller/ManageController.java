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
import entity.Question;
import entity.QuestionSingle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a method that calls <code>dao.impl.QuestionDAO</code> to get list
 * paging questions, total question by account id from database to calculate
 * total page then forward to <code>ManageQuiz.jsp</code>. Servlet will directly
 * forward to <code>Error.jsp</code> when any error occurs.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class ManageController extends HttpServlet {

    /**
     * Calls <code>dao.impl.QuestionDAO</code> to get list paging questions,
     * total question by account id from database to calculate total page then
     * forward to <code>ManageQuiz.jsp</code>. Servlet will directly forward to
     * <code>Error.jsp</code> when any error occurs.
     *
     * @param request stores attributes: questions, totalPage, pageIndex, numQuestion
     * ex to send to <code>ManageQuiz.jsp</code> and <code>Error.jsp</code>. It is
     * a <code> javax.servlet.http.HttpServletRequest</code>
     * @param response provide HTTP-specific functionality in sending a response
     * to client's web browser. It is a <code> javax.servlet.http.HttpServletResponse
     * </code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            QuestionDAO questionDAO = new QuestionDAOImpl();
            Account account = (Account) request.getSession().getAttribute("account");
            int pageSize = 5;
            int totalQuestion = questionDAO.countTotalQuestion();
            //Get the page you want to go to
            String index = request.getParameter("page");
            if (index == null) {
                index = "1";
            }
            int pageIndex = Integer.parseInt(index);
            //Calculate the number of pages
            int totalPage = (totalQuestion % pageSize == 0) ? (totalQuestion / pageSize) : (totalQuestion / pageSize) + 1;
            List<QuestionSingle> questions = questionDAO.getListPagingQuestion(pageIndex, pageSize);

            request.setAttribute("questions", questions);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("numQuestion", totalQuestion);
            request.getRequestDispatcher("view/html/ManageQuiz.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageController.class.getName()).log(Level.SEVERE, null, ex);
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
