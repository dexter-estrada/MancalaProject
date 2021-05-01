import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StoneSelection extends DataModel {


    private JButton threeStonesButton;
    private JButton fourStonesButton;
    private JButton selectStoneButton;
    private int numStones;
    private MancalaFrame pitStones;

    public StoneSelection() {

        //Default the initial stone
        numStones = 3;

        JFrame popup = new JFrame();
        Object[] text = {"Three Stones", "Four Stones"};
        int choice = JOptionPane.showOptionDialog(popup, "Please select the amount stones in the Pit", "Stone Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, text, text[0]);
        if (choice == 0) {
            numStones = 3;
            pitStones.add(numStones);
        } else if (choice == 1){
            numStones = 4;
            pitStones.add(numStones);
        }

        /*
        threeStonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectStoneButton(threeStonesButton);
                numStones = 3;
            }
            private void selectStoneButton(JButton threeStonesButton) {
                pitStones.add(numStones);
            }
        });

        fourStonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectStoneButton(fourStonesButton);
                numStones = 4;
            }
            private void selectStoneButton(JButton fourStonesButton) {
                pitStones.add(numStones);
            }
        });
         */

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
