package Notify_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException{
        InetAddress host = InetAddress.getLocalHost();
        NotifyRunner notify = new NotifyRunner(5001);
        Thread thread = new Thread(notify);
        thread.start();

        Socket socket = new Socket(host, 5001);
        String message = "first";
        Scanner scan = new Scanner(System.in);
        PrintWriter print = new PrintWriter(socket.getOutputStream());
        while(!message.equals("close")){
            System.out.println("Enter message: ");
            message = scan.nextLine();
            print.println(message);
        }
        socket.close();

    }
}
