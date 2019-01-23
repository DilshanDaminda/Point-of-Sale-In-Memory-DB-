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
import lk.ijse.dep.fx.DB_Connection.DBCON;
import lk.ijse.dep.fx.Manage.Manage_Items_Stock;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sun.security.pkcs11.Secmod;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Manage_Item {
    public static ArrayList<Manage_Items_Stock> itemstock = new ArrayList<>();
    @FXML
    public AnchorPane frmmanageitem;
    @FXML
    public TextField txtdescription;
    @FXML
    public TextField txtunitprice;
    @FXML
    public TextField txtqty;
    @FXML
    public TextField txtcode;
    @FXML
    public TableView<Manage_Items_Stock> tblmanageitem;

    public void convert() throws SQLException {
        ObservableList<Manage_Items_Stock> as = FXCollections.observableArrayList(itemstock);
        tblmanageitem.setItems(as);
    }

    public void initialize() throws SQLException {


//        convert();

        tblmanageitem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblmanageitem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblmanageitem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        tblmanageitem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));


        DBCON.getcon();
        PreparedStatement st = DBCON.getcon().prepareStatement("select * from items");
        ResultSet set = st.executeQuery();
        while (set.next()) {
//            this.itemstock.add(new Manage_Items_Stock(set.getString("item_code"), set.getString("description"), set.getString("unit_price"), set.getString("qty")));
            Manage_Items_Stock stock= new  Manage_Items_Stock(set.getString("item_code"), set.getString("description"), set.getString("unit_price"), set.getString("qty"));
           tblmanageitem.getItems().add(stock);
        }



        tblmanageitem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Manage_Items_Stock>() {
            @Override
            public void changed(ObservableValue<? extends Manage_Items_Stock> observable, Manage_Items_Stock oldValue, Manage_Items_Stock asd) {

                if (asd==null){return;}
                txtcode.setText(asd.getCode());
                txtdescription.setText(asd.getDescription());
                txtunitprice.setText(asd.getUnitprice());
                txtqty.setText(asd.getQty());
                enabled();
                txtcode.setDisable(true);
//                clear();
                return;
            }
        });

//        tblmanageitem.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Manage_Items_Stock>() {
//            @Override
//            public void changed(ObservableValue<? extends Manage_Items_Stock> observable, Manage_Items_Stock oldValue, Manage_Items_Stock pk) {
//                txtcode.setText(pk.getCode());
//                txtdescription.setText(pk.getDescription());
//                txtunitprice.setText(pk.getUnitprice());
//                txtqty.setText(pk.getQty());
//                txtqty.setDisable(true);
//            }
//        });

    }

    public void clear() {
        txtcode.clear();
        txtdescription.clear();
        txtunitprice.clear();
        txtqty.clear();
    }

    public void enabled() {
        txtcode.setDisable(false);
        txtdescription.setDisable(false);
        txtunitprice.setDisable(false);
        txtqty.setDisable(false);
    }

    public void addnewitem(ActionEvent actionEvent) {
        enabled();
    }

    public void goback(ActionEvent actionEvent) throws IOException {


        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) frmmanageitem.getScene().getWindow();
        pmstage.setTitle("Dashboard");
        pmstage.setScene(sc);
    }

    public void itemsave(ActionEvent actionEvent) throws SQLException {

        if (txtcode.getText().trim().isEmpty() || txtdescription.getText().trim().isEmpty() || txtunitprice.getText().trim().isEmpty() || txtqty.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot Have Empty Fields", ButtonType.OK).show();
            txtcode.requestFocus();
            return;
        }

        for (int i = 0; i < tblmanageitem.getItems().size(); i++) {
            DBCON.getcon();
            if (txtcode.getText().equals(tblmanageitem.getItems().get(i).getCode())) {
                if (txtcode.isDisable()) {
                    tblmanageitem.getItems().get(i).setQty(txtqty.getText());
                    tblmanageitem.getItems().get(i).setDescription(txtdescription.getText());
                    tblmanageitem.getItems().get(i).setUnitprice(txtunitprice.getText());


                    PreparedStatement statement = DBCON.getcon().prepareStatement("update items set description=?,unit_price=?,qty=? where item_code=?");
                    statement.setObject(1, txtdescription.getText());
                    statement.setObject(2, txtunitprice.getText());
                    statement.setObject(3, txtqty.getText());
                    statement.setObject(4, txtcode.getText());

                    int asd = statement.executeUpdate();
                    if (asd>0){
                        new Alert(Alert.AlertType.INFORMATION, "Item Updated", ButtonType.OK).show();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Item Not Updated", ButtonType.OK).show();
                    }

                    tblmanageitem.refresh();
                    return;
                }
            }
            if (txtcode.getText().equals(tblmanageitem.getItems().get(i).getCode())) {
                new Alert(Alert.AlertType.ERROR, "Item Already Exisist", ButtonType.OK).show();
                txtcode.requestFocus();
                return;
            }
        }


        DBCON.getcon();
        PreparedStatement st = DBCON.getcon().prepareStatement("insert into items values (?,?,?,?)");
        st.setObject(1, txtcode.getText());
        st.setObject(2, txtdescription.getText());
        st.setObject(3, txtunitprice.getText());
        st.setObject(4, txtqty.getText());
        int i = st.executeUpdate();
        if (i > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "Item Added", ButtonType.OK).showAndWait();
            Manage_Items_Stock manage_items_stock=new Manage_Items_Stock(txtcode.getText(),txtdescription.getText(),txtunitprice.getText(),txtqty.getText());
            tblmanageitem.getItems().add(manage_items_stock);
        } else {
            new Alert(Alert.AlertType.ERROR, "Item NOT Added", ButtonType.OK).showAndWait();
        }
//        convert();

        clear();
        return;
    }

    public void itemdelete(ActionEvent actionEvent) throws SQLException {
        DBCON.getcon();
        for (int i = 0; i < tblmanageitem.getItems().size(); i++) {
            if (txtcode.getText().equals(tblmanageitem.getItems().get(i).getCode())) {
                PreparedStatement st = DBCON.getcon().prepareStatement("DELETE from items where item_code=?");
                st.setObject(1, txtcode.getText());
                int rs = st.executeUpdate();
                if (rs > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Deleted", ButtonType.OK).showAndWait();
                }
                tblmanageitem.getItems().remove(i);
//                convert();
                tblmanageitem.refresh();
                clear();

                return;

            }
        }
    }

    public void getdata(MouseEvent mouseEvent) {
    }

    public void gotoitemreport(ActionEvent actionEvent) throws JRException, SQLException {
        File file=new File("table/newreport.jasper");
        System.out.println(file.getAbsoluteFile());
        JasperReport compilereport= (JasperReport) JRLoader.loadObject(file);
        JasperPrint filledreport= JasperFillManager.fillReport(compilereport,new HashMap<>(),DBCON.getcon());
        JasperViewer.viewReport(filledreport,false);
    }

}
