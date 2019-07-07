/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.masary.database.manager;

//import static com.masary.database.manager.BaseManger.ds;
import com.masary.utils.SystemSettingsUtil;
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
public class BaseManger {

    private Connection conn = null;
    static DataSource ds;

    /**
     * Get DataSource from tomcat
     */
    static {
        initDataSource();
    }

	public static void initDataSource()
	{
		try {
            Context ic = new InitialContext();
            Context envContext = (Context) ic.lookup("java:/comp/env");
  
            String datasourceName = SystemSettingsUtil.getInstance().loadProperty("config.datasource.name");
            
              ds = (DataSource) envContext.lookup(datasourceName);


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
        try {
            conn = ds.getConnection();
        } catch (Exception ex) {
            MasaryManager.logger.error("Error in getting new database connection " + ex.getMessage(), ex);
        }

        return conn;
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                MasaryManager.logger.error("Close Connection Error  " + ex.getMessage(), ex);
            }
        }
    }

    protected void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
                MasaryManager.logger.error("Close Statement " + e.getMessage(), e);
            }
        }
    }
}
