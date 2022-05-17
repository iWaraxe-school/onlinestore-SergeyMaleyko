package by.issoft.domain.commands;

public class CommandDemo {
    public static void main(final String[] arguments) {
        ShoppingCart cart = new ShoppingCart();

        ShoppingCartOperation put = cart::put;
        ShoppingCartOperation remove = cart::remove;
        ShoppingCartOperation clear = cart::clear;
        ShoppingCartOperation show = cart::show;

        ShoppingCartOperationExecutor mySwitch = new ShoppingCartOperationExecutor();
        mySwitch.register("put", put);
        mySwitch.register("remove", remove);
        mySwitch.register("clear", clear);
        mySwitch.register("show", show);

        mySwitch.execute("put");
        mySwitch.execute("remove");
        mySwitch.execute("clear");
        mySwitch.execute("show");
    }
}
