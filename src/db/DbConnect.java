/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author trisha deshmukh
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Statement;
import javax.swing.JOptionPane;

public class DbConnect {
    public static Connection c;
    public static Statement s;
    static{
        try{
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/vv2", "root", "trisha2005");
            s=c.createStatement();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}