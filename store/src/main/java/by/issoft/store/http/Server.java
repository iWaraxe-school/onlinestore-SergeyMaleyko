package by.issoft.store.http;

import by.issoft.store.http.handlers.CartHandler;
import by.issoft.store.http.handlers.CategoryHandler;
import by.issoft.store.http.handlers.EchoHandler;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

    public static final int PORT = 8080;
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "password";
    private static final int THREADS_AMOUNT = 20;
    private static final int MAX_THREADS_AMOUNT = 200;
    private static final int QUEUE_CAPACITY = 1024;

    public void startServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
            createContext(server,"/", new EchoHandler());
            createContext(server, "/categories", new CategoryHandler());
            createContext(server, "/cart", new CartHandler());
            server.setExecutor(new ThreadPoolExecutor(
                    THREADS_AMOUNT,
                    MAX_THREADS_AMOUNT,
                    0,
                    TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(QUEUE_CAPACITY)));
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createContext(HttpServer server, String path, HttpHandler handler) {
        server.createContext(path, handler).setAuthenticator(new BasicAuthenticator("store") {
            @Override
            public boolean checkCredentials(String user, String password) {
                return user.equals(USERNAME) && password.equals(PASSWORD);
            }
        });
    }
}
