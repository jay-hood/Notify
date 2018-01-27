package Thread_Test;

class ClientHandlerOne{
    public static void main(String[] args) {
        Client clientOne = new Client();
        Thread threadOne = new Thread(clientOne);
        threadOne.start();

    }
}

class ClientHandlerTwo{
    public static void main(String[] args) {
        Client clientTwo = new Client();
        Thread threadTwo = new Thread(clientTwo);
        threadTwo.start();
    }

}
