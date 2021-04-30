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
    private ArrayList<ChangeListener> listeners;    // Listeners attached to the DataModel

    // Testing doing turns. MUST DELETE BEFORE RELEASING
    public static void main(String[] args) {
        DataModel game = new DataModel(8);
        game.printText();
        game.playerAMove(6);
        game.printText();
        game.playerBMove(6);
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
     * Removes the stones from player A's selected pit
     * and distributes them to sequential pits except for player B's Mancala
     * @param chosenPit An integer from 1 - 6
     */
    public void playerAMove(int chosenPit) {
        // Convert to index starting at 0
        chosenPit--;

        int stones = playerAPits.get(chosenPit);

        if (stones != 0) {
            playerAPits.set(chosenPit, 0);
            moveHelper(stones, chosenPit + 1, 0, 0);
        }

        // Updating to viewers
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Removes the stones from player B's selected pit
     * and distribute them to sequential pits except for player A's Mancala
     * @param chosenPit An integer from 1 - 6
     */
    public void playerBMove(int chosenPit) {
        // Convert to index starting at 0
        chosenPit--;

        int stones = playerBPits.get(chosenPit - 1);

        if (stones != 0) {
            playerBPits.set(chosenPit, 0);
            moveHelper(stones, chosenPit + 1, 1, 1);
        }

        // Updating to viewers
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Recursively adds the stones to subsequent pits and Mancala
     * @param stones remaining stones to distribute
     * @param pit Tracks which pit the recursion starts
     * @param playerNo Tracks whose turn it is
     * @param sideNo Tracks which array is being updated
     * @return The remaining number of stones
     */
    private int moveHelper(int stones, int pit, int playerNo, int sideNo) {
        int i = pit;
        while (stones != 0) {
            if (sideNo == 0) {
                if (i == playerAPits.size()) {
                    stones = addToScore(stones, playerNo, sideNo);
                    stones = moveHelper(stones, 0, playerNo, 1);
                } else {
                    playerAPits.set(i, playerAPits.get(i) + 1);
                    stones--;
                    i++;
                }
            } else if (sideNo == 1) {
                if (i == playerBPits.size()) {
                    stones = addToScore(stones, playerNo, sideNo);
                    stones = moveHelper(stones, 0, playerNo, 0);
                } else {
                    playerBPits.set(i, playerBPits.get(i) + 1);
                    stones--;
                    i++;
                }
            }
        }
        return stones;
    }

    /**
     * Adds a point to a mancala
     * @param stones Remaining stones to distribute
     * @param playerNo Tracks whose turn it is
     * @param sideNo Tracks which array is being updated
     * @return The remaining number of stones
     */
    private int addToScore(int stones, int playerNo, int sideNo) {
        if (playerNo == 0 && sideNo == 0) {
            playerAMancala++;
            stones--;
        } else if (playerNo == 1 && sideNo == 1) {
            playerBMancala++;
            stones--;
        }
        return stones;
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
