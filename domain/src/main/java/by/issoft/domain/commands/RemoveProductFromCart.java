package by.issoft.domain.commands;

/** ConcreteCommand #2 */
public class RemoveProductFromCart implements ShoppingCartOperation {

    private final ShoppingCart cart;

    public RemoveProductFromCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override // Command
    public void execute() {
        cart.remove();
    }
}