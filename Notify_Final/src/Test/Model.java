package Test;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Model {

    private String username;
    private String address;
    private Stage connectionStage;
    private Stage usernameStage;

    private String userMessage;
    private String messsage;
    private Boolean connectionStatus = true;
    private Socket socket;
    private int port = 9000;
    private Scanner receiver;
    private PrintWriter printWriter;
    private TextArea textarea;
    private InetAddress host;
    private Thread thread;

    public void setTextArea(TextArea textarea){
        this.textarea = textarea;
    }

    public void initConnection(){
        connectionStatus = true;
        try {
            if(address.equals("host")) {
                host = InetAddress.getLocalHost();
                socket = new Socket(host, port);
            }
            else{
                socket = new Socket(address, port);
            }
            receiver = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            /*
            Task task = new Task<Void>() {
              @Override public Void call(){
                  String message;
                  while(connectionStatus){
                    message = receiver.nextLine();
                    textarea.appendText(message + "\n");
                }
              return null;
              }
            };
            new Thread(task).start();
            //update();
            */
            Model.InboundMessageHandler handler = new Model.InboundMessageHandler();
            thread = new Thread(handler);
            thread.start();

            /*
            Platform.runLater(new Runnable(){
                public void run(){
                    String message;
                    while(connectionStatus){
                        message = receiver.nextLine();
                        textarea.appendText(message + "\n");
                        //This feels wrong, but it might work. I should look into if there's a better way of doing this.
                    }
                }
            });
            */
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }


    public void clearConnection(){
        host = null;
        connectionStatus = false;
        if(socket!=null){
            try {
                socket.close();
            }
            catch(IOException iex){
                iex.printStackTrace();
            }
        }
        socket = null;
        receiver = null;
        printWriter = null;
        if(thread!=null){
            thread.interrupt();
        }
    }


    public void setUsername(String username){
        this.username = username;

    }

    public void setAddress(String address){
        this.address = address;

    }

    public void sendMessage(String message){
        printWriter.println(message);
    }

    public String getUsername(){
        return username;
    }

    public String getAddress(){
        return address;
    }

    public void setConnectionStage(Stage stage){
        this.connectionStage = stage;
    }

    public Stage getConnectionStage(){
        return this.connectionStage;
    }

    public void setUsernameStage(Stage stage){
        this.usernameStage = stage;
    }

    public Stage getUsernameStage(){
        return this.usernameStage;
    }


    private class InboundMessageHandler implements Runnable {

        private String message;
        @Override
        public void run(){
            while(connectionStatus){
                    message = receiver.nextLine();
                Platform.runLater(() ->{
                    textarea.appendText(message + "\n");
                });
            }
        }
    }
}

