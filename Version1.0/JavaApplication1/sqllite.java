package javaapplication1;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class sqllite {
   
    public void Insertdb(String s,int i) throws Exception {
       
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbtest","root","kk12345");
    
        Statement stat = conn.createStatement();
        
        PreparedStatement prep = conn.prepareStatement("insert into nummer values (?, ?);");
        
        prep.setString(1, s);
        
        prep.setString(2, s);
        
        prep.addBatch();
        
    }
    
    public void Sortdb() throws Exception {
        
        try {
            
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbtest","root","kk12345");
        
        Statement stat = conn.createStatement();
        
        conn.setAutoCommit(true);
    
        ResultSet rs = stat.executeQuery("SELECT * id FROM nummer ORDER BY num");
    
        while (rs.next()) {
            
            int col = rs.getInt(1);
      
            System.out.println("num = " + rs.getString("num"));
      
            System.out.println("id = " + rs.getString("id"));
    
        }
       
        rs.close();
    
        } catch(SQLException e) {
            System.out.println("Error, could not sort the db");
        }
    }
    
    
    public static void run() throws Exception {
        
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dbtest","root","kk12345");
    
        Statement stat = conn.createStatement();
        
        stat.executeUpdate("drop table if exists nummer;");
    
        stat.executeUpdate("create table nummer (id INT(10), num VARCHAR(10));");
        
        conn.setAutoCommit(false);
    
        conn.close();
    }
}

