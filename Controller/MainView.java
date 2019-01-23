package lk.ijse.dep.fx.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.fx.DB_Connection.DBCON;
import lk.ijse.dep.fx.Manage.Manage_Items_Stock;
import lk.ijse.dep.fx.Manage.Manage_customer;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainView {
    @FXML
    public AnchorPane frmMain;


    @FXML
    public void gotomanagecustomer(MouseEvent mouseEvent) throws IOException {
        Parent path= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/Manage_Cutomer.fxml"));
        Scene sc=new Scene(path);
        Stage pmstage=(Stage) frmMain.getScene().getWindow();
        pmstage.setTitle("Manage Customer");
        pmstage.setScene(sc);
    }
    @FXML
    public void gotomanageitems(MouseEvent mouseEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/Manage_Items.fxml"));
        Scene sc=new Scene(root);
        Stage pmstage=(Stage) frmMain.getScene().getWindow();
        pmstage.setTitle("Manage Item");
        pmstage.setScene(sc);
    }
    @FXML
    public void PlaceinOrder(MouseEvent mouseEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/Order.fxml"));
        Scene sc=new Scene(root);
        Stage pmstage=(Stage) frmMain.getScene().getWindow();
        pmstage.setTitle("Place Order");
        pmstage.setScene(sc);
    }

    public void vieworder(MouseEvent mouseEvent) throws IOException {
        Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/OrderView.fxml"));
        Scene sc=new Scene(root);
        Stage pmstage=(Stage) frmMain.getScene().getWindow();
        pmstage.setTitle("View Order");
        pmstage.setScene(sc);
    }

    public void gotosettings(MouseEvent mouseEvent) throws IOException {
        if (MainLogin.a==1){
            Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/SystemAdmin.fxml"));
            Scene sc=new Scene(root);
            Stage pmstage=(Stage) frmMain.getScene().getWindow();
            pmstage.setScene(sc);
        }

        if (MainLogin.a==2){
            Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/Admin.fxml"));
            Scene sc=new Scene(root);
            Stage pmstage=(Stage) frmMain.getScene().getWindow();
            pmstage.setScene(sc);
        }
        if (MainLogin.a==3){
            Parent root=FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/UserView.fxml"));
            Scene sc=new Scene(root);
            Stage pmstage=(Stage) frmMain.getScene().getWindow();
            pmstage.setScene(sc);
        }

    }

    public void gotothetest(MouseEvent mouseEvent) throws IOException {

    }

    public void gotoooooo(MouseEvent mouseEvent) throws IOException {

    }
}
