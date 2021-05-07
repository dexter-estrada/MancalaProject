/**
 * Holds the value of a player's mancala/score
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class PlayerMancala {
    private int numStones;

    /**
     * Default constructor. Sets stones to 0
     */
    public PlayerMancala() {
        numStones = 0;
    }

    /**
     * Adds an amount of stones to a player's mancala
     * @param stone The amount of stones to be added to the mancala
     */
    public void addNumStones(int stone) {
        numStones += stone;
    }

    /**
     * Gets the amount of stones inside a player's mancala
     * @return The amount of stones inside the player's mancala
     */
    public int getNumStones() {
        return numStones;
    }

    /**
     * Sets the amount of stones for a player's mancala
     * @param stone The amount of stones to set to the mancala
     */
    public void setNumStones(int stone) {
        numStones = stone;
    }

    /**
     * Increments the player's mancala by 1 stone
     */
    public void incNumStones() {
        numStones++;
    }

    /**
     * Decrements the player's mancala by 1 stone
     */
    public void decNumStones() {
        numStones--;
    }

    /**
     * Takes the stones from a pit and adds it to this mancala
     * @return The amount of stones taken from a pit
     */
    public int takeOppositeStones() {
        int oppositeStones = numStones;
        numStones = 0;
        return oppositeStones;
    }

}
