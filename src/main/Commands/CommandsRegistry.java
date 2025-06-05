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

    public void addCommand(String verb, Command Command) {
        this.commandsRegistry.put(verb, Command);
    }

    public void executeCommand(String verb) {

    }
}
