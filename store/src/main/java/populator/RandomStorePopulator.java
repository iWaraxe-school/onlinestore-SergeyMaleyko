package populator;

import by.issoft.domain.categories.CategoryNames;
import com.github.javafaker.Faker;

import static by.issoft.domain.categories.CategoryNames.*;

public class RandomStorePopulator {

    private Faker faker = new Faker();

    public RandomStorePopulator() {
    }

    public String getProductName(CategoryNames categoryName) {
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

    public Double getPrice() { return faker.number().randomDouble(1,1,100);}

    public Double getRate() { return faker.number().randomDouble(1,1,1_000);}

}
