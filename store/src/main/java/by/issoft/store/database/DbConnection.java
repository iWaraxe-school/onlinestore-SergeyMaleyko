package by.issoft.store.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL =
        // Server
        "jdbc:h2:tcp://localhost/~/NavigatorStore";
        // Embedded
        // "jdbc:h2:~/NavigatorStore";
    private static final String DB_USER = "adm";
    private static final String DB_PASSWORD = "";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if(!connection.isClosed()) {
                System.out.println("DB processing ...");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
