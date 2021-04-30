import javax.swing.*;
import java.awt.*;

/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StyleBoardGame {
    void setDayStyle(JFrame frame, JButton[] buttons) {
        frame.setBackground(Color.YELLOW);
        for (JButton button : buttons) {
            button.setBackground(Color.RED);
            button.setForeground(Color.black);
        }
    }
    void setNightStyle(JFrame frame, JButton[] buttons) {
        frame.setBackground(Color.BLACK);
        for (JButton button : buttons) {
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
        }
    }
}
