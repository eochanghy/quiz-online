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
import entity.Account;
import entity.Question;
import entity.QuestionSingle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Contains method about question table to implement. Method insertQuestion:
 * Inserts question to question table, answer of question to answer table.
 * Method countTotalQuestionById: Counts total question by id in question table.
 * Method getListPagingQuestion: Gets list question with paging Method
 * countAllQuestion: Counts total question in question table. Method
 * getRandomQuestion: Gets list random question in question table. Method
 * deleteQuestion: Deletes question by id in question table.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class QuestionDAOImpl extends DBContext implements QuestionDAO {

    @Override
    public List<QuestionSingle> getAllQuestion() throws FileNotFoundException, XMLStreamException {
        List<QuestionSingle> questions = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("D:/FPT_University/SUM2022/PRX301/Project/Lab01_Quiz/Quiz.xml"));
        QuestionSingle question = new QuestionSingle();
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamReader.START_ELEMENT) {
                String name = reader.getName().toString();
                if (name.equals("item")) {
                    question = new QuestionSingle();
                    String id = reader.getAttributeValue("", "id");
                    question.setId(id);
                }
                if ("question".equals(name)) {
                    question.setQuestion(reader.getElementText());
                }
                if ("answer".equals(name)) {
                    String correct = reader.getAttributeValue("", "correct");
                    String answerContent = reader.getElementText();
                    if (correct != null && "y".equals(correct)) {
                        question.setResult(answerContent);
                    }
                    if (question.getOption1() == null) {
                        question.setOption1(answerContent);
//                        reader.next();
                    } else {
                        if (question.getOption2() == null) {
                            question.setOption2(answerContent);
//                            reader.next();
                        } else {
                            if (question.getOption3() == null) {
                                question.setOption3(answerContent);
//                                reader.next();
                            } else {
                                question.setOption4(answerContent);
//                                reader.next();
                            }
                        }
                    }
                }
                if ("subject".equals(name)) {
                    question.setSubject(reader.getElementText());
                }
                if ("level".equals(name)) {
                    question.setLevel(reader.getElementText());
                }
            }
            if (type == XMLStreamReader.END_ELEMENT) {
                String name = reader.getName().toString();
                if ("item".equals(name)) {
                    questions.add(question);
                }
            }
        }
        return questions;
    }

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
    public void insertQuestion(QuestionSingle question) throws Exception {
        List<QuestionSingle> questions = getAllQuestion();
        questions.add(question);

        File file = new File("D:/FPT_University/SUM2022/PRX301/Project/Lab01_Quiz/Quiz.xml");
        writeQuestionsXML(questions, file);
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
    public int countTotalQuestion() throws Exception {
        List<QuestionSingle> questions = getAllQuestion();
        if (questions.size() > 0) {
            return questions.size();
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
    public List<QuestionSingle> getListPagingQuestion(int pageIndex, int pageSize) throws Exception {

        List<QuestionSingle> questions = getAllQuestion();
        List<QuestionSingle> returnQuestions = new ArrayList<>();
        int lastIndex = pageIndex * pageSize > questions.size() ? questions.size() : pageIndex * pageSize;

        for (int i = (pageIndex - 1) * pageSize; i < lastIndex; i++) {
            returnQuestions.add(questions.get(i));
        }
        return returnQuestions;
    }

    /**
     * Counts total question in question table. The result is an int number.
     *
     * @return an int number.
     * @throws java.lang.Exception
     */
    @Override
    public int countAllQuestionCondition(String subject, String level) throws Exception {

        List<QuestionSingle> questions = getAllQuestion();
        if (questions.size() > 0) {
            List<QuestionSingle> returnQuestions = new ArrayList<>();
            for (QuestionSingle q : questions) {
                if (q.getSubject().equalsIgnoreCase(subject) && q.getLevel().equalsIgnoreCase(level)) {
                    returnQuestions.add(q);
                }
            }
            return returnQuestions.size();
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
    public List<QuestionSingle> getRandomQuestion(int numQuestion, String subject, String level) throws Exception {
        List<QuestionSingle> listQuestion = getAllQuestion();
        List<QuestionSingle> listQuestionCondition = new ArrayList<>();
        for (QuestionSingle q : listQuestion) {
            if (q.getSubject().equalsIgnoreCase(subject) && q.getLevel().equalsIgnoreCase(level)) {
                listQuestionCondition.add(q);
            }
        }
        List<QuestionSingle> returnQuestion = new ArrayList<>();
        for (int i = 0; i < numQuestion; i++) {
            Random random = new Random();
            int index = random.nextInt(listQuestionCondition.size());
            returnQuestion.add(listQuestionCondition.get(index));
            listQuestionCondition.remove(index);
        }

        return returnQuestion;
    }

    /**
     * Deletes question by id in question table.
     *
     * @param id the id of question. It is an int number.
     * @throws java.lang.Exception
     */
    @Override
    public void deleteQuestion(String id) throws Exception {
        List<QuestionSingle> questions = getAllQuestion();
        int deleteIndex = -1;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId().equalsIgnoreCase(id)) {
                deleteIndex = i;
            }
        }
        if (deleteIndex != -1) {
            questions.remove(deleteIndex);
            File file = new File("D:/FPT_University/SUM2022/PRX301/Project/Lab01_Quiz/Quiz.xml");
            writeQuestionsXML(questions, file);
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

    private void writeQuestionsXML(List<QuestionSingle> questions, File file) {
        try {

            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            XMLEventWriter writer = factory.createXMLEventWriter(new FileOutputStream(file));
            XMLEventFactory ef = XMLEventFactory.newFactory();

            StartDocument sd = ef.createStartDocument();
            EndDocument ed = ef.createEndDocument();

            StartElement sQuiz = ef.createStartElement("", "", "quiz");
            EndElement eQuiz = ef.createEndElement("", "", "quiz");

            StartElement sItems = ef.createStartElement("", "", "items");
            EndElement eItems = ef.createEndElement("", "", "items");

            StartElement sItem = ef.createStartElement("", "", "item");
            EndElement eItem = ef.createEndElement("", "", "item");

            StartElement sQuestion = ef.createStartElement("", "", "question");
            EndElement eQuestion = ef.createEndElement("", "", "question");

            StartElement sAnswer = ef.createStartElement("", "", "answer");
            EndElement eAnswer = ef.createEndElement("", "", "answer");

            StartElement sSubject = ef.createStartElement("", "", "subject");
            EndElement eSubject = ef.createEndElement("", "", "subject");

            StartElement sLevel = ef.createStartElement("", "", "level");
            EndElement eLevel = ef.createEndElement("", "", "level");

            //add 
            writer.add(sd);
            writer.add(sQuiz);
            writer.add(sItems);
            for (QuestionSingle q : questions) {
                writer.add(sItem);
                Attribute id = ef.createAttribute("id", String.valueOf(q.getId()));
                writer.add(id);

                writer.add(sQuestion);
                writer.add(ef.createCharacters(q.getQuestion()));
                writer.add(eQuestion);

                writer.add(sAnswer);
                if (q.getResult().equalsIgnoreCase(q.getOption1())) {
                    Attribute correct = ef.createAttribute("correct", "y");
                    writer.add(correct);
                }
                writer.add(ef.createCharacters(q.getOption1()));
                writer.add(eAnswer);

                writer.add(sAnswer);
                if (q.getResult().equalsIgnoreCase(q.getOption2())) {
                    Attribute correct = ef.createAttribute("correct", "y");
                    writer.add(correct);
                }
                writer.add(ef.createCharacters(q.getOption2()));
                writer.add(eAnswer);

                writer.add(sAnswer);
                if (q.getResult().equalsIgnoreCase(q.getOption3())) {
                    Attribute correct = ef.createAttribute("correct", "y");
                    writer.add(correct);
                }
                writer.add(ef.createCharacters(q.getOption3()));
                writer.add(eAnswer);

                writer.add(sAnswer);
                if (q.getResult().equalsIgnoreCase(q.getOption4())) {
                    Attribute correct = ef.createAttribute("correct", "y");
                    writer.add(correct);
                }
                writer.add(ef.createCharacters(q.getOption4()));
                writer.add(eAnswer);

                writer.add(sSubject);
                writer.add(ef.createCharacters(q.getSubject()));
                writer.add(eSubject);

                writer.add(sLevel);
                writer.add(ef.createCharacters(q.getLevel()));
                writer.add(eLevel);

                writer.add(eItem);
            }

            writer.add(eItems);
            writer.add(eQuiz);

            writer.add(ed);

            writer.flush();
            writer.close();
        } catch (FileNotFoundException | XMLStreamException ex) {
            Logger.getLogger(AccountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
