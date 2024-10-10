package solutions.pack12b_SSP;

public class State {
    private int[] state;
    private int operations;

    public State(int[] state, int operations) {
        this.state = state;
        this.operations = operations;
    }

    public int[] getState() {
        return state;
    }

    public int getOperations() {
        return operations;
    }
}