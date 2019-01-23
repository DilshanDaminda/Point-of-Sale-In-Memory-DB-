package lk.ijse.dep.fx.Manage;

public class DBorder_details {
   private String item_code;
   private String item_description;
   private double unit_price;
   private int qty;
   private double tot;

    public String getItem_code() {
        return item_code;
    }

    public DBorder_details(String item_code, String item_description, double unit_price, int qty, double tot) {
        this.item_code = item_code;
        this.item_description = item_description;
        this.unit_price = unit_price;
        this.qty = qty;
        this.tot = tot;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }
}
