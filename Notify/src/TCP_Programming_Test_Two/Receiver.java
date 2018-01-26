package TCP_Programming_Test_Two;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Receiver {

    //To make a multithreaded server, everything here would need to be in a Runnable class that would be instantiated as
    //a new user to be handled by a larger client. The multithreaded server would have to be started by itself and then the
    //requests would be handled by a client class.
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
