import javax.swing.*;
import java.awt.*;

/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StoneSelection extends DataModel{
    StoneSelection() {
    JFrame popup = new JFrame();
    Object[] text = {"3 stones", "4 stones"};
    int choice = JOptionPane.showOptionDialog(popup, "Please select the amount of starting stones per pit", "Stone Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, text, text[0]);
    setStones(choice);
}
    void setStones(int number) {
        int stones = number + 3;
        setPits(stones);
    }
}
