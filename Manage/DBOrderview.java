package lk.ijse.dep.fx.Manage;

public class DBOrderview {

    private String order_id;
    private String order_date;
    private String customer_id;
    private String Customer_name;

    public String getOrder_id() {
        return order_id;
    }

    public DBOrderview(String order_id, String order_date, String customer_id, String customer_name) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.customer_id = customer_id;
        Customer_name = customer_name;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return Customer_name;
    }

    public void setCustomer_name(String customer_name) {
        Customer_name = customer_name;
    }
}
