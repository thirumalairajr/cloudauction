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
    final int discount50 = 20;
    
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
    public void DisplayInstances() throws SQLException
    {
        String query = "SELECT * FROM instances WHERE available>0;";
        rs = dbcon.executeSelectQuery(query);
        if (!rs.next()) 
        {                            
            System.out.println("No instances avazilable");

        }
        else 
        {
            System.out.println("InstanceID InstanceName Memory vCPU platform");
            do 
            {
                int instance_id = rs.getInt(1);
                String instance_name = rs.getString(2);
                int memory = rs.getInt(3);
                int vcpu = rs.getInt(4);
                int platform = rs.getInt(5);
                System.out.println(instance_id+") "+instance_name+" "+memory+" "+vcpu+" "+platform);
        
            } while (rs.next());
        }
        
       
    }
    public int calculatePercentage(int max_count,int available)
    {
        return (int) (((float)(max_count-available)/(float)max_count)*100);
    }
    public void requestInstance(int instance_id) throws SQLException
    {
        String query = "UPDATE instances SET available=available-1 WHERE instance_id="+instance_id+";";
        dbcon.executeDMLQuery(query);
        query = "SELECT max_count,available FROM instances WHERE instance_id="+instance_id+";";
        rs = dbcon.executeSelectQuery(query);
        rs.next();
        int max_count = rs.getInt(1);
        int available = rs.getInt(2);
        
        if(calculatePercentage(max_count, available)>=50)
        {
            query = "UPDATE instances SET discount="+discount50+" WHERE instance_id="+instance_id+";";
            dbcon.executeDMLQuery(query);
        }
    }
    
}
