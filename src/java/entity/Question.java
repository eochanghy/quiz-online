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
package entity;

import java.util.ArrayList;

/**
 * Contains properties, constructor, getter, setter of Question object.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class Question {

    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private ArrayList<Integer> result;
    private String date;
    private int accountId;

    /**
     * Parameterless constructor used to initialize a
     * <code>entity.Question</code> object.
     */
    public Question() {
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include id, question,
     * option1, option2, option3, option4 result, date, accountId.
     *
     * @param id the id of Question. It is an int number.
     * @param question the question of Question. It is a
     * <code>java.lang.String</code> object
     * @param option1 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option2 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option3 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option4 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param result the result of Question. It is a
     * <code>java.util.ArrayList</code> object
     * @param date the date of Question. It is a <code>java.lang.String</code>
     * object
     * @param accountId the accountId of Question. It is an int number. object
     */
    public Question(int id, String question, String option1, String option2, String option3, String option4, ArrayList<Integer> result, String date, int accountId) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.result = result;
        this.date = date;
        this.accountId = accountId;
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include question, option1,
     * option2, option3, option4, result, date, accountId.
     *
     * @param question the question of Question. It is a
     * <code>java.lang.String</code> object
     * @param option1 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option2 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option3 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option4 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param date the date of Question. It is a <code>java.lang.String</code>
     * object
     * @param accountId the accountId of Question. It is an int number. object
     */
    public Question(String question, String option1, String option2, String option3, String option4, String date, int accountId) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.date = date;
        this.accountId = accountId;
    }
    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include question, option1,
     * option2, option3, option4, date, accountId.
     *
     * @param id the id of question. It is an int number.
     * @param question the question of Question. It is a
     * <code>java.lang.String</code> object
     * @param option1 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option2 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option3 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param option4 the option 1 of Question. It is a
     * <code>java.lang.String</code> object
     * @param date the date of Question. It is a <code>java.lang.String</code>
     * object
     * @param accountId the accountId of Question. It is an int number. object
     */
    public Question(int id, String question, String option1, String option2, String option3, String option4, String date, int accountId) {
        this.id = id;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.date = date;
        this.accountId = accountId;
    }

    
    /**
     * Get value from id attribute
     *
     * @return id of object
     */
    public int getId() {
        return id;
    }

    /**
     * Set value for id attribute
     *
     * @param id the id of Question. It is an int number.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get value from question attribute
     *
     * @return question of object
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Set value for question attribute
     *
     * @param question the question of Question. It is a
     * <code>java.lang.String</code>
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Get value from option1 attribute
     *
     * @return option1 of object
     */
    public String getOption1() {
        return option1;
    }
    /**
     * Set value for option1 attribute
     *
     * @param option1  the option1 of Question. It is a
     * <code>java.lang.String</code>
     */
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    /**
     * Get value from option2 attribute
     *
     * @return option2 of object
     */
    public String getOption2() {
        return option2;
    }
    /**
     * Set value for option2 attribute
     *
     * @param option2  the option2 of Question. It is a
     * <code>java.lang.String</code>
     */
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    /**
     * Get value from option3 attribute
     *
     * @return option3 of object
     */
    public String getOption3() {
        return option3;
    }
    /**
     * Set value for option3 attribute
     *
     * @param option3  the option3 of Question. It is a
     * <code>java.lang.String</code>
     */
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    /**
     * Get value from option4 attribute
     *
     * @return option4 of object
     */
    public String getOption4() {
        return option4;
    }
    /**
     * Set value for option4 attribute
     *
     * @param option4  the option4 of Question. It is a
     * <code>java.lang.String</code>
     */
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    /**
     * Get value from result attribute
     *
     * @return result of object
     */
    public ArrayList<Integer> getResult() {
        return result;
    }
    /**
     * Set value for result attribute
     *
     * @param result the result of Question. It is a
     * <code>java.util.ArrayList</code>
     */
    public void setResult(ArrayList<Integer> result) {
        this.result = result;
    }

    /**
     * Get value from date attribute
     *
     * @return date of object
     */
    public String getDate() {
        return date;
    }
    /**
     * Set value for date attribute
     *
     * @param date the date of Question. It is a
     * <code>java.lang.String</code>
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get value from accountId attribute
     *
     * @return accountId of object
     */
    public int getAccountId() {
        return accountId;
    }
    /**
     * Set value for accountId attribute
     *
     * @param accountId the accountId of Question. It is an int number.
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
