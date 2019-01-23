package lk.ijse.dep.fx.Manage;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class Manage_Order {
    private String code;
    private String description;
    private String unitprice;
    private String qty;
      private String date;
    private String customer;
    private String customer_id;
    private String order_id;


    private String total;

    public Manage_Order(String code, String description, String unitprice, String qty, String total) {

        this.code = code;
        this.description = description;
        this.unitprice = unitprice;
        this.qty = qty;
        this.total = total;
    }





    public String getCode() {
        return code;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
