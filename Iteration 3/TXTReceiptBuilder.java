public class TXTReceiptBuilder implements ReceiptBuilder {
   StringBuilder str = new StringBuilder();
   @Override
   public void setHeader(String storeName, int orderID, String cashier, String customer, String date)
   {
      str.append("---------------------------------------------------------------\n");
      str.append(storeName);
      str.append("\n");
      str.append("Order Number: " + String.valueOf(orderID));
      str.append("\n");
      str.append("Cashier: " + cashier + "\n");
      str.append("Customer: " + customer + "\n");
      str.append("Date: " + date + "\n");
      str.append("---------------------------------------------------------------\n");
   }
   
   public void setFooter(String totalCost, String totalTax)
   {
      str.append("---------------------------------------------------------------\n");
      str.append("Total Tax: $" + totalTax + "\n");
      str.append("Total: $" + totalCost + "\n");
      str.append("Thanks for Shopping With Us!!!");
   }
   
   public void addLine(int productID, String productName, double qty, double price)
   {
      str.append(productID);
      str.append("     ");
      str.append(productName);
      str.append("     ");
      str.append(qty);
      str.append("     $");
      str.append(price);
      str.append("\n");
   }
   
   public String toString()
   {
      return str.toString();
   }
}