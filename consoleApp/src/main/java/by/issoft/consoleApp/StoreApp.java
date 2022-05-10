package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

public class StoreApp {

    public static void main(String[] args) {

        System.out.println("This is storeApp!"+"\r\n");
        Store store = Store.getInstance();

        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.fillStore("FAKER");
        //storeHelper.printAllCatAndProd();
        storeHelper.printStoreSorted();
        storeHelper.printStoreTopProducts();

   }
}