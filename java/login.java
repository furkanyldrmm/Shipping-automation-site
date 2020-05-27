
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



@ManagedBean ( name="login" )
@SessionScoped
public class login {
private String email;
private String password;
private String loggedin="block";
private String logged="none";
private String sayfa;
private String userfname;

CachedRowSet rowSet=null;

DataSource dataSource;
   public login() {
        try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("jdbc/addressbook");
		} catch (NamingException e) {
			e.printStackTrace();
		}
    }
public String goster(){
    return loggedin;
}
public String gitcekmi(){
    if(logged=="block"){
        sayfa="createcargo";
    }else{
        sayfa="login";
    }
    
    return sayfa;
}
public String logout(){
 
    loggedin="block";
    logged="none";
    return "login";
}
public String gosterhesabım(){
    if(loggedin=="block"){
        logged="none";
    }else{
        logged="block";
    }
    return logged;
}
public String getemail()
 {
 return email;
 }
public String getuserfname()
 {
 return userfname;
 }
public void setuserfname( String userfname )
 {
 this.userfname = userfname;
 } 
public String getloggedin()
 {
 return loggedin;
 }
public void setloggedin( String loggedin )
 {
 this.loggedin = loggedin;
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
 
 public String user() throws SQLException
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
     connection.prepareStatement( "Select user_fname from users where user_email=? ");

     addEntry.setString( 1, getemail() );
     ResultSet rs = addEntry.executeQuery();
    if (rs.next() ) {
        userfname=rs.getString("user_fname");
    }
    }
     finally
    {
    connection.close(); // return this connection to pool
    } // end finally
     return userfname;
 }
 
 
  public String cargogetir() throws SQLException
 {
        // check whether dataSource was injected by the server
     if ( dataSource == null )
     throw new SQLException( "Unable to obtain DataSource" );

     // obtain a connection from the connection pool
     Connection connection = dataSource.getConnection();
     
     if ( connection == null )
            throw new SQLException( "Unable to connect to DataSource" );
     
     
     // create a PreparedStatement to insert a new book entry
        String html = "<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <th>Cargo id</th>\n" +
                    "    <th>Reciever Name</th>\n" +
                    "    <th>Sender Adress</th>\n" +
                    "    <th>Reciever Adress</th>\n" +
                    "    <th>Notes</th>\n" +
                    "    <th>Pieces</th>\n" +
                    "  </tr>\n" +
                    "</table>";
        PreparedStatement addEntry;
        addEntry = connection.prepareStatement("SELECT * FROM cargos WHERE sname = ? ");
        addEntry.setString( 1 , getuserfname() );
        ResultSet rs = addEntry.executeQuery();
        while(rs.next()){
             html += "<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <td>"+String.valueOf(rs.getInt("cargoid")) + "</td>\n" +
                    "    <td>"+rs.getString("rname") + "</td>\n" +
                    "    <td>"+rs.getString("saddress") + "</td>\n" +
                    "    <td>"+rs.getString("raddress") + "</td>\n" +
                    "    <td>"+rs.getString("notes") + "</td>\n" +
                    "    <td>"+rs.getString("pieces") + "</td>\n" +
                    "  </tr>\n"+
                    "</table>";
        }
        return html;
    
 }
 
 
 
 public String control() throws SQLException
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
 connection.prepareStatement( "Select user_psw from users where user_email=? and user_psw=?");
 
 addEntry.setString( 1, getemail() );
 addEntry.setString( 2, getpassword() );
 addEntry.execute();
 ResultSet rs = addEntry.getResultSet();
 if (rs.next() ) {
     loggedin="none";
     logged="block";
     return "hesabim";
 }else{
     loggedin="block";
     logged="none";
     return "yanlisifre";
 }
  // go back to index.xhtml home page
 } // end try
 finally
 {
 connection.close(); // return this connection to pool
 } // end finally
 } 
 
}
   
