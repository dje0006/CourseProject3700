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
   
   private String name;
   private int idNum;
   private String password;
   private boolean isManager;
   private boolean temp;
   private String photo;

	/*
	* Constructor for the User class.
	* @param newName
	* @param manager
	*/
   public User(String newName, boolean manager, String pas, int id, String photoPath) {
      idNum = id;
      name = newName;
      password = pas;
      isManager = manager;
      temp = false;
      photo = photoPath;
   }
   
   /*
   * Empty constructor for user.
   */
   public User() {
      temp = true;
   }
   
   /*
   * Checks if the user is a manager.
   */
   public boolean isManager() {
      return isManager;
   }
   
   /*
   * Sets the user's image to what is passed in.
   */
   public boolean setImg(String img) {
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      app.changeUserPhoto(idNum, img);
      photo = img;
      return true;
   }
   
   /*
   * Returns an image.
   */
   public String getImg() {
      return photo;
   }
   
   /*
   * Returns a user's information.
   */
   public User getUser(String name, String pass) {
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      String res = app.selectUser(name, pass);
      if (res == null)
         return null;
      int i = Character.getNumericValue(res.charAt(0));
      if(i == 1)
         isManager = true;
      else
         isManager = false;
      res = res.substring(1);
      String[] tokens = res.split("#");
      
      return new User(name, isManager, pass, Integer.parseInt(tokens[0]), tokens[1]);
   }
   
   /*
    * Returns the name of the current user.
    */
   public boolean newUser(String newName, boolean man, String pass) {
      if(!isManager)
         return false;
        
      
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      int manager = (man) ? 1 : 0;
      String photo = "";
      app.insertUser(newName, manager, photo, pass);
      
      return true;
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
   public boolean changePassword(String newPas) {
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      app.changeUserPassword(idNum, newPas);
      password = newPas;
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


   /*
   * Retrieves the information to be able to handle
   * products and quantities.
   */
   public String[][] getReportScreen(){
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      String itemsS = app.getAllProducts();
      String[] itemsT = itemsS.split("#");
      String quantityS = app.getQuantitySold();
      String[] quantityT = quantityS.split("#");
      String[][] resultArray = new String[quantityT.length/2][3];
      if(quantityS == null || itemsS == null)
         return resultArray;
      for(int i = 0; i < quantityT.length/2; i++){
         int index = i * 2;
         resultArray[i][0] = quantityT[i*2];
         resultArray[i][2] = quantityT[i*2+1];
         for(int x = 0; x < itemsT.length/2; x++){
            if(resultArray[i][0].equals(itemsT[x*2]))
               resultArray[i][1] = itemsT[x * 2 +1];
         }
      }
      return resultArray;
   }
   
   /*
   * Returns the total value of the what has been sold.
   */
   public int getTotalField()
   {
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      int total = app.getOverallTotal();
      return total;
   }
   
   /*
   * Returns the date and respective total of each transaction.
   */
   public String[][] getDateAndTotal()
   {
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      String dateAndTotal = app.getDateTotal();
      String[] dateAndTotalT = dateAndTotal.split("#");
      String[][] resultArray = new String[dateAndTotalT.length/2][2];
      if (dateAndTotal == null)
         return resultArray;
      int x = 0;
      for (int i = 0; i < dateAndTotalT.length/2; i++)
      {
         resultArray[i][0]= dateAndTotalT[x];
         x++;
         resultArray[i][1]= dateAndTotalT[x];
         x++;
      }
      return resultArray;
   }
   
   /*
   * Returns all of the information present in the Orders table.
   */
   public String[][] getAllOrderInfo()
   {
      net.sqlitetutorial.DB_App app = new net.sqlitetutorial.DB_App();
      String allOrders = app.getAllOrders();
      String[] allOrdersT = allOrders.split("#");
      String[][] resultArray = new String[allOrdersT.length/3][3];
      if (allOrders == null)
      {
         return resultArray;
      }
      int x = 0;
      for (int i = 0; i < allOrdersT.length/3; i++)
      {
         resultArray[i][0] = allOrdersT[x];
         x++;
         resultArray[i][1] = allOrdersT[x];
         x++;
         resultArray[i][2] = allOrdersT[x];
         x++;
      }
      return resultArray;
   }
}