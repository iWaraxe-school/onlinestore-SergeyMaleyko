package by.issoft.store.http.handlers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import populator.DbStorePopulator;
import populator.Populator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartHandler implements HttpHandler {
    private final Populator populator = new DbStorePopulator();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuffer response = new StringBuffer();

        List<Category> categoryList = populator.getAllCategories();
        List<Product> allProductsList = new ArrayList<>();
        for (Category category : categoryList) {
            allProductsList.addAll(category.getSortProductByPrice());
        }
        Random random = new Random();
        int r = random.nextInt(10) + 1;
        for (int i = 0; i < r; i++) {
            response.append(allProductsList.get(i).toString());
        }

        exchange.sendResponseHeaders(200, response.toString().length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
