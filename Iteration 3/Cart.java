import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;


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

   public Cart(int userNum, int customer, int orderNum){
      dateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
      idNum = START + orderNum;
      cusNum = customer;
   }

   public List getItems(){
      return items;
   }

   public void setItems(List i){
      items = i;
   }

   public double getTotal(){
      return total;
   }

   public void setTotal(double t){
      total = t;
   }

   public void setPayments(List p){
      payments = p;
   }

   public List getPayments() {
      return payments;
   }

   public void setBalance(double b){
      total = b;
   }

   public int getNumItems(){
      return items.size();
   }

   public String getDT() {
      return dateTime;
   }

   public int getID() {
      return idNum;
   }
	
   public int getCusNum() {
      return cusNum;
   }

   public void addCashPayment(double amount){
      PaymentType type = new PaymentType();
      payments.add(type.beginPaymentSession(amount));
   }

   public void addItem(InventoryItem i){
      items.add(i);
   }
}