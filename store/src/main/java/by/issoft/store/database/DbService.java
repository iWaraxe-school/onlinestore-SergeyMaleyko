package by.issoft.store.database;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.CategoryType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbService extends  DbConnection {

    Connection connection = getConnection();

    public void disconnectDb() throws SQLException {
        connection.close();
        System.out.println("COMPLETED.");
    }

    public void createTables() {
        PreparedStatement preparedStatement = null;
        String create_category_table_query = "CREATE TABLE IF NOT EXISTS CATEGORY (" +
                "category_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL)";
        String create_product_table_query = "DROP TABLE IF EXISTS PRODUCT;" +
                " CREATE TABLE IF NOT EXISTS PRODUCT (" +
                " product_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                " category_id INTEGER NOT NULL," +
                " name VARCHAR(255) NOT NULL," +
                " price DOUBLE NOT NULL," +
                " rate DOUBLE NOT NULL," +
                " CONSTRAINT fk_category_id" +
                " FOREIGN KEY (category_id)" +
                " REFERENCES CATEGORY (category_id));";
        try {
            preparedStatement = connection.prepareStatement(create_category_table_query);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(create_product_table_query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertAllCategories(List<Category> categoryList) {
        for (Category category : categoryList) {
            insertCategory(category);
        }
    }

    public void insertCategory(Category category) {
        PreparedStatement preparedStatement = null;
        String categoryName = category.getNameCategory();
        String checkExisting = String.format(
                "SELECT category_id FROM CATEGORY WHERE name = '%s';", categoryName);
        String query = String.format(
                "INSERT INTO CATEGORY (name) VALUES ('%s');", categoryName);
        try {
            preparedStatement = connection.prepareStatement(checkExisting);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.absolute(1)) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertProduct(Product product) {
        PreparedStatement preparedStatement = null;
        try {
            String query = String.format(
                    "INSERT INTO PRODUCT (category_id, name, price, rate) " +
                            "VALUES (%d, '%s', %.2f, %.2f);"
                    , product.getCategoryId()
                    , product.getName().replace("'","''")
                    , product.getPrice()
                    , product.getRate());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertAllProducts(List<Category> categoryList, List<Product> productList) {
        for (Category category : categoryList) {
            productList.addAll(category.getSortProductById());
        }
        for (Product product : productList) {
            insertProduct(product);
        }
        System.out.println("The generated random data have been filed in DB.");
    }

    public List<Product> selectAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT p.* FROM Product p " +
                        "JOIN Category c ON p.category_id = c.category_id " +
                        "ORDER BY c.category_id ASC, p.product_id ASC;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Product product = Product.newBuilder()
                        .setProductId(resultSet.getInt("product_id"))
                        .setCategoryId(resultSet.getInt("category_id"))
                        .setName(resultSet.getString("name"))
                        .setPrice(resultSet.getDouble("price"))
                        .setRate(resultSet.getDouble("rate"))
                        .build();
                productList.add(product);
            }
        }
        return productList;
    }

    public List<Category> selectAllCategories() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        List<Product> productList;
        String sqlCategory = "SELECT * FROM Category ORDER BY category_id ASC;";
        productList = selectAllProducts();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCategory);
            while (resultSet.next()) {
                String categoryName = resultSet.getString("name").toUpperCase();
                Category category = new Category(CategoryType.valueOf(categoryName));
                for (Product product : productList) {
                    if (product.getCategoryId() == resultSet.getInt("category_id")) {
                        category.setItemProduct(product);
                    }
                }
                categoryList.add(category);
            }
        }
        System.out.println("The store data have been populated from DB.");
        return categoryList;
    }
}
