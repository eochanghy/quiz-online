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

import entity.Result;

/**
 * Defines method about result table to implement. 
 * Method insertResult: Inserts result to result table.  
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public interface ResultDAO {
    /**
     * Inserts result to database.
     *
     * @param result the result will be insert. It is a
     * <code>entity.Result</code> object.
     * @throws java.lang.Exception
     */    
    void insertResult(Result result) throws Exception;
}
