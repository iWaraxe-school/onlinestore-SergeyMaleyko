package utils;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private Faker faker = new Faker();

    public RandomStorePopulator() { }

    public String getProductName(String categoryName) {
        switch (categoryName) {
            case "Bike":
                return faker.company().name();
            case "Phone":
                return faker.commerce().productName();
            case "Milk":
                return faker.food().ingredient();
            default:
                return null;
        }
    }

    public Double getPrice() { return faker.number().randomDouble(1,1,100);}

    public Double getRate() { return faker.number().randomDouble(1,1,100);}

    static Store populateStore(int categoryNum, int productNum) {

        Faker faker = new Faker();
        Store store = new Store();

        for (int i = 0; i < categoryNum; i++) {
            Category category = new Category(faker.commerce().productName());

            for (int j = 0; j < productNum; j++) {
                Product product = new Product(faker.commerce().productName(),
                    Double.parseDouble(faker.commerce().price()),
                    Double.parseDouble(faker.commerce().price()));
                category.addProduct(product);
            }

            store.setCategoryItem(category);
        }
        return store;
    }
}
