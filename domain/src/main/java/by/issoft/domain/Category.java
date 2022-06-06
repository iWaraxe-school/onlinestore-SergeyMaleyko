package by.issoft.domain;

import by.issoft.domain.categories.CategoryType;
import by.issoft.domain.comparators.ProductComparator;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final CategoryType name;
    private final List<Product> productList = new ArrayList<>();

    public Category(CategoryType name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return name.getIndex();
    }

    public String getNameCategory() {
        String categoryName = name.toString().toLowerCase();
        return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public int getSizeProduct() {
        return productList.size();
    }

    public void setItemProduct(Product product) {
        this.productList.add(product);
    }

    public List<Product> getSortProductByPrice() {
        return ProductComparator.sortProductList(productList, "price");
    }

    public List<Product> getSortProductById() {
        return ProductComparator.sortProductList(productList, "id");
    }

    public void sort() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("%s category:%n", getNameCategory()));
        List<Product> plist = ProductComparator.sortProductList(productList, "price");
        for (Product product : plist) {
            info.append(product);
        }
        info.append(String.format("Number of products: %s%n", getSizeProduct()));
        System.out.println(info);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append(String.format("Category name: %s, ", getNameCategory()));
        info.append(String.format("ID: %s.%n", getCategoryId()));
        info.append(String.format("Number of products: %s%n", getSizeProduct()));
        for (Product product : productList) {
            info.append(product.toString());
        }
        return info.toString();
    }
}
