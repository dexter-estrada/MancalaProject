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
     * Removes the stones from player A's selected pit
     * and distributes them to sequential pits except for player B's Mancala
     * @param chosenPit An integer from 1 - 6
     */
    public void playerAMove(int chosenPit) {
        int stones = playerAPits.get(chosenPit - 1);

        while (stones != 0) {
            // TODO Case when A6 is selected
            playerAPits.set(chosenPit, playerAPits.get(chosenPit) + 1);
            stones--;
            chosenPit++;
            // If the end of the array is reached
            if (chosenPit == playerAPits.size()) {
                stones = moveHelper(stones, 0, 0);
            }
        }

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
        int stones = playerBPits.get(chosenPit - 1);

        while (stones != 0) {
            // TODO Case when B6 is selected
            playerBPits.set(chosenPit, playerBPits.get(chosenPit) + 1);
            stones--;
            chosenPit++;
            // If the end of the array is reached
            if (chosenPit == playerBPits.size()) {
                stones = moveHelper(stones, 1, 1);
            }
        }

        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    /**
     * Recursively adds the stones to subsequent pits and Mancala
     * TODO redo logic of moveHelper
     * @param stones remaining stones to distribute
     * @param playerNo Tracks whose turn it is
     * @param sideNo Tracks which array is being updated
     * @return The remaining number of stones
     */
    private int moveHelper(int stones, int playerNo, int sideNo) {
        if (playerNo == 0 && sideNo == 0) {
            playerAMancala++;
            stones--;
            sideNo = 1;
        } else if (playerNo == 1 && sideNo == 1) {
            playerBMancala++;
            stones--;
            sideNo = 0;
        }
        int i = 0;
        while (stones != 0) {
            if (sideNo == 0) {
                playerAPits.set(i, playerAPits.get(i) + 1);
                stones--;
                i++;
                if (i == playerAPits.size()) {
                    stones = moveHelper(stones, playerNo, 1);
                }
            } else if (sideNo == 1) {
                playerBPits.set(i, playerBPits.get(i) + 1);
                stones--;
                i++;
                if (i == playerBPits.size()) {
                    stones = moveHelper(stones, playerNo, 0);
                }
            }
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
