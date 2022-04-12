package by.issoft.domain;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

public class Product {

    private String name;
    private double rate;
    private double price;

    public Product(String name, double rate, double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public Product() {

    }

    public static void printProductInfo(@NotNull Product product){
        System.out.println(
                product.getName() + " " +
                product.getRate() + " " +
                product.getPrice());
    }

    public String getName() {
        Preconditions.checkArgument(name.equals(""), "Blank name.");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
