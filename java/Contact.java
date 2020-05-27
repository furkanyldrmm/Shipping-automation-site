/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author galat
 */

@ManagedBean (name="message")
@RequestScoped
public class Contact {
    DataSource dataSource;
    public Contact(){
        try
        {
            Context ctx = new InitialContext();
            // You must write the database you will use. Here we use "sample" built-in database.
            dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
        }
        catch (NamingException e) 
        {
            e.printStackTrace();
        }
    }
    String name;
    String subject;
    String messageValue;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getMessageValue(){
        return messageValue;
    }
    public void setMessageValue(String messageValue){
        this.messageValue = messageValue;
    }
    public String response() throws SQLException{
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

            // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

            // check whether connection was successful
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );
        
        if ( name != null && subject != null && messageValue != null ){
            PreparedStatement stmt;
            stmt = connection.prepareStatement("INSERT INTO MESAJ (messagername, messagesubject, message) VALUES (?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, subject);
            stmt.setString(3, messageValue);
            stmt.execute();
            return "Your Message Has Been Sent!";
        }else{
            return "";
        } // request has not yet been made
    }
    
}
