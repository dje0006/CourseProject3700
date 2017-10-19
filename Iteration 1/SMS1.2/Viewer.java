import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * This is the Viewer Class that is responsible for creating, designing,
 * and operating the GUI that is the centerpiece between the item manipulation
 * and the database. A user is presented with a simple layout with options to choose
 * from based on what action they would like to perform in the system.
 *
 * Authors: Skyler Evans & Dustin Easterling
 * Date: 10/12/2017 - End of Fall Semester
 * Assignment: Store Management System Course Project
 * Professor: Tung Nguyen
 */
public class Viewer extends JFrame implements ActionListener {
   private static final long serialVersionUID = 1L;
   private User currentUser;
   private Transaction currentTransaction;


   JFrame window = new JFrame("Store Management System");
   JPanel userPan = new JPanel();
   JPanel checkOutPan = new JPanel();
   JPanel itemPan = new JPanel();
   JPanel manPan = new JPanel();
   JPanel paymentPan = new JPanel();
   

   /*
   * The Main driver for this Store Management System.
   */
   public static void main(String[] args) {
      new Viewer().setVisible(true);
   }
   
   private Viewer() {

//////////////////USER PANEL START/////////////////////////////
	  //set grid style layout
      userPan.setLayout(new GridLayout());
	  
      //create Cashier Button
      JButton cashierButt = new JButton("Cashier");
      cashierButt.addActionListener(this);

      //create Manager Button
      JButton manButt = new JButton("Manager");
      manButt.addActionListener(this);

      //add Cashier and Manager Buttons
      userPan.add(cashierButt);
      userPan.add(manButt);

      window.add(userPan);
      window.setSize(1000,600);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setVisible(true);


////////////////USER PANEL END/////////////////////////////////

////////////////MANAGER PANEL START/////////////////////////
	  //set grid style layout
      manPan.setLayout(new GridLayout(3,1));
	  
      //create add Item Button
      JButton addItemButt = new JButton("New Item");
      addItemButt.addActionListener(this);


      //create Update Item Button
      JButton updateButt = new JButton("Update Item");
      updateButt.addActionListener(this);

      //create back button
      JButton backButtMain = new JButton("Back");
      backButtMain.setActionCommand("backButtMain");
      backButtMain.addActionListener(this);


      //add buttons
      manPan.add(addItemButt);
      manPan.add(updateButt);
      manPan.add(backButtMain);

////////////////MANAGER PANEL END/////////////////////////


////////////////ITEM PANEL START/////////////////////////////
	  //set border style layout
      itemPan.setLayout(new BorderLayout());

      //itemPan.add(namePriceQuantityTotal);
////////////////ITEM PANEL END///////////////////////////////


////////////////CHECKOUT PANEL START/////////////////////////
	  //set grid style layout
      checkOutPan.setLayout(new GridLayout(6,1));
	  
	  //create Add Item button
      JButton addButt = new JButton("Add Item");
      addButt.addActionListener(this);
      checkOutPan.add(addButt);

	  //create Remove button
      JButton removeButt = new JButton("Remove");
      removeButt.addActionListener(this);
      checkOutPan.add(removeButt);

	  //create discount button
      JButton discountButt = new JButton("Apply Discount");
      discountButt.addActionListener(this);
      checkOutPan.add(discountButt);

	  //create override button
      JButton overrideButt = new JButton("Override");
      overrideButt.addActionListener(this);
      checkOutPan.add(overrideButt);

	  //create payment button
      JButton payButt = new JButton("Payment");
      payButt.addActionListener(this);
      checkOutPan.add(payButt);

	  //create back button
      JButton backButtMain2 = new JButton("Back");
      backButtMain2.setActionCommand("backButtMain");
      backButtMain2.addActionListener(this);
      checkOutPan.add(backButtMain2);
////////////////CHECKOUT PANEL END/////////////////////////

///////////////PAYMENT PANEL START//////////////////////////

      //Display Item Panel on Left and New Payment Option Buttons on right
      //set grid style layout
      paymentPan.setLayout(new GridLayout(5,1));
      
	  //create Cash button
      JButton cashButt = new JButton("Cash");
      cashButt.addActionListener(this);
      paymentPan.add(cashButt);
      
	  //create Credit/Debit button
      JButton credebButton = new JButton("Credit/Debit");
      credebButton.addActionListener(this);
      paymentPan.add(credebButton);
      
	  //create Gift Card button
      JButton giftButt = new JButton("Gift Card");
      giftButt.addActionListener(this);
      paymentPan.add(giftButt);
      
	  //create Finish button
      JButton finishButt = new JButton("Finish");
      finishButt.addActionListener(this);
      paymentPan.add(finishButt);
      
	  //create back button
      JButton backButtCheckoutScreen = new JButton("Back"); 
      backButtCheckoutScreen.setActionCommand("backButtCheckout");
      backButtCheckoutScreen.addActionListener(this);
      paymentPan.add(backButtCheckoutScreen);
      
////////////////PAYMENT PANEL END//////////////////////////
   }

