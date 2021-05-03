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
    private ArrayList<Pit> playerAPits;         // Player A's pits
    private PlayerMancala playerAMancala;                     // Player A's Mancala
    private ArrayList<Pit> playerBPits;         // Player B's pits
    private PlayerMancala playerBMancala;                     // Player B's Mancala

    // Tracks the last move that was made
    private int lastPlayerNo = -1;                      // Last player who made a move
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
        playerAMancala = new PlayerMancala();
        playerBMancala = new PlayerMancala();
        for (int i = 0; i < 6; i++) {
            playerAPits.add(new Pit(0, 'A', i));
            playerBPits.add(new Pit(0, 'B', i));
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
        playerAMancala = new PlayerMancala();
        playerBMancala = new PlayerMancala();
        for (int i = 0; i < 6; i++) {
            playerAPits.add(new Pit(numberOfStones, 'A', i));
            playerBPits.add(new Pit(numberOfStones, 'A', i));
        }
        listeners = new ArrayList<>();
    }

    /**
     * Prints console version of output. MUST DELETE BEFORE RELEASING
     */
    public void printText() {
        System.out.println("Player A:");
        for (Pit pit : playerAPits) {
            System.out.print(pit.getStoneAmount() + " ");
        }
        System.out.println();
        System.out.println(playerAMancala.getNumStones());
        System.out.println("Player B:");
        for (Pit pit : playerBPits) {
            System.out.print(pit.getStoneAmount() + " ");
        }
        System.out.println();
        System.out.println(playerBMancala.getNumStones());
        System.out.println();
    }

    /**
     * Sets the number of stones for all the pits and resets the score
     * @param stones The number of stones to set per pit
     */
    public void setStones(int stones) {
        for (int i = 0; i < 6; i++) {
            playerAPits.get(i).setStoneAmount(stones);
            playerBPits.get(i).setStoneAmount(stones);
        }
        playerAMancala.setNumStones(0);
        playerBMancala.setNumStones(0);
        lastPlayerNo = -1;          // First move

        update();
    }

    /**
     * Removes the stones from player A's selected pit
     * and distributes them to sequential pits except for player B's Mancala
     * @param chosenPit An integer from 0 - 5
     */
    public void playerAMove(int chosenPit) {
        // Convert to index starting at 0
        //chosenPit--;

        // Checks if it's player A's turn
        if (lastPlayerNo == 1 || lastPlayerNo == -1) {
            int stonesLeft = playerAPits.get(chosenPit).getStoneAmount();

            if (stonesLeft != 0) {
                playerAPits.get(chosenPit).setStoneAmount(0);
                lastPlayerNo = 0;
                lastSideNo = 0;
                lastPit = chosenPit;
                lastStones = stonesLeft;
                moveHelper(stonesLeft, chosenPit + 1);
            }
            update();
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

        // Checks if it's player B's turn
        if (lastPlayerNo == 0) {
            int stonesLeft = playerBPits.get(chosenPit).getStoneAmount();

            if (stonesLeft != 0) {
                playerBPits.get(chosenPit).setStoneAmount(0);
                lastPlayerNo = 1;
                lastSideNo = 1;
                lastPit = chosenPit;
                lastStones = stonesLeft;
                moveHelper(stonesLeft, chosenPit + 1);
            }
            update();
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
                    playerAPits.get(i).iterateStonePit();
                    stonesLeft--;
                    i++;
                }
            } else if (lastSideNo == 1) {
                if (i == playerBPits.size()) {
                    stonesLeft = addToScore(stonesLeft);
                    lastSideNo = 0;
                    stonesLeft = moveHelper(stonesLeft, 0);
                } else {
                    playerBPits.get(i).iterateStonePit();
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
            playerAMancala.incNumStones();
            stonesLeft--;
            // Extra move when ended up at mancala
            if (stonesLeft == 0) {
                lastPlayerNo = 1;
            }
        } else if (lastPlayerNo == 1 && lastSideNo == 1) {
            playerBMancala.incNumStones();
            stonesLeft--;
            // Extra move when ended up at mancala
            if (stonesLeft == 0) {
                lastPlayerNo = 0;
            }
        }
        return stonesLeft;
    }

    /**
     * Undoes the last move that was done
     */
    public void undoMove() {
        if (lastPlayerNo == 0) {
            playerAPits.get(lastPit).setStoneAmount(lastStones);
            lastSideNo = lastPlayerNo;
            undoMoveHelper(lastStones, lastPit + 1);
        } else if (lastPlayerNo == 1) {
            playerBPits.get(lastPit).setStoneAmount(lastStones);
            lastSideNo = lastPlayerNo;
            undoMoveHelper(lastStones, lastPit + 1);
        }
        update();
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
                    playerAPits.get(i).decrementStonePit();
                    stonesLeft--;
                    i++;
                }
            } else if (lastSideNo == 1) {
                if (i == playerBPits.size()) {
                    stonesLeft = subtractFromScore(stonesLeft);
                    lastSideNo = 0;
                    stonesLeft = undoMoveHelper(stonesLeft, 0);
                } else {
                    playerBPits.get(i).decrementStonePit();
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
            playerAMancala.decNumStones();
            stonesLeft--;
        } else if (lastPlayerNo == 1 && lastSideNo == 1) {
            playerBMancala.decNumStones();
            stonesLeft--;
        }
        return stonesLeft;
    }

    public String checkWinnerPlayer() {
        //Check no stones on the player !
        boolean endGame = true;
        for (int i = 0; i < 6; i++) {
            for (Pit pitA : playerAPits) {
                if (pitA.getStoneAmount() != 0) {
                    endGame = false;
                }
            }
        }

        //If All stones in the pit Side A are 0, mancala B captures all stones from the opposite Pit of side A
        if (endGame) {
            for (int j=0; j<6; j++) {
                playerBMancala.addNumStones(playerBPits.get(j).takeAllStones());
            }
        }

        //Check if no stones in Pit's side B
        if (endGame == false) {
            endGame = true;
            for (int i=0; i<6; i++) {
                for (Pit pitB: playerBPits) {
                    if (pitB.getStoneAmount() != 0) {
                        endGame = false;
                    }
                }

                //If All stones in the pit Side B are 0, mancala A captures all stones from the opposite Pit of side B
                if (endGame) {
                    for (int j=0; j<6; j++) {
                        playerAMancala.addNumStones(playerAPits.get(j).takeAllStones());
                    }
                }
            }

        }
        //End game
        if (endGame) {
            update();
            if (playerBMancala.getNumStones() > playerAMancala.getNumStones()) {
                return "Player-B Win";
            } else {
                return "Player-A Win";
            }

        }
        return null;
    }


    /**
     * Gets the stones in Player A's pits
     * @return An int ArrayList of Player A's stones
     */
    public ArrayList<Pit> getPlayerAPits() {
        return playerAPits;
    }

    /**
     * Gets the score of Player A
     * @return An int of Player A's current score
     */
    public PlayerMancala getPlayerAMancala() {
        return playerAMancala;
    }

    /**
     * Get the stones in Player B's pits
     * @return An int ArrayList of Player B's stones
     */
    public ArrayList<Pit> getPlayerBPits() {
        return playerBPits;
    }

    /**
     * Get the score of Player B
     * @return An int of Player B's current score
     */
    public PlayerMancala getPlayerBMancala() {
        return playerBMancala;
    }

    /**
     * Adds a viewer
     * @param listener A class that implements ChangeListener
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Updates the viewer every time this is called
     */
    private void update() {
        for (ChangeListener listener : listeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }
}
