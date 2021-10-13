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
package dao.impl;

import context.DBContext;
import dao.QuestionDAO;
import entity.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains method about question table to implement. 
 * Method insertQuestion: Inserts question to question table, answer of question to answer table. 
 * Method countTotalQuestionById: Counts total question by id in question table. 
 * Method getListPagingQuestion: Gets list question with paging 
 * Method countAllQuestion: Counts total question in question table. 
 * Method getRandomQuestion: Gets list random question in question table. 
 * Method deleteQuestion: Deletes question by id in question table.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class QuestionDAOImpl extends DBContext implements QuestionDAO {

    /**
     * Inserts question, answer of question to database.
     *
     * @param question the question will be insert. It is a a
     * <code>entity.Question</code> object
     * @param listAnswer the list answer of question. It is a
     * <code>java.util.ArrayList</code> object.
     * @throws java.lang.Exception
     */
    @Override
    public void insertQuestion(Question question, ArrayList<String> listAnswer) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sqlQuestion = "Insert into dbo.Question\n"
                    + "values (?, ?, ?, ?, ?, ?, ?);";
            String sqlAnswer = "";
            for (String as : listAnswer) {
                sqlAnswer += "\nInsert into dbo.Answer\n"
                        + "values (?, (SELECT IDENT_CURRENT('Question')));";
            }
            String sql = sqlQuestion + sqlAnswer;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, question.getQuestion());
            ps.setString(2, question.getOption1());
            ps.setString(3, question.getOption2());
            ps.setString(4, question.getOption3());
            ps.setString(5, question.getOption4());
            ps.setString(6, question.getDate());
            ps.setInt(7, question.getAccountId());
            int count = 7;
            for (String as : listAnswer) {
                count++;
                ps.setString(count, as);
            }
            ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closePrepareStatement(ps);
            closeConnection(con);
        }
    }

    /**
     * Counts total question by id in question table. The result is an int
     * number.
     *
     * @param id the user id of account. It is an int number.
     * @return an int number.
     * @throws java.lang.Exception
     */
    @Override
    public int countTotalQuestionById(int id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) as total FROM Question as q where q.AccountID = ?";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(con);
        }
        return 0;
    }

    /**
     * get list question with paging. All question that get by accountId from
     * row (pageSize*pageIndex - (pageSize-1)) to (pageSize*pageIndex) row in
     * database will be returned. The result contain a list of
     * <code>entity.Question</code> objects with id, question, option1, option2,
     * option3, option4 result, date, accountId.
     *
     * @param accountId the account id of account. It is an int number.
     * @param pageIndex the index of the page
     * @param pageSize maximum number of Question on one page
     * @return a list of <code>entity.Question</code> objects. It is a
     * <code>java.util.List</code> object
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<Question> getListPagingQuestion(int pageIndex, int pageSize, int accountId) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "SELECT a.ID,a.Question, a.Option1,a.Option2,a.Option3,a.Option4,a.Date FROM\n"
                    + " (SELECT ROW_NUMBER() OVER (ORDER BY id ASC) as rid,q.*\n"
                    + " FROM Question as q where q.AccountID = ? ) a\n"
                    + " WHERE rid >= (? - 1)*? + 1\n"
                    + " AND rid <= ? * ?";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageSize);
            ps.setInt(5, pageIndex);
            rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("ID"));
                q.setQuestion(rs.getString("Question"));
                q.setOption1(rs.getString("Option1"));
                q.setOption2(rs.getString("Option2"));
                q.setOption3(rs.getString("Option3"));
                q.setOption4(rs.getString("Option4"));
                q.setDate(rs.getString("Date"));
                questions.add(q);
            }

        } catch (Exception ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(con);
        }
        return questions;
    }

    /**
     * Counts total question in question table. The result is an int number.
     *
     * @return an int number.
     * @throws java.lang.Exception
     */
    @Override
    public int countAllQuestion() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) as total FROM Question as q";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(con);
        }
        return 0;
    }

    /**
     * Gets list random question in database with maximum size. The result
     * contain a list of <code>entity.Question</code> objects with id, question,
     * option1, option2, option3, option4 result, date, accountId.
     *
     * @param numQuestion the maximum question will be returned. It is an int
     * number.
     * @return a list of <code>entity.Question</code> objects. It is a
     * <code>java.util.List</code> object
     * @throws java.lang.Exception
     */
    @Override
    public ArrayList<Question> getRandomQuestion(int numQuestion) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Question> listQuestion = new ArrayList<>();
        try {
            String sql = "select qu.*,a.AnswerContent\n"
                    + "from\n"
                    + "(SELECT top(?) q.*\n"
                    + "from Question as q\n"
                    + "order by NEWID()) as qu, Answer as a\n"
                    + "where qu.ID = a.QuestionID";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, numQuestion);
            rs = ps.executeQuery();
            Question question = new Question();
            question.setId(0);
            ArrayList<Integer> results = new ArrayList<>();
            while (rs.next()) {
                if (question.getId() != rs.getInt(("ID"))) {
                    if (question.getId() != 0) {
                        listQuestion.add(question);
                    }
                    question = new Question();
                    question.setId(rs.getInt("ID"));
                    question.setQuestion(rs.getString("Question"));
                    question.setOption1(rs.getString("Option1"));
                    question.setOption2(rs.getString("Option2"));
                    question.setOption3(rs.getString("Option3"));
                    question.setOption4(rs.getString("Option4"));
                    results = new ArrayList<>();
                    int resultElement = rs.getInt("AnswerContent");
                    results.add(resultElement);
                    question.setResult(results);
                    question.setDate(rs.getString("Date"));
                } else {
                    int resultElement = rs.getInt("AnswerContent");
                    results.add(resultElement);
                    question.setResult(results);
                }
            }
            listQuestion.add(question);

        } catch (Exception ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(con);
        }
        return listQuestion;
    }

    /**
     * Deletes question by id in question table.
     *
     * @param id the id of question. It is an int number.
     * @throws java.lang.Exception
     */
    @Override
    public void deleteQuestion(int id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM [Question]\n"
                    + "where ID = ?;\n"
                    + "DELETE FROM [Answer]\n"
                    + "WHERE QuestionID = ?";

            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closePrepareStatement(ps);
            closeConnection(con);
        }
    }

    /**
     * Get largest questionId in question table. The result is an int number.
     *
     * @return an int number.
     * @throws java.lang.Exception
     */
    @Override
    public int getLargestQuestionId() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT top(1) q.ID\n"
                    + "from Question as q\n"
                    + "Order by q.ID desc";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("ID");
            }

        } catch (Exception ex) {
            Logger.getLogger(QuestionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(con);
        }
        return 0;
    }

}