   /*
   * Creates effectiveness for each button
   * when a user clicks on a button on the GUI.
   *
   * @param e
   */
   @Override
   public void actionPerformed(ActionEvent e){

      String name = e.getActionCommand();

      if(name.equals("Cashier")){
         checkoutScreen();
         updateItemList();
      }
      else if(name.equals("Manager")){
         managerScreen();
      }
      else if(name.equals("backButtMain")){
         backButtMainScreen();
      }
      else if(name.equals("backButtCheckout")){
         backButtCheckout();
         updateItemList();
      }
      else if(name.equals("New Item")){
         newItemPrompt();
      }
      else if(name.equals("Update Item")){
         updateItemPrompt();
      }
      else if(name.equals("Add Item")){
         addItemPrompt();
         updateItemList();
      }
      else if(name.equals("Remove")){
         removeItemPrompt();
         updateItemList();
      }
      else if(name.equals("Payment")){
      paymentScreen();
     }
     else if(name.equals("Cash")){
      cashScreen();
      updateItemList();
     }
     else if(name.equals("Credit/Debit")){
        //cdScreen();
     }
     else if(name.equals("Finish")){
      finishTransaction();
      updateItemList();
     }

   }

   /*
   * Continually updates the item list panel by 
   * listing the ID of the product, name, quantity,
   * weight, and price. 
   */
   private void updateItemList(){
      String[] columns = {"ID#", "Name", "Quantity","Weight", "Price"};
      int numOfItems = currentTransaction.getNumItems();
      java.util.List<InventoryItem> items = currentTransaction.getItems();
      Object[][] data = new Object[numOfItems][5];

      itemPan.removeAll();

      for (int i = 0; i < numOfItems; i++){
        InventoryItem invItem = items.get(i);
        int id =  invItem.getIDNum();
        String name = invItem.getName();
        double weight = invItem.getWeight();
        double quantity = invItem.getQuantity();
        double price = invItem.getPrice();
        data[i][0]=id;
        data[i][1]=name;
        data[i][2]=quantity;
        data[i][3]=weight;
        data[i][4]=price;
      }
		//creates a text field for total
        JTextField total = new JTextField();
        total.setText("$" + currentTransaction.getBalance());
        total.setEditable(false);
        total.setHorizontalAlignment(JTextField.CENTER);

		//creates a format for the table
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
 
		//creates a title
        JLabel lblHeading = new JLabel("Items In Cart");
        lblHeading.setFont(new Font("Arial",Font.TRUETYPE_FONT,24));
 
        itemPan.add(scrollPane, BorderLayout.CENTER);
        itemPan.add(total, BorderLayout.PAGE_END);
        window.getContentPane().repaint();
        window.revalidate();
   }

   /*
   * Displays the checkout screen.
   */
   private void checkoutScreen(){
      JTextField userName = new JTextField();
      JTextField password = new JTextField();

      Object[] fields = {"user name", userName, "password", password};

      JOptionPane.showConfirmDialog(null, fields, "User sign in",JOptionPane.OK_CANCEL_OPTION);

      currentUser = new User("Cashier", false);
      currentTransaction = currentUser.beginTransaction();

      boolean valid = true;

      if(valid){
         window.getContentPane().removeAll();
         window.getContentPane().repaint();
         window.setLayout(new GridLayout(1,2));
         window.add(itemPan);
         window.add(checkOutPan);
         window.revalidate();
      }
   }

