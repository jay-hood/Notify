package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main extends Application {

    private Socket socket;
    private int port = 9000;
    private InetAddress host;
    private Scanner scanner;
    private Scanner receiver;
    private PrintWriter printWriter;
    private String message = "";
    private boolean connected = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Controller controller = new Controller();
        loader.setController(controller);

        try {
            host = InetAddress.getLocalHost();
            socket = new Socket(host, port);
            receiver = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            InboundMessageHandler handler = new InboundMessageHandler(receiver, controller);
            Thread thread = new Thread(handler);
            thread.start();
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
        /*
        This while loop was originally intended for use with the console.
        I need to make a new method that will integrate with the GUI.
        */

        while(controller.getConnectionStatus()){
            printWriter.println(controller.getUserMessage());
        }
    }


    public static void main(String[] args) {

        launch(args);
    }

    private class InboundMessageHandler implements Runnable {

        private String message = "";
        private Scanner receiver;
        Controller controller;

        public InboundMessageHandler(Scanner receiver, Controller controller){
            this.controller = controller;
            this.receiver = receiver;
        }

        @Override
        public void run(){
            while(controller.getConnectionStatus()){
                message = receiver.nextLine();
                controller.setReceivedMessages(message);

            }
        }
    }
}


