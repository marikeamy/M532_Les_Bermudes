package main.Commands;

import java.util.Map;

public class CommandsRegistry {
    private Map<String, Command> commandsRegistry;

    public CommandsRegistry(Map<String, Command> commandsRegistry) {
        this.commandsRegistry = commandsRegistry;
    }

    public Map<String, Command> getCommandsRegistry() {
        return this.commandsRegistry;
    }

    public ICommand getCommand(String verb) {
        return this.commandsRegistry.get(verb);
    }

    /*
     * public ICommand getCommand(Map<String, ICommand> commandsRegistry) {
     * return this.ICommand;
     * }
     */
    public void addCommand(String verb, ICommand Command) {

    }

    public void executeCommand(String verb) {

    }
}
