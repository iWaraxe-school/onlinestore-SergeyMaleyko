package by.issoft.domain;

import by.issoft.domain.categories.CategoryNames;
import by.issoft.domain.comparators.ProductComparator;

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
        return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1);
    }

    public int getProductSize() {
        return productList.size();
    }

    public void setProductItem(Product product) {
        this.productList.add(product);
    }

    public List<Product> getProductList() {
        return ProductComparator.sortProductList(productList, "price");
    }

    public void sort() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s category:%n", getName()));
        List<Product> plist = ProductComparator.sortProductList(productList, "");
        for (Product product : plist) {
            info.append(product);
        }
        info.append(String.format("Number of products: %s%n", getProductSize()));
        System.out.println(info);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%nCategory name: %s. The list of products: %n", getName()));
        for (Product product : productList) {
            info.append(product.toString());
        }
        info.append(String.format("Number of products: %s", getProductSize()));

        return info.toString();
    }
}
