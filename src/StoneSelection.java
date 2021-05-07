import javax.swing.*;

/**
 * Description: Create the stone selections which has 3 or 4 stones
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StoneSelection extends JButton {
    private int numStones;
    private MancalaFrame pitStones;

    /**
     * Constructs a StoneSelection class with the parameter dataModel given
     * @param dataModel - object of DataModel class
     */
    public StoneSelection(DataModel dataModel) {
        setText("Stone Button");
        //Default the initial stone
        numStones = 3;

        addActionListener(e -> {
            JFrame popup = new JFrame();
            Object[] text = {"Three Stones", "Four Stones"};
            int choice = JOptionPane.showOptionDialog(popup, "Please select the amount stones in the Pit", "Stone Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, text, text[0]);
            if (choice == 0) {
                dataModel.setStones(3);

            } else if (choice == 1){
                dataModel.setStones(4);
            }
        });

    }

    /**
     * Gets number stones in the Pit
     * @return - number of stones
     */
    public int getNumStones() {
        return numStones;
    }

    /**
     * Gets stone button form MancalaFrame class
     * @return - number of stones in a pit of each side
     */
    public JButton getPitStones() {

        return pitStones.getStoneButton();
    }
}
