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

public class MainLogin {
    public AnchorPane frmlogin;
    public TextField txtusename;
    public Button btnlogin;
    public TextField txtpassword;
    public Button btnclear;

    public static String name="";
    public static int a=0;
    String x="";
    String y="";



    public void loginto(MouseEvent mouseEvent) throws IOException {
        x = txtusename.getText();
        y = txtpassword.getText();

        if (txtusename.getText().trim().isEmpty() || txtpassword.getText().trim().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Cannot have Empty Fields").show();
            return;

        }
        if (x.equals(SystemAdmin.SystemUser) && y.equals(SystemAdmin.Syspword)) {
            Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
            Scene sc = new Scene(root);
            Stage pmstage = (Stage) frmlogin.getScene().getWindow();
            pmstage.setTitle("Dashboard");
            pmstage.setScene(sc);
            this.a = 1;
            this.name=txtusename.getText();
            return;
        }
        if (!(SystemAdmin.Admin==null)){
            for (int i = 0; i < SystemAdmin.Admin.size(); i++) {
                if (x.equals(SystemAdmin.Admin.get(i).getAdmin_User_Name()) && y.equals(SystemAdmin.Admin.get(i).getAdmin_User_Password())) {
                    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
                    Scene sc = new Scene(root);
                    Stage pmstage = (Stage) frmlogin.getScene().getWindow();
                    pmstage.setScene(sc);
                    this.name = txtusename.getText();
                    this.a = 2;
                    return;
                }
            }
            }
            if (!(SystemAdmin.user==null)){

            for (int q = 0; q < SystemAdmin.user.size(); q++) {
                if (x.equals(SystemAdmin.user.get(q).getUser_Username()) && y.equals(SystemAdmin.user.get(q).getUser_Userpassword())) {
                    Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainView.fxml"));
                    Scene sc = new Scene(root);
                    Stage pmstage = (Stage) frmlogin.getScene().getWindow();
                    pmstage.setScene(sc);
                    this.name=txtusename.getText();
                    this.a = 3;
                    return;
                }
            }
            }

            new Alert(Alert.AlertType.ERROR,"Please Check You Entry").show();
        txtpassword.clear();
        txtusename.clear();
            return;

    }

    public void clearall(MouseEvent mouseEvent) {
        txtpassword.clear();
        txtusename.clear();
    }
}
