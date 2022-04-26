package populator;

import by.issoft.domain.categories.CategoryNames;
import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private final Faker faker = new Faker();

    public RandomStorePopulator() {
    }

    public String getProductName(CategoryNames categoryName) {
        switch (categoryName) {
            case Bike:
                return faker.company().name();
            case Phone:
                return faker.commerce().productName();
            case Milk:
                return faker.food().ingredient();
            default:
                return null;
        }
    }

    public Double getPrice() { return faker.number().randomDouble(1,1,100);}

    public Double getRate() { return faker.number().randomDouble(1,1,1_000);}

}
