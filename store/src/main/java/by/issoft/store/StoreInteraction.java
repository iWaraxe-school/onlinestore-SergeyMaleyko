package by.issoft.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreInteraction {

    public static void execStoreInteraction(Store store) {

        StoreHelper storeHelper = new StoreHelper(store);
        storeHelper.fillStore("FAKER");

        try {

            boolean console = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("The store is created and filled with random products.");

            while (console) {

                System.out.println("The store interacts with using next commands: sort, top, info, quit/q...");

                String command = bufferedReader.readLine();

                switch (command) {
                    case "info":
                        storeHelper.printAllCatAndProd();
                        break;
                    case "sort":
                        store.sort();
                        break;
                    case "top":
                        store.top();
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
