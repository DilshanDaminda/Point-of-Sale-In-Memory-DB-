package lk.ijse.dep.fx.Controller;

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
import lk.ijse.dep.fx.Manage.User_DB;
import lk.ijse.dep.fx.Manage.test1;

import java.io.IOException;

public class Admin {
    @FXML
    public AnchorPane frmmainadmin;
    @FXML
    public TextField txtsystemoldpassword;
    @FXML
    public TextField txtsystemnewpassword;
    @FXML
    public TextField txtsystemnewpasswordconfirm;
    @FXML
    public TextField txtadminusername;
    @FXML
    public Button btnadmincreate;
    @FXML
    public Button btnsystempwordchange;
    @FXML
    public TableView <User_DB>tbluserview;
    @FXML
    public Button btnadmincreate1;
    @FXML
    public TextField txtusername;
    @FXML
    public Button btnnewuser;
    @FXML
    public TextField txtuserpassword;

    public static ObservableList <User_DB>user2;

    public void initialize(){

        txtadminusername.setText(MainLogin.name);
       txtadminusername.setDisable(true);
        txtusername.setDisable(true);
        txtuserpassword.setDisable(true);

        tbluserview.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("User_Username"));
        tbluserview.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("User_Userpassword"));
        if (!(SystemAdmin.user==null)){
            tbluserview.setItems(SystemAdmin.user);
        }


    }

    public void enableuser(MouseEvent mouseEvent) {
        txtusername.setDisable(false);
        txtuserpassword.setDisable(false);
    }

    public void chageadmin(MouseEvent mouseEvent) {

        if (txtsystemoldpassword.getText().trim().isEmpty() || txtsystemnewpassword.getText().trim().isEmpty() || txtsystemnewpasswordconfirm.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Cannot have empty Fields").show();
            return;
        }
        for (int i = 0; i <SystemAdmin.Admin.size() ; i++) {
            if (txtadminusername.getText().equals(SystemAdmin.Admin.get(i).getAdmin_User_Name())){

                if (txtsystemoldpassword.getText().equals(SystemAdmin.Admin.get(i).getAdmin_User_Password()));{
                    if(txtsystemnewpassword.getText().equals(txtsystemnewpasswordconfirm.getText())){
                        SystemAdmin.Admin.get(i).setAdmin_User_Password(txtsystemnewpassword.getText());
                        new Alert(Alert.AlertType.INFORMATION,"Password Updated").show();
                        txtadminusername.clear();
                        txtsystemoldpassword.clear();
                        txtsystemnewpassword.clear();
                        txtsystemnewpasswordconfirm.clear();
                        return;
                    }
                    else {
                        new Alert(Alert.AlertType.ERROR,"Password Doesen't Match").show();
                        return;
                    }
                }

            }
        }
        return;


    }

    public void createuser(MouseEvent mouseEvent) {
        SystemAdmin.user=tbluserview.getItems();
        if (txtusername.getText().trim().isEmpty() || txtuserpassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Cannot have empty Fields").show();
            return;
        }
        for (int i = 0; i <SystemAdmin.user.size() ; i++) {

            if (txtusername.getText().equals(SystemAdmin.user.get(i).getUser_Username()))
            {
                new Alert(Alert.AlertType.ERROR,"User Already Exist").show();
                txtusername.clear();
                txtuserpassword.clear();
                txtusername.requestFocus();
                return;
            }
        }
        SystemAdmin.user.add(new User_DB(txtusername.getText(),txtuserpassword.getText()));
        tbluserview.refresh();
        txtusername.clear();
        txtuserpassword.clear();

        return;
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainLogin.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) tbluserview.getScene().getWindow();
        pmstage.setScene(sc);
        return;
    }
}
