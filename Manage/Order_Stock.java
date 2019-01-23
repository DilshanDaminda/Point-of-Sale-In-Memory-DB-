package lk.ijse.dep.fx.Manage;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Order_Stock {
    public static ArrayList asd= new ArrayList();


    private String date;
    private String customer_name;
    private String customer_id;
    private String order_id;

    public static ArrayList getAsd() {
        return asd;
    }

    private String total;

    public Order_Stock(String order_id, String customer_id, String date, String customer_name, String total, ObservableList<Manage_Order> check) {
        this.date = date;
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.total = total;
      this.asd= (ArrayList) check;
    }

    public Order_Stock(String order_id, String customer_id, String date, String customer_name, String total) {
        this.date = date;
        this.customer_name = customer_name;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.total = total;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


}
