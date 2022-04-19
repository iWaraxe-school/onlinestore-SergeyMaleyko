package by.issoft.domain;

import by.issoft.domain.categories.CategoryNames;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final CategoryNames name;

    private List<Product> productList;

    public Category(CategoryNames name) {
        this.name = name;
        productList = new ArrayList<Product>();
    }

    public String getName() {
        return name.toString();
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

    public void setProductItem(Product product) {
        this.productList.add(product);
    }
}
