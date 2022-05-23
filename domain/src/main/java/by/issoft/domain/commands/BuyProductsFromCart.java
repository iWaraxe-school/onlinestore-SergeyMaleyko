package by.issoft.domain.commands;

/** ConcreteCommand #2 */
public class BuyProductsFromCart implements ShoppingCartOperation {

    private final ShoppingCart cart;

    public BuyProductsFromCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override // Command
    public void execute() {
        cart.buy();
    }
}