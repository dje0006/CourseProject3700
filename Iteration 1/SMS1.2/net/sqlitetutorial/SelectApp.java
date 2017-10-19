package net.sqlitetutorial;
 
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * 
 */
public class SelectApp {
 
    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Store.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
 
    
    /**
     * select all rows in the warehouses table
     */
    public String selectID(int id){
        String sql = "SELECT * FROM Products WHERE id = " + id;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            String result = "";
            while (rs.next()) {
                 result = result + rs.getInt("id") + "#";
                 result = result + rs.getString("name") + "#";
                 result = result + rs.getDouble("price") + "#";
                 result = result + rs.getDouble("weight") + "#";
                 result = result + rs.getInt("quantity") + "#";
                 result = result + rs.getInt("producer") + "#";
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
 
}