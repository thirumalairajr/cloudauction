/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudauction;

import static cloudauction.Users.id;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thiru
 */
public class Instances {
    
    static int id;
    DBconnection dbcon;
    ResultSet rs;
    
    public Instances() throws SQLException
    {
        dbcon = new DBconnection();
        rs = dbcon.executeSelectQuery("SELECT MAX(instance_id) FROM instances;");
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
    
    
}
