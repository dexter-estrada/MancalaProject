import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * The numerical representation of a Mancala board
 * Player A's side consists of pits A1-A6 and their Mancala
 * Player B's side consists of pits B1-B6 and their Mancala
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class DataModel {
    private ArrayList<Integer> playerAPits;         // Player A's pits
    private int playerAMancala;                     // Player A's Mancala
    private ArrayList<Integer> playerBPits;         // Player B's pits
    private int playerBMancala;                     // Player B's Mancala

    private int lastPlayerNo;                           // Last player who made a move
    private int lastStones;                             // Number of stones used last turn
    private int lastSideNo;                             // Tracks which side was last selected
    private int lastPit;                                // Tracks which pit was last selected

    private ArrayList<ChangeListener> listeners;    // Listeners attached to the DataModel

    // Testing doing turns. MUST DELETE BEFORE RELEASING
    public static void main(String[] args) {
        DataModel game = new DataModel(8);
        game.printText();
        game.playerAMove(5);
        game.printText();
        game.playerBMove(5);
        game.printText();
        game.undoMove();
        game.printText();
    }

    /**
     * Default constructor. Doesn't add any stones to the pits
     */
    public DataModel() {
        playerAPits = new ArrayList<>();
        playerBPits = new ArrayList<>();
        playerAMancala = 0;
        playerBMancala = 0;
        for (int i = 0; i < 6; i++) {
            playerAPits.add(0);
            playerBPits.add(0);
        }
        listeners = new ArrayList<>();
    }

    /**
     * Fills the pits with a user-defined set of stones
     * @param numberOfStones The number of pits per stone
     */
    public DataModel(int numberOfStones) {
        playerAPits = new ArrayList<>();
        playerBPits = new ArrayList<>();
        playerAMancala = 0;
        playerBMancala = 0;
        for (int i = 0; i < 6; i++) {
            playerAPits.add(numberOfStones);
            playerBPits.add(numberOfStones);
        }
        listeners = new ArrayList<>();
    }

    /**
     * Prints console version of output. MUST DELETE BEFORE RELEASING
     */
    public void printText() {
        System.out.println("Player A:");
        for (int pit : playerAPits) {
            System.out.print(pit + " ");
        }
        System.out.println();
        System.out.println(playerAMancala);
        System.out.println("Player B:");
        for (int pit : playerBPits) {
            System.out.print(pit + " ");
        }
        System.out.println();
        System.out.println(playerBMancala);
        System.out.println();
    }

    /**
     * Sets the number of stones for all the pits and resets the score
     * @param stones The number of stones to set per pit
     */
    public void setStones(int stones) {
        for (int i = 0; i < 6; i++) {
            playerAPits.set(i, stones);
            playerBPits.set(i, stones);
        }
        playerAMancala = playerBMancala = 0;

        // Updating to viewers
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Removes the stones from player A's selected pit
     * and distributes them to sequential pits except for player B's Mancala
     * @param chosenPit An integer from 0 - 5
     */
    public void playerAMove(int chosenPit) {
        // Convert to index starting at 0
        //chosenPit--;

        int stonesLeft = playerAPits.get(chosenPit);

        if (stonesLeft != 0) {
            playerAPits.set(chosenPit, 0);
            lastPlayerNo = 0;
            lastSideNo = 0;
            lastPit = chosenPit;
            lastStones = stonesLeft;
            moveHelper(stonesLeft, chosenPit + 1);
        }

        // Updating to viewers
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Removes the stones from player B's selected pit
     * and distribute them to sequential pits except for player A's Mancala
     * @param chosenPit An integer from 0 - 5
     */
    public void playerBMove(int chosenPit) {
        // Convert to index starting at 0
        //chosenPit--;

        int stonesLeft = playerBPits.get(chosenPit);

        if (stonesLeft != 0) {
            playerBPits.set(chosenPit, 0);
            lastPlayerNo = 1;
            lastSideNo = 1;
            lastPit = chosenPit;
            lastStones = stonesLeft;
            moveHelper(stonesLeft, chosenPit + 1);
        }

        // Updating to viewers
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Recursively adds the stones to subsequent pits and Mancala
     * @param stonesLeft remaining stones to distribute
     * @param pit Tracks which pit the recursion starts
     * @return The remaining number of stones
     */
    private int moveHelper(int stonesLeft, int pit) {
        int i = pit;
        while (stonesLeft != 0) {
            if (lastSideNo == 0) {
                if (i == playerAPits.size()) {
                    stonesLeft = addToScore(stonesLeft);
                    lastSideNo = 1;
                    stonesLeft = moveHelper(stonesLeft, 0);
                } else {
                    playerAPits.set(i, playerAPits.get(i) + 1);
                    stonesLeft--;
                    i++;
                }
            } else if (lastSideNo == 1) {
                if (i == playerBPits.size()) {
                    stonesLeft = addToScore(stonesLeft);
                    lastSideNo = 0;
                    stonesLeft = moveHelper(stonesLeft, 0);
                } else {
                    playerBPits.set(i, playerBPits.get(i) + 1);
                    stonesLeft--;
                    i++;
                }
            }
        }
        return stonesLeft;
    }

    /**
     * Adds a point to a mancala
     * @param stonesLeft Remaining stones to distribute
     * @return The remaining number of stones
     */
    private int addToScore(int stonesLeft) {
        if (lastPlayerNo == 0 && lastSideNo == 0) {
            playerAMancala++;
            stonesLeft--;
        } else if (lastPlayerNo == 1 && lastSideNo == 1) {
            playerBMancala++;
            stonesLeft--;
        }
        return stonesLeft;
    }

    /**
     * Undoes the last move that was done
     */
    public void undoMove() {
        if (lastPlayerNo == 0) {
            playerAPits.set(lastPit, lastStones);
            lastSideNo = lastPlayerNo;
            undoMoveHelper(lastStones, lastPit + 1);
        } else if (lastPlayerNo == 1) {
            playerBPits.set(lastPit, lastStones);
            lastSideNo = lastPlayerNo;
            undoMoveHelper(lastStones, lastPit + 1);
        }

        // Updating to viewers
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Recursively subtracts subsequent pits and mancala
     * @param stonesLeft Stones left to take
     * @param pit Starting pit number
     * @return Remaining number of stones
     */
    private int undoMoveHelper(int stonesLeft, int pit) {
        int i = pit;
        while (stonesLeft != 0) {
            if (lastSideNo == 0) {
                if (i == playerAPits.size()) {
                    stonesLeft = subtractFromScore(stonesLeft);
                    lastSideNo = 1;
                    stonesLeft = undoMoveHelper(stonesLeft, 0);
                } else {
                    playerAPits.set(i, playerAPits.get(i) - 1);
                    stonesLeft--;
                    i++;
                }
            } else if (lastSideNo == 1) {
                if (i == playerBPits.size()) {
                    stonesLeft = subtractFromScore(stonesLeft);
                    lastSideNo = 0;
                    stonesLeft = undoMoveHelper(stonesLeft, 0);
                } else {
                    playerBPits.set(i, playerBPits.get(i) - 1);
                    stonesLeft--;
                    i++;
                }
            }
        }
        return stonesLeft;
    }

    /**
     * Subtracts a point from a mancala
     * @param stonesLeft Stones left to take
     * @return The remaining number of stones to take
     */
    private int subtractFromScore(int stonesLeft) {
        if (lastPlayerNo == 0 && lastSideNo == 0) {
            playerAMancala--;
            stonesLeft--;
        } else if (lastPlayerNo == 1 && lastSideNo == 1) {
            playerBMancala--;
            stonesLeft--;
        }
        return stonesLeft;
    }

    /**
     * Gets the stones in Player A's pits
     * @return An int ArrayList of Player A's stones
     */
    public ArrayList<Integer> getPlayerAPits() {
        return playerAPits;
    }

    /**
     * Gets the score of Player A
     * @return An int of Player A's current score
     */
    public int getPlayerAMancala() {
        return playerAMancala;
    }

    /**
     * Get the stones in Player B's pits
     * @return An int ArrayList of Player B's stones
     */
    public ArrayList<Integer> getPlayerBPits() {
        return playerBPits;
    }

    /**
     * Get the score of Player B
     * @return An int of Player B's current score
     */
    public int getPlayerBMancala() {
        return playerBMancala;
    }

    /**
     * Adds a viewer
     * @param listener A class that implements ChangeListener
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }
}
