import javax.swing.*;

/**
 * Description
 * Creates a popup that allows the player to select the style of board
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StyleButton {
    StyleButton() {
        JFrame popup = new JFrame();
        Object[] text = {"Day Style", "Night Style"};
        int choice = JOptionPane.showOptionDialog(popup, "Please select a board style", "Style Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, text, text[0]);
        if (choice == 0) {
            //day style
        } else if (choice == 1) {
            //night style
        }
    }
}
