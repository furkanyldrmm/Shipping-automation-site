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

@ManagedBean (name="create")
@RequestScoped
public class CreateCargo {
    DataSource dataSource;
    public CreateCargo(){
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
    String sendname;
    String recname;
    String sadress;
    String messageValue;
    String radress;
    String piece;
    String note;
    public String getSendname(){
        return sendname;
    }
    public void setSendname(String sendname){
        this.sendname = sendname;
    }
    public String getRecname(){
        return recname;
    }
    public void setRecname(String recname){
        this.recname = recname;
    }
    public String getSadress(){
        return sadress;
    }
    public void setSadress(String sadress){
        this.sadress = sadress;
    }
     public String getRadress(){
        return radress;
    }
    public void setRadress(String radress){
        this.radress = radress;
    }
     public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }
     public String getPiece(){
        return sadress;
    }
    public void setPiece(String piece){
        this.piece = piece;
    }
    public String response() throws SQLException{
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

            // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

            // check whether connection was successful
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );
        
        if ( sendname != null && recname != null && radress != null ){
            PreparedStatement stmt;
            stmt = connection.prepareStatement("INSERT INTO CARGOS (sname, rname, pieces,saddress,raddress,notes) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, sendname);
            stmt.setString(2, recname);
            stmt.setString(3, piece);
                        stmt.setString(4, sadress);
            stmt.setString(5, radress);
            stmt.setString(6, note);

            stmt.execute();
            return "Your cargo has been successfully created!";
        }else{
            return "Please fill in the blanks";
        } // request has not yet been made
    }
    
}
