/**
 * Description: class Pit sets up amount of stone in the pit
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class Pit {
    private int stoneAmount;
    private char sidePit;
    private int indexPit;

    /**
     * Construct the Pit class with the initial stone amount given
     * @param stoneAmount - number of stones
     */
    public Pit(int stoneAmount) {
        this.stoneAmount = stoneAmount;
    }

    /**
     * Construct the Pit class with the Pit's size and the index of Pit
     * @param sidePit - side A or B of Pit
     * @param indexPit - index of Pit
     */
    public Pit(char sidePit, int indexPit) {
        this.sidePit = sidePit;
        this.indexPit = indexPit;
    }

    /**
     * Construct the Pit class with the initial stone amount given, the Pit's size, and the index of Pit
     * @param stoneAmount - number of stones
     * @param sidePit - side A or B of Pit
     * @param indexPit - index of Pit
     */
    public Pit(int stoneAmount, char sidePit, int indexPit) {
        this.stoneAmount = stoneAmount;
        this.sidePit = sidePit;
        this.indexPit = indexPit;
    }

    /**
     * Get numbers of stone amount in the Pit
     * @return  - amount stone in the Pit
     */
    public int getStoneAmount() {
        return stoneAmount;
    }


    /**
     * Sets numbers of stone amount in the Pit
     * @param stone - number of stones
     */
    public void setStoneAmount(int stone) {
        stoneAmount = stone;
    }

    /**
     * Take all stones of the opposite pit
     * @return - stone amount for opposite Mancala player
     */
    public int takeAllStones() {
        int oppositeStones = stoneAmount;
        stoneAmount = 0;
        return oppositeStones;
    }

    /**
     *  Returns empty stone in the Pit after making a move
     */
    public void resetPit() {
        stoneAmount = 0;
    }

    /**
     *  Distribute amount of stones in the Pit to the next Pit
     */
    public void iterateStonePit() {
        stoneAmount++;
    }

    /**
     * Takes back stones distributed
     */
    public void decrementStonePit() {
        stoneAmount--;
    }

    /**
     * Get size of Pit
     * @return - side of Pit
     */
    public char getSidePit() {
        return sidePit;
    }

    /**
     * Set side of Pit
     * @param side - side of Pit
     */
    public void setSidePit(char side) {
        sidePit = side;
    }

    /**
     * Get position of Pit
     * @return index of Pit
     */
    public int getIndexPit() {
        return indexPit;
    }

    /**
     * Set position of Pit
     * @param index of Pit
     */
    public void setIndexPit(int index) {
        indexPit = index;
    }


}
