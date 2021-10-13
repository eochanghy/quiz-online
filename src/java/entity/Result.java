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


/**
 * Contains properties, constructor, getter, setter of Result object.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class Result {
    private int accountId;
    private double score;
    private String date;
    /**
     * Parameterless constructor used to initialize a <code>entity.Result</code>
     * object.
     */
    public Result() {
    }
    /**
     * Constructor has parameters used to initialize an object with information
     * passed in Parameters that need to be passed include accountId, score,
     * date.
     *
     * @param score the score of Result. It is an double number.
     * @param accountId  the accountId of Result. It is an int number.
     * @param date the date of Result. It is a <code>java.lang.String</code> object 
     */
    public Result(int accountId, double score, String date) {
        this.accountId = accountId;
        this.score = score;
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
     * @param accountId the account id of Result. It is an int number.
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
     * @param score the score of Result. It is an double number.
     */
    public void setScore(double score) {
        this.score = score;
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
     * @param date the date of Question. It is a <code>java.lang.String</code> object 
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
