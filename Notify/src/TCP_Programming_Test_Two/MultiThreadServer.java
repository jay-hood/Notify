package TCP_Programming_Test_Two;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer implements Runnable{

    private int port_number = 0;
    private ServerSocket socket;
    private Thread thread;
    private InetAddress host;

    public MultiThreadServer(int port_number) throws IOException{
        this.port_number = port_number;
        this.host = InetAddress.getLocalHost();
    }

    @Override
    public void run(){
        try{
            Socket sock  = socket.accept();
            Client client = new Client();
            thread = new Thread(client);
        }
        catch(IOException iex){
            iex.printStackTrace();
        }

    }

}
