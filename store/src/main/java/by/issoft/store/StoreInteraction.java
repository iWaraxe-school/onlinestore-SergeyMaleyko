package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.commands.ShoppingCart;
import by.issoft.domain.commands.ShoppingCartOperation;
import by.issoft.domain.commands.ShoppingCartOperationExecutor;
import lombok.SneakyThrows;
import populator.DbStorePopulator;
import populator.HttpPopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StoreInteraction {

    @SneakyThrows
    public static void execStoreInteraction(Store store) {
        StoreHelper storeHelper = new StoreHelper(store);
        DbStorePopulator dbStorePopulator = new DbStorePopulator();
        HttpPopulator httpPopulator = new HttpPopulator();
        List<Category> categoryList = new ArrayList<>();

        // Use data from Faker.
        categoryList = dbStorePopulator.populateStoreFaker();
        // Use data from DB.
        dbStorePopulator.fillStore();
        categoryList = dbStorePopulator.getAllCategories();
        // Use data from Http Server.
        httpPopulator.fillStore();
        categoryList = httpPopulator.getAllCategories();

        try {
            boolean console = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("The store is created and filled with random products.");
            while (console) {
                System.out.println("The store interacts with using next commands: info/i, sort/s, top/t, order/o, quit/q...");
                String command = bufferedReader.readLine();
                switch (command) {
                    case "info","i":
                        storeHelper.printAllProductsByCategories();
                        break;
                    case "sort","s":
                        store.sort();
                        break;
                    case "top","t":
                        store.top();
                        break;
                    case "order","o":
                        List<Product> allProductsList = new ArrayList<>();
                        List<Product> orderedProductsList = new ArrayList<>();
                        for (Category category : categoryList) {
                            allProductsList.addAll(category.getSortProductById());
                        }
                        ShoppingCart cart = new ShoppingCart(allProductsList, orderedProductsList);
                        ShoppingCartOperation put = cart::put;
                        ShoppingCartOperation show = cart::show;
                        ShoppingCartOperation buy = cart::buy;
                        ShoppingCartOperation clear = cart::clear;
                        ShoppingCartOperationExecutor execCommand = new ShoppingCartOperationExecutor();
                        execCommand.register("put", put);
                        execCommand.register("show", show);
                        execCommand.register("buy", buy);
                        execCommand.register("clear", clear);

                        boolean consoleOrder = true;
                        BufferedReader bufferedReaderOrder = new BufferedReader(new InputStreamReader(System.in));
                        System.out.println("Order Goods!");
                        while (consoleOrder) {
                            System.out.println("Order commands: put/p, show/i, buy/b, stop/s ...");
                            String commandOrder = bufferedReaderOrder.readLine();
                            switch (commandOrder) {
                                case "put","p":
                                    // Add random products to Cart
                                    execCommand.execute("put");
                                    break;
                                case "buy","b":
                                    execCommand.execute("buy");
                                    break;
                                case "show","i":
                                    execCommand.execute("show");
                                    break;
                                case "stop","s":
                                    execCommand.execute("clear");
                                    consoleOrder = false;
                                    break;
                                default:
                                    System.out.println("Order: Command is not supported.");
                                    break;
                            }
                        }
                        break;
                    case "quit", "q":
                        bufferedReader.close();
                        console = false;
                        break;
                    default:
                        System.out.println("Store: Command is not supported.");
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
