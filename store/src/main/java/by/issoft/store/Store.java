package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.comparators.ProductComparator;

import java.util.ArrayList;
import java.util.List;

public class Store {

    public List<Category> categoryList = new ArrayList<>();
    protected List<Product> productList = new ArrayList<>();
    private volatile static Store storeInstance;

    // Singleton pattern
    private Store() {
    }

    public static Store getInstance() {
        if (storeInstance != null) {
            return storeInstance;
        }
        synchronized (Store.class) {
            if (storeInstance == null) {
                storeInstance = new Store();
            }
            return storeInstance;
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void sort() {
        for (Category category : categoryList) {
            category.sort();
        }
    }

    public void top() {
        List<Product> productList = new ArrayList<>();
        for (Category category : categoryList) {
            productList.addAll(category.getSortProductByPrice());
        }
        ProductComparator.sortProductReversed(productList, "price");
        System.out.println("Top 5 products by price:");
        for (int i = 0; i < 5; i++) {
            System.out.println(productList.get(i));
        }
    }
}
