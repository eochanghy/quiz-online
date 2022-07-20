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

import entity.Account;
import java.util.List;

/**
 * Defines method about account table to implement. 
 * Method getAccount: Gets account by user name and password. 
 * Method createAccount: Inserts account to account table. 
 * Method isExistedAccount: Checks account is existed in database.
 * Method getListPagingSearchResult: Gets list search result article with paging.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public interface AccountDAO {

    List<Account> getAllAccount() throws Exception;
    
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
    Account getAcount(String name, String password) throws Exception;

    /**
     * Inserts account to database.
     *
     * @param a the account will be insert. It is a
     * <code>entity.Account</code> object
     * @throws java.lang.Exception
     */
    void createAccount(Account a) throws Exception;

    /**
     * Checks account is existed in database. If account is existed, return true
     * else return false. The result is a <code>java.lang.Boolean</code> objects.
     *
     * @param userName the user name of account. It is a
     * <code>java.lang.String</code> object
     * @return a <code>java.lang.Boolean</code> objects.
     * @throws java.lang.Exception
     */
    boolean isExistedAccount(String userName) throws Exception;

}
