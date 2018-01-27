package Thread_Test;

import java.io.IOException;
//import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;




public class Server{
    private ServerSocket serverSocket;
    private int portNumber = 9000;
    private Socket socket;
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
                Thread thread = new Thread(CH);
                thread.start();
            }
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }


}
