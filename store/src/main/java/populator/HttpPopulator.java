package populator;

import by.issoft.domain.Category;
import by.issoft.store.http.Client;
import by.issoft.store.http.Server;

import java.util.List;

public class HttpPopulator implements Populator {
     private final Client client = new Client();

    @Override
    public void fillStore() {
        Server storeServer = new Server();
        storeServer.startServer();
    }

    @Override
    public List<Category> getAllCategories() {
        return client.getAllCategories();
    }

    @Override
    public void addProductToCart() {
        client.addProductToCart();
    }
}
