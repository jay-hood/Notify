package sample;

import Model_Version.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Notify!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Controller controller = loader.getController();
        controller.connectionClicked();
        controller.initConnection();
        */

    }


    public static void main(String[] args) {

        launch(args);
    }

}


