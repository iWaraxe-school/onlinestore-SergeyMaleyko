package by.issoft.domain.commands;

import java.util.HashMap;

/** The Invoker class */
public class ShoppingCartOperationExecutor {

    private final HashMap<String, ShoppingCartOperation> commandMap = new HashMap<>();

    public void register(String commandName, ShoppingCartOperation command) {
        commandMap.put(commandName, command);
    }

    public void execute(String commandName) {
            ShoppingCartOperation command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute();
    }
}
