/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudauction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author thiru
 */
public class DBconnection {
    final String dbURL = "jdbc:mysql://localhost:3306/cloudauction";
    final String username = "root";
    final String password = "";
    Connection DBcon;
    public DBconnection() throws SQLException
    {
        DBcon = DriverManager.getConnection(dbURL,username,password);
    }        
    public ResultSet executeSelectQuery(String query) throws SQLException
    {
        Statement  stmt = DBcon.prepareStatement(query);
        ResultSet rs = stmt.executeQuery(query);
        
        return rs;
    }
    public  boolean executeDMLQuery(String query) throws SQLException
    {
        Statement  stmt = DBcon.prepareStatement(query);
        int rowcount = stmt.executeUpdate(query);
        return (rowcount>0)? true : false; 
    }
    
    
}
