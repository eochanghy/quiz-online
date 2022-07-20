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
package dao;

import entity.Question;
import entity.QuestionSingle;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines method about question table to implement. 
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
public interface QuestionDAO {

    List<QuestionSingle> getAllQuestion() throws Exception;

    /**
     * Inserts question, answer of question to database.
     *
     * @param q the question will be insert. It is a
     * <code>entity.Question</code> object
     * @param listAnswer the list answer of question. It is a
     * <code>java.util.ArrayList</code> object.
     * @throws java.lang.Exception
     */    
    void insertQuestion(QuestionSingle q) throws Exception;
    /**
     * Counts total question by id in question table. The result is an int number.
     *
     * @param id the user id of account. It is an int number.
     * @return an int number.
     * @throws java.lang.Exception
     */    
    int countTotalQuestion() throws Exception;
    /**
     * get list question with paging. All question that get by
     * accountId from row (pageSize*pageIndex - (pageSize-1)) to
     * (pageSize*pageIndex) row in database will be returned. The result contain
     * a list of <code>entity.Question</code> objects with id, question,
     * option1, option2, option3, option4 result, date, accountId.
     *
     * @param accountId the account id of account. It is an int number.
     * @param pageIndex the index of the page
     * @param pageSize maximum number of Question on one page
     * @return a list of <code>entity.Question</code> objects. It is a
     * <code>java.util.List</code> object
     * @throws java.lang.Exception
     */    
    List<QuestionSingle> getListPagingQuestion(int pageIndex, int pageSize) throws Exception;
    /**
     * Counts total question in question table. The result is an int number.
     *
     * @return an int number.
     * @throws java.lang.Exception
     */        
    int countAllQuestionCondition(String subject, String level) throws Exception;
    /**
     * Gets list random question in database with maximum size. The result contain
     * a list of <code>entity.Question</code> objects with id, question,
     * option1, option2, option3, option4 result, date, accountId.
     *
     * @param numQuestion  the maximum question will be returned. It is an int number.
     * @return a list of <code>entity.Question</code> objects. It is a
     * <code>java.util.List</code> object
     * @throws java.lang.Exception
     */    
    List<QuestionSingle> getRandomQuestion(int numQuestion, String subject, String level) throws Exception;
    /**
     * Deletes question by id in question table.
     *
     * @param id the id of question. It is an int number.
     * @throws java.lang.Exception
     */     
    void deleteQuestion(String id) throws Exception;
    /**
     * Get largest questionId in question table. The result is an int number.
     *
     * @return an int number.
     * @throws java.lang.Exception
     */  
    int getLargestQuestionId()throws Exception;
}
