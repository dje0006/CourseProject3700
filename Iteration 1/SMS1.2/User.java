/*
 * This is the User Class that is responsible for maintaining the current
 * information of the user (manager/cashier) that is operating the system.
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class User {
   
   private static int currentIDnum = 100;
   private String name;
   private int idNum;
   private String password;
   private boolean isManager;

	/*
	* Constructor for the User class.
	* @param newName
	* @param manager
	*/
   public User(String newName, boolean manager) {
      name = newName;
      idNum = currentIDnum++;
      password = "password";
      isManager = manager;
   }
   
   /*
   * Returns the name of the current user.
   */
   public String getName() {
      return name;
   }
   
   /*
   * Returns the ID Number of the current user.
   */
   public int getIDNum() {
      return idNum;
   }

   /*
   * Allows a user to change their system password.
   */
   public boolean changePassword() {
      String tempPass = "";
      // prompt current password
      boolean valid = false;
      while (!valid){
         if (tempPass.equals(password)){
            valid = true;
         }
         else{
            //display that that was not the valid password
            //prompt if they would like to try again
            boolean again = true;
            if (!again){
               return false;
            }
            //prompt for current password again
         }
      }
      valid = false;
      String newPass1 = "";
      String newPass2 = "";
      //prompt for new password
      while (!valid){
         if (newPass1.equals(newPass2)){
            valid = true;
         }
         else{
            //display that that was not the valid password

            //prompt if they would like to try again
            boolean again = true;
            if (!again){
               return false;
            }
            //prompt for current password again
         }
      }
      password = newPass1;
      return true;
   }

   /*
   * Initiates the transaction by assigning it an ID Number.
   */
   public Transaction beginTransaction(){
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      return new Transaction(idNum, app.getCurrentTransactionID());
   }

   /*
   * Finalizes the transaction by inserting the finished order into the database.
   * @param transaction
   */
   public boolean finalizeTransaction(Transaction transaction){
      String temp = transaction.storeTansaction();
      String delims = "[#]";
      String[] tokens = temp.split(delims);
      int orderNum = Integer.parseInt(tokens[0]);
      String dateTime = tokens[1];
      double total = Double.parseDouble(tokens[2]);
      if(total > 0){
         net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
         app.insertOrder(orderNum,dateTime,total);
      }
      return true;
   }

   /*
   * Logs a user out of the system.
   */
   public void logOut(){
      
   }

   /*
   * Returns the requested information of an item.
   * @param id
   */
   public String getItemInfo(int id){
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      String result = app.selectID(id);
      return result;
   }

   /*
   * Updates a product's information in the database.
   *
   * @param prodID
   * @param prodName
   * @param prodPrice
   * @param prodWeight
   * @param prodQuantity
   * @param prodProcuer
   */
   public boolean updateProduct(int prodID, String prodName, double prodPrice, double prodWeight, int prodQuantity, int prodProducer){
      if(!isManager){
            return false;
         }
      net.sqlitetutorial.DB_App update = new net.sqlitetutorial.DB_App();
      update.update(prodID,prodName,prodPrice,prodWeight,prodQuantity,prodProducer);
      return true;
   }

   /*
   * If the user is a manager, perform adding a product into the system.
   *
   * @param prodID
   * @param prodName
   * @param prodPrice
   * @param prodWeight
   * @param prodQuantity
   * @param prodProducer
   */
   public boolean addProduct(int prodID, String prodName, double prodPrice, double prodWeight, int prodQuantity, int prodProducer){
         if(!isManager){
            return false;
         }
         net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
         app.insertProduct(prodID,prodName,prodPrice,prodWeight,prodQuantity,prodProducer);
      return true;
   }
   
   /*
   * Performs a check to see if an item ID exists in the system.
   *
   * @param prodID
   */
   public boolean doesIDExist(int prodID){
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      return (app.selectID(prodID).length()>8);
   }
}