import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * The numerical representation of a Mancala board
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
     * Removes the stones from the selected pit, and distributes them to sequential pits
     */
    public void doMove() {

        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
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
