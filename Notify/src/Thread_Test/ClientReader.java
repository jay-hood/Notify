package Thread_Test;

import java.util.Scanner;

public class ClientReader implements Runnable {
    private String message = "";
    private Scanner receiver;

    public ClientReader(Scanner receiver){
        this.receiver = receiver;
    }

    @Override
    public void run(){
        while(!message.equals("close")){
            message = receiver.nextLine();
            System.out.println("Incoming message: " + message);
        }
    }
}
