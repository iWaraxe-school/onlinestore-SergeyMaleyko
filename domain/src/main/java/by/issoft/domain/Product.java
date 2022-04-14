package by.issoft.domain;

public class Product {

    private final String name;
    private final Double rate;
    private final Double price;

    public Product(String name, Double rate, Double price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public String getName() {
         return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("Product name: %s, price: %.2f, rate: %.2f; %n", name, price, rate);
    }

    public static void printProductInfo(Product product){
        System.out.println(product);
    }
}
