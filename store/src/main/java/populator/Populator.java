package populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public interface Populator {

    List<Category> getCategoryList();

    List<Product> getProductList();

    //RandomStorePopulator populator = new RandomStorePopulator();
    //Store store = Store.getInstance();
    //void fillStore();
}
