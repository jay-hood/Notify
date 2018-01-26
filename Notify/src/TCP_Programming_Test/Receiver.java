package TCP_Programming_Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Receiver {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(7001);
        Socket socket = server.accept();
        Scanner scan = new Scanner(socket.getInputStream());
        PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
        String message;
        while(scan.hasNextLine()) {
            message = scan.nextLine();
            System.out.println("Received message: " + message);
            print.println("Message received: " + message);
        }
        socket.close();
    }
}
