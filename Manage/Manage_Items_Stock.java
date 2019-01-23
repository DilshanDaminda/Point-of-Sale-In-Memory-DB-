package lk.ijse.dep.fx.Manage;

public class Manage_Items_Stock {
    private String code;
    private String description;
    private String unitprice;
    private String qty;

    public String getCode() {
        return code;
    }

    public Manage_Items_Stock(String code, String description, String unitprice, String qty) {
        this.code = code;
        this.description = description;
        this.unitprice = unitprice;
        this.qty = qty;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
