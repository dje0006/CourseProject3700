package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
 
/**
 *
 * This is the database application class that is essentially in charge of 
 * sending, retrieving, and udpating information within our database.
 * 
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class DB_App {
 
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
	
 ///////////////////////////////////////////////////////////////////////////////////////////
 ///////////////////////////////////Insertion App///////////////////////////////////////////
 ///////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Insert a new row into the Products table if it is a valid product.
     *
     * @param id
     * @param name
	 * @param price
	 * @param weight
	 * @param quantity
	 * @param producer
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
    
	/**
     * Insert a new row into the OrderItem table if it is a valid Order Item.
     *
     * @param orderNumber
     * @param dateTime
	 * @param itemNumber
	 * @param quantity
	 * @param weight
	 * @param subtotal
	 * @param total
     */
    public boolean insertOrderItem(int orderNum, String dateTime, int itemNum, int quantity, double weight, double subtotal, double total) {
        String sql = "INSERT INTO OrderItem(orderNum, dateTime, itemNum, quantity, weight, subtotal, total) VALUES(?,?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderNum);
            pstmt.setString(2, dateTime);
            pstmt.setInt(3, itemNum);
            pstmt.setInt(4, quantity);
            pstmt.setDouble(5, weight);
            pstmt.setDouble(6, subtotal);
            pstmt.setDouble(7, total);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insert order item not valid");
            return false;
        }
        return true;
    } 
	
	/**
     * Insert a new row into the Order table if it is a valid order.
     *
     * @param orderNum
     * @param dateTime
	 * @param total
     */
    public boolean insertOrder(int orderNum, String dateTime, double total) {
        String sql = "INSERT INTO Orders(orderNum, dateTime, total) VALUES(?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderNum);
            pstmt.setString(2, dateTime);
            pstmt.setDouble(3, total);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("insert Order not valid");
            return false;
        }
        return true;
    }
///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Selection App///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Select a specific product ID from the Products Table. 
	 *
	 * @param id
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

///////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Update App//////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////


	/*
	* Update a Product in the Products Table.
	*
	* @param id
	* @param name
	* @param price
	* @param weight
	* @param quantity
	* @param producer
	*
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
 
            // set the corresponding parameters
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setDouble(3, weight);
            pstmt.setInt(4, quantity);
            pstmt.setInt(5, producer);
            pstmt.setInt(6, id);
            // push the updated information
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/*
	* Update the Products Table in order to reflect a change in the quantity of a Product.
	* In this case, it is decreasing the stored quantity count to reflect the selling of an item.
	* Selling an item results in lowering the product inventory of the store.
	*
	* @param id
	*/
    public void decreaseQuantity(int id) {
        String sql = "UPDATE Products SET quantity = ?  "
                + "WHERE id = ?";
      String result = selectID(id);
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
            
            // set the corresponding parameters
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, id);
            // push the updated information 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/*
	* Update the Products Table in order to reflect a change in the quantity of a Product.
	* In this case, it is increasing the stored quantity count to reflect the acquiring of an item.
	* Acquiring an item results in raising the product inventory of the store.
	*
	* @param id
	*/
    public void increaseQuantity(int id) {
        String sql = "UPDATE Products SET quantity = ?  "
                + "WHERE id = ?";
      String result = selectID(id);
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////Methods for General Usage//////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	* Retrieve the current current transaction information from the Orders Table.
	*/
    public int getCurrentTransactionID() {
        String sql = "SELECT COUNT(*) FROM Orders";
      try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            int result = rs.getInt(1);

            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 200;
    }

}