   /*
   * Displays the payment screen.
   */
   private void paymentScreen(){
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.setLayout(new GridLayout(1,2));
      window.add(itemPan);
      window.add(paymentPan);
      window.revalidate();
   }

   /*
   * Displays the manager screen.
   */
   private void managerScreen(){
      JTextField userName = new JTextField();
      JTextField password = new JTextField();

      Object[] fields = {"user name", userName, "password", password};

      JOptionPane.showConfirmDialog(null, fields, "User sign in",JOptionPane.OK_CANCEL_OPTION);
      currentUser = new User("manager", true);

      boolean valid = true;

      if(valid){
         window.getContentPane().removeAll();
         window.getContentPane().repaint();
         window.add(manPan);
         window.revalidate();
      }
   }

   /*
   * Takes a user back to the main screen.
   */
   private void backButtMainScreen(){

      currentTransaction.cancel();
      currentUser = null;
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.setLayout(new GridLayout(1,2));
      window.getContentPane().add(userPan);
      window.revalidate();
   }

   /*
   * Displays the prompt for when a user wants to add a new item to the system.
   */
   private void newItemPrompt(){
      JTextField prodID = new JTextField();
      JTextField prodName = new JTextField();
      JTextField prodPrice = new JTextField();
      JTextField prodWeight = new JTextField();
      JTextField prodQuantity = new JTextField();
      JTextField prodProducer = new JTextField();

      Object[] fields = {"ID#", prodID, "Name", prodName,"Price", prodPrice, "Weight", prodWeight, "Quantity", prodQuantity, "Producer", prodProducer};

      JOptionPane.showConfirmDialog(null, fields, "create new Item",JOptionPane.OK_CANCEL_OPTION);

      int id = Integer.parseInt(prodID.getText());
      String name = prodName.getText();
      double price = Double.parseDouble(prodPrice.getText());
      double weight = Double.parseDouble(prodWeight.getText());
      int quantity = Integer.parseInt(prodQuantity.getText());
      int producer = Integer.parseInt(prodProducer.getText());

      boolean newID = !currentUser.doesIDExist(id);

      if(newID){
         currentUser.addProduct(id,name,price,weight,quantity,producer);
         JOptionPane.showMessageDialog(null, "Product Added");
      }
      else{
         JOptionPane.showMessageDialog(null, "Product ID Already Exists");
      }
   }

   /*
   * Displays the prompt for when a user wants to update an existing item in the system.
   */
   private void updateItemPrompt(){
      JTextField prodID = new JTextField();

      Object[] fields1 = {"ID#", prodID};

      JOptionPane.showConfirmDialog(null, fields1, "Update Item",JOptionPane.OK_CANCEL_OPTION);

      int id = Integer.parseInt(prodID.getText());
      if(!currentUser.doesIDExist(id)){
         JOptionPane.showMessageDialog(null, "Item Does Not Exist");
         return;
      }
      String existingValues = currentUser.getItemInfo(id);

      String oldProdName = "";
      String oldProdPrice = "";
      String oldProdWeight = "";
      String oldProdQuantity = "";
      String oldProdProducer = "";

      String temp = "";
      int x = 0;
      for (int i = 0; i < existingValues.length(); i++)
      {
         if (existingValues.charAt(i) == '#')
         {
            x++;
            if (x == 2)
            {
               oldProdName = temp;
            }
            if (x == 3)
            {
               oldProdPrice = temp;
            }
            if (x == 4)
            {
               oldProdWeight = temp;
            }
            if (x == 5)
            {
               oldProdQuantity = temp;
            }
            if (x == 6)
            {
               oldProdProducer = temp;
            }
            temp = "";
         }
         else
         {
            temp = temp + existingValues.charAt(i);
         }
      }

	  //sets up text fields
      JTextField prodName = new JTextField();
      JTextField prodPrice = new JTextField();
      JTextField prodWeight = new JTextField();
      JTextField prodQuantity = new JTextField();
      JTextField prodProducer = new JTextField();
      Object[] fields2 = {"Name", prodName,"Price", prodPrice, "Weight", prodWeight, "Quantity", prodQuantity, "Producer", prodProducer};

	  //creates text based on above
      prodName.setText(oldProdName);
      prodPrice.setText(oldProdPrice);
      prodWeight.setText(oldProdWeight);
      prodQuantity.setText(oldProdQuantity);
      prodProducer.setText(oldProdProducer);

      JOptionPane.showConfirmDialog(null, fields2, "Update Item",JOptionPane.OK_CANCEL_OPTION);

      String name = prodName.getText();
      double price = Double.parseDouble(prodPrice.getText());
      double weight = Double.parseDouble(prodWeight.getText());
      int quantity = Integer.parseInt(prodQuantity.getText());
      int producer = Integer.parseInt(prodProducer.getText());

      currentUser.updateProduct(id,name,price,weight,quantity,producer);      
   }

