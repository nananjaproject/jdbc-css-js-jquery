package com.util;

import java.sql.Connection;
import java.sql.SQLException;
 
import oracle.jdbc.pool.OracleDataSource;
 
public class DataHandler {
    public DataHandler() {
    }
    
    public String jdbcUrl = null;
    public String userid = null;
    public String password = null; 
    public Connection conn;
    
    public void getDBConnection() throws SQLException{
    	// JDBC2.0
    	// JDBC3.0
    	// [JDBC4.0 ]
    	// 데이터소스
    	// DataSource ds 객체 생성 후
    	// ds.getConnection() 메서드를 통해서 Connection 객체를 얻어올 수 있더라
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn=ds.getConnection(userid,password);
        
    }
}