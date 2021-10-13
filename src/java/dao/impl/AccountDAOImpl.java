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
import dao.AccountDAO;
import entity.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains method about account table to implement. 
 * Method getAccount: Gets account by user name and password. 
 * Method createAccount: Inserts account to account table. 
 * Method isExistedAccount: Checks account is existed in database.
 * Method getListPagingSearchResult: Gets list search result article with paging.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class AccountDAOImpl extends DBContext implements AccountDAO {

    /**
     * Gets Account by user name and password. The account that have user name
     * password correct with input will be returned. The result is a
     * <code>entity.Account</code> objects with id, name, password, type, email.
     *
     * @param name the user name of account. It is a
     * <code>java.lang.String</code> object
     * @param password the password of account. It is a
     * <code>java.lang.String</code> object
     * @return a <code>entity.Account</code> objects.
     * @throws java.lang.Exception
     */    
    @Override
    public Account getAcount(String name, String password) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[username]\n"
                    + "      ,[password]\n"
                    + "      ,[type]\n"
                    + "      ,[email]\n"
                    + "  FROM [Account]\n"
                    + "where username = ? and password = ?";

            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("type"),
                        rs.getString("email"));
            }

        } catch (Exception ex) {
            Logger.getLogger(AccountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(con);
        }
        return null;
    }
    /**
     * Inserts account to database.
     *
     * @param a the account will be insert. It is a a
     * <code>entity.Account</code> object
     * @throws java.lang.Exception
     */
    @Override
    public void createAccount(Account a) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO [Account]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[type]\n"
                    + "           ,[email])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getName());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getType());
            ps.setString(4, a.getEmail());
            ps.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(AccountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closePrepareStatement(ps);
            closeConnection(con);
        }

    }
    /**
     * Checks account is existed in database. If account is existed, return true
     * else return false. The result is a <code>java.lang.Boolean</code> objects.
     *
     * @param userName the user name of account. It is a
     * <code>java.lang.String</code> object
     * @return a <code>java.lang.Boolean</code> objects.
     * @throws java.lang.Exception
     */
    @Override
    public boolean isExistedAccount(String userName) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT [username]\n" +
                    "  FROM [Account]\n" +
                    "  where username = ?";

            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(AccountDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            closeResultSet(rs);
            closePrepareStatement(ps);
            closeConnection(con);
        }
        return false;
    }

}
