package Notify_2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

//import java.util.ArrayList;


public class Server {
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
                /*
                Okay the point of this initial string is to use it as the key for the hashmap.
                But the guy on reddit said that I should instead use a concurrenthashmap that uses a key
                Which would be a unique username or ID that was associated with each user.
                Rather than this, it should be something like
                String incomingRequest = null
                while (incomingRequest = scanner.readLine() != null) {
                   ...
                }

                */
                hashMap.put(message,pw);
                //I think this is the "read message" location that the commenter on reddit was talking about.
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

