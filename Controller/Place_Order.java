package lk.ijse.dep.fx.Controller;

import javafx.beans.binding.ObjectExpression;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.fx.DB_Connection.DBCON;
import lk.ijse.dep.fx.Manage.Manage_Items_Stock;
import lk.ijse.dep.fx.Manage.Manage_Order;
import lk.ijse.dep.fx.Manage.stockupdate;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.omg.CORBA.OBJECT_NOT_EXIST;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Place_Order {
    public static ArrayList<stockupdate> orders = new ArrayList();
    public static ArrayList asd = new ArrayList();
    public ObservableList<Manage_Order> itembulk;
    @FXML
    public Button btnadd;
    @FXML
    public TextField txtorderid;
    @FXML
    public TableView<Manage_Order> tblorderdetails;
    @FXML
    public TextField txtcustomerid;
    @FXML
    public TextField txtitemcode;
    @FXML
    public TextField txtunitprice;
    @FXML
    public TextField txtqtyonhand;
    @FXML
    public TextField txtdate;
    @FXML
    public TextField txttotal;
    @FXML
    public TextField txtcusname;
    @FXML
    public TextField txtdescription;
    @FXML
    public TextField txtqty;
    @FXML
    public Button btnremove;
    @FXML
    public Button btnplaceorder;
    @FXML
    public Label lblgotomain;
    int count = 0;
    double total = 0;
    double whtotal = 0;

    public Place_Order() throws JRException {
    }


    public void clear() {
        txtitemcode.clear();
        txtcustomerid.clear();
        txtqty.clear();
        txtorderid.clear();
        txtqtyonhand.clear();
        txttotal.clear();
        txtdescription.clear();
        txtcusname.clear();
    }

    public void autoincrement() throws SQLException {
        DBCON.getcon();
        PreparedStatement sdf = DBCON.getcon().prepareStatement("select count(order_id) as asd  from order_main");
        ResultSet set = sdf.executeQuery();
        int x = 0;
        while (set.next()) {
            x = Integer.parseInt(set.getString("asd"));
        }
        count = x + 1;
        txtorderid.setText(Integer.toString(count));
    }

    public void initialize() throws SQLException {

        DBCON.getcon();
        PreparedStatement st = DBCON.getcon().prepareStatement("select * from items");
        ResultSet set = st.executeQuery();
        ArrayList<stockupdate> temp = new ArrayList();
        while (set.next()) {

            temp.add(new stockupdate(set.getString("item_code"), set.getInt("qty"), set.getString("description"), set.getDouble("unit_price")));
        }
        this.orders = temp;
        System.out.println(orders.size());
        System.out.println(orders.get(1).getItemcode());


        autoincrement();
        txtdate.setText(LocalDate.now().toString());
        txtorderid.setDisable(true);


        tblorderdetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblorderdetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblorderdetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        tblorderdetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblorderdetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        if (itembulk != null) {
            tblorderdetails.setItems(itembulk);
        }
        tblorderdetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Manage_Order>() {
            @Override
            public void changed(ObservableValue<? extends Manage_Order> observable, Manage_Order oldValue, Manage_Order pk) {

                if (pk == null) {
                    return;
                }
                txtitemcode.setText(pk.getCode());
                txtqty.setText(pk.getQty());
                txtunitprice.setText(pk.getUnitprice());
                txtitemcode.requestFocus();
                return;
            }
        });

    }

    public void findtotal() {
        double asd = 0;
        for (int i = 0; i < itembulk.size(); i++) {

            double x = Double.parseDouble(itembulk.get(i).getTotal());
            asd += x;
        }
        txttotal.setText(Double.toString(asd));
    }


    @FXML
    public void Adding(ActionEvent actionEvent) throws SQLException {

        DBCON.getcon();

        if (txtcustomerid.getText().trim().isEmpty() || txtitemcode.getText().trim().isEmpty() || txtqty.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot Proceed With Empty Fields", ButtonType.OK).show();
            return;
        }

        double x, y, z, tot;
        x = Double.parseDouble(txtqty.getText());
        y = Double.parseDouble(txtunitprice.getText());
        z = Double.parseDouble(txtqtyonhand.getText());
        tot = x * y;
        this.itembulk = tblorderdetails.getItems();

        if (z == 0) {
            new Alert(Alert.AlertType.ERROR, "Item Out Of Stock", ButtonType.OK).show();
            return;
        }

        if (!(x <= z)) {
            new Alert(Alert.AlertType.ERROR, "Item Quantity not valid", ButtonType.OK).show();
            return;
        }

        for (int i = 0; i < itembulk.size(); i++) {
            if (txtitemcode.getText().equals(itembulk.get(i).getCode())) {
                double p, q, r, s, t;
                p = Double.parseDouble(txtunitprice.getText());
                q = Double.parseDouble(txtqty.getText());
                r = Double.parseDouble(itembulk.get(i).getQty());
                s = r + q;
                t = s * p;
                itembulk.get(i).setQty(Double.toString(s));
                itembulk.get(i).setTotal(Double.toString(t));
                stockupdate();
                findtotal();
                tblorderdetails.refresh();
                return;
            }
        }
//        PreparedStatement st=DBCON.getcon().prepareStatement("insert into ")
        itembulk.add(new Manage_Order(txtitemcode.getText(), txtdescription.getText(), txtunitprice.getText(), txtqty.getText(), Double.toString(tot)));
        findtotal();
        stockupdate();
        txtitemcode.clear();
        txtqty.clear();
        txtqtyonhand.clear();
        txtdescription.clear();
        txtunitprice.clear();
        return;
    }


    public void stockupdate() {
        for (int i = 0; i < orders.size(); i++) {
            if (txtitemcode.getText().equals(orders.get(i).getItemcode())) {
                int x, y, z;
                x = orders.get(i).getQuantity();
                y = Integer.parseInt(txtqty.getText());
                if (x < y) {
                    return;
                }
                z = x - y;
                this.orders.get(i).setQuantity(z);
//                txtqtyonhand.setText(Integer.toString(z));
                return;
            }
        }

    }

    @FXML
    public void removing(ActionEvent actionEvent) {

        String xc = tblorderdetails.getSelectionModel().getSelectedItem().getCode();
        int temp = Integer.parseInt(tblorderdetails.getSelectionModel().getSelectedItem().getQty());

        for (int n = 0; n < orders.size(); n++) {
            if (xc.equals(orders.get(n).getItemcode())) {
                int p;
                p = orders.get(n).getQuantity();
                this.orders.get(n).setQuantity(p + temp);
            }
        }
//        tblorderdetails.getItems().remove(tblorderdetails.getSelectionModel().getSelectedItem());
        for (int i = 0; i <= itembulk.size(); i++) {
            if (xc.equals(itembulk.get(i).getCode())) {
                tblorderdetails.getSelectionModel().clearSelection();
                itembulk.remove(i);

                Alert x = new Alert(Alert.AlertType.CONFIRMATION, "Item Deleted", ButtonType.OK);
                x.show();
                findtotal();
                txtitemcode.clear();
                txtunitprice.clear();
                txtdescription.clear();
                txtqty.clear();
                txtqtyonhand.clear();
                txtitemcode.requestFocus();
                return;
            }
        }


    }

    @FXML
    public void placeorder(ActionEvent actionEvent) throws SQLException, JRException {
        DBCON.getcon();
        if (itembulk == null) {
            new Alert(Alert.AlertType.ERROR, "There is no Data", ButtonType.OK).show();
            return;
        }
        if (tblorderdetails.getItems().size() == 0) {
            new Alert(Alert.AlertType.ERROR, "There is no Data", ButtonType.OK).show();
            return;
        }
        PreparedStatement st = DBCON.getcon().prepareStatement("INSERT INTO order_main values (?,?,?)");
        st.setObject(1, txtorderid.getText());
        st.setObject(2, txtdate.getText());
        st.setObject(3, txtcustomerid.getText());
        int i = st.executeUpdate();
        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Saved", ButtonType.OK).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order unable to Saved", ButtonType.OK).show();
        }
