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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a method that gets parameter "id" from <code>ManageQuiz.jsp</code>,
 * calls <code>dao.impl.QuestionDAOImpl</code> to delete question from database
 * then forward to <code>ManageQuiz.jsp</code>. Servlet will directly forward to
 * <code>Error.jsp</code> when any error occurs.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class DeleteController extends HttpServlet {

    /**
     * Gets parameter "id" from <code>ManageQuiz.jsp</code>, calls
     * <code>dao.impl.QuestionDAOImpl</code> to delete question from database
     * then forward to <code>ManageQuiz.jsp</code>. Servlet will directly
     * forward to <code>Error.jsp</code> when any error occurs.
     *
     * @param request store attribute: id, ex to send to <code>Error.jsp</code>.
     * It is a <code> javax.servlet.http.HttpServletRequest</code>
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
            String id = request.getParameter("id");
            QuestionDAO questionDAO = new QuestionDAOImpl();
            questionDAO.deleteQuestion(id);
            response.sendRedirect("Manage");
        } catch (Exception ex) {
            Logger.getLogger(DeleteController.class.getName()).log(Level.SEVERE, null, ex);
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
