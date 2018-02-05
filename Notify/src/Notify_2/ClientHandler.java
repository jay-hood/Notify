package Notify_2;

class ClientHandlerOne{
    public static void main(String[] args) {
        Notify_2.Client clientOne = new Notify_2.Client();
        Thread threadOne = new Thread(clientOne);
        threadOne.start();

    }
}

class ClientHandlerTwo{
    public static void main(String[] args) {
        Notify_2.Client clientTwo = new Notify_2.Client();
        Thread threadTwo = new Thread(clientTwo);
        threadTwo.start();
    }

}
