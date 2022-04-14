package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import utils.RandomStorePopulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    private Map<Category, Integer> categoryProductMap = new HashMap<>();

    private List<Category> categoryList = new ArrayList<Category>();

    public Store() {    }

    public void fillStore(Map<Category, Integer> categoryProductMap) {

        RandomStorePopulator populateRandomStore = new RandomStorePopulator();

        for (Map.Entry<Category, Integer> entry : categoryProductMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Product product = new Product(
                        populateRandomStore.getProductName(entry.getKey().getName()),
                        populateRandomStore.getPrice(),
                        populateRandomStore.getRate());
                entry.getKey().addProduct(product);
            }
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void setCategoryItem(Category category) {
        this.categoryList.add(category);
    }

    public void printAllCatAndProd() {
        System.out.println("The list of the categories and products in the store:");

        for (Map.Entry<Category, Integer> entry : categoryProductMap.entrySet()) {
            entry.getKey().printAllProducts();
        }
    }
}
