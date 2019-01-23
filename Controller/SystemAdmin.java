package lk.ijse.dep.fx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.fx.Manage.Admin_DB;
import lk.ijse.dep.fx.Manage.User_DB;

import java.io.IOException;
import java.util.ArrayList;

public class SystemAdmin {
    @FXML
    public AnchorPane frmsystem;
    @FXML
    public Button btnusercreate;
    @FXML
    public TextField txtsystemoldpassword;
    @FXML
    public TextField txtsystemnewpassword;
    @FXML
    public TextField txtuserpassword;
    @FXML
    public TextField txtusername;
    @FXML
    public TextField txtsystemnewpasswordconfirm;
    @FXML
    public TextField txtadminusername;
    @FXML
    public Button btnnewuser;
    @FXML
    public Button btnadmincreate;
    @FXML
    public Button btnnewadmin;
    @FXML
    public Button btnsystempwordchange;
    @FXML
    public TextField txtadminpassword;
    @FXML
    public TableView <Admin_DB> tbladminview;
    @FXML
    public TableView  <User_DB> tblmainuserview;
    @FXML
    public Button btnback;
    @FXML
    public Button btnmainmenu;
    @FXML
    public Button btnlogout;

    static String SystemUser="systemadmin";
    static String Syspword="123";

    public static ObservableList <Admin_DB>Admin;
    public static ObservableList <User_DB>user;

    public void initialize(){

        txtadminusername.setDisable(true);
        txtadminpassword.setDisable(true);
        txtuserpassword.setDisable(true);
        txtusername.setDisable(true);

        tblmainuserview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("User_Username"));
        tblmainuserview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("User_Userpassword"));
        if (!(user==null)){
            this.tblmainuserview.setItems(user);
        }

        if (!(Admin==null)){
            this.tbladminview.setItems(Admin);
            tbladminview.refresh();
        }
        tbladminview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Admin_User_Name"));
        tbladminview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Admin_User_Password"));



    }

    @FXML
    public void createnewuser(MouseEvent mouseEvent) {
        user=tblmainuserview.getItems();
    if(txtusername.getText().trim().isEmpty() || txtuserpassword.getText().trim().isEmpty())
    {
    new Alert(Alert.AlertType.ERROR,"Cannot have empty fields").show();
    return;
    }

    for (int i = 0; i <user.size() ; i++) {

            if (txtusername.getText().equals(user.get(i).getUser_Username()))
            {
                new Alert(Alert.AlertType.ERROR,"User Already Exist").show();
                txtusername.clear();
                txtuserpassword.clear();
                txtusername.requestFocus();
                return;
            }
        }

    user.add(new User_DB(txtusername.getText(),txtuserpassword.getText()) );
    new Alert(Alert.AlertType.INFORMATION,"New User Created");
    tblmainuserview.refresh();
    txtusername.clear();
    txtuserpassword.clear();
    return;
    }
    @FXML
    public void enableduser(MouseEvent mouseEvent) {
        txtusername.setDisable(false);
        txtuserpassword.setDisable(false);

    }
    @FXML
    public void createadmin(MouseEvent mouseEvent) {
       if (txtadminpassword.getText().trim().isEmpty() || txtadminusername.getText().trim().isEmpty()){
           new Alert(Alert.AlertType.ERROR,"Cannot have empty fields").show();
           return;
       }



       if (tbladminview.getItems().size()==2){
           new Alert(Alert.AlertType.ERROR,"Mxiumum Admin are sets").show();
           return;
       }
        Admin=tbladminview.getItems();
       Admin.add(new Admin_DB(txtadminusername.getText(),txtadminpassword.getText()));
        new Alert(Alert.AlertType.INFORMATION,"Admin has Created").show();
        tbladminview.refresh();
        txtadminusername.clear();
        txtadminpassword.clear();
        return;
    }
    @FXML
    public void ebalededit(MouseEvent mouseEvent) {
        txtadminusername.setDisable(false);
        txtadminpassword.setDisable(false);

    }
    @FXML
    public void updatesysuser(MouseEvent mouseEvent) {
        if (txtsystemoldpassword.getText().trim().isEmpty() || txtsystemnewpassword.getText().trim().isEmpty() || txtsystemnewpasswordconfirm.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Cannot have empty fields").show();
            return;
        }
        if (txtsystemoldpassword.getText().equals(Syspword)) {
            if (txtsystemnewpassword.getText().equals(txtsystemnewpasswordconfirm.getText())) {
                Syspword = txtsystemnewpassword.getText();
                new Alert(Alert.AlertType.INFORMATION,"Pasword has change").show();
                txtsystemoldpassword.clear();
                txtsystemnewpassword.clear();
                txtsystemnewpasswordconfirm.clear();
                return;
            }
            else {
                new Alert(Alert.AlertType.ERROR,"Password dosent match").show();
                return;
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Old Password dosent match").show();
            return;
        }
    }
    @FXML
    public void gotomain(MouseEvent mouseEvent) throws IOException {
        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) tbladminview.getScene().getWindow();
        pmstage.setScene(sc);
    }
    @FXML
    public void Logout(MouseEvent mouseEvent) throws IOException {
        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainLogin.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) tbladminview.getScene().getWindow();
        pmstage.setScene(sc);

    }

    public void removeadmin(MouseEvent mouseEvent) {
    }

    public void removeusers(MouseEvent mouseEvent) {
    }
}
