public interface ReceiptBuilder {
   public void setHeader(String storeName, int orderID, String cashier, String customer, String date);
   public void addLine(int productID, String productName, double qty, double price);
   public void setFooter(String total, String tax);
   public String toString();
}