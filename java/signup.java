
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import javax.annotation.Resource;
 import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
 import javax.sql.DataSource;
 import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



@ManagedBean ( name="signup" )
@SessionScoped
public class signup {
private String fname;
private String lname;
private String email;
private String password;

CachedRowSet rowSet=null;

DataSource dataSource;
   public signup() {
        try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("jdbc/addressbook");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
   
public String getfname()
 {
 return fname;
 } 
 public void setfname( String fname )
 {
 this.fname = fname;
 } 

public String getlname()
 {
 return lname;
 }
 public void setlname( String lname )
 {
 this.lname = lname;
 } 
public String getemail()
 {
 return email;
 }
 public void setemail( String email )
 {
 this.email = email;
 } 
public String getpassword()
 {
 return password;
 }
 public void setpassword( String password )
 {
 this.password = password;
 } 
   
   public String ekle() throws SQLException
 {
 // check whether dataSource was injected by the server
 if ( dataSource == null )
 throw new SQLException( "Unable to obtain DataSource" );

 // obtain a connection from the connection pool
 Connection connection = dataSource.getConnection();

 // check whether connection was successful
 if ( connection == null )
 throw new SQLException( "Unable to connect to DataSource" );

 try
 {
 // create a PreparedStatement to insert a new book entry
 PreparedStatement addEntry =
 connection.prepareStatement( "insert into users " +
 "(user_FNAME,user_LNAME,user_email,user_psw,cargo_count)" +
 "values ( ?, ?, ?, ?,0 )" );

 // specify the PreparedStatement's arguments
 addEntry.setString( 1, getfname() );
 addEntry.setString( 2, getlname() );
 addEntry.setString( 3, getemail() );
 addEntry.setString( 4, getpassword() );



addEntry.executeUpdate(); // insert the entry
 return "index"; // go back to index.xhtml home page
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
 } 
}