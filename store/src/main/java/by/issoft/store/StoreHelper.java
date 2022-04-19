package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.CategoryNames;
import populator.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoreHelper {

    Store store = Store.getInstance();
    //private static int count = 0;

    public StoreHelper() {
    }

    public Store getStore() {
        return store;
    }

    public StoreHelper(Store store) {
        this.store = store;
    }

    //public void setStore(Store store) { this.store = store; }

    public void fillStore(String mode) {

        if (mode.equals("FAKER")) {
            fillStoreByFaker(store.categoryList);
        }
        //else if (mode.equals("HTTP"))
        //else if (mode.equals("DB"))
        else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    private final void fillStoreByFaker(List<Category> categoryList) {

        RandomStorePopulator populator = new RandomStorePopulator();

        for (CategoryNames category : CategoryNames.values()) {

            Category c = new Category(category);

            Random random = new Random();
            int r = random.nextInt(5) + 1;

            for (int i = 0; i < r; i++) {
                Product product = new Product(
                        populator.getProductName(category),
                        populator.getPrice(),
                        populator.getRate());

                c.setProductItem(product);
               }
            categoryList.add(c);
        }
    }

    public void printAllCatAndProd() {
        System.out.println("The list of the categories and products in the store:"+"\r\n");
        for (Category category : store.categoryList) {
            System.out.println(category.toString());
        }
    }
}
