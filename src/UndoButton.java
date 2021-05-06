import javax.swing.*;

/**
 * Description
 * Constructs an "undo" button that allows 3 uses per move
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */

public class UndoButton extends JButton {
    /**
     * default constructor that creates an undo button with actionListener
     * @param d receives datamodel as input
     */
    public UndoButton(DataModel d) {
        setText("undo");
        addActionListener(event -> {
            //checks if used twice in a row
            if (checkCounter() == MAXUSAGE) {
                message();
            } else {
                //checks if undo button has been used 3 times before next player moves
                if (!d.checkUndoCounter()) {
                    maxMessage();
                } else {
                    d.iterateUndoCounter();
                    iterateCounter();
                    d.undoMove();
                }
            }
        });
    }

    /**
     *  Returns counter value
     */
    private int checkCounter() {
        return counter;
    }

    /**
     *  Returns counter to 0
     */
    public static void resetCounter() {
        counter = 0;
    }

    /**
     *  Iterates counter forward by one
     */
    private void iterateCounter() {
        counter++;
    }

    /**
     *  Creates a popup informing the player that they can not select undo
     *  twice and please select a pit
     */
    private void message() {
        String text = "You can not undo more than once without selecting a pit. Please select a pit.";
        String title = "Undo Error";
        JFrame popup = new JFrame();
        JOptionPane.showMessageDialog(popup, text, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     *  Creates a popup informing the player that the maximum amount of
     *  undo usages have been met and please select a pit
     */
    private void maxMessage() {
        String text = "Maximum uses of undo button have been reached. Please select a pit.";
        String title = "Undo Error";
        JFrame popup = new JFrame();
        JOptionPane.showMessageDialog(popup, text, title, JOptionPane.WARNING_MESSAGE);
    }

    private static int counter = 0;
    private final int MAXUSAGE = 1;

}