   /*
   * Mimics the functionality of actually scanning an item.
   * Instead, a user is prompted to add the item to their transaction through number
   * and item ID recognition.
   */
   public void addItemPrompt(){
      JTextField prodID = new JTextField();
      JTextField quantity = new JTextField();
      Object[] fields1 = {"ID#", prodID,"quantity", quantity};

      JOptionPane.showConfirmDialog(null, fields1, "Add Item",JOptionPane.OK_CANCEL_OPTION);

      int id = Integer.parseInt(prodID.getText());
      int numItems = Integer.parseInt(quantity.getText());
      if(!currentUser.doesIDExist(id)){
         JOptionPane.showMessageDialog(null, "Item Does Not Exist");
         return;
      }
      for(int i = 0; i < numItems; i++) {
         currentTransaction.addItem(id);
      }
      
   }

   /*
   * Allows the user to enter an item and quantity to remove from their current Transaction.
   */
   public void removeItemPrompt(){
      JTextField prodID = new JTextField();
      JTextField quantity = new JTextField();
      Object[] fields1 = {"ID#", prodID,"quantity to remove", quantity};

      JOptionPane.showConfirmDialog(null, fields1, "Remove Item",JOptionPane.OK_CANCEL_OPTION);

      int id = Integer.parseInt(prodID.getText());
      int numItems = Integer.parseInt(quantity.getText());
      if(!currentUser.doesIDExist(id)){
         JOptionPane.showMessageDialog(null, "Item Does Not Exist");
         return;
      }
      boolean exists;
      for(int i = 0; i < numItems; i++) {
        exists = currentTransaction.removeItem(id);
        if (!exists){
            i = numItems; 
        }
      }
      
   }

   /*
   * Returns the user back to the checkout screen.
   */
   private void backButtCheckout(){
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.setLayout(new GridLayout(1,2));
      window.add(itemPan);
      window.add(checkOutPan);
      window.revalidate();
   }

   /*
   * Displays the screen to pay with cash.
   */
   private void cashScreen(){
      JTextField amount = new JTextField();
      
      Object[] fields = {"Enter Cash Amount", amount};
      
      JOptionPane.showConfirmDialog(null,fields, "Cash Payment",JOptionPane.OK_CANCEL_OPTION);
      
      boolean valid = true;
      double tempAmount = Double.parseDouble(amount.getText());
      if (valid){
         currentTransaction.addCashPayment(tempAmount); //Deduct the cash amount from the total of the checkout.
         window.getContentPane().removeAll();
         window.getContentPane().repaint();
         window.add(itemPan);
         window.add(paymentPan);
         window.revalidate();
      }
   }

   /*
   * Finishes and confirms the termination of the transaction.
   */
   private void finishTransaction(){
      if(currentTransaction.getBalance() != 0){
            return;
      }
      currentUser.finalizeTransaction(currentTransaction);
      currentTransaction = currentUser.beginTransaction();
   }
}