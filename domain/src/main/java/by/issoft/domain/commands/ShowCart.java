package by.issoft.domain.commands;

/** ConcreteCommand #4 */
public class ShowCart implements ShoppingCartOperation {

    private final ShoppingCart cart;

    public ShowCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override // Command
    public void execute() {
        cart.show();
    }
}
