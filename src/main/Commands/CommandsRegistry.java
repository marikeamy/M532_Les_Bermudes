package main;

import java.util.Map;

public class CommandsRegistry {
    private Map<String, ICommand> commandsRegistry;

    public CommandsRegistry(Map<String, ICommand> commandsRegistry) {
        this.commandsRegistry = commandsRegistry;
    }

    public Map<String, ICommand> getCommandsRegistry() {
        return this.commandsRegistry;
    }

    /*
     * public ICommand getCommand(Map<String, ICommand> commandsRegistry) {
     * return this.ICommand;
     * }
     */
    public void addCommand(String verb, ICommand iCommand) {

    }

    public void executeCommand(String verb) {

    }
}
