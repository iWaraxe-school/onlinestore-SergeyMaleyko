package by.issoft.domain;

import by.issoft.domain.discounters.ChristmasDiscounter;
import by.issoft.domain.discounters.Discounter;
import by.issoft.domain.discounters.WeeklyDiscounter;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private Integer id;
    private String name;
    private Double rate;
    private Double price;
    private Integer categoryId;
    // Strategy pattern
    Discounter discountWeek = new WeeklyDiscounter();
    Discounter discountChristmas = new ChristmasDiscounter();

    public static Builder newBuilder() {
        return new Product().new Builder();
    }

    // Pattern Builder
    public class Builder {

        private Integer id;
        private String name;
        private Double rate;
        private Double price;
        private Integer categoryId;

        private Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRate(Double rate) {
            this.rate = rate;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Product build() {
            Product.this.id = id;
            Product.this.name = this.name;
            //Product.this.price = this.price;
            Product.this.price = discountPrice(this.price);
            Product.this.rate = this.rate;
            Product.this.categoryId = this.categoryId;
            return Product.this;
        }

        private Double discountPrice(Double price) {
            List<Discounter> discounterList = new ArrayList<>();
            discounterList.add(discountWeek);
            discounterList.add(discountChristmas);
            for (Discounter discounter : discounterList) {
                if (discountWeek.checkCondition()) {
                    this.price = discountWeek.applyDiscount(price);
                } else if (discountChristmas.checkCondition()) {
                    this.price = discountChristmas.applyDiscount(price);
                }
            }
            return this.price;
        }
    }

    public Product getProduct() {
        return this;
    }

    public Integer getId() {
         return id;
    }

    public String getName() { return name; }

    public Double getPrice() {
        return price;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        String message = "";
        if (discountWeek.checkCondition()) { message = "Weekly Discount!!! " +
                discountWeek.applyDiscount(price) + ", ";}
        else if (discountChristmas.checkCondition()) { message = "Christmas Discount!!! " +
                discountChristmas.applyDiscount(price) + ", ";}
        return String.format("ID %s, " +
                " Product name: %s, " +
                "price: %.2f, " + message +
                "rate: %.2f; " +
                "%n", id, name, price, rate);
    }
}
