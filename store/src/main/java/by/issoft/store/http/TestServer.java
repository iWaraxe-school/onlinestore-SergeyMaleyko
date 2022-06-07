package by.issoft.store.http;

public class TestServer {
    public static void main(String[] args) {
        System.out.println("Test HTTP.");
        Server storeServer = new Server();
        storeServer.startServer();
        Client client = new Client();

        System.out.println("Print - getAllCategories -\n" + client.getAllCategories());

        System.out.println("Print - addProductToCart -\n" + client.addProductToCart());
    }
}
