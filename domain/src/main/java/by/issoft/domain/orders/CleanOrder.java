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
        int timeoutSec = 30;
        log.info("Start clean up thread.");
        while (runTask) {
            synchronized (orderProductList) {
                if (!orderProductList.isEmpty()) {
                    log.info("Cleaned the shopping cart: " + orderProductList.size() + " goods." +
                                    " Waiting " + timeoutSec + "sec...");
                    orderProductList.clear();
                    orderProductList.wait(timeoutSec*1_000);
                } else {
                    log.info("Your list of goods is empty! CleanOrder is completed.");
                    runTask = false;
                }
            }
        }
    }
}
