package lk.ijse.dep.fx.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import lk.ijse.dep.fx.DB_Connection.DBCON;
import lk.ijse.dep.fx.Manage.Manage_customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sun.awt.SunHints;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Manage_Customer {
    @FXML
    public AnchorPane frmnewcustomer;
    @FXML
    public TableView<Manage_customer> tblcustomer;
    @FXML
    public TextField txtcusid;
    @FXML
    public TextField txtcusname;
    @FXML
    public TextField txtcusaddress;





public static ObservableList<Manage_customer> customerTMS;

    public void initialize() throws SQLException {



        tblcustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cusid"));
        tblcustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cusname"));
        tblcustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cusaddress"));

        DBCON.getcon();
        PreparedStatement st=DBCON.getcon().prepareStatement("select * from customer");
        ResultSet rs =st.executeQuery();
        while (rs.next()){
//            customerTMS =tblcustomer.getItems();
//            this.customerTMS.add(new Manage_customer(rs.getString("cusid"),rs.getString("cusname"),rs.getString("address")));
            Manage_customer manage_customer= new Manage_customer(rs.getString("cusid"),rs.getString("cusname"),rs.getString("address"));
            tblcustomer.getItems().add(manage_customer);
        }


        tblcustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Manage_customer>() {
            @Override
            public void changed(ObservableValue<? extends Manage_customer> observable, Manage_customer oldValue, Manage_customer x) {
                if (x==null){return;}
                txtcusid.setText(x.getCusid());
                txtcusname.setText(x.getCusname());
                txtcusaddress.setText(x.getCusaddress());
                enable();
                txtcusid.setDisable(true);

            }
        });




    }
public void enable(){
    txtcusid.setDisable(false);
    txtcusaddress.setDisable(false);
    txtcusname.setDisable(false);
    }

  public void cleared(){
        txtcusid.clear();
        txtcusname.clear();
        txtcusaddress.clear();
  }
    @FXML
    public void newcustomer(ActionEvent actionEvent) {
        enable();
        cleared();
    }

    @FXML
    public void newcustomer_save(ActionEvent actionEvent) throws SQLException {
        String x, y, z;
        x = txtcusid.getText();
        y = txtcusname.getText();
        z = txtcusaddress.getText();
        DBCON.getcon();
        for (int i = 0; i < tblcustomer.getItems().size(); i++) {

            if (txtcusid.getText().equals(tblcustomer.getItems().get(i).getCusid())) {
                if (txtcusid.isDisable()) {

                    PreparedStatement statement=DBCON.getcon().prepareStatement("UPDATE customer set cusname=?,address=? where cusid=?");
                    statement.setObject(1,txtcusname.getText());
                    statement.setObject(2,txtcusaddress.getText());
                    statement.setObject(3,txtcusid.getText());
                    int i1 = statement.executeUpdate();
                        if (i1>0){
                    new Alert(Alert.AlertType.INFORMATION, "User Is updated", ButtonType.OK).show();
                    tblcustomer.getItems().get(i).setCusname(txtcusname.getText());
                    tblcustomer.getItems().get(i).setCusaddress(txtcusaddress.getText());
                            tblcustomer.refresh();
                    cleared();
                            return;
                     }
                    else {
                            new Alert(Alert.AlertType.ERROR, "User Is Not updated", ButtonType.OK).show();
                            return;
                        }

                }

            }

            if (txtcusid.getText().equals(tblcustomer.getItems().get(i).getCusid())) {
                new Alert(Alert.AlertType.ERROR, "User Is Already Exist", ButtonType.OK).show();
                cleared();
                return;
            }

        }
        if (x.trim().isEmpty() || y.trim().isEmpty() || z.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot Have Empty Fields", ButtonType.OK).show();
            txtcusid.requestFocus();
            return;
        }



        PreparedStatement st = DBCON.getcon().prepareStatement("insert into customer values (?,?,?)");
        st.setObject(1, x);
        st.setObject(2, y);
        st.setObject(3, z);
        int i = st.executeUpdate();
        if (i > 0) {
            new Alert(Alert.AlertType.INFORMATION, "User Successfully Added", ButtonType.OK).show();
            Manage_customer manage_customer=new Manage_customer(x,y,z);
            tblcustomer.getItems().add(manage_customer);
            cleared();
            return;
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer not Added", ButtonType.OK).show();
            return;
        }

    }

    @FXML
    public void Gotomain(MouseEvent mouseEvent) throws IOException {
        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) frmnewcustomer.getScene().getWindow();
        pmstage.setTitle("Dashboard");
        pmstage.setScene(sc);
    }
    @FXML
    public void newcus_delete(ActionEvent actionEvent) throws SQLException {
        String x;
        x=txtcusid.getText();
        for (int i = 0; i <tblcustomer.getItems().size() ; i++) {
            if(x.equals(tblcustomer.getItems().get(i).getCusid())){

                PreparedStatement st=DBCON.getcon().prepareStatement("DELETE from customer where cusid=?");
                st.setObject(1,txtcusid.getText());
                int rst = st.executeUpdate();
                if (rst>0){
                    new Alert(Alert.AlertType.INFORMATION,"Customer Deleted",ButtonType.OK).showAndWait();
                }
                else {
                    new Alert(Alert.AlertType.ERROR,"Customer Not Deleted",ButtonType.OK).showAndWait();
                }
                tblcustomer.getItems().remove(i);
                tblcustomer.getSelectionModel().clearSelection();
//                converter();

                cleared();

                return;
            }
        }

    }

    @FXML
    public void senddata(MouseEvent mouseEvent) {

    }

    public void gotoreports(ActionEvent actionEvent) throws JRException, SQLException {


        Connection connection = DBCON.getcon();
        File file = new File("table/customernew.jasper");
        System.out.println(file.getAbsoluteFile());

        JasperReport jasperReport =  (JasperReport) JRLoader.loadObject(file);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);
        JasperViewer.viewReport(jasperPrint);
    }
}
//    {
//        File file=new File("Report/CustomerReport.jasper");
////           System.out.println(file.getAbsolutePath());
//        JasperReport compilereport= (JasperReport) JRLoader.loadObject(file);
//      DefaultTableModel temp=new DefaultTableModel(new Object[]{"Customer_ID","Customer_Name","Customer_Address"},0);
//        ObservableList<Manage_customer> asd=tblcustomer.getItems();
////        for (Manage_customer cus:asd) {
////
////            Object[] rowdata={cus.getCusid(),cus.getCusname(),cus.getCusaddress()};
////            temp.addRow(rowdata);
////        }
////        JasperPrint filledreport= JasperFillManager.fillReport(compilereport,new HashMap<>(),new JRTableModelDataSource(temp));
////        JasperViewer.viewReport(filledreport,false);
////    }
//