//        orders.add(new Order_Stock(txtorderid.getText(), txtcustomerid.getText(), txtdate.getText(), txtcusname.getText(), txttotal.getText(), itembulk));
        PreparedStatement asd = DBCON.getcon().prepareStatement("INSERT into order_details values (?,?,?,?,?)");
        int qw = 0, s = 0;
//        for (int j = 0; j <tblorderdetails.getItems().size() ; j++) {

        ObservableList<Manage_Order> temp = tblorderdetails.getItems();

        for (Manage_Order manage_order : temp
        ) {

            asd.setObject(1, txtorderid.getText());
            asd.setObject(2, manage_order.getCode());
            asd.setObject(3, manage_order.getDescription());
            asd.setObject(4, manage_order.getUnitprice());
            asd.setObject(5, manage_order.getQty());


//            PreparedStatement stockupdate=DBCON.getcon().prepareStatement("select qty from items where item_code=?");
//            stockupdate.setObject(1,manage_order.getCode());

            PreparedStatement update = DBCON.getcon().prepareStatement("UPDATE items set qty=? where item_code=?");
            for (int j = 0; j < orders.size(); j++) {
                if (manage_order.getCode().equals(orders.get(i).getItemcode())) {
                    update.setObject(1, orders.get(i).getQuantity());
                    update.setObject(2, manage_order.getCode());
                    s = update.executeUpdate();
                }

            }


            qw = asd.executeUpdate();

        }
        if (qw > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Order details Saved", ButtonType.OK).show();
        }
        if (s > 0) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Item Updated", ButtonType.OK).show();
        }


