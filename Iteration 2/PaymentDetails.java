import java.util.Date;
import java.text.SimpleDateFormat;

/*
 * This is the Payment Details Class that defines the details
 * of each payment.
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class PaymentDetails {
   private static int current_pDetnum = 300;
   private int pDetNum;
   private int pTypeNum;
   private double amount;
   private String dateTime;


   /*
   * Constructor for creating Payment Detail objects.
   *
   * @param pType
   * @param newAmount
   */
   public PaymentDetails(int pType, double newAmmount) {
      pDetNum = current_pDetnum++;
      pTypeNum = pType;
      amount = newAmmount;
      dateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
   }
   
   /*
   * Returns the payment details number.
   */
   public int getP_DetNum() {
      return pDetNum;
   }

   /*
   * Returns the payment type number.
   */
   public int getP_TypeNum() {
      return pTypeNum;
   }
   
   /*
   * Returns the payment details number.
   */
   public double getAmount() {
      return amount;
   }

   /*
   * Returns the payment Date and Time.
   */
   public String getDateTime(){
      return dateTime;
   }
}