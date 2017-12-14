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
         System.out.println("insert order item not valid: " + e);
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
   public boolean insertOrder(int orderNum, String dateTime, double total, int custID) {
      String sql = "INSERT INTO Orders(orderNum, dateTime, total, customerNum) VALUES(?,?,?,?)";
   
      try (Connection conn = this.connect();
      		PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setInt(1, orderNum);
         pstmt.setString(2, dateTime);
         pstmt.setDouble(3, total);
         pstmt.setInt(4, custID);
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println("insert Order not valid: " + e);
         return false;
      }
      return true;
   }


	/**
	 * Insert a new User.
	 */
   public boolean insertUser(String name, int isManager, String photo, String password) {
      String sql = "INSERT INTO Users(name, isManager, photo, password) VALUES(?,?,?,?)";
   
      try (Connection conn = this.connect();
      		PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setString(1, name);
         pstmt.setInt(2, isManager);
         pstmt.setString(3, photo);
         pstmt.setString(4, password);
      
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println("insert User not valid");
         return false;
      }
      return true;
   }

	/**
	 * Insert a new User.
	 */
   public boolean insertCustomer(String name) {
      String sql = "INSERT INTO Customer(name,points) VALUES(?,?)";
   
      try (Connection conn = this.connect();
      		PreparedStatement pstmt = conn.prepareStatement(sql)) {
         pstmt.setString(1, name);
         pstmt.setInt(2,0);
      
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println("insert User not valid");
         return false;
      }
      return true;
   }
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////Selection App///////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////

	/*
   * Queries the database for calculating the overall total the store has obtained.
   */
   public int getOverallTotal()
   {
      String sql = "SELECT total FROM Orders";
      
      try (Connection conn = this.connect();
      		Statement stmt  = conn.createStatement();
      		ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
         int result = 0;
         while (rs.next()) {
            result = result + rs.getInt("total");
         }
         return result;
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return 0;
   }
   
   /*
   * Queries the database for the date and total from the Orders table.
   */
   public String getDateTotal() 
   {
      String sql = "SELECT * FROM Orders ORDER BY dateTime ASC";
      
      try (Connection conn = this.connect();
      		Statement stmt  = conn.createStatement();
      		ResultSet rs    = stmt.executeQuery(sql)){
      
      	// loop through the result set
         String result = "";
         while (rs.next()) {
            result = result + rs.getString("dateTime") + "#" + rs.getDouble("total") + "#";
         }
         return result.substring(0,result.length()-1);
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }
   
   public String getAllProducts(){
   
      String sql = "SELECT * FROM Products ORDER BY name ASC";
   
      try (Connection conn = this.connect();
      		Statement stmt  = conn.createStatement();
      		ResultSet rs    = stmt.executeQuery(sql)){
      
      	// loop through the result set
         String result = "";
         while (rs.next()) {
            result = result + rs.getInt("id") + "#" + rs.getString("name") + "#";
         }
         return result.substring(0,result.length()-1);
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }

	/*
   * Queries the database for the order numbers, date, and total from the Orders table.
   */
   public String getAllOrders()
   {
      String sql = "SELECT * FROM Orders ORDER BY orderNum ASC";
      
      try (Connection conn = this.connect();
      		Statement stmt  = conn.createStatement();
      		ResultSet rs    = stmt.executeQuery(sql)){
      
      	// loop through the result set
         String result = "";
         while (rs.next()) {
            result = result + rs.getInt("orderNum") + "#" + rs.getString("dateTime") + "#" + rs.getDouble("total") + "#";
         }
         return result.substring(0,result.length()-1);
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }

   public String getQuantitySold(){
   
      String sql = "SELECT * FROM OrderItem ORDER BY itemNum ASC";
   
      try (Connection conn = this.connect();
      		Statement stmt  = conn.createStatement();
      		ResultSet rs    = stmt.executeQuery(sql)){
      
      	// loop through the result set
         String result = "";
         int[][] itemsSold = new int[100][2];
         int i = -1;
         while (rs.next()) {
            if(i < 0) {
               i = 0;
               itemsSold[i][0] = rs.getInt("itemNum");
               itemsSold[i][1] = rs.getInt("quantity");
            }
            else if(rs.getInt("itemNum") != itemsSold[i][0]){
               i++;
               itemsSold[i][0] = rs.getInt("itemNum");
               itemsSold[i][1] = rs.getInt("quantity");
            }
            else{
               itemsSold[i][1] = itemsSold[i][1] + rs.getInt("quantity");
            }
         }
         for(int x = 0; x <= i; x++){
            result = result + itemsSold[x][0] + "#" + itemsSold[x][1] + "#";
         }
         return result.substring(0,result.length()-1);
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return null;
   }


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


	/**
	 * Select a specific product ID from the Products Table. 
	 *
	 * @param id
	 */
   public String selectUser(String name, String pass){
   
      String sql = "SELECT * FROM Users WHERE name = " +"\"" +name + "\"";
   
      try (Connection conn = this.connect();
      		Statement stmt  = conn.createStatement();
      		ResultSet rs    = stmt.executeQuery(sql)){
      
      	// loop through the result set
         String result = "";
         while (rs.next()) {
            if(rs.getString("password").equals(pass)) {
               result ="" + rs.getInt("isManager") + rs.getInt("id") + "#";
               if (rs.getString("photo").length() == 0)
                  result = result + "1";
               else
                  result = result +rs.getString("photo");
            }
         }
         return result;
      } catch (SQLException e) {
         System.out.println(e);
      }
      return null;
   }

	
	/**
	 * Select a specific product ID from the Products Table. 
	 *
	 * @param id
	 */
   public String selectCustomer(int pass){
   
      String sql = "SELECT * FROM Customer WHERE id = " + pass;
   
      try (Connection conn = this.connect();
      		Statement stmt  = conn.createStatement();
      		ResultSet rs    = stmt.executeQuery(sql)){
      
      	// loop through the result set
         String result = "";
         while (rs.next()) {
            result = result + rs.getString("name") + "#";
            result = result + rs.getInt("points");
         }
         return result;
      } catch (SQLException e) {
         System.out.println(e);
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



   public void savePoints(int id, int points) {
      String sql = "UPDATE Customer SET points = ?  "
         	+ "WHERE id = ?";
      try (Connection conn = this.connect();
      		PreparedStatement pstmt = conn.prepareStatement(sql)) {
      
      	// set the corresponding param
         pstmt.setInt(1, points);
         pstmt.setInt(2, id);
      	// update 
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }




   public void changeUserPhoto(int id,String img) {
      String sql = "UPDATE Users SET photo  = ?  "
         	+ "WHERE id = ?";
   
      try (Connection conn = this.connect();
      		PreparedStatement pstmt = conn.prepareStatement(sql)) {
      
      	// set the corresponding parameters
         pstmt.setString(1, img);
         pstmt.setInt(2, id);
      	// push the updated information
         pstmt.executeUpdate();
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
   }
	
	
   public void changeUserPassword(int id,String newPass) {
      String sql = "UPDATE Users SET password  = ?  "
         	+ "WHERE id = ?";
   
      try (Connection conn = this.connect();
      		PreparedStatement pstmt = conn.prepareStatement(sql)) {
      
      	// set the corresponding parameters
         pstmt.setString(1, newPass);
         pstmt.setInt(2, id);
      	// push the updated information
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