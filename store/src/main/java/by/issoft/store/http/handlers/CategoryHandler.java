package by.issoft.store.http.handlers;

import by.issoft.domain.Category;
import by.issoft.store.http.json.JsonUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import populator.DbStorePopulator;
import populator.Populator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CategoryHandler implements HttpHandler {
    private final Populator populator = new DbStorePopulator();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        StringBuffer response = new StringBuffer();
        List<Category> categoryList = populator.getAllCategories();

        JsonUtils.buildStoreJson(categoryList);
        response.append(JsonUtils.buildStoreJson(categoryList));

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
