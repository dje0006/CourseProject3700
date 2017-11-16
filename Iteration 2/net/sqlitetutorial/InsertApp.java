package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class InsertApp {
 
    /**
     * Connect to the test.db database
     *
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
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public boolean insertProduct(int id,String name, double price, double weight, int quantity, int producer) {
        String sql = "INSERT INTO Products(id,name,price,weight,quantity,producer) VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setDouble(3, price);
            pstmt.setDouble(4, weight);
            pstmt.setInt(5, quantity);
            pstmt.setInt(6, producer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("not valid");
            return false;
        }
        return true;
    } 
    

    public boolean insertOrderItem(int orderNum, int dateTime, int itemNum, int quantity, double weight, double subtotal, double total) {
        String sql = "INSERT INTO OrderItem(orderNum, dateTime, itemNum, quantity, weight, subtotal, total) VALUES(?,?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderNum);
            pstmt.setInt(2, dateTime);
            pstmt.setInt(3, itemNum);
            pstmt.setInt(4, quantity);
            pstmt.setDouble(5, weight);
            pstmt.setDouble(6, subtotal);
            pstmt.setDouble(7, total);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("not valid");
            return false;
        }
        return true;
    } 

    public boolean insertOrder(int orderNum, int dateTime, double total) {
        String sql = "INSERT INTO Orders(orderNum, dateTime, total) VALUES(?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderNum);
            pstmt.setInt(2, dateTime);
            pstmt.setDouble(3, total);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("not valid");
            return false;
        }
        return true;
    }
 
}