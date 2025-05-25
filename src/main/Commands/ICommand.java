package main.Commands;

public interface ICommand {
    public String getDescription();

    public String getVerb();

    void execute(String argument);

}