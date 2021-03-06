package Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
import librarymanagementsystem.LibraryManagementSystem;
import static librarymanagementsystem.LibraryManagementSystem.prop;

public final class dbConn {

    private String server;
    private String user;
    private String pass;
    private String database;
    private Connection conn;
    private Properties prop;
    
    public dbConn(){
        
        
        prop = LibraryManagementSystem.getSystemSettings();
            
        String h = prop.getProperty("host");
        String d = prop.getProperty("database");
        String u = prop.getProperty("user");

        if(h != null){
            this.dbConn(h, u, "", d);
            this.connect();
        }else{
            server = "localhost";
            user = "root";
            pass = "";
            database = "librarymanagementsystem";
        }
    }
    
    public void dbConn(String server, String user, String pass, String db){
        this.server = server;
        this.user = user;
        this.pass = pass;
        this.database = db;
    }
    
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+server+"/"+database+"?zeroDateTimeBehavior=convertToNull", user, pass);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch(ClassNotFoundException e){
            System.out.println("Exception: " + e.getMessage());
        }
        return conn;
    }
    
//    public Connection dbconn() {
//        conn = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?zeroDateTimeBehavior=convertToNull", "root", "");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Your not Connected");
//        }
//        return conn;
//    }

    public void executeQuery(String query) {
        Statement st;
        try {
            st = dbconn().createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void Disconnect() throws SQLException{
        conn.close();
    }
    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }
    
    public Connection dbconn(){
        return conn;
    }
    

}

