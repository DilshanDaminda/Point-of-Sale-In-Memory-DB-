package lk.ijse.dep.fx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.dep.fx.DB_Connection.DBCON;
import lk.ijse.dep.fx.Manage.DBOrderview;
import lk.ijse.dep.fx.Manage.DBorder_details;
import lk.ijse.dep.fx.Manage.Manage_Order;
import lk.ijse.dep.fx.Manage.Order_Stock;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class Order_Checking_Viewing {
    @FXML
    public Button btnbak;
    @FXML
    public TableView <DBorder_details> tblorderdetailview;
    @FXML
    public TextField txtorderid;
    @FXML
    public TextField txtcutomerid;
    @FXML
    public TextField txtcusname;
    @FXML
    public TextField txtorderdate;
    @FXML
    public Label lbltotal;

//   public static String oid;

    @FXML
    public void backtomain(ActionEvent actionEvent) throws IOException {
        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/OrderView.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) tblorderdetailview.getScene().getWindow();
        pmstage.setScene(sc);

    }
//
//    public void initData(){
//
//    }

    public void initialize() throws SQLException {


//        DBCON.getcon();
//        PreparedStatement st=DBCON.getcon().prepareStatement("select  item_code,item_description,unit_price,order_qty,(unit_price*order_qty) as tot  from order_details where order_id=?");
//        st.setObject(1,"5");
//        ResultSet resultSet = st.executeQuery();
//        while (resultSet.next()){
//            DBorder_details dBorder_details= new DBorder_details(resultSet.getString("item_code"),resultSet.getString("item_description"),resultSet.getDouble("unit_price"),resultSet.getInt("order_qty"),resultSet.getDouble("tot"));
//            tblorderdetailview.getItems().add(dBorder_details);
//        }
//        tblorderdetailview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_code"));
//    tblorderdetailview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("item_description"));
//    tblorderdetailview.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unit_price"));
//    tblorderdetailview.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
//    tblorderdetailview.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tot"));
//
//    }

}

    public void clickReport(ActionEvent actionEvent) throws SQLException, JRException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/placeorderdb", "root", "1234");
        File file = new File("Report/Last_bill.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
        JasperViewer.viewReport(jasperPrint,false);

    }
}