//        String Oid = txtorderid.getText();
//        String cus_id = txtcustomerid.getText();
//        String cus_name = txtcusname.getText();
//        File file = new File("Report/place_order.jasper");
//        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
//
//
//        HashMap<String, Object> parems = new HashMap<>();
//        parems.put("oid", Oid);
//        parems.put("cus_id", cus_id);
//        parems.put("cus_name", cus_name);
//
//        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"item_code", "item_desc", "item_qty", "item_unitPrice", "total"}, 0);
//        ObservableList<Manage_Order> itmes = tblorderdetails.getItems();
//
//        for (Manage_Order itme : itmes) {
//            Object[] rowDate = {itme.getCode(), itme.getDescription(), itme.getQty(), itme.getUnitprice(), itme.getTotal()};
//            dtm.addRow(rowDate);
//        }
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parems, new JRTableModelDataSource(dtm));
//        JasperViewer.viewReport(jasperPrint,false);





        this.itembulk.clear();
        this.itembulk = null;
        this.tblorderdetails.getItems().clear();
        clear();
        autoincrement();
        return;


    }

    @FXML
    public void gotomainmenu(MouseEvent mouseEvent) throws IOException {
        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) btnadd.getScene().getWindow();
        pmstage.setTitle("Dashboard");
        pmstage.setScene(sc);


    }

    public void serachcus(KeyEvent keyEvent) throws SQLException {

        DBCON.getcon();
        PreparedStatement asd = DBCON.getcon().prepareStatement("select cusname from customer where cusid=?");
        asd.setObject(1, txtcustomerid.getText());
        ResultSet query = asd.executeQuery();
        while (query.next()) {
            txtcusname.setText(query.getNString("cusname"));
        }

    }

    public void searchitem(KeyEvent keyEvent) throws SQLException {

        for (int i = 0; i < orders.size(); i++) {
            if (txtitemcode.getText().equals(orders.get(i).getItemcode())) {
                txtdescription.setText(orders.get(i).getDescription());
                txtunitprice.setText(orders.get(i).getUnitprice() + "");
                txtqtyonhand.setText(orders.get(i).getQuantity() + "");
                return;
            }
        }
    }

    public void clickReport(ActionEvent actionEvent) throws JRException {
        String Oid = txtorderid.getText();
        String cus_id = txtcustomerid.getText();
        String cus_name = txtcusname.getText();
        File file = new File("Report/place_order.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);


        HashMap<String, Object> parems = new HashMap<>();
        parems.put("oid", Oid);
        parems.put("cus_id", cus_id);
        parems.put("cus_name", cus_name);

        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"item_code", "item_desc", "item_qty", "item_unitPrice" }, 0);
        ObservableList<Manage_Order> itmes = tblorderdetails.getItems();

        for (Manage_Order itme : itmes) {
            Object[] rowDate = {itme.getCode(), itme.getDescription(), itme.getQty(), itme.getUnitprice()    };
            dtm.addRow(rowDate);
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parems, new JRTableModelDataSource(dtm));
        JasperViewer.viewReport(jasperPrint,false);


//
//        File file = new File("table/place_order.jasper");
//        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
//
//        HashMap<String, Object> parems = new HashMap<>();
//        parems.put("oid", Oid);
//        parems.put("cus_id", Cus_id);
//        parems.put("cus_name", Cus_Name);
//
//        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"item_code", "item_desc", "item_qty", "item_unitPrice", "total"}, 0);
//        ObservableList<OrderDetailTM> itmes = tblOrderDetails.getItems();
//
//        for (OrderDetailTM item : itmes) {
//            Object[] rowDate = {item.getCode(), item.getDescription(), item.getQty(), item.getUnitPrice(), item.getTotal()};
//            dtm.addRow(rowDate);
//        }
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parems, new JRTableModelDataSource(dtm));
//        JasperViewer.viewReport(jasperPrint,false);
    }
}