package populator;

import by.issoft.domain.categories.CategoryType;
import com.github.javafaker.Faker;

public class RandomStorePopulator {

    private final Faker faker = new Faker();

    public RandomStorePopulator() {
    }

    public String getProductName(CategoryType categoryName) {
        switch (categoryName) {
            case BIKE:
                return faker.company().name();
            case PHONE:
                return faker.commerce().productName();
            case MILK:
                return faker.food().ingredient();
            default:
                return null;
        }
    }

    public Double getPrice() { return faker.number().randomDouble(1,1,1_000);}

    public Double getRate() { return faker.number().randomDouble(1,1,100);}

}
