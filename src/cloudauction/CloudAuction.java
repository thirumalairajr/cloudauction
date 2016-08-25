/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudauction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author thiru
 */
public class CloudAuction {

    /**
     * @param args the command line arguments
     */
    static Instances instances;
    static Users users;
    public CloudAuction() throws SQLException
    {
        instances = new Instances();
        users = new Users();
    }
    public static void provideOptions() throws SQLException
    {
        Scanner s = new Scanner(System.in);
        main_loop:
        while(true)
        {
            
            System.out.println("1)Request a new resource \n 2)Exit");
            int n = s.nextInt();
            if(n==2)
            {
                System.out.println("Cloud Auction completed!! DB has been updated");
                System.exit(0);
            }
            else
            {
                instances.DisplayInstances();
                System.out.println("Willing to choose any?\n1)Yes\n2)No");
                int choice = s.nextInt();
                if(choice==1)
                {
                    System.out.println("Enter the instance_id");
                    int instance_id = s.nextInt();
                    s.nextLine();
                    System.out.println("Enter your name:");
                    String username = s.nextLine();
                    users.insertUser(username, instance_id);
                    instances.requestInstance(instance_id);
                }
                else
                {
                    continue main_loop;
                }
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        CloudAuction cloudauction = new CloudAuction();
        cloudauction.provideOptions();
    
        
       
    }
    
}
