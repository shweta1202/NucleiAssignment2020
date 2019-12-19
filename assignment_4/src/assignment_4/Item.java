package assignment_4;

public class Item {
    private int itemID;
    private String itemName;
    private double itemPrice;
    private String itemType;
    private double finalPrice;

    public int getItemId() {
        return itemID;
    }
    public void setId(int itemID) {
        this.itemID = itemID;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemType() {
        return itemType;
    }
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public double getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }
    @Override
    public String toString() {
        return "Item [Item Name=" + itemName + ", Type=" + itemType + ", Final Price=" + finalPrice + "]";
    }

}
