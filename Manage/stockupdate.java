package lk.ijse.dep.fx.Manage;

public class stockupdate {
    private String itemcode;
    private int quantity;
    private String description;
    private double unitprice;

    public stockupdate(String itemcode, int quantity, String description, double unitprice) {
        this.itemcode = itemcode;
        this.quantity = quantity;
        this.description = description;
        this.unitprice = unitprice;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }
}
