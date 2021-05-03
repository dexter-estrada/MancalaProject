import javax.swing.*;

/**
 * Description
 * Constructs an "undo" button that allows 3 uses per move
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */

public class UndoButton extends JButton {
    public UndoButton(DataModel d) {
        setText("undo");
        addActionListener(event -> {
            if (checkCounter() == MAXUSAGE) {
                message();
            } else {
                if (
                        d.checkUndoCounter()) {
                        d.iterateUndoCounter();
                        iterateCounter();
                        d.undoMove();
                }
            }
        });
    }

    public UndoButton() {

    }

    /*
     *  Returns counter value
     *  @param none
     */
    private int checkCounter() {
        return counter;
    }

    /*
     *  Returns counter to 0
     *  @param none
     */
    public void resetCounter() {
        counter = 0;
    }

    /*
     *  Iterates counter forward by one
     *  @param none
     */
    private void iterateCounter() {
        counter++;
    }

    /*
     *  Creates a pop informing the player that the maximum amount of
     *  undo usages has been met and please select a pit
     *  @param none
     */
    private void message() {
        String text = "You can not undo more than once without selecting a pit. Please select a pit.";
        String title = "Undo Error";
        JFrame popup = new JFrame();
        JOptionPane.showMessageDialog(popup, text, title, JOptionPane.WARNING_MESSAGE);
    }

    private int counter = 0;
    private final int MAXUSAGE = 1;

}
