package by.issoft.domain.commands;

/** ConcreteCommand #1 */
public class PutProductInCart implements ShoppingCartOperation {
    private final ShoppingCart cart;

    public PutProductInCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override // Command
    public void execute() {
        cart.put();
    }
}
