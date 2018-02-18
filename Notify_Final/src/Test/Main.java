package Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


        Model model = new Model();
        FXMLLoader primary = new FXMLLoader(getClass().getResource("main2.fxml"));
        Test.Controller controller = new Test.Controller(model);
        primary.setController(controller);
        Parent root = primary.load();
        primaryStage.setTitle("Notify!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        FXMLLoader connection = new FXMLLoader(getClass().getResource("connection.fxml"));
        ConnectionController conController = new Test.ConnectionController((model));
        connection.setController(conController);

        FXMLLoader username = new FXMLLoader(getClass().getResource("username.fxml"));
        username.setController(conController);

        Parent root2 = connection.load();
        Parent root3 = username.load();

        controller.setConnectionRoot(root2);
        controller.setUsernameRoot(root3);
        controller.connectionStartUp();

    }


    public static void main(String[] args) {

        launch(args);
    }

}


