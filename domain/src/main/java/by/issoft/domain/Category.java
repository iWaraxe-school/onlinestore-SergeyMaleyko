package by.issoft.domain;

import by.issoft.domain.categories.CategoryNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Category {

    private final CategoryNames name;

    private Map<Category, Integer> categoryMap;

    private final List<Product> productList;

    public Category(CategoryNames name) {
        this.name = name;
        productList = new ArrayList<>();
    }

    public String getName() {
        return name.toString();
    }

    public int getProductSize() {
        return productList.size();
    }

    public void setProductItem(Product product) {
        this.productList.add(product);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%nCategory name: %s. The list of products: %n", name));
        for (Product product : productList) {
            info.append(product.toString());
        }
        info.append(String.format("Number of products: %s", getProductSize()));

        return info.toString();
    }
}
