package Notify_2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

//import java.util.ArrayList;


public class Server {
    private ServerSocket serverSocket;
    private int portNumber = 9000;
    private Socket socket;
    //public ArrayList<PrintWriter> clients = new ArrayList<>();
    private ConcurrentHashMap<Integer, ConnectionHandler> hashMap = new ConcurrentHashMap<>();
    //private PrintWriter printWriter;
    private int ID = 0;




    public static void main(String[] args) {
        Server server = new Server();
        server.runServer();
    }

    public Server(){
        try{
            serverSocket = new ServerSocket(portNumber);
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }
    public void runServer(){
        try{
            while (true) {
                socket = serverSocket.accept();
                ConnectionHandler CH = new ConnectionHandler(socket);
                Thread connection = new Thread(CH);
                connection.start();
                hashMap.put(ID, CH);
                ID++;
            }
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }

    private class ConnectionHandler implements Runnable {
        private Socket connectionSocket;
        private Scanner scanner;
        private String incomingRequest;
        private PrintWriter pw;
        //private String username;


        public PrintWriter getPrintWriter(){
            return this.pw;
        }

        public ConnectionHandler(Socket socket) {
            this.connectionSocket = socket;
        }
        //next step is to test the hashmap and then iterate through it, getting each printwriter
        //and using it to send the message to everyone.
        public void run() {
            try{
                scanner = new Scanner(connectionSocket.getInputStream());
                pw = new PrintWriter(connectionSocket.getOutputStream(), true);
                incomingRequest = scanner.nextLine();


                /*
                Okay the point of this initial string is to use it as the key for the hashmap.
                But the guy on reddit said that I should instead use a concurrenthashmap that uses a key
                Which would be a unique username or ID that was associated with each user.
                Rather than this, it should be something like
                String incomingRequest = null
                while (incomingRequest = scanner.readLine() != null) {
                   ...
                }
                Okay so apparently .readLine is a bufferedreader method. This means that I'd have to change what
                the scanner to something else.
                */
                //hashMap.put(message,pw);
                //I think this is the "read message" location that the commenter on reddit was talking about.
                while (incomingRequest!= null) {
                    System.out.println(incomingRequest);
                    for(ConnectionHandler out : hashMap.values()){
                        //if(out.getPrintWriter()!=this.pw) {
                            System.out.println("Sending message.");
                            out.getPrintWriter().println(incomingRequest);
                        //}
                    }
                    //pw.println(message);
                    incomingRequest = scanner.nextLine();
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

