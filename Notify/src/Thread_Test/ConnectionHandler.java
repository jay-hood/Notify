package Thread_Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class ConnectionHandler implements Runnable {

    private Socket socket;
    private Scanner scanner;
    private String message;


    public ConnectionHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            scanner = new Scanner(socket.getInputStream());
            message = scanner.nextLine();
            while (!message.toLowerCase().equals("close")) {
                System.out.println(message);
                message = scanner.nextLine();

            }
            socket.close();
        }
        catch(IOException iex){
            iex.printStackTrace();
        }
    }
}
