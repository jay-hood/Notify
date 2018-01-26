package TCP_Programming_Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


class Sender{
    public static void main(String[] args) throws IOException{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host, 7001);
        Scanner scan = new Scanner(socket.getInputStream());
        PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
        String message = "Hello!";
        print.println(message);
        while(scan.hasNextLine()){
            message = scan.nextLine();
            System.out.println(message);
        }
        socket.close();

    }
}
