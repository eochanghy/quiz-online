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
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Contains methods to connect to the database and close the connection
 * <p>
 * Bugs: None
 *
 * @author Nguyen Thanh Dat
 */
public class DBContext {

    private InitialContext initial;
    private Context context;
    private String dbName;
    private String serverName;
    private String portNumber;
    private String userName;
    private String password;

    /**
     * Parameterless constructor used to get data from Context.xml
     */
    public DBContext() {

        try {
            initial = new InitialContext();
            Object obj = initial.lookup("java:comp/env");
            context = (Context) obj;
            serverName = context.lookup("serverName").toString();
            dbName = context.lookup("dbName").toString();
            portNumber = context.lookup("portNumber").toString();
            userName = context.lookup("userName").toString();
            password = context.lookup("password").toString();
        } catch (NamingException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * To connect with the database
     *
     * @return a <code>java.sql.Connection</code> to given database URL
     * @throws Exception
     */
    public Connection getConnection() throws Exception {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    /**
     * Close Connection
     *
     * @param con the Connection. It is a <code>java.sql.Connection</code>
     * object
     * @throws SQLException
     */
    public void closeConnection(Connection con) throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    /**
     * Close PrepareStatement
     *
     * @param ps the PreparedStatement. It is a
     * <code>java.sql.PreparedStatement</code> object
     * @throws SQLException
     */
    public void closePrepareStatement(PreparedStatement ps) throws SQLException {
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
    }

    /**
     * Close ResultSet
     *
     * @param rs the ResultSet. It is a <code>java.sql.ResultSet</code> object
     * @throws SQLException
     */
    public void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }
}
