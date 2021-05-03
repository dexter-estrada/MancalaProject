import javax.swing.*;
import java.awt.*;

/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StyleBoardGame{
    StyleBoardGame() {
        JFrame popup = new JFrame();
        Object[] text = {"Day Style", "Night Style"};
        int choice = JOptionPane.showOptionDialog(popup, "Please select a board style", "Style Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, text, text[0]);
        if (choice == 0) {
            //setDayStyle(mancalaFrame, pitButtons);
        } else if (choice == 1) {
            //setNightStyle(mancalaFrame, pitButtons);
        }
    }

    void setDayStyle(JFrame frame, JButton[] buttons) {
        frame.setBackground(Color.YELLOW);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Color.RED);
            buttons[i].setForeground(Color.BLACK);
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
