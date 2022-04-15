package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final String name;

    List<Product> productList;

    public Category(String name) {
        this.name = name;
        productList = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("Category name: %s. The list of products: %n", name));
        for (Product product : productList) {
            info.append(product.toString());
        }
        return info.toString();
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void printAllProducts() {
        System.out.println(productList.toString());
    }
}
