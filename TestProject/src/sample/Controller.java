package sample;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Controller {
    private String userMessage;
    private Boolean connectionStatus = true;
    private Socket socket;
    private int port = 9000;
    private InetAddress host;
    private Scanner receiver;
    private PrintWriter printWriter;

    @FXML
    private TextArea userEntry;

    @FXML
    private TextArea receivedMessages;


    public void initConnection(){
        try {
            host = InetAddress.getLocalHost();
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
    /*
    I think a good idea would be to test the functionality by passing the text from the user entry box
    to the received messages box. Just to make sure everything works alright and formats properly before attempting to
    add genuine communications.
     */
    public void submitButtonClicked(){
        userMessage = userEntry.getText().replaceAll("\n", System.getProperty("line.separator"));
        userEntry.clear();
        printWriter.println(userMessage);
    }

    public void connectionClicked(){
        try{
            FXMLLoader loadConnectionWindow = new FXMLLoader(getClass().getResource("connection.fxml"));
            Parent root1 = loadConnectionWindow.load();
            Stage stage = new Stage();
            stage.setTitle("Connection Details");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }

    /*
    public String getUserMessage(){
        return userMessage;
    }

    public void setReceivedMessages(String message){
        receivedMessages.setText(message);
    }



    public boolean getConnectionStatus(){
        return connectionStatus;
    }
    */

    public void connectionClosed(){
        connectionStatus = false;
        userEntry.setText("Connection closed.");
    }

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
}
