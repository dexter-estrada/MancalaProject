/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class PlayerMancala {
    private int numStones;

    public PlayerMancala() {
        numStones = 0;
    }

    public void addNumStones(int stone) {
        numStones += stone;
    }

    public int getNumStones() {
        return numStones;
    }

    public void setNumStones(int stone) {
        numStones = stone;
    }

    public int takeOppositeStones() {
        int oppositeStones = numStones;
        numStones = 0;
        return oppositeStones;
    }

}
