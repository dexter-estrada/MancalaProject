import javax.swing.*;
import java.awt.*;

/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class StyleBoardGame extends JButton {
    public MancalaFrame mancalaFrame;
    public DataModel dataModel;
    public  PitButtons[] pitButtons;

    StyleBoardGame() {

        //this.pitButtons = pitButtons;

        JFrame popup = new JFrame();
        Object[] text = {"Day Style", "Night Style"};
        int choice = JOptionPane.showOptionDialog(popup, "Please select a board style", "Style Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, text, text[0]);

        if (choice == 0) {
            //for (int i=0; i<pitButtons.length; i++) {

            //}
            setDayStyle(mancalaFrame, pitButtons);
        } else if (choice == 1) {
            setNightStyle(mancalaFrame, pitButtons);
        }
    }

    void setDayStyle(MancalaFrame mancalaFrame, PitButtons[] pitButtons) {
        mancalaFrame.setBackground(Color.YELLOW);
        for (int i = 0; i < pitButtons.length; i++) {
            pitButtons[i].setBackground(Color.RED);
            pitButtons[i].setForeground(Color.BLACK);
        }
    }

    void setNightStyle(MancalaFrame mancalaFrame, PitButtons[] pitButtons) {
        mancalaFrame.setBackground(Color.BLACK);
        for (PitButtons button : pitButtons) {
            button.setBackground(Color.BLUE);
            button.setForeground(Color.WHITE);
        }
    }

}
