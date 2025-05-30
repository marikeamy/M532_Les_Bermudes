package main.Game;

public class Letter extends Item {
    private String answer;
    private String locationNameToUnlock; /* Tess */
    private boolean solved = false; /* Tess */

    public Letter(String name, String description, String answer, String locationNameToUnlock /* Tess */) {
        super(name, description);
        this.answer = answer;
        this.locationNameToUnlock = locationNameToUnlock; /* Tess */
    }

    public boolean checkAnswer(String input) {
        return input.trim().equalsIgnoreCase(answer.trim());
    }

    }

    public boolean isSolved() {
        return solved;
    }

    public void markAsSolved() {
        solved = true;
    }

    public String getLocationNameToUnlock() { /* Tess */
        return locationNameToUnlock;
    }

    @Override
    public void interactionsWithItem() {
        // TODO Auto-generated method stub

    }
}
