package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
/**
 *
 * 
 */
public class UpdateApp {
 
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
     * Update data of specified by the id
     *
     * @param id
     * @param name name of the warehouse
     * @param capacity capacity of the warehouse
     */
    public void update(int id,String name, double price, double weight, int quantity, int producer) {
        String sql = "UPDATE Products SET name = ? , "
                + "price = ?, "
                + "weight = ?, "
                + "quantity = ?,"
                + "producer = ?"
                + "WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setDouble(3, weight);
            pstmt.setInt(4, quantity);
            pstmt.setInt(5, producer);
            pstmt.setInt(6, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void decreaseQuantity(int id) {
        String sql = "UPDATE Products SET quantity = ?  "
                + "WHERE id = ?";
        SelectApp app = new SelectApp();
      String result = app.selectID(id);
      int x = 0;
      int quantity = 0;
      String temp = "";
      for (int i = 0; i < result.length(); i++)
      {
         if (result.charAt(i) == '#')
         {
            x++;
            if (x == 5)
            {
               quantity = Integer.parseInt(temp) - 1;
               i = result.length();
            }
            temp = "";
         }
         else
         {
            temp = temp + result.charAt(i);
         }
      }
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // set the corresponding param
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void increaseQuantity(int id) {
        String sql = "UPDATE Products SET quantity = ?  "
                + "WHERE id = ?";
        SelectApp app = new SelectApp();
      String result = app.selectID(id);
      int x = 0;
      int quantity = 0;
      String temp = "";
      for (int i = 0; i < result.length(); i++)
      {
         if (result.charAt(i) == '#')
         {
            x++;
            if (x == 5)
            {
               quantity = Integer.parseInt(temp) + 1;
               i = result.length();
            }
            temp = "";
         }
         else
         {
            temp = temp + result.charAt(i);
         }
      }
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // set the corresponding param
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}