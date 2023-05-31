package tictactoe;

public enum Square {
    X('X'), O('O'), EMPTY(' '),
    ;
    private final char state;

    Square(char state) {
        this.state = state;
    }

    public char getState() {
        return state;
    }

}
