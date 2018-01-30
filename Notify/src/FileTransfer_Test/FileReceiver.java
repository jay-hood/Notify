package FileTransfer_Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceiver {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9002);
        Socket socket = serverSocket.accept();

        //DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        byte[] bytes = new byte[1024];

        in.read(bytes);
        System.out.println(bytes);

        FileOutputStream fos = new FileOutputStream("C:\\users\\hoodj\\Desktop\\FileTest\\test.txt");
        fos.write(bytes);
        in.close();
    }
}
