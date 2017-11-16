import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/*
 * This is the Cart Class that is responsible for holding the data
 * of a transaction. 
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class Cart{


   private String dateTime;
   private final int START = 100;
   private int idNum;
   private int userNum;
   private int cusNum;
   List<PaymentDetails> payments = new ArrayList<PaymentDetails>();
   List<InventoryItem> items = new ArrayList<InventoryItem>();
   private double total = 0;
   private double balance = 0;

   /*
   * Constructor for the Cart class.
   *
   * @param userNum
   * @param orderNum
   */
   public Cart(int userNum, int orderNum){
      dateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
      idNum = START + orderNum;
   }

   /*
   * Returns a list of items in the cart.
   */
   public List getItems(){
      return items;
   }

   /*
   * Sets a list of items.
   */
   public void setItems(List i){
      items = i;
   }

   /*
   * Returns the total.
   */
   public double getTotal(){
      return total;
   }

   /*
   * Sets the total
   */
   public void setTotal(double t){
      total = t;
   }

   /*
   * Sets the total
   */
   public void setPayments(List p){
      payments = p;
   }

   /*
   * Returns a list of payments.
   */
   public List getPayments() {
      return payments;
   }

   /*
   * Sets the balance.
   */
   public void setBalance(double b){
      total = b;
   }

   /*
   * Returns the number of items.
   */
   public int getNumItems(){
      return items.size();
   }

   /*
   * Returns the date and time.
   */
   public String getDT() {
      return dateTime;
   }

   /*
   * Returns the ID number.
   */
   public int getID() {
      return idNum;
   }

   /*
   * Performs the payment with cash.
   */
   public void addCashPayment(double amount){
      PaymentType type = new PaymentType();
      payments.add(type.beginPaymentSession(amount));
   }

   /*
   * Adds an item.
   */
   public void addItem(InventoryItem i){
      items.add(i);
   }
}