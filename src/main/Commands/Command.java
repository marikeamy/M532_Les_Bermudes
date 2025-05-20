package main.Commands;

public abstract class Command implements ICommand {
    protected String description;
    protected String verb;

    public Command(String description, String verb) {
        this.description = description;
        this.verb = verb;
    }
    public String getDescription() {
        return this.description;
    }

    public String getVerb() {
        return this.verb;
    }
}