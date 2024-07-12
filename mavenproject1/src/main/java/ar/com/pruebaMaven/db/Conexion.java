/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.pruebaMaven.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sara
 */
public class Conexion {
    /*me indica cual es el driver que voy a utilizar*/
   public String driver="com.mysql.cj.jdbc.Driver";
   
   public Connection getConnection() throws ClassNotFoundException, SQLException{
     Connection conexion= null;
     try{
         /*esto es para que reconozco el driver en todo el proyecto algo así como una variable global */
        Class.forName(driver);
        conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/movies_cac24117","root","");
       
     }
     catch(SQLException e){
         System.out.println("Error en conexion"+e);}
    return conexion;  
   }

 public static void main(String[] args) throws ClassNotFoundException, SQLException{
    Connection conexion=null;
    Conexion con = new Conexion();
    conexion=con.getConnection();
    
    PreparedStatement ps;
    ResultSet rs;
    
     ps=conexion.prepareStatement("Select * from peliculas");
     rs=ps.executeQuery();
     
     while(rs.next()){
         String titulo= rs.getString("titulo");
         System.out.println(titulo);
     }}

}