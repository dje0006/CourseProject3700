import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/*
 * This is the Transaction class that is in charge of conducting 
 * all of the transaction processing.
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class Transaction {
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
   * Constructor for Transaction object.
   * @param userNum
   * @param orderNum
   */
   public Transaction(int userNum, int orderNum) {
      dateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
      idNum = START + orderNum;
   }
   
   /*
   * Takes the weight of the item.
   * @param weight
   */
   private double weighItem(int weight) {
      return 0;
   }
   
   /*
   * Sends a request for the Manager Number
   */
   private boolean requestManNum() {
      return true;
   }

   /*
   * Returns the total of the transaction.
   */
   public double getTotal(){
      double tempTot = 0.0;
      for(InventoryItem i: items){
         tempTot = tempTot + i.getTotal();
      }
      total = tempTot;
      return total;
   }

   /*
   * Returns the balance of the transaction.
   */
   public double getBalance(){
      double tempTot = 0;
      for(InventoryItem i: items){
         tempTot = tempTot + i.getTotal();
      }
      for(PaymentDetails p: payments){
         tempTot = tempTot - p.getAmount();
      }
      balance = tempTot;
      return tempTot;
   }

   /*
   * Returns the ID of the Transaction.
   */
   public int getID() {
      return 0;
   }

   /*
   * Returns the Number of Items in the current Transaction.
   */
   public int getNumItems(){
      return items.size();
   }

   /*
   * Returns the Date of the Transaction.
   */
   public int getDate(){
      return 0;
   }

   /*
   * Returns the Time of the Transaction.
   */
   public int getTime(){
      return 0;
   }

   /*
   * Returns the User Number associated with the Transaction.
   */
   public int getUserNum(){
      return 0;
   }

   /*
   * Returns the customer number associated with the Transaction.
   */
   public int getCusNum(){
      return 0;
   }

   /*
   * Returns the payment details of the current Transaction.
   */
   public PaymentDetails[] getPaymentDet(){
      PaymentDetails[] x = new PaymentDetails[0];
      return x;
   }

   /*
   * Returns the list of items associated with the current Transaction.
   */
   public List getItems() {
      return items;
   }

   /*
   * Applies payment details to the current Transaction.
   * @param amount
   */
   public boolean addPaymentDet(int amount) {
      getTotal();
      getBalance();
      return true;
   }

   /*
   * Applies a cash payment to the current Transaction.
   * @param amount
   */
   public boolean addCashPayment(double amount) {
      PaymentType type = new PaymentType();
      payments.add(type.beginPaymentSession(amount));
      getTotal();
      getBalance();
      return true;
   }   

   /*
   * Removes payment details from the current Transaction.
   */
   public boolean removePaymentDet() {
      getTotal();
      getBalance();
      return true;
   }

   /*
   * Adds a new item to the current Transaction process.
   * @param itemNum
   */
   public boolean addItem(int itemNum) {
      //check if item is already in cart if so add to quantity otherwise add inventory item to Items List
      boolean found = false;
      int index = 0;
      for(InventoryItem i: items){
         if (i.getIDNum() == itemNum){
            i.incrementQuantity();
            i.decreaseDB();
            total = total + i.getPrice();
            found = true;
         }
         index++;
      }
      if(!found){
         InventoryItem newItem = new InventoryItem();
         if(!newItem.fillItem(itemNum)){
            return false;
         }
         newItem.incrementQuantity();
         newItem.decreaseDB();
         items.add(newItem);
      }
      
      getTotal();
      getBalance();
      return true;
   }

   /*
   * Removes an already existing item from the current Transaction if it is valid.
   * @param itemNum
   */
   public boolean removeItem(int itemNum){
      boolean found = false;
      int index = 0;
      for(InventoryItem i: items){
         if (i.getIDNum() == itemNum){
            i.decrementQuantity();
            i.decreaseDB();
            total = total + i.getPrice();
            found = true;
            if( i.getQuantity() == 0){
               items.remove(index);
            }
            getTotal();
            getBalance();
            return true;
         }
         index++;
      }
      return false;
   }

   /*
   * Applies a discount to the current Transaction.
   */
   public boolean applyDiscount() {
      getTotal();
      getBalance();
      return true;
   }

   /*
   * Issues a manager override to the current Transaction.
   */
   public boolean override() {
      getTotal();
      getBalance();
      return true;
   }

   /*
   * Saves the contents of the current Transaction.
   */
   public String storeTansaction() {
      for(InventoryItem i: items){
         i.close(idNum,dateTime,total);
      }
      return "" + idNum + "#" + dateTime + "#" + total;
   }

   /*
   * Cancels a current Transaction.
   */
   public boolean cancel() {
      while (items.size() > 0){
         int idToRemove = items.get(0).getIDNum();
         int numToRemove = items.get(0).getQuantity();
         for (int i = 0; i < numToRemove; i++){
            removeItem(idToRemove);
         }
      }
      payments = new ArrayList<PaymentDetails>();
      return true;
   }

}