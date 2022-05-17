package by.issoft.domain.commands;

import by.issoft.domain.Product;

import java.util.HashMap;
import java.util.Map;

/** The Receiver class */
public class ShoppingCart {

    private Product cart;
    private final Map<Product, Integer> productMap = new HashMap<>();

    public ShoppingCart(){
    }

    public ShoppingCart(Map<Product, Integer> productMap){
        this.cart = cart;
    }

    public Product getCart(){
        return cart;
    }

    public void setCart(Product cart){
        this.cart = cart;
    }

    public void put() {
        System.out.println("Demo command: The product is added in Shopping Cart!!!\n");
    }

    public void remove() {
        System.out.println("Demo command: The product is removed from Shopping Cart!!!\n");
    }

    public void clear() {
        System.out.println("Demo command: The Shopping Cart is cleared!!!\n");
    }

    public void show() {
        System.out.println("Demo command: Show goods from the cart!!!\n");
    }

    // additional command methods

}
