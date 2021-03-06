import javax.swing.*;
/**
 * Description: Class PitButtons holds name and keeps track of index of pit button
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class PitButtons extends JButton {
    private int indexPitButton;
    private String namePitButton;
    private String namePit;

    /**
     * Construct the PitButtons class with no parameter
     */
    public PitButtons() {

    }


    /**
     * Construct the PitButtons class with the indexPit given
     * @param name - The name
     */
    public PitButtons(String name) {
        namePit = name;
    }

    /**
     * Construct the PitButtons class with the indexPit given
     * @param namePit - The name of the pit
     * @param index - The index of the pit
     */
    public PitButtons(String namePit, int index) {
        //super(namePitButton);
        namePitButton = namePit;
        indexPitButton = index;
    }

    /**
     * Get the index Pit's button
     * @return - index pit
     */
    public int getIndexPit() {
        return indexPitButton;
    }

    /**
     * Get name pit's Button
     * @return - name of Pit's Button
     */
    public String getNamePitButton() {
        return namePitButton;
    }

    /**
     * Get preference name and index of pit Button
     * @return - name Pit's button and index of Pit's Button
     */
    public String toString() {
        return namePitButton + "-" + indexPitButton;
    }



}





















