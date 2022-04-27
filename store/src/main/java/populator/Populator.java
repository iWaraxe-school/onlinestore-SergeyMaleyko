package populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.List;

public interface Populator {

    List<Category> getCategoryList();

    List<Product> getProductList();

    void fillStore();

}
