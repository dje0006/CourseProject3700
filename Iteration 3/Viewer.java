import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
   private Customer currentCustomer;
   private Transaction currentTransaction;


   JFrame window = new JFrame("Store Management System");
   JPanel mainPan = new JPanel();
   JPanel userPan = new JPanel();
   JPanel checkOutPan = new JPanel();
   JPanel itemPan = new JPanel();
   JPanel manPan = new JPanel();
   JPanel paymentPan = new JPanel();
   JPanel manReportPan = new JPanel();
   JPanel reportOptionsPan = new JPanel();
   JPanel changeReportPan = new JPanel();
   JPanel filterPan = new JPanel();
   JPanel receiptPan = new JPanel();


	/*
	 * The Main driver for this Store Management System.
	 */
   public static void main(String[] args) {
      new Viewer().setVisible(true);
   }

   /*
   * Creates the constructor for this viewer class
   * It is in charge of creating new buttons and assigning
   * these buttons to the correct panel. It also sets the layout
   * of each individual view.
   */
   private Viewer() {
   
   	//////////////////main PANEL START/////////////////////////////
   	//set grid style layout
      mainPan.setLayout(new GridLayout());
   
   	//create Cashier Button
      JButton cashierButt = new JButton("Cashier");
      cashierButt.addActionListener(this);
   
   	//create Manager Button
      JButton manButt = new JButton("Manager");
      manButt.addActionListener(this);
   
   	//create User Screen
      JButton userButt = new JButton("User Screen");
      userButt.addActionListener(this);
   
   	//add Cashier and Manager Buttons
      mainPan.add(userButt);
      mainPan.add(cashierButt);
      mainPan.add(manButt);
   
      window.add(mainPan);
      window.setSize(1000,600);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setVisible(true);
   
   
   	////////////////main PANEL END/////////////////////////////////
   
   	////////////////MANAGER PANEL START/////////////////////////
   	//set grid style layout
      manPan.setLayout(new GridLayout(6,1));
   
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
   
   	//create back button
      JButton addUserButt = new JButton("Add User");
      addUserButt .addActionListener(this);
      
      
      JButton addCustButt = new JButton("Add Customer");
      addCustButt.addActionListener(this);
      
   
   	//create back button
      JButton reportButt = new JButton("View Report");
      reportButt.addActionListener(this);
   
   
   	//add buttons
      manPan.add(addUserButt);
      manPan.add(addCustButt);
      manPan.add(reportButt);
      manPan.add(addItemButt);
      manPan.add(updateButt);
      manPan.add(backButtMain);
   
   	////////////////MANAGER PANEL END/////////////////////////
   
   ////////////////REPORT FILTER PANEL START/////////////////
   
      changeReportPan.setLayout(new GridLayout(2,1));
   
      JButton changeFilterButton = new JButton("Select Another Data Filter");
      changeFilterButton.addActionListener(this);
   
      JButton backToReportButton = new JButton("Quit");
      backToReportButton.addActionListener(this);
   
      changeReportPan.add(changeFilterButton);
      changeReportPan.add(backToReportButton);
   
   
   ////////////////REPORT FILTER PANEL END///////////////////
   
   ///////////////FILTER PANEL START/////////////////////////
   
      filterPan.setLayout(new BorderLayout());
   
   ///////////////FILTER PANEL END///////////////////////////
   
   ////////////////REPORT OPTIONS PANEL START////////////////
   
      reportOptionsPan.setLayout(new GridLayout(3,1));
   
      JButton namesAllTimeButton = new JButton("Products Sold All Time");
      namesAllTimeButton.addActionListener(this);
   
      JButton revenueButton = new JButton("Total Revenue");
      revenueButton.addActionListener(this);
   
      JButton transHistoryButton = new JButton("Transaction History");
      transHistoryButton.addActionListener(this);
   
      reportOptionsPan.add(namesAllTimeButton);
      reportOptionsPan.add(revenueButton);
      reportOptionsPan.add(transHistoryButton);
   
   ////////////////REPORT OPTIONS PANEL END//////////////////
   
   	////////////////User PANEL START/////////////////////////
   	//set grid style layout
      userPan.setLayout(new GridLayout(4,1));
   
   	//create view Image Button
      JButton viewImgButt = new JButton("View photo");
      viewImgButt.addActionListener(this);
   
   
   	//create set image button
      JButton setImgButt = new JButton("Set photo");
      setImgButt.addActionListener(this);
   
   	//create change password button
      JButton changePassButt = new JButton("Change password");
      changePassButt.addActionListener(this);
   
      JButton backButtMain2 = new JButton("Back");
      backButtMain2.setActionCommand("backButtMain");
      backButtMain2.addActionListener(this);
   
   
   
   	//add buttons
      userPan.add(viewImgButt);
      userPan.add(setImgButt);
      userPan.add(changePassButt);
      userPan.add(backButtMain2);
   
   	////////////////User PANEL END/////////////////////////
   
   	////////////////ITEM PANEL START/////////////////////////////
   	//set border style layout
      itemPan.setLayout(new BorderLayout());
   
   	//itemPan.add(namePriceQuantityTotal);
   	////////////////ITEM PANEL END///////////////////////////////
   
   
   	////////////////CHECKOUT PANEL START/////////////////////////
   	//set grid style layout
      checkOutPan.setLayout(new GridLayout(6,1)); // (7,1) with receipt button
   
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
     
     //create receipt button
      //JButton receiptButton = new JButton("Print Receipt");
      //receiptButton.addActionListener(this);
      //checkOutPan.add(receiptButton);
   
   	//create back button
      JButton backButtMain3 = new JButton("Back");
      backButtMain3.setActionCommand("backButtMain");
      backButtMain3.addActionListener(this);
      checkOutPan.add(backButtMain3);
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
      else if(name.equals("User Screen")){
         userScreen();
      }
      else if(name.equals("View Report")){
         viewReport();
      }
      else if(name.equals("Set photo")){
         setImg();
      }
      else if(name.equals("Add Customer")){
         addCustomer();
      }
      else if(name.equals("View photo")){
         showImg();
      }
      else if(name.equals("Change password")){
         changePassword();
      }
      else if(name.equals("backButtMain")){
         backButtMainScreen();
      }
      else if(name.equals("backButtCheckout")){
         backButtCheckout();
         updateItemList();
      }
      else if(name.equals("Add User")){
         addUser();
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
      //else if(name.equals("Print Receipt")){
         //showReceipt();
      //}
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
      else if(name.equals("Products Sold All Time"))
      {
         allTimeProductsFilter();
      }
      else if(name.equals("Total Revenue"))
      {
         totalRevFilter();
      }
      else if(name.equals("Transaction History"))
      {
         transHistoryFilter();
      }
      else if(name.equals("Select Another Data Filter"))
      {
         viewReport();
      }
      else if(name.equals("Quit"))
      {
         quitReturn();
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
      total.setText("Total: $" + currentTransaction.getBalance());
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
   * Exits the generate report session.
   */
   private void quitReturn()
   {
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.add(manPan);
      window.revalidate();
   }
   
   public double round(double value, int digits) {
      if (digits < 0)
      {
         throw new IllegalArgumentException();
      }
      
      long factor = (long) Math.pow(10,digits);
      value = value * factor;
      long tmp = Math.round(value);
      return (double) tmp / factor;
   }
   
   private void showReceipt()
   {
      ReceiptBuilder builder = new TXTReceiptBuilder();
      int orderNum = currentTransaction.getOrderNumber();
      String cashierName = currentUser.getName();
      String customerName = currentCustomer.getName();
      String dateTime = currentTransaction.getDateAndTime();
      
      builder.setHeader("Modeling 3700 Store", orderNum, cashierName, customerName, dateTime);
      
      int numOfItems = currentTransaction.getNumItems();
      java.util.List<InventoryItem> items = currentTransaction.getItems();
      for (int i = 0; i < numOfItems; i++)
      {
         InventoryItem invItem = items.get(i);
         int id =  invItem.getIDNum();
         String name = invItem.getName();
         double quantity = invItem.getQuantity();
         double price = invItem.getPrice();
         builder.addLine(id, name, quantity, price);
      }
      
      double totalTax = currentTransaction.getTotal() * 0.09;
      double bal = currentTransaction.getTotal() + totalTax;
      
      double totalTaxRounded = round(totalTax, 2);
      double balRounded = round(bal, 2);
      
      String taxStr = String.valueOf(totalTaxRounded);
      String balStr = String.valueOf(balRounded);
      
      builder.setFooter(balStr, taxStr);
      
      JOptionPane.showMessageDialog(null, builder.toString(), "Receipt!", JOptionPane.INFORMATION_MESSAGE);
   }
   
   /*
   * Displays the information for this report filter
   *
   * This filter displays the ID#, Name, and Quantity 
   * of items that have been sold.
   */
   private void allTimeProductsFilter()
   {
      String[] columns = {"ID#", "Name", "Quantity"};
      String[][] items = currentUser.getReportScreen();
      Object[][] data = new Object[items.length][3];
      
      filterPan.removeAll();
      
      for (int i = 0; i < items.length; i++ ){
         data[i][0] = items[i][0];
         data[i][1] = items[i][1];
         data[i][2] = items[i][2];
      }
   
      //creates a format for the table
      JTable table = new JTable(data, columns);
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(true);
   
      filterPan.add(scrollPane, BorderLayout.CENTER);
   
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.setLayout(new GridLayout(1,2));
      window.add(filterPan);
      window.add(changeReportPan);
      window.revalidate();
   }
   
   /*
   * Display the information for this report filter
   *
   * This filter displays the Date and Total of 
   * transactions that have been made.
   */
   private void totalRevFilter()
   {
      String[] columns = {"Date", "Total"};
      int totalAmnt = currentUser.getTotalField(); 
      String[][] items = currentUser.getDateAndTotal();
      Object[][] data = new Object[items.length][2];
     
      filterPan.removeAll();
      
      for (int i = 0; i < items.length; i++)
      {
         data[i][0] = items[i][0];
         data[i][1] = items[i][1];
      }
      
      JTable table = new JTable(data, columns);
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(true);
   
      filterPan.add(scrollPane, BorderLayout.CENTER);
      
      JTextField total = new JTextField();
      total.setText("Total Revenue: $" + totalAmnt);
      total.setEditable(false);
      total.setHorizontalAlignment(JTextField.CENTER);
      
      filterPan.add(total, BorderLayout.PAGE_END);
      
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.setLayout(new GridLayout(1,2));
      window.add(filterPan);
      window.add(changeReportPan);
      window.revalidate();
   }
   
   /*
   * Display the information for this report filter.
   *
   * This filter displays the Order Number, Date, and Total
   * for all transactions that have been made.
   */
   private void transHistoryFilter()
   {
      String[] columns = {"Order Number", "Date", "Total"};
      String[][] items = currentUser.getAllOrderInfo();
      Object[][] data = new Object[items.length][3];
      
      filterPan.removeAll();
      
      for (int i = 0; i < items.length; i++)
      {
         data[i][0] = items[i][0];
         data[i][1] = items[i][1];
         data[i][2] = items[i][2];
      }
      
      JTable table = new JTable(data, columns);
      JScrollPane scrollPane = new JScrollPane(table);
      table.setFillsViewportHeight(true);
   
      filterPan.add(scrollPane, BorderLayout.CENTER);
      
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.setLayout(new GridLayout(1,2));
      window.add(filterPan);
      window.add(changeReportPan);
      window.revalidate();
   }
   
    /*
	 * Displays the prompt for user login.
	 */
   private void getUser() {
      JTextField userName = new JTextField();
      JTextField password = new JTextField();
      JPasswordField pf = new JPasswordField();
   
      Object[] fields = {"User Name:", userName, "Password:", pf};
   
      int option = JOptionPane.showConfirmDialog(null, fields, "User Sign In",JOptionPane.OK_CANCEL_OPTION);
      if (option == JOptionPane.CANCEL_OPTION)
         currentUser = null;
      else {
         currentUser = new User();
         String pfStr = new String(pf.getPassword());
         currentUser = currentUser.getUser(userName.getText(), pfStr);	   
      }
   }

   private void getUserC() {
      JTextField userName = new JTextField();
      JTextField password = new JTextField();
      JTextField cusNum = new JTextField();
      JPasswordField pf = new JPasswordField();
   
      Object[] fields = {"User Name:", userName, "Password:", pf, "Customer #:", cusNum};
   
      int option = JOptionPane.showConfirmDialog(null, fields, "User Sign In",JOptionPane.OK_CANCEL_OPTION);
      if (option == JOptionPane.CANCEL_OPTION) {
         currentUser = null;
         return;
      }
      else {
         currentUser = new User();
         String pfStr = new String(pf.getPassword());
         currentCustomer = currentUser.getCustomer(Integer.parseInt(cusNum.getText()));
         currentUser = currentUser.getUser(userName.getText(), pfStr);	   
      }
      
   }

   /*
   * Displays the prompt for change of password.
   */
   private void changePassword() {
      JTextField newPassword = new JTextField();
      JTextField retype = new JTextField();
      JPasswordField newPF = new JPasswordField(); // new
      JPasswordField rePF = new JPasswordField(); // new
   
      //Object[] fields = {"New Password:", newPassword, "Retype Password:", retype};
      Object[] fields = {"New Password:", newPF, "Retype Password:", rePF};
   
      int option =JOptionPane.showConfirmDialog(null, fields, "Change Password",JOptionPane.OK_CANCEL_OPTION);
      if(option != JOptionPane.CANCEL_OPTION) {
         String newPFStr = new String(newPF.getPassword()); //new
         String rePFStr = new String(rePF.getPassword()); //new
         //if(!newPassword.getText().equals(retype.getText())) {
         if(!newPFStr.equals(rePFStr)) {
            JOptionPane.showMessageDialog(null, "Password does not match!", "Error", JOptionPane.INFORMATION_MESSAGE);
         }
         else {
            //currentUser.changePassword(newPassword.getText());
            currentUser.changePassword(newPFStr);
         }
      }
   }

   /*
   * Initiates the report viewing panel.
   */
   private void viewReport() {
   
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.add(reportOptionsPan);
      window.revalidate();
   }

	/*
	 * present user screen.
	 */
   private void userScreen() {
   
      getUser();
      boolean valid = (currentUser != null);
   
      if(valid){
         window.getContentPane().removeAll();
         window.getContentPane().repaint();
         window.add(userPan);
         window.revalidate();
      }
   }

	/*
	 * set image popup.
	 */
   private void setImg() {
   
   
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.showOpenDialog(null);
      File selectedFile = fileChooser.getSelectedFile();
      String path = selectedFile.getAbsolutePath();
      int checkLength = path.length() - 3;
      if(path.substring(checkLength).equals("png") || path.substring(checkLength).equals("jpg")) {
         String newPath = copyPic(path);
         currentUser.setImg(newPath);
         ImageIcon img = new ImageIcon(System.getProperty("user.dir") + newPath);
         JOptionPane.showMessageDialog(null, "", "User Image", JOptionPane.INFORMATION_MESSAGE, img);
      }
      else {
         JOptionPane.showMessageDialog(null, "Wrong file type \n must be a png or jpg", "Error", JOptionPane.INFORMATION_MESSAGE);
      }
   }

   private String copyPic(String path) {
      try {
         Random rand = new Random(); 
         int randNumber = rand.nextInt(100000) + 1;
         String dir = System.getProperty("user.dir") + "/images/" + randNumber + path.substring(path.length() - 4);
         Files.copy(Paths.get(path), Paths.get(dir));
         return "/images/" + randNumber + path.substring(path.length() - 4);
      }
      catch(Exception e){
         System.out.println(e);
      }
      return "";
   }

   private void showImg() {
      ImageIcon img = new ImageIcon(System.getProperty("user.dir") + currentUser.getImg());
      JOptionPane.showMessageDialog(null, "", "User Image", JOptionPane.INFORMATION_MESSAGE, img);
   }

	/*
	 * Displays the checkout screen.
	 */
   private void checkoutScreen(){
   
      getUserC();
      boolean valid = (currentUser != null);
   
      if(valid){
         currentTransaction = currentUser.beginTransaction(currentCustomer.getIDNum());
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
   
      getUser();
   
      boolean valid = (currentUser != null) && (currentUser.isManager());
   
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
   
      if(currentTransaction != null){
         currentTransaction.cancel();
      }
      currentUser = null;
      window.getContentPane().removeAll();
      window.getContentPane().repaint();
      window.setLayout(new GridLayout(1,2));
      window.getContentPane().add(mainPan);
      window.revalidate();
   }

	/*
	 * create new user
	 */
   private void addUser() {
      JTextField userName = new JTextField();
      JTextField pass = new JTextField();
      JPasswordField pf = new JPasswordField();
      JRadioButton manOption = new JRadioButton("Manager");
   
      Object[] fields = {"Username:" , userName,"Password:", pf, "", manOption};
      JOptionPane.showConfirmDialog(null, fields, "Create New User",JOptionPane.OK_CANCEL_OPTION);
   
      String name = userName.getText();
      String password = pass.getText();
      String pfStr = new String(pf.getPassword());
      boolean isMan = false;
      if (manOption.isSelected()) 
         isMan = true;
   
      currentUser.newUser(name, isMan, pfStr);
   }


   /*
    * create new user
    */
   private void addCustomer() {
      JTextField userName = new JTextField();
      JTextField pass = new JTextField();
      JPasswordField pf = new JPasswordField();
   
      Object[] fields = {"Userame:" , userName,"Password:", pf};
      JOptionPane.showConfirmDialog(null, fields, "Create New Customer",JOptionPane.OK_CANCEL_OPTION);
   
      String name = userName.getText();
      String password = pass.getText();
      String pfStr = new String(pf.getPassword());
      currentUser.newCustomer(name, pfStr);
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
   
      JOptionPane.showConfirmDialog(null, fields, "Create New Item",JOptionPane.OK_CANCEL_OPTION);
   
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
      Object[] fields1 = {"ID#:", prodID,"Quantity:", quantity};
   
      JOptionPane.showConfirmDialog(null, fields1, "Add Item",JOptionPane.OK_CANCEL_OPTION);
   
      int id = Integer.parseInt(prodID.getText());
      int numItems = Integer.parseInt(quantity.getText());
      if(!currentUser.doesIDExist(id)){
         JOptionPane.showMessageDialog(null, "Item Does Not Exist!");
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
      Object[] fields1 = {"ID#:", prodID,"Quantity to Remove:", quantity};
   
      JOptionPane.showConfirmDialog(null, fields1, "Remove Item",JOptionPane.OK_CANCEL_OPTION);
   
      int id = Integer.parseInt(prodID.getText());
      int numItems = Integer.parseInt(quantity.getText());
      if(!currentUser.doesIDExist(id)){
         JOptionPane.showMessageDialog(null, "Item Does Not Exist!");
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

   private void getCustomer() {
      JTextField cusNum = new JTextField();
   
      Object[] fields = {"Customer#", cusNum};
   
      int option = JOptionPane.showConfirmDialog(null, fields, "New Customer",JOptionPane.OK_CANCEL_OPTION);
      if (option == JOptionPane.CANCEL_OPTION) {
         currentUser = null;
         return;
      }
      else {
         currentCustomer = currentUser.getCustomer(Integer.parseInt(cusNum.getText()));
      }
   }
   
	/*
	 * Finishes and confirms the termination of the transaction.
	 */
   private void finishTransaction(){
      if(currentTransaction.getBalance() != 0){
         return;
      }
      currentCustomer.addPoints((int) currentTransaction.getTotal());
      currentUser.updatePoints(currentCustomer);
      currentUser.finalizeTransaction(currentTransaction);
      //getCustomer();
      showReceipt();
      currentTransaction = currentUser.beginTransaction(currentCustomer.getIDNum());
   }
}