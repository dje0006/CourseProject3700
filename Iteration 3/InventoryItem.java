//package net.sqlitetutorial;
import java.util.*;

/*
 * This is the Inventory Item Class that defines and controls what contents
 * a product contains. The products in our database have a name, ID number,
 * weight, price, producer, and quantity.
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class InventoryItem {
   private String name;
   private int idNum;
   private double weight;
   private double price;
   private int producer;
   private int quantity;


   /*
   * Empty constructor for an InventoryItem.
   */
   public InventoryItem() {
   }

   /*
   * Used to fill in an item's contents.
   *
   * @param id
   */
   public boolean fillItem(int id){

      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      String result = app.selectID(id);
      if ( result.length() < 5){
         return false;
      }
      int x = 0;
      String temp = "";
      idNum = id;
      for (int i = 0; i < result.length(); i++)
      {
         if (result.charAt(i) == '#')
         {
            x++;
            if (x == 2)
            {
               name = temp;
            }
            if (x == 3)
            {
               price = Double.parseDouble(temp);
            }
            if (x == 4)
            {
               weight = Double.parseDouble(temp);
            }
            if (x == 5)
            {
               quantity = 0;
            }
            if (x == 6)
            {
               producer = Integer.parseInt(temp);
            }
            temp = "";
         }
         else
         {
            temp = temp + result.charAt(i);
         }
      }
      return true;
   }
   
   /*
   * Returns the name of the Inventory Item.
   */
   public String getName() {
      return name;
   }
	
	/*
   * Returns the ID Number of the Inventory Item.
   */
   public int getIDNum() {
      return idNum;
   }

   /*
   * Returns the weight of the Inventory Item.
   */
   public double getWeight(){
      return weight;
   }

   /*
   * Returns the price of the Inventory Item.
   */
   public double getPrice(){
      return price;
   }

   /*
   * Returns the total (price x quantity) of the Inventory Item.
   */
   public double getTotal(){
      return price * quantity;
   }

   /*
   * Sets the price of the Inventory Item.
   *
   * @param newPrice
   */
   public void setPrice(double newPrice){
      price = newPrice;
   }

   /*
   * Returns the producer of the Inventory Item.
   */
   public int getProducer(){
      return producer;
   }

   /*
   * Returns the quantity of the Inventory Item.
   */
   public int getQuantity(){
      return quantity;

   }
	
	/*
   * Increments the quantity of the Inventory Item.
   */
   public boolean incrementQuantity(){
      quantity++;
      return true;

   }

   /*
   * Decrements the quantity of the Inventory Item.
   */
   public boolean decrementQuantity(){
      quantity--;
      return true;

   }

   /*
   * Decreases the quantity of the Inventory Item in the database.
   */
   public boolean decreaseDB(){
      net.sqlitetutorial.DB_App update = new net.sqlitetutorial.DB_App();
      update.decreaseQuantity(idNum);
      return true;
   }

   /*
   * Increases the quantity of the Inventory Item in the database.
   */
   public boolean increaseDB(){
      net.sqlitetutorial.DB_App update = new net.sqlitetutorial.DB_App();
      update.increaseQuantity(idNum);
      return true;
   }

   /*
   * Closes the current transaction order and inserts a new row in the Order Item database.
   *
   * @param orderNum
   * @param dateTime
   * @param total
   */
   public boolean close(int orderNum, String dateTime, double total){
      net.sqlitetutorial.DB_App insert = new net.sqlitetutorial.DB_App();
      insert.insertOrderItem(orderNum, dateTime, idNum, quantity, weight, getTotal(),total);

      return true;
   }
}