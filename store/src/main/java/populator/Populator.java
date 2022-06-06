package populator;

import by.issoft.domain.Category;

import java.util.List;

public interface Populator {

    void fillStore();

    List<Category> getAllCategories();

    void addProductToCart();

}
