import java.util.*;
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
   private Cart cart;
   private int userNumber;
   private int customerNumber;
   private int orderNumber;

   /*
   * Constructor for Transaction object.
   * @param userNum
   * @param orderNum
   */
   public Transaction(int userNum,int cusNum, int orderNum) {
      cart = new Cart(userNum,cusNum, orderNum);
      userNumber = userNum;
      customerNumber = cusNum;
      orderNumber = orderNum;
   }
   
   public int getOrderNumber()
   {
      return orderNumber;
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
      List<InventoryItem> items = cart.getItems();
      double tempTot = 0.0;
      for(InventoryItem i: items){
         tempTot = tempTot + i.getTotal();
      }
      cart.setTotal(tempTot);
      return tempTot;
   }

   /*
   * Returns the balance of the transaction.
   */
   public double getBalance(){
      List<InventoryItem> items = cart.getItems();
      List<PaymentDetails> payments = cart.getPayments();
      double tempTot = 0;
      for(InventoryItem i: items){
         tempTot = tempTot + i.getTotal();
      }
      for(PaymentDetails p: payments){
         tempTot = tempTot - p.getAmount();
      }
      cart.setBalance(tempTot);
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
      return cart.getNumItems();
   }

   /*
   * Returns the Date of the Transaction.
   */
   public String getDateAndTime() {
      return cart.getDT();
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
      return cart.getItems();
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
      cart.addCashPayment(amount);
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
      List<InventoryItem> items = getItems();
      for(InventoryItem i: items){
         if (i.getIDNum() == itemNum){
            i.incrementQuantity();
            i.decreaseDB();
            double total = cart.getTotal();
            total = total + i.getPrice();
            cart.setTotal(total);
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
      cart.setItems(items);
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
      List<InventoryItem> items = getItems();
      for(InventoryItem i: items){
         if (i.getIDNum() == itemNum){
            i.decrementQuantity();
            i.decreaseDB();
            double total = cart.getTotal();
            total = total + i.getPrice();
            cart.setTotal(total);
            found = true;
            if( i.getQuantity() == 0){
               items.remove(index);
            }
            cart.setItems(items);
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
      String dateTime = cart.getDT();
      int idNum = cart.getID();
      double total = cart.getTotal();
      int cusNum = cart.getCusNum();
      
      
      List<InventoryItem> items = getItems();
      for(InventoryItem i: items){
         i.close(idNum,dateTime,total);
      }
      cart.setItems(items);
      return "" + idNum + "#" + dateTime + "#" + total + "#" + cusNum;
   }

   /*
   * Cancels a current Transaction.
   */
   public boolean cancel() {
      List<InventoryItem> items = getItems();
      while (items.size() > 0){
         int idToRemove = items.get(0).getIDNum();
         int numToRemove = items.get(0).getQuantity();
         for (int i = 0; i < numToRemove; i++){
            removeItem(idToRemove);
         }
      }
      cart.setItems(items);
      cart.setPayments(new ArrayList<PaymentDetails>());
      return true;
   }

}