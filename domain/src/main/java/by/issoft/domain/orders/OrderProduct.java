package by.issoft.domain.orders;

import by.issoft.domain.Product;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class OrderProduct extends Thread {

    private final List<Product> productList;
    private List<Product> orderProductList = new CopyOnWriteArrayList<>();

    public OrderProduct(List<Product> productList, List<Product> orderProductList) {
        this.productList = productList;
        this.orderProductList = orderProductList;
    }

    @SneakyThrows
    @Override
    public void run() {
        int randomTime = ThreadLocalRandom.current().nextInt(1, productList.size());
        synchronized (productList) {
            log.info("Adding a random product in the cart for: " + randomTime + " sec.");
            productList.wait(randomTime * 1000);
            orderProductList.add(productList.get(randomTime));
            log.info("Product is ordered: " + productList.get(randomTime));
        }
    }
}
