package by.issoft.store.http;

import by.issoft.domain.Category;
import by.issoft.store.http.json.JsonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class Client {
    public static final String HOST = "localhost";
    public static final int PORT = 8080;
    public static final String PROTOCOL = "http";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "password";

    public HttpURLConnection getConnection(String path, String method) {
        HttpURLConnection connection = null;
        try {
            URL address = new URL(PROTOCOL, HOST, PORT, path);
            connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod(method);
            Base64.Encoder encoder = Base64.getEncoder();
            String auth = USERNAME + ":" + PASSWORD;
            connection.setRequestProperty("Authorization", "Basic " + encoder.encodeToString(auth.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Category> getAllCategories() {
        HttpURLConnection httpURLConnection = new Client().getConnection("/categories", "GET");
        List<Category> categoryList = null;
        try {
            InputStreamReader input = new InputStreamReader(httpURLConnection.getInputStream());
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = input.read()) != -1) {
                sb.append((char) c);
            }
            /** Parse Json. */
            categoryList = JsonUtils.parseStoreJson(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            System.out.println("The store data have been populated form HttpServer.");
            httpURLConnection.disconnect();
        }
        return categoryList;
    }

    public String addProductToCart() {
        HttpURLConnection httpURLConnection = new Client().getConnection("/cart", "POST");
        StringBuilder sb = new StringBuilder();
        sb.append("Products are placed in Order:\n");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (br.ready()) {
                sb.append(br.readLine().trim() + "\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return sb.toString();
    }
}
