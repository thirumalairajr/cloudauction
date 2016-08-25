/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudauction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thiru
 */
public class Users {
    static int id;
    DBconnection dbcon;
    ResultSet rs;
    public Users() throws SQLException
    {
        dbcon = new DBconnection();
        rs = dbcon.executeSelectQuery("SELECT MAX(user_id) FROM user;");
        if (!rs.next() )
        {    
            id = 1;
        } 
        else 
        {
            id = rs.getInt(1);
            id++;
        }
        
    }        
    public boolean insertUser(String username,int instance_id) throws SQLException
    {
        String query = "INSERT INTO user VALUES ("+id+",\""+username+"\","+instance_id+",FALSE);";
        return dbcon.executeDMLQuery(query);
    }
    
}
