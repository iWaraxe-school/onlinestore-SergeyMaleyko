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
        storeHelper.fillStore("FAKER");
        List<Category> categoryList = store.getCategoryList();
        List<Product> productList = new ArrayList<>();
        DbService dbService = new DbService();
        dbService.createTables();
        dbService.insertAllCategories(categoryList);
        dbService.insertAllProducts(categoryList, productList);
        dbService.disconnectDb();
    }

    public void printAllProductsByCategories() {
        storeHelper.printAllCatAndProd();
    }

    @SneakyThrows
    public void populateStoreFromDb() {
        storeHelper.fillStore("DB");
        DbService dbService = new DbService();
        dbService.selectAllCategories();
        dbService.disconnectDb();
    }
}
