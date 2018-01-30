package FileTransfer_Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class FileSender {
    public static void main(String[] args) {

        String fileLocation = "C:\\users\\hoodj\\Desktop\\test.txt";
        File file = new File(fileLocation);
        //long fileSize = file.length();
        byte [] array = new byte[8192];

        //Need to read the documentation on input and output streams
        int port = 9002;
        try {
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host, port);
            FileInputStream input = new FileInputStream(file);
            OutputStream output = socket.getOutputStream();
            int count;
            while((count = input.read(array)) > 0){
                output.write(array, 0, count);
            }
            input.close();
            output.close();
            socket.close();

        }
        catch(IOException iex){
            iex.printStackTrace();
        }


    }
}
