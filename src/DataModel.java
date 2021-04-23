import javax.swing.event.ChangeListener;
import java.util.ArrayList;

/**
 * The numerical representation of a Mancala board
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class DataModel {
    private ArrayList<Integer> playerA;             // Player A's pits
    private int playerAMancala;                     // Player A's Mancala
    private ArrayList<Integer> playerB;             // Player B's pits
    private int playerBMancala;                     // Player B's Mancala
    private ArrayList<ChangeListener> listeners;    // Listeners attached to the DataModel

    /**
     * Default constructor. Fills the default number of 3 stones per pit
     */
    public DataModel() {
        playerA = new ArrayList<>();
        playerB = new ArrayList<>();
        playerAMancala = 0;
        playerBMancala = 0;
        for (int i = 0; i < 6; i++) {
            playerA.add(3);
            playerB.add(3);
        }
        listeners = new ArrayList<>();
    }

    /**
     * Fills the pits with a user-defined set of stones
     * @param numberOfStones The number of pits per stone
     */
    public DataModel(int numberOfStones) {
        playerA = new ArrayList<>();
        playerB = new ArrayList<>();
        playerAMancala = 0;
        playerBMancala = 0;
        for (int i = 0; i < 6; i++) {
            playerA.add(numberOfStones);
            playerB.add(numberOfStones);
        }
        listeners = new ArrayList<>();
    }
}
