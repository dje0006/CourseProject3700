import java.util.*;

/*
 * This is the Payment Type Class that defines the various information 
 * that is associated with each payment.
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class PaymentType {
   private static int currentIDnum = 200;
   private String type;
   private int idNum;
   private int cardNum;
   private int expDate;
   private String billAddress;
   private int cusNum;

   /*
   * Constructor for the Payment Type class if paying with cash.
   */
   public PaymentType() {
      type = "cash";
      idNum = 0;
      cardNum = 0;
      expDate = 0;
      billAddress = null;
      cusNum = 0;
   }

   /*
   * Constructor for the Payment Type class if paying with card.
   */
   public PaymentType(String newType, int newCardNum, int newExpDate, String newBillAddress, int newCusNum ) {
      type = newType;
      idNum = currentIDnum++;
      cardNum = newCardNum;
      expDate = newExpDate;
      billAddress = newBillAddress;
      cusNum = newCusNum;
   }
   
   /*
   * Returns the type of payment.
   */
   public String getType() {
      return type;
   }

   /*
   * Sets the type of payment.
   * @param newType
   */
   public boolean setType(String newType){
      type = newType;
      return true;
   }
   
   /*
   * Returns the ID Number of the payment.
   */
   public int getIDNum() {
      return idNum;
   }

   /*
   * Returns the Card Number used in the payment.
   */
   public int getCardNum(){
      return cardNum;
   }

   /*
   * Returns the Expiration Date of the payment type.
   */
   public int getExpDate(){
      return expDate;
   }

   /*
   * Returns the Billing Address of payment type.
   */
   public String getBillAddress(){
      return billAddress;
   }

   /*
   * Sets the Billing Address of the payment type.
   * @param newAddress
   */
   public boolean setBillAddress(String newAddress){
      billAddress = newAddress;
      return true;
   }

   /*
   * Returns the customer number.
   */
   public int getCusNum(){
      return cusNum;
   }

   /*
   * Initiates the payment session sequence by associating it with an ID Number and Amount.
   * @param amount
   */
   public PaymentDetails beginPaymentSession(double ammount){
      return new PaymentDetails(idNum, ammount);
   }
}