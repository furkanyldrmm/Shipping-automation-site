
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author galat
 */
@ManagedBean (name="branch")
@SessionScoped
public class Branches {
    DataSource dataSource;
    public Branches(){
        try{
            Context ctx = new InitialContext();
            // You must write the database you will use. Here we use "sample" built-in database.
            dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
        }catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public String printBranches() throws SQLException{
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );
        String html = "<div class=\"branches\" style=\" margin-top:50px;width:60%;float:left;margin-left:20%;background-color:#F2F2F2;border-radius:10px;border: 2px solid #EAEAEA;\">";
        html += "<ul style=\"float:left;margin-left:50px;\">";

        html+="<h3 style=\"margin-right:5px; color: black;\">Turkey</h3>";
        PreparedStatement stmt;
        stmt = connection.prepareStatement("SELECT * FROM BRANCHES where COUNTRY = 'tr' ORDER BY name ASC");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            html += "<li style=\" display:50px;\">" + rs.getString("name")+ "</li><br></br>";
        }
        html += "</ul>";
     
        html += "<ul style=\"float:left;margin-left:50px;\">";
        html += "<h3 style=\"margin-right:5px; color: black;\">America United State</h3>";
        stmt = connection.prepareStatement("SELECT * FROM BRANCHES WHERE COUNTRY = 'usa' ORDER BY name ASC");
        rs = stmt.executeQuery();
        while(rs.next()){
            html += "<li class=\"vehicles\" style=\"border: none;\">" + rs.getString("name") + "</li><br></br>";
        }
        html += "</ul>";
         html += "<ul style=\"float:left;margin-left:50px;\">";
        html += "<h3 style=\"text-align:center;color: black;\">England</h3>";
        stmt = connection.prepareStatement("SELECT * FROM BRANCHES WHERE COUNTRY = 'en' ORDER BY name ASC");
        rs = stmt.executeQuery();
        while(rs.next()){
            html += "<li class=\"vehicles\" style=\"border: none;\">" + rs.getString("name") + "</li><br></br>";
        }
        html += "</ul>";
            html += "<ul style=\"float:left;margin-left:50px;\">";
        html += "<h3 style=\"margin-right:5px; color: black;\">Germany</h3>";
        stmt = connection.prepareStatement("SELECT * FROM BRANCHES WHERE COUNTRY = 'ge' ORDER BY name ASC");
        rs = stmt.executeQuery();
        while(rs.next()){
            html += "<li class=\"vehicles\" style=\"border: none;\">" + rs.getString("name") + "</li><br></br>";
        }
        html += "</ul></div>";
        return html;
    }
}
