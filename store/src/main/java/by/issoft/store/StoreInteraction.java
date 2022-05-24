package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.domain.commands.ShoppingCart;
import by.issoft.domain.commands.ShoppingCartOperation;
import by.issoft.domain.commands.ShoppingCartOperationExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StoreInteraction {

    public static void execStoreInteraction(Store store) {
        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.fillStore("FAKER");
        try {
            boolean console = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("The store is created and filled with random products.");
            while (console) {
                System.out.println("The store interacts with using next commands: info/i, sort/s, top/t, order/o, quit/q...");
                String command = bufferedReader.readLine();
                switch (command) {
                    case "info","i":
                        storeHelper.printAllCatAndProd();
                        break;
                    case "sort","s":
                        store.sort();
                        break;
                    case "top","t":
                        store.top();
                        break;
                    case "order","o":
                        List<Category> categoryList = store.getCategoryList();
                        List<Product> productList = new ArrayList<>();
                        List<Product> orderProductList = new ArrayList<>();
                        for (Category category : categoryList) {
                            productList.addAll(category.getSortProductById());
                        }
                        ShoppingCart cart = new ShoppingCart(productList, orderProductList);
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
                        System.out.println("Command is not supported.");
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
