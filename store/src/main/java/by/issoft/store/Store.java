package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.*;

public class Store {

    public List<Category> categoryList = new ArrayList<>();
    protected List<Product> productList = new ArrayList<>();

    private static Store storeInstance;

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

    public List<Product> getProductList() {
        return productList;
    }
}
