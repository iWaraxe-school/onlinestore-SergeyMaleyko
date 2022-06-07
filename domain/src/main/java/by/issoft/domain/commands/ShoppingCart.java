package by.issoft.domain.commands;

import by.issoft.domain.Product;
import by.issoft.domain.orders.BuyOrder;
import by.issoft.domain.orders.CleanOrder;
import by.issoft.domain.orders.OrderProduct;

import java.util.ArrayList;
import java.util.List;

/** The Receiver class */
public class ShoppingCart {

    List<Product> productList = new ArrayList<>();
    List<Product> orderProductList = new ArrayList<>();

    public ShoppingCart(){
    }

    public ShoppingCart(List<Product> productList, List<Product> orderProductList){
        this.productList = productList;
        this.orderProductList = orderProductList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Product> getOrderProductList() {
        return orderProductList;
    }

    public void put() {
        for (int i = 0; i < 30; i++) {
            new Thread(new OrderProduct(productList, orderProductList)).start();
        }
    }

    public void buy() {
        new Thread(new BuyOrder(orderProductList)).start();
    }

    public void clear() {
        new Thread(new CleanOrder(orderProductList)).start();
    }

    public void show() {
        System.out.println("Product List:\n" + productList);
        System.out.println("Order List:\n" + orderProductList);
        System.out.println("Ordered: " + orderProductList.size() + " goods.");
    }
    // additional command methods
}
