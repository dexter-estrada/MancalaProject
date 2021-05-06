import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StoneSelection extends JButton {
    private JButton threeStonesButton;
    private JButton fourStonesButton;
    private JButton selectStoneButton;
    private int numStones;
    private MancalaFrame pitStones;

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
                //numStones = 3;
                //pitStones.add(numStones);
            } else if (choice == 1){
                dataModel.setStones(4);
                //numStones = 4;
                //pitStones.add(numStones);
            }
        });

    }

    /**
     * Gets number stones in the Pit
     * @return
     */
    public int getNumStones() {
        return numStones;
    }

    /**
     * Gets stone button form MancalaFrame class
     * @return
     */
    public JButton getPitStones() {
        return pitStones.getStoneButton();
    }
}
