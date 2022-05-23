package by.issoft.domain.orders;

import by.issoft.domain.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BuyOrder implements Runnable {

    private final List<Product> productList;

    public BuyOrder(List<Product> productList) {
        this.productList = productList;
    }

    @SneakyThrows
    @Override
    public void run() {
        int randomTime = ThreadLocalRandom.current().nextInt(1, 5);
        synchronized (productList) {
            log.info("Start processing your cart: " + randomTime + " sec.");
            productList.wait(randomTime * 1_000);
            CleanOrder cleanOrder = new CleanOrder(ordersList());
            (new Thread(cleanOrder)).start();
        }
    }

    @SneakyThrows
    private synchronized List<Product> ordersList() {
        List<Product> orderProductList = this.productList;
        TimeUnit.SECONDS.sleep(1);
        log.info("Your order is completed:\n" + orderProductList.toString());
        return orderProductList;
    }
}
