/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database;

//import static com.masary.database.manager.BaseManager.ds;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;
import javax.naming.*;
import javax.sql.*;

/**
 * Responsible for all database connections.
 *
 * @author Melad
 */
public class BaseManager {

    private Connection conn = null;
    static DataSource ds;

    /**
     * Get DataSource from tomcat
     */
    static {
        try {
            Context ic = new InitialContext();
            Context envContext = (Context) ic.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/topupDB");
//            ds = (DataSource) envContext.lookup("jdbc/topupDev");
//            ds = (DataSource) envContext.lookup("jdbc/topup16DB");
//            ds = (DataSource) envContext.lookup("jdbc/topup16internalDB");
//            ds = (DataSource) envContext.lookup("jdbc/topup16DB");
//            ds = (DataSource) envContext.lookup("jdbc/topupDB2");
//            ds = (DataSource) envContext.lookup("jdbc/topupOldDB");
//            ds = (DataSource) envContext.lookup("jdbc/topupWorkingDB"); 
//            ds = (DataSource) envContext.lookup("jdbc/topup16DB");
//            ds = (DataSource) envContext.lookup("jdbc/topupDB_MASTER");
//            ds = (DataSource) envContext.lookup("jdbc/NewDBServer");   
//            ds = (DataSource) envContext.lookup("jdbc/topupVoucherDB");
//            ds = (DataSource) envContext.lookup("jdbc/cocaDB");
//            ds = (DataSource) envContext.lookup("jdbc/topupTestDB");
//            System.out.println("New DataSource ");
//                MasaryManager.logger.info("New DataSource"+ds);
//            ds = (DataSource) envContext.lookup("jdbc/topupDev");

        } catch (NamingException ex) {
            MasaryManager.logger.error("Error in defining the Data Source " + ex.getMessage(), ex);
        } catch (Exception e) {
            MasaryManager.logger.error("Error in defining the Data Source " + e.getMessage(), e);
        }
    }

    /**
     * Get reusable database connection
     */
    public Connection getConnection() {
//        conn = null;
//        if (conn == null) { // Check
        try {
            conn = ds.getConnection();
            MasaryManager.logger.info("New Database Connection");
        } catch (Exception ex) {
            MasaryManager.logger.error("Error in getting new database connection " + ex.getMessage());
        }
//        }
        return conn;
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                // MasaryManager.logger.info("Database connection has been closed");
            } catch (SQLException ex) {
                MasaryManager.logger.error("Close Connection Error  " + ex.getMessage());
            } finally {
                MasaryManager.logger.info("Database connection has been closed");
            }
        }
    }

    protected void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                MasaryManager.logger.error("Close Statement " + e.getMessage());
            }
        }
    }
}
