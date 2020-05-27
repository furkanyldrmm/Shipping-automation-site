
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
@ManagedBean (name="vehicles")
@SessionScoped
public class Vehicles {
    DataSource dataSource;
    public Vehicles(){
        try{
            Context ctx = new InitialContext();
            // You must write the database you will use. Here we use "sample" built-in database.
            dataSource = (DataSource) ctx.lookup("jdbc/addressbook");
        }catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public String printVehicles() throws SQLException{
        if ( dataSource == null )
            throw new SQLException( "Unable to obtain DataSource" );

        // obtain a connection from the connection pool
        Connection connection = dataSource.getConnection();

        // check whether connection was successful
        if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );
        String html = "<div class=\"introducingVehicles\" style=\" margin-top:100px;width:60%;float:left;margin-left:20%;background-color:#F2F2F2;border-radius:10px;border: 2px solid #EAEAEA;\">";
        html += "<ul style=\"list-style-type:square;float:left;margin-left:100px;\">";
        html += "<h1><i class=\"fas fa-car\" style=\"margin-right:5px; color: black;\"></i>Cars</h1>";
        PreparedStatement stmt;
        stmt = connection.prepareStatement("SELECT * FROM VEHICLE WHERE VEHICLETYPE = 'car' ORDER BY VEHICLEAMOUNT DESC");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            html += "<li class=\"vehicles\" style=\"border: none;\">" + rs.getString("VEHICLENAME") + " x" + String.valueOf(rs.getInt("VEHICLEAMOUNT")) +  "</li><br></br>";
        }
        html += "</ul>";
        html += "<ul style=\"list-style-type:square;float:left;margin-left:100px;\">";
        html += "<h1><i class=\"fas fa-plane\" style=\"margin-right:5px; color: black;\"></i>Planes</h1>";
        stmt = connection.prepareStatement("SELECT * FROM VEHICLE WHERE VEHICLETYPE = 'plane'");
        rs = stmt.executeQuery();
        while(rs.next()){
            html += "<li class=\"vehicles\" style=\"border: none;\">" + rs.getString("VEHICLENAME") + "</li><br></br>";
        }
        html += "</ul>";
        html += "<ul style=\"list-style-type:square;float:left;margin-left:100px;\">";
        html += "<h1><i class=\"fas fa-ship\" style=\"margin-right:5px; color: black;\"></i>Ships</h1>";
        stmt = connection.prepareStatement("SELECT * FROM VEHICLE WHERE VEHICLETYPE = 'ship'");
        rs = stmt.executeQuery();
        while(rs.next()){
            html += "<li class=\"vehicles\" style=\"border: none;\">" + rs.getString("VEHICLENAME") + "</li>";
        }
        html += "</ul>";
        html += "</div>";
        return html;
    }
}
