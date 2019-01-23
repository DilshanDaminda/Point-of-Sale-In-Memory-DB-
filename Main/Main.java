package lk.ijse.dep.fx.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/fx/View/MainLogin.fxml"));
        Scene sc=new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
