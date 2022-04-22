package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.CategoryNames;
import org.reflections.Reflections;
import populator.RandomStorePopulator;

import java.util.*;

public class StoreHelper {

    Store store = Store.getInstance();
    private static int count = 1;

    public StoreHelper() {
    }

    public Store getStore() {
        return store;
    }

    public StoreHelper(Store store) {
        this.store = store;
    }

     public void fillStore(String mode) {
        if (mode.equals("FAKER")) {
            Map<Category, Integer> categoryMap;
            if (count == 0) {
                categoryMap = reflectCategory();
                count++;
            } else {
                categoryMap = fillCategoryMap();
            }
        }
        //else if (mode.equals("DB"))
        //else if (mode.equals("HTTP"))
        else {
            throw new IllegalArgumentException("Illegal argument");
        }
    }

    private void fillCategoryList(List<Category> categoryList) {

        RandomStorePopulator populator = new RandomStorePopulator();

        for (CategoryNames category : CategoryNames.values()) {

            Category c = new Category(category);

            Random random = new Random();
            int r = random.nextInt(9) + 1;

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

    public Map<Category, Integer> fillCategoryMap() {
        List<Category> categoryList = store.getCategoryList();
        if (categoryList.isEmpty()) {
            fillCategoryList(categoryList);
        }

        Map<Category, Integer> categoryMap = new HashMap<>();
        for (Category category : categoryList) {
            categoryMap.put(category, category.getProductSize());
        }
        return categoryMap;
    }

    // Refection
    public static Map<Category, Integer> reflectCategory() {
        Map<Category, Integer> categoryMap = new HashMap<>();

        Reflections reflections = new Reflections("by.issoft.domain.categories");

        Set<Class<? extends Category>> subTypesOfCategory =
                reflections.getSubTypesOf(Category.class);

        for (Class<? extends Category> type : subTypesOfCategory) {
            Random random = new Random();
            try {
                categoryMap.put(type.getConstructor().newInstance(), random.nextInt(10));
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
        return categoryMap;
    }

    public void printAllCatAndProd() {
        System.out.println("The list of the categories and products in the store:");

        for (Category category : store.categoryList) {
            System.out.println(category);
        }
    }
}
