package populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.StoreHelper;
import by.issoft.store.database.DbService;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class DbStorePopulator implements Populator {
    Store store = Store.getInstance();
    StoreHelper storeHelper = new StoreHelper(store);

    @SneakyThrows
    @Override
    public void fillStore() {
        List<Category> categoryList = populateStoreFaker();
        List<Product> productList = new ArrayList<>();
        DbService dbService = new DbService();
        dbService.createTables();
        dbService.insertAllCategories(categoryList);
        dbService.insertAllProducts(categoryList, productList);
        dbService.disconnectDb();
    }

    @Override
    public List<Category> getAllCategories() {
        return populateStoreFromDb();
    }

    @Override
    public void addProductToCart() {
        System.out.println("Not supported.");
    }

    @SneakyThrows
    public List<Category> populateStoreFromDb() {
        DbService dbService = new DbService();
        List<Category> categoryList = dbService.selectAllCategories();
        dbService.disconnectDb();
        return categoryList;
    }

    public List<Category> populateStoreFaker() {
        storeHelper.fillStore("FAKER");
        List<Category> categoryList = store.getCategoryList();
        return categoryList;
    }
}
