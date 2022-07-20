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
import java.util.List;

/**
 * Contains properties, constructor, getter, setter of Quiz object.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class Quiz {

    private double score;
    private List<QuestionSingle> questions;
    private long endTime;
    private int currentQuestion;

    /**
     * Parameterless constructor used to initialize a <code>entity.Quiz</code>
     * object.
     */
    public Quiz() {
    }

    public Quiz(double score, List<QuestionSingle> questions, long endTime, int currentQuestion) {
        this.score = score;
        this.questions = questions;
        this.endTime = endTime;
        this.currentQuestion = currentQuestion;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<QuestionSingle> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionSingle> questions) {
        this.questions = questions;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    

}
