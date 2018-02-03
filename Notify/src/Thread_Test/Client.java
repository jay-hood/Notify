package Thread_Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable{
    private Socket socket;
    private int port = 9000;
    private InetAddress host;
    private Scanner scanner;
    private Scanner receiver;
    private PrintWriter printWriter;
    private String message = "";


    @Override
    public void run(){
        try {
            host = InetAddress.getLocalHost();
            socket = new Socket(host, port);
            scanner = new Scanner(System.in);
            receiver = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            ClientReader CR = new ClientReader(receiver);
            Thread thread = new Thread(CR);
            thread.start();
        }
        catch(IOException iex){
            iex.printStackTrace();
        }

        while(!message.toLowerCase().equals("close")){
            System.out.print("Outgoing message: ");
            message = scanner.nextLine();
            printWriter.println(message);
            /*
            message = receiver.nextLine();
            System.out.println("Incoming message: " + message);
            */

        }
    }

}
