package lk.ijse.dep.fx.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class User {
    public AnchorPane frmuserchange;
    public TextField txtsystemoldpassword;
    public TextField txtsystemnewpassword;
    public TextField txtsystemnewpasswordconfirm;
    public Button btnchangepassword;
    public TextField txtadminusername1;
    public void initialize(){
        txtadminusername1.setDisable(true);
        txtadminusername1.setText(MainLogin.name);
    }

    public void changepassword(MouseEvent mouseEvent) {

        if (txtsystemnewpassword.getText().trim().isEmpty() || txtsystemnewpasswordconfirm.getText().trim().isEmpty() || txtsystemoldpassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Cannot have empty fields").show();
        }

        for (int i = 0; i <SystemAdmin.user.size() ; i++) {
            if (txtadminusername1.getText().equals(SystemAdmin.user.get(i).getUser_Username())){
                if (txtsystemoldpassword.getText().equals(SystemAdmin.user.get(i).getUser_Userpassword())){
                    if(txtsystemnewpassword.getText().equals(txtsystemnewpasswordconfirm.getText())){
                        SystemAdmin.user.get(i).setUser_Userpassword(txtsystemnewpassword.getText());
                        new Alert(Alert.AlertType.INFORMATION,"Password Updated").show();
                        txtadminusername1.clear();
                        txtsystemoldpassword.clear();
                        txtsystemnewpasswordconfirm.clear();
                        txtsystemnewpassword.clear();
                        return;
                    }
                    else {
                        new Alert(Alert.AlertType.ERROR,"Password Dosen't Match").show();
                        return;
                    }
                }
                else {
                    new Alert(Alert.AlertType.ERROR,"Old Password Dosen't Match").show();
                    return;
                }
            }
        }

    }

    public void bak(MouseEvent mouseEvent) throws IOException {
        Parent path = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainLogin.fxml"));
        Scene sc = new Scene(path);
        Stage pmstage = (Stage) frmuserchange.getScene().getWindow();
        pmstage.setScene(sc);
    }
}
