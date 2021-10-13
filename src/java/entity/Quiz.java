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
 * Contains properties, constructor, getter, setter of Quiz object.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class Quiz {

    private double score;
    private ArrayList<Question> questions;
    private long endTime;
    private int currentQuestion;

    /**
     * Parameterless constructor used to initialize a <code>entity.Quiz</code>
     * object.
     */
    public Quiz() {
    }

    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include score, questions,
     * endTime.
     *
     * @param score the score of Quiz. It is an double number.
     * @param questions the question of Quiz. It is a
     * <code>java.util.ArrayList</code> object
     * @param endTime the end time of Quiz. It is an long number.
     */
    public Quiz(double score, ArrayList<Question> questions, long endTime) {
        this.score = score;
        this.questions = questions;
        this.endTime = endTime;
    }

    /**
     * Get value from score attribute
     *
     * @return score of object
     */
    public double getScore() {
        return score;
    }

    /**
     * Set value for score attribute
     *
     * @param score the score of Quiz. It is an double number.
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Get value from questions attribute
     *
     * @return questions of object
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Set value for questions attribute
     *
     * @param questions the questions of Quiz. It is an
     * <code>java.util.ArrayList</code> object.
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * Get value from endTime attribute
     *
     * @return endTime of object
     */
    public long getEndTime() {
        return endTime;
    }

    /**
     * Set value for endTime attribute
     *
     * @param endTime the end time of Quiz. It is an long number.
     */
    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    /**
     * Get value from currentQuestion attribute
     *
     * @return current Question of object
     */
    public int getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * Set value for currentQuestion attribute
     *
     * @param currentQuestion the current Question of Quiz. It is an int number.
     */
    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

}
