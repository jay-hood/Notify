package Thread_Test;

import java.io.IOException;
//import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Server {
    private ServerSocket serverSocket;
    private int portNumber = 9000;
    private Socket socket;
    //private PrintWriter printWriter;
    private Scanner scanner;
    private String message;


    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.output();
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }

    public Server(){
        try{
            serverSocket = new ServerSocket(portNumber);
            socket = serverSocket.accept();
            scanner = new Scanner(socket.getInputStream());
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }

    public void output() throws IOException{
        message = scanner.nextLine();
        while(!message.toLowerCase().equals("close")){
            System.out.println(message);
            message = scanner.nextLine();

        }
        socket.close();
    }

}
