package Notify_1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NotifyRunner implements Runnable{

    private InetAddress host;
    private int outgoing_port;
    private int incoming_port;
    private Socket socket;
    private ServerSocket server_socket;

    public NotifyRunner(int port) throws IOException{
        this.server_socket = new ServerSocket(port);
    }

    @Override
    public void run(){
        try{
            this.socket = server_socket.accept();
            System.out.println("Connection established!");
            Scanner scan = new Scanner(socket.getInputStream());
            String message = "";
            while(!message.equals("close")){
                message = scan.nextLine();
                System.out.println("Received message: " + message);
            }
            socket.close();
        }
        catch(IOException iex){
            iex.printStackTrace();
        }


    }
}
