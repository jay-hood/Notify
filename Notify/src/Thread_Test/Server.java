package Thread_Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Server{
    private ServerSocket serverSocket;
    private int portNumber = 9000;
    private Socket socket;
    public ArrayList<PrintWriter> clients = new ArrayList<>();

    //private PrintWriter printWriter;



    public static void main(String[] args) {
        Server server = new Server();
        server.output();
    }

    public Server(){
        try{
            serverSocket = new ServerSocket(portNumber);
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }
    public void output(){
        try{
            while (true) {
                socket = serverSocket.accept();
                ConnectionHandler CH = new ConnectionHandler(socket);
                Thread connection = new Thread(CH);
                connection.start();
            }
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }

    private class ConnectionHandler implements Runnable {
        private Socket connectionSocket;
        private Scanner scanner;
        private String message;
        private PrintWriter pw;


        public ConnectionHandler(Socket socket) {
            this.connectionSocket = socket;
        }

        public void run() {
            try{
                scanner = new Scanner(connectionSocket.getInputStream());
                pw = new PrintWriter(connectionSocket.getOutputStream(), true);
                clients.add(pw);
                message = scanner.nextLine();
                while (!message.toLowerCase().equals("close")) {
                    System.out.println(message);
                    pw.println(message);
                    message = scanner.nextLine();

                }
                connectionSocket.close();
            }
            catch (IOException iex) {
                iex.printStackTrace();
            }
        }
    }


}

