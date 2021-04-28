/**
 * Description: class Pit sets up amount of stone in the pit
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class Pit {
    private int stoneAmount;

    /**
     * Construct the Pit class with the initial stone amount given
     * @param stoneAmount - number of stones
     */
    public Pit(int stoneAmount) {
        stoneAmount = this.stoneAmount;
    }

    /**
     * Get number of  stone amount in the Pit
     * @return  - stones
     */
    public int getStoneAmount() {
        return stoneAmount;
    }

    /**
     * Take all stones of the opposite pit
     * @return
     */
    public int takeAllStones() {
        int oppositeStones = stoneAmount;
        stoneAmount = 0;
        return oppositeStones;
    }


    /**
     * Sets number of stone amount in the Pit
     * @param stone - number of stones
     */
    public void setStoneAmount(int stone) {
        stoneAmount = stone;
    }

}
