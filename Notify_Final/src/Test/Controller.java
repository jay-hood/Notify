package Test;

//import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//import javafx.scene.control.Button;

public class Controller {

    private String userMessage;
    private Boolean connectionStatus = true;
    private Socket socket;
    private int port = 9000;
    private String host;
    private Scanner receiver;
    private PrintWriter printWriter;
    private final Model model;
    private Parent loadConnectionWindow;
    private Parent loadUsernameWindow;

    @FXML
    private TextArea userEntry;

    @FXML
    private TextArea receivedMessages;

    public Controller(Model model){
        this.model = model;
    }


    /*
    public void initConnection(){
        try {
            //host = InetAddress.getLocalHost();
            socket = new Socket(host, port);
            receiver = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            InboundMessageHandler handler = new InboundMessageHandler(receiver);
            Thread thread = new Thread(handler);
            thread.start();
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }

    I think a good idea would be to test the functionality by passing the text from the user entry box
    to the received messages box. Just to make sure everything works alright and formats properly before attempting to
    add genuine communications.
     */
    public void submitButtonClicked(){
        userMessage = userEntry.getText().replaceAll("\n", " ");
        //userMessage = userEntry.getText();
        //receivedMessages.setText(userMessage);//testing receivedMessages
        model.sendMessage(getUserMessage());
        userEntry.clear();
        //printWriter.println(userMessage);
    }

    public void setConnectionRoot(Parent root){
        this.loadConnectionWindow = root;
    }

    public void setUsernameRoot(Parent root){
        this.loadUsernameWindow = root;
    }


    public void connectionStartUp(){

            model.setTextArea(receivedMessages);
            Stage stage = new Stage();
            stage.setTitle("Connection Details");
            stage.setScene(new Scene(loadConnectionWindow));
            stage.show();
            model.setConnectionStage(stage);

    }

    public void connectionClicked(){
        model.getConnectionStage().show();
    }

    public void setHost(){
        this.host = model.getAddress();
    }

    public void close(){
        System.exit(0);
    }


    public String getUserMessage() {
        return model.getUsername() + ": " + userMessage;
    }

    public void changeUsername(){

            Stage stage = new Stage();
            stage.setTitle("Change Username");
            stage.setScene(new Scene(loadUsernameWindow));
            stage.show();
            model.setUsernameStage(stage);
    }

    public void setReceivedMessages(String message){
        receivedMessages.setText(message);
    }



    public boolean getConnectionStatus(){
        return connectionStatus;
    }


    public void connectionClosed(){
        connectionStatus = false;
        userEntry.setText("Connection closed.");
    }
    /*
    private class InboundMessageHandler implements Runnable {

        private String message = "";
        private Scanner receiver;

        public InboundMessageHandler(Scanner receiver){
            this.receiver = receiver;
        }

        @Override
        public void run(){
            while(connectionStatus){
                message = receiver.nextLine();
                receivedMessages.setText(message);
            }
        }
    }
    */
}
