package TCP_Programming_Test_Two;

import java.io.IOException;

public class Start_Server {

    public static void main(String[] args) throws IOException {
        MultiThreadServer MTS = new MultiThreadServer(9000);
        Thread thread = new Thread(MTS);
        thread.start();
    }
}
