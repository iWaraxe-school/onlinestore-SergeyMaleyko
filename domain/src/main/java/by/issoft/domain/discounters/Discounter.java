package by.issoft.domain.discounters;

// Strategy pattern
public interface Discounter {
    Double applyDiscount(Double price);

    boolean checkCondition();
}
