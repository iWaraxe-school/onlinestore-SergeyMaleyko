package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.StoreInteraction;

public class StoreApp {

    public static void main(String[] args) {
        Store store = Store.getInstance();
        StoreInteraction.execStoreInteraction(store);
   }
}