/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.javeriana.objetos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jesus Bayona
 */
public class consultas {
    public String creacionCarrito (BigDecimal NUM_ID,
                                String ORDER_DATE,
                                BigDecimal PRICE,
                                String STATUS,
                                String COMMENTS,
                                String CUST_DOCUMENT_NUMBER,
                                String CUST_DOCUMENT_TYPE) throws SQLException{
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        try { 
            String Valor ="OK";
            String url= "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection con = DriverManager.getConnection(url,usuario,contrasena); 
            String insert = "INSERT INTO SALES_QUOTE (NUM_ID,ORDER_DATE,PRICE,STATUS,COMMENTS,CUST_DOCUMENT_NUMBER,CUST_DOCUMENT_TYPE)"+
            "VALUES ("+NUM_ID+",TO_DATE('"+ORDER_DATE+"','DD/MM/YYYY'),"+PRICE+",'"+STATUS+"','"+COMMENTS+"','"+CUST_DOCUMENT_NUMBER +"','"+CUST_DOCUMENT_TYPE+"')";
            Statement stmt = con.createStatement(); 
            stmt.executeUpdate(insert);
            con.close();
            return Valor;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return "Se genero un error al guardar el Carro de compras";
        } 
    }
    
    public String creacionQuoteItems (BigDecimal NUM_ID,
                                    BigDecimal PRODUCT_ID,
                                    String PRODUCT_NAME,
                                    String PARTNUM,
                                    BigDecimal PRICE,
                                    BigDecimal QUANTITY,
                                    BigDecimal ORDER_ID) throws SQLException{
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        try { 
            String Valor ="OK";
            String url= "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection con = DriverManager.getConnection(url,usuario,contrasena); 
            String insert = "INSERT INTO QUOTE_ITEMS (NUM_ID,PRODUCT_ID,PRODUCT_NAME,PARTNUM,PRICE,QUANTITY,ORDER_ID)"+
            "VALUES ("+NUM_ID+","+PRODUCT_ID+",'"+PRODUCT_NAME+"','"+PARTNUM+"',"+PRICE +","+QUANTITY+","+ORDER_ID+")";
            Statement stmt = con.createStatement(); 
            stmt.executeUpdate(insert);
            con.close();
            return Valor;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return "Se genero un error al guardar los items del carro de compras";
        } 
    }
    
    public String creacionReserva (BigDecimal NUM_ID,
                                String ORDER_DATE,
                                BigDecimal PRICE,
                                String STATUS,
                                String COMMENTS,
                                String CUST_DOCUMENT_NUMBER,
                                String CUST_DOCUMENT_TYPE) throws SQLException{
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        try { 
            String Valor ="OK";
            String url= "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection con = DriverManager.getConnection(url,usuario,contrasena); 
            String insert = "INSERT INTO SALES_ORDER (ID,ORDER_DATE,PRICE,STATUS,COMMENTS,CUST_DOCUMENT_NUMBER, CUST_DOCUMENT_TYPE)"+
            "VALUES ("+NUM_ID+",TO_DATE('"+ORDER_DATE+"','DD/MM/YYYY'),"+PRICE+",'"+STATUS+"','"+COMMENTS+"','"+CUST_DOCUMENT_NUMBER +"','"+CUST_DOCUMENT_TYPE+"')";
            Statement stmt = con.createStatement(); 
            stmt.executeUpdate(insert);
            con.close();
            return Valor;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return "Se genero un error al guardar la reserva";
        } 
    }
    
    public String creacionItemReserva (BigDecimal NUM_ID,
                                    BigDecimal PRODUCT_ID,
                                    String PRODUCT_NAME,
                                    String PARTNUM,
                                    BigDecimal PRICE,
                                    BigDecimal QUANTITY,
                                    BigDecimal ORDER_ID) throws SQLException{
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        try { 
            String Valor ="OK";
            String url= "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection con = DriverManager.getConnection(url,usuario,contrasena); 
            String insert = "INSERT INTO order_item (ID,PRODUCT_ID,PRODUCT_NAME,PARTNUM,PRICE,QUANTITY,ORDER_ID)"+
            "VALUES ("+NUM_ID+","+PRODUCT_ID+",'"+PRODUCT_NAME+"','"+PARTNUM+"',"+PRICE +","+QUANTITY+","+ORDER_ID+")";
            Statement stmt = con.createStatement(); 
            stmt.executeUpdate(insert);
            con.close();
            return Valor;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return "Se genero un error al guardar los items de la reserva";
        } 
    }
    
    
     public String autenticacion (String email,
                                  String pass) throws SQLException{
        ///jdbc:oracle:thin:@localhost:1521:XE [bayonaje on BAYONAJE]
        try { 
            String Valor ="Bienvenido ";
            String url= "jdbc:oracle:thin:@localhost:1521:XE";
            String usuario = "bayonaje";
            String contrasena = "javeriana";
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
            Connection con = DriverManager.getConnection(url,usuario,contrasena); 
            String insert = "SELECT C.FIRST_NAME, C.LAST_NAME FROM CUSTOMER C "+
            "WHERE C.EMAIL ='"+email+"' AND  C.PASSWORD ='"+pass+"'";
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(insert);
            while(rs.next()){
               String lastName = rs.getString("LAST_NAME");
               String firstName = rs.getString("FIRST_NAME");
               Valor = Valor +firstName +" "+lastName;
            }
            
            con.close();
            return Valor;
        } catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
            return "Usuario y/o contraeña errorenos";
        } 
    }
}
