
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apple
 */
public class DBconnection {
    private Connection con;
    private Statement st;
//    private ResultSet rs;
    private Properties props;
    private String dbLocal;
    public Connection DBCon(){
        try{

                Class.forName("com.mysql.jdbc.Driver");
                props = new Properties();
                props.put("user", "root");
                props.put("password", "287593");
                dbLocal = "jdbc:mysql://localhost:3306/project";
                con = DriverManager.getConnection(dbLocal,props);
                st = con.createStatement();

            }catch(Exception ex){

                System.out.println("CountDb:  "+ex);
            }
        return con;
    }
    
}
