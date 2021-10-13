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
import dao.ResultDAO;
import entity.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains method about result table to implement. 
 * Method insertResult: Inserts result to result table.  
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class ResultDAOImpl extends DBContext implements ResultDAO{
    /**
     * Inserts result to database.
     *
     * @param result the result will be insert. It is a
     * <code>entity.Result</code> object.
     * @throws java.lang.Exception
     */ 
    @Override
    public void insertResult(Result result) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO [dbo].[Result]\n" +
                    "           ([accountid]\n" +
                    "           ,[result]\n" +
                    "           ,[date])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?)";

            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, result.getAccountId());
            ps.setDouble(2, result.getScore());
            ps.setString(3, result.getDate());
            ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(ResultDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closePrepareStatement(ps);
            closeConnection(con);
        }
    }
    
}
