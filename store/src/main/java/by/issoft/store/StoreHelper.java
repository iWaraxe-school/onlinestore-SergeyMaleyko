package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.categories.CategoryType;
import org.reflections.Reflections;
import populator.RandomStorePopulator;

import java.util.*;

public class StoreHelper {

    Store store = Store.getInstance();
    private static int count = 1;

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

    private Product populateProduct(CategoryType category, Integer pid) {
        RandomStorePopulator populator = new RandomStorePopulator();
        Product product = Product.newBuilder()
            .setId(pid++)
            .setName(populator.getProductName(category))
            .setPrice(populator.getPrice())
            .setRate(populator.getRate())
            .setCategoryId(category.getIndex())
            .build();
        return product;
    }

    private List<Product> populateProductList() {
        int pid = 1;
        List<Product> productList = new ArrayList<>();
        for (CategoryType category : CategoryType.values()) {
            Category c = new Category(category);
            Random random = new Random();
            int r = random.nextInt(10) + 1;
            for (int i = 0; i < r; i++) {
                Product product = populateProduct(category, pid++);
                productList.add(product);
            }
        }
        return productList;
    }

    private void fillCategoryList(List<Category> categoryList) {
        int pid = 1;
        for (CategoryType category : CategoryType.values()) {
            Category c = new Category(category);
            Random random = new Random();
            int r = random.nextInt(30) + 1;
            for (int i = 0; i < r; i++) {
                Product product = populateProduct(category, pid++);
                c.setItemProduct(product);
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
            categoryMap.put(category, category.getSizeProduct());
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

    public void printRandomProduct() {
        System.out.println("Random Product:");
        Random random = new Random();
        int pid = random.nextInt(30) + 1;
        Product p = populateProduct(CategoryType.BIKE, pid);
        String s = "Product ID: " + p.getId() + ", " +
                "Category ID: " + p.getCategoryId()  + ", " +
                p.getProduct();
        System.out.println(s);
    }
    public void printProductList(){
        System.out.println(populateProductList());
    }
}
