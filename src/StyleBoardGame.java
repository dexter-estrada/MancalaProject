import javax.swing.*;
import java.awt.*;

/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StyleBoardGame extends JButton implements StyleBoard {
    public JFrame mancalaFrame;
    public  PitButtons[] pitButtons;

    StyleBoardGame(JFrame mancalaFrame, PitButtons[] pitButtons) {
        this.mancalaFrame = mancalaFrame;
        this.pitButtons = pitButtons;
        setText("Style Board Button");
        addActionListener(e -> {
            JFrame popup = new JFrame();
            Object[] text = {"Day Style", "Night Style"};
            int choice = JOptionPane.showOptionDialog(popup, "Please select a board style", "Style Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, text, text[0]);

            if (choice == 0) {
                setDayStyle();
            } else if (choice == 1) {
                setNightStyle();
            }
        });
    }

    public void setDayStyle() {
        mancalaFrame.setBackground(Color.YELLOW);
        mancalaFrame.repaint();
        for (PitButtons pitButton : pitButtons) {
            pitButton.setBackground(Color.RED);
            pitButton.setForeground(Color.BLACK);
        }
    }

    public void setNightStyle() {
        mancalaFrame.setBackground(Color.BLACK);
        mancalaFrame.repaint();
        for (PitButtons button : pitButtons) {
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
        }
    }

}
