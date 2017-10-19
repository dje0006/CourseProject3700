import java.util.*;

/*
 * This is the Customer class that is in charge of setting, holding, and maintaining
 * the information for each customer that interactions with the Store Management System.
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class Customer {
   int phoneNum;
   int idNum;
   String address;
   String name;
   String email;
   List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
   
   /*
   * Constructor for creating a customer object. 
   *
   * @param newName
   * @param newID
   * @param newAddress
   * @param newPhoneNumber
   * @param newEmail
   */
   public Customer(String newName, int newID, String newAddress, int newPhoneNum, String newEmail) {
      name = newName;
      idNum = newID;
      address = newAddress;
      phoneNum = newPhoneNum;
      email = newEmail;
      paymentTypes.add(new PaymentType());
   }
   
   /*
   * Returns the name of the Customer.
   */
   public String getName() {
      return name;
   }
	
	/*
   * Sets the name of the Customer.
   * @param nameNew
   */
   public boolean setName(String nameNew) {
      name = nameNew;
      return true;
   }
   
   /*
   * Returns the ID Number of the Customer.
   */
   public int getIDNum() {
      return idNum;
   }

/*
   * Returns the Address of the Customer.
   */
   public String getAddress(){
      return address;
   }

	/*
	 * Sets the address of the Customer.
	 * @param newAddress
    */
   public boolean setAddress(String newAddress){
      address = newAddress;
      return true;
   }

   /*
   * Returns the phone number of the Customer.
   */
   public int getPhoneNum(){
      return phoneNum;
   }

   /*
   * Sets the phone number of the Customer.
   * @param newNum
   */
   public boolean setPhoneNUm(int newNum){
      phoneNum = newNum;
      return true;
   }

   /*
   * Returns the E-Mail of the Customer.
   */
   public String getEmail(){
      return email;
   }

   /*
   * Sets the E-Mail of the Customer.
   * @param newEmail
   */
   public boolean setEmail(String newEmail){
      email = newEmail;
      return true;
   }

   /*
   * Add a new payment type to the transaction.
   */
   public boolean addPaymentType(){
      String newType = "";
      int newCardNum = 0;
      int newExpDate = 0; 
      String newBillAddress = ""; 
      int newCusNum = 0;
      // prompt user to fill in previous fields//



      paymentTypes.add(new PaymentType(newType,newCardNum,newExpDate,newBillAddress,newCusNum));
      return true;

   }

   /*
   * Remove a payment type from the transaction.
   */
   public boolean removePaymentType(){
      int selection = -1;
      //list payment types in array




      //prompt user for number to remove



      paymentTypes.remove(selection);
      return true;

   }

   /*
   * Choose a payment type for the transaction.
   * @param ammount
   */
   public PaymentDetails selectPaymentType(double ammount){
      int selection = -1;
      //list payment types in array



      //prompt user for number to use



      
      PaymentType temp = paymentTypes.get(selection);
      return temp.beginPaymentSession(ammount);
      
   }
}