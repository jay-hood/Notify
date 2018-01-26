package Thread_Test;

public class ClientRunner {
    public static void main(String[] args) {
        Client clientOne = new Client();
        Thread threadOne = new Thread(clientOne);
        threadOne.start();
        Client clientTwo = new Client();
        Thread threadTwo = new Thread(clientTwo);
        threadTwo.start();
    }
}
