package by.issoft.domain.commands;

/** ConcreteCommand #3 */
public class ClearCart implements ShoppingCartOperation {

    private final ShoppingCart cart;

    public ClearCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override // Command
    public void execute() {
        cart.clear();
    }
}
