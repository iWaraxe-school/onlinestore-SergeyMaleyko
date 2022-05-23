package by.issoft.domain.orders;

import by.issoft.domain.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CleanOrder implements Runnable {

    private final List<Product> orderProductList;

    public CleanOrder(List<Product> orderProductList) {
        this.orderProductList = orderProductList;
    }

    @SneakyThrows
    @Override
    public void run() {
        boolean runTask = true;
        log.info("Start clean up thread.");
        while (runTask) {
            synchronized (orderProductList) {
                if (!orderProductList.isEmpty()) {
                    log.info("Cleaned the shopping cart: " + orderProductList.size() + " goods."); //+ orderProductList.toString());
                    orderProductList.clear();
                    orderProductList.wait(30_000);
                } else {
                    log.info("Your list of goods is empty!");
                    runTask = false;
                }
            }
        }
    }
}
