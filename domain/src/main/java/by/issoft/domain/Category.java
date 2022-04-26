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
        String categoryName = name.toString().toLowerCase();
        if(categoryName.isEmpty()) return "";
        return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1);
    }

    public int getProductSize() {
        return productList.size();
    }

    public void setProductItem(Product product) {
        this.productList.add(product);
    }

    @Override
    public String toString() {
        String name = getName();
        StringBuilder info = new StringBuilder();
        info.append(String.format("%nCategory name: %s. The list of products: %n", name));
        for (Product product : productList) {
            info.append(product.toString());
        }
        info.append(String.format("Number of products: %s", getProductSize()));

        return info.toString();
    }
}
