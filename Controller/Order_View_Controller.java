package lk.ijse.dep.fx.Controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.xml.internal.ws.wsdl.writer.document.StartWithExtensionsType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.dep.fx.DB_Connection.DBCON;
import lk.ijse.dep.fx.Manage.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Order_View_Controller {
    @FXML
    public TableView<DBOrderview> tblsearchtable;
    @FXML
    public  TextField txtordersearch;
    @FXML
    public  Label lblgotomain;
   private static ObservableList<DBOrderview> as;
    public static ObservableList<Manage_Order> datadd;
    public static String oid;

    public void initialize() throws SQLException {


        DBCON.getcon();
        PreparedStatement st=DBCON.getcon().prepareStatement(" select order_main.order_id,order_main.order_date,order_main.cusid,customer.cusname from order_main inner join customer on order_main.cusid=customer.cusid;");
        ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                DBOrderview dbOrderview=new DBOrderview(resultSet.getString("order_id"),resultSet.getString("order_date"),resultSet.getString("cusid"),resultSet.getString("cusname"));
                tblsearchtable.getItems().add(dbOrderview);

            }
            as=tblsearchtable.getItems();

        tblsearchtable.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order_id"));
        tblsearchtable.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("order_date"));
        tblsearchtable.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tblsearchtable.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("customer_name"));
//        tblsearchtable.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
//        this.as = FXCollections.observableArrayList(Place_Order.orders);
//        this.tblsearchtable.setItems(as);
//        this.datadd=FXCollections.observableArrayList(Order_Stock.asd);
        tblsearchtable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            double total=0;
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {


                    FXMLLoader loader=new FXMLLoader();
                    loader.setLocation(getClass().getResource("/lk/ijse/dep/fx/View/Order_Checking.fxml"));

                    try {
                        Parent orderview=loader.load();
                        Scene sc=new Scene(orderview);
                      Order_Checking_Viewing control=  loader.getController();
                      control.txtorderid.setText(tblsearchtable.getSelectionModel().getSelectedItem().getOrder_id());
                        control.txtcutomerid.setText(tblsearchtable.getSelectionModel().getSelectedItem().getCustomer_id());
                        control.txtcusname.setText(tblsearchtable.getSelectionModel().getSelectedItem().getCustomer_name());
                        control.txtorderdate.setText(tblsearchtable.getSelectionModel().getSelectedItem().getOrder_date());
                        oid=tblsearchtable.getSelectionModel().getSelectedItem().getOrder_id();


                        PreparedStatement st= null;
                        try {
                            st = DBCON.getcon().prepareStatement("select  item_code,item_description,unit_price,order_qty,(unit_price*order_qty) as tot  from order_details where order_id=?");
                            st.setObject(1,oid);
                            ResultSet resultSet = st.executeQuery();
                            while (resultSet.next()){
                                DBorder_details dBorder_details= new DBorder_details(resultSet.getString("item_code"),resultSet.getString("item_description"),resultSet.getDouble("unit_price"),resultSet.getInt("order_qty"),resultSet.getDouble("tot"));
                               control.tblorderdetailview.getItems().add(dBorder_details);
                                total+=resultSet.getDouble("tot");
                            }
                            control.lbltotal.setText(total+"");

                            control.tblorderdetailview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_code"));
                            control.tblorderdetailview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("item_description"));
                            control.tblorderdetailview.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unit_price"));
                            control.tblorderdetailview.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
                            control.tblorderdetailview.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tot"));


                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        Stage pmstage = (Stage) tblsearchtable.getScene().getWindow();
                        pmstage.setScene(sc);






//




                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void sorting(KeyEvent keyEvent) {

        tblsearchtable.setItems(as);
        ObservableList temp=FXCollections.observableArrayList();
        for (int i = 0; i <as.size() ; i++) {

            if (as.get(i).getOrder_id().startsWith(txtordersearch.getText())){

                String x= as.get(i).getOrder_id();
                String y= as.get(i).getOrder_date();
                String z=as.get(i).getCustomer_id();
                String p=as.get(i).getCustomer_name();
//                String q=as.get(i).getTotal();
                temp.add(new DBOrderview(x,y,z,p));
                tblsearchtable.setItems(temp);
            }
        }

        }

    public void gotomainmenu(MouseEvent mouseEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
        Scene sc=new Scene(root);
        Stage pmstage=(Stage) lblgotomain.getScene().getWindow();
        pmstage.setTitle("Dashboard");
        pmstage.setScene(sc);
        return;
    }

    public void asdasd(MouseEvent mouseEvent) {


        }

    public void clickReport(ActionEvent actionEvent) throws SQLException, JRException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/placeorderdb", "root", "1234");
        File file = new File("Report/allOrder.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
        JasperViewer.viewReport(jasperPrint,false);
    }
}

