import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Description: View portions: Pit buttons, undo button, style frame button, and mancala button of the player
 * on the game board frame.
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class MancalaFrame {
    private JFrame mancalaFrame;
    private JPanel buttonPanel;
    private JPanel pitPanel;
    private JPanel boardGamePanel;
    private StyleBoardGame styleBoardPanel;
    private PitButtons[] pitButtons;
    private JButton changeStyleBoardButton;
    private JButton playerAScore;
    private JButton playerBScore;
    private JButton undoButton;
    private JButton quitButton;

    /**
     * Constructs the MancalaFrame to manage the position of PitButtons, undoButton,
     *  * styleFrameButtons, and player score buttons.
     */
    public MancalaFrame() {
        //Get score player A
        playerAScore = new JButton("0");
        playerAScore.setEnabled(false);
        playerAScore.setFocusable(false);
        playerAScore.setBackground(Color.lightGray);
        playerAScore.setForeground(Color.black);
        playerAScore.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Get score player B
        playerBScore = new JButton("0");
        playerBScore.setEnabled(false);
        playerBScore.setFocusable(false);
        playerBScore.setBackground(Color.lightGray);
        playerBScore.setForeground(Color.black);

        //Undo Button
        undoButton = new JButton("Undo");
        undoButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //quit Button
        quitButton = new JButton("Quit Mancala");
        quitButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //create size of PitButtons
        pitButtons = new PitButtons[12];


        //Set JPanel for undo button and quit button
        buttonPanel = new JPanel();
        buttonPanel.add(undoButton, BorderLayout.EAST);
        buttonPanel.add(changeStyleBoardButton, BorderLayout.WEST);

        //Board game panel
        boardGamePanel = new JPanel();
        boardGamePanel.setLayout( new BorderLayout());
        boardGamePanel.add(buttonPanel, BorderLayout.SOUTH);
        boardGamePanel.add(pitPanel, BorderLayout.CENTER);
        boardGamePanel.add(playerAScore, BorderLayout.EAST);
        boardGamePanel.add(playerBScore, BorderLayout.WEST);

        //Mancala Frame
        mancalaFrame = new JFrame();
        mancalaFrame.add(boardGamePanel);
        mancalaFrame.setTitle("Mancala Game");
        mancalaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mancalaFrame.pack();
        boardGamePanel.setSize(700, 300);
        mancalaFrame.setVisible(true);

    }

    /**
     * Gets pit Buttons array from the PitButtons class
     * @return
     */
    public PitButtons[] getPitButtons() {
        return pitButtons;
    }

    /**
     * Gets a player A score
     * @return
     */
    public JButton getPlayerAScore() {
        return playerAScore;
    }

    /**
     * Gets a player B score
     * @return
     */
    public JButton getPlayerBScore() {
        return playerBScore;
    }

    /**
     * Gets a select choice JPanel from the given string select
     * @param select
     * @return
     */
    public JPanel getPanel(String select) {
        if (select.equals("Pit")) {
            return pitPanel;
        } else if (select.equals("Buttons")) {
            return buttonPanel;
        } else {
            return null;
        }
    }


    /**
     * Gets a select choice JButton from the given string select
     * @param select
     * @return
     */
    public JButton getSelectButton(String select) {
        if (select.equals("Undo")) {
            return undoButton;
        } else if (select.equals("Choose Style Board")) {
            return changeStyleBoardButton;
      //  } else if (select.equals("Quit Mancala")) {
            //return quitButton;
        } else {
            return null;
        }
    }
}
