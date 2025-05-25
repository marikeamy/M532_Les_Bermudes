package main.Game;

public class Letter extends Item {
    private String answer;

    public Letter(String name, String description, String answer) {
        super(name, description);
        this.answer = answer;
    }

    @Override
    public void interactionsWithItem() {
        // TODO Auto-generated method stub
        
    }
}
