package Thread_Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


public class Server{
    private ServerSocket serverSocket;
    private int portNumber = 9000;
    private Socket socket;
    //public ArrayList<PrintWriter> clients = new ArrayList<>();
    private HashMap<String, PrintWriter> hashMap = new HashMap<>();
    //private PrintWriter printWriter;
    private Iterator iterator = hashMap.entrySet().iterator();



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
        //next step is to test the hashmap and then iterate through it, getting each printwriter
        //and using it to send the message to everyone.
        public void run() {
            try{
                scanner = new Scanner(connectionSocket.getInputStream());
                pw = new PrintWriter(connectionSocket.getOutputStream(), true);
                message = scanner.nextLine();
                hashMap.put(message,pw);
                while (!message.toLowerCase().equals("close")) {
                    System.out.println(message);
                    for(PrintWriter out : hashMap.values()){
                        if(out!=pw) {
                            System.out.println("Sending message.");
                            out.println(message);
                        }
                    }
                    //pw.println(message);
                    message = scanner.nextLine();

                }
                System.out.println(hashMap.toString());
                connectionSocket.close();
            }
            catch (IOException iex) {
                iex.printStackTrace();
            }
        }
    }


}

