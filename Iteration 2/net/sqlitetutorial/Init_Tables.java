package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * 
 */
public class Init_Tables {
 
    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:Store.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Products (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	name TEXT NOT NULL,\n"
                + "	price REAL,\n"
                + " weight REAL,\n"
                + " quantity INTEGER,\n"
                + " producer INTEGER\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        url = "jdbc:sqlite:Store.db";
        
        // SQL statement for creating a new table
        sql = "CREATE TABLE IF NOT EXISTS OrderItem (\n"
                + " orderNum INTEGER ,\n"
                + " dateTime TEXT NOT NULL,\n"
                + " itemNum INTEGER ,\n"
                + " quantity INTEGER,\n"
                + " weight REAL,\n"
                + " subtotal REAL,\n"
                + " total REAL,\n"
                + " PRIMARY KEY (orderNum, ItemNum)\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        url = "jdbc:sqlite:Store.db";
        
        // SQL statement for creating a new table
        sql = "CREATE TABLE IF NOT EXISTS Orders (\n"
                + " orderNum INTEGER PRIMARY KEY,\n"
                + " dateTime TEXT NOT NULL,\n"
                + " total REAL\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
 
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createNewTable();
        InsertApp app = new InsertApp();
        app.insertProduct(100,"apple",10.0,0.0,5,200);

    }
 
}