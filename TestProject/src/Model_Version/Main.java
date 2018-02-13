package Model_Version;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


        Model model = new Model();
        FXMLLoader primary = new FXMLLoader(getClass().getResource("main.fxml"));
        Model_Version.Controller controller = new Model_Version.Controller(model);
        primary.setController(controller);
        Parent root = primary.load();
        primaryStage.setTitle("Notify!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();



        FXMLLoader connection = new FXMLLoader(getClass().getResource("connection.fxml"));
        connection.setController(new Model_Version.ConnectionController(model));
        controller.setFXMLLoader(connection);
        controller.connectionClicked();

    }


    public static void main(String[] args) {

        launch(args);
    }

}


