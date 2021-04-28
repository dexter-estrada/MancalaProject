import javax.swing.*;
/**
 * Description: Class PitButtons holds name and keep track of index of pit button
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class PitButtons extends JButton {
    private int indexPitButton;
    private char namePitButton;

    /**
     * Construct the PitButtons class with the indexPit given
     * @param i - indexPit
     */
    public PitButtons(char name, int index) {
        namePitButton = name;
        indexPitButton = index;
    }

    /**
     * Get the index button of Pit
     * @return - index pit
     */
    public int getIndexPit() {
        return indexPitButton;
    }

    /**
     * Get name pit Button
     * @return
     */
    public char getNamePitButton() {
        return namePitButton;
    }

    /**
     * Get preference name of pit Button
     * @return
     */
    public String toString() {
        return namePitButton + "-" + indexPitButton;
    }


}

