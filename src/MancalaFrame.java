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
    private JPanel selectPanel;
    private StyleBoardGame styleBoardPanel;
    private PitButtons[] pitButtons;
    private JButton styleBoardButton;
    private JButton mancalaA;
    private JButton mancalaB;
    private JButton undoButton;
    private JButton quitButton;
    private JButton stoneButton;


    /**
     * Constructs the MancalaFrame to manage the position of PitButtons, undoButton,
     *  * styleFrameButtons, and player score buttons.
     */
    public MancalaFrame() {
        //Get score mancala A
        mancalaA = new JButton("A");
        mancalaA.setEnabled(false);
        mancalaA.setFocusable(false);
        mancalaA.setBackground(Color.BLUE);
        mancalaA.setForeground(Color.black);
        mancalaA.setBorder(new EmptyBorder(40, 40, 40, 40));

        //Get score mancala B
        mancalaB = new JButton("B");
        mancalaB.setEnabled(false);
        mancalaB.setFocusable(false);
        mancalaB.setBackground(Color.RED);
        mancalaB.setForeground(Color.black);
        mancalaB.setBorder(new EmptyBorder(40, 40, 40, 40));

        //Undo Button
        undoButton = new JButton("Undo");
        undoButton.setBackground(Color.PINK);
        undoButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Style Board Button
        styleBoardButton = new JButton("Style Board Button");
        styleBoardButton.setBackground(Color.orange);
        styleBoardButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Stone Button
        stoneButton = new JButton("Stone Button");
        stoneButton.setBackground(Color.CYAN);
        stoneButton.setBorder(new EmptyBorder(10, 10, 10, 10));


        //Set JPanel for style board game Button and stone Button
        selectPanel = new JPanel();
        selectPanel.add(styleBoardButton, BorderLayout.NORTH);
        selectPanel.add(stoneButton, BorderLayout.SOUTH);


        //create size of PitButtons
        pitButtons = new PitButtons[12];

        //Create pitPanel to hold pits and mancala A and mancalaB
        pitPanel = new JPanel();
        pitPanel.setLayout(new GridLayout(2, 6));
        pitPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int i=0; i<pitButtons.length; i++) {
            if (i<6) {
                pitButtons[i] = new PitButtons('A', i);
                pitButtons[i].setBackground(Color.LIGHT_GRAY);
                pitPanel.add(pitButtons[i]);
            } else {
                pitButtons[i] = new PitButtons('B', i);
                pitButtons[i].setBackground(Color.LIGHT_GRAY);
                pitPanel.add(pitButtons[i]);
            }
        }

        //Set JPanel for undo button and quit button
        buttonPanel = new JPanel();
        buttonPanel.add(undoButton, BorderLayout.NORTH);


        //Board game panel
        boardGamePanel = new JPanel();
        boardGamePanel.setLayout( new BorderLayout());
        boardGamePanel.add(selectPanel, BorderLayout.NORTH);
        boardGamePanel.add(pitPanel, BorderLayout.CENTER);
        boardGamePanel.add(mancalaA, BorderLayout.EAST);
        boardGamePanel.add(mancalaB, BorderLayout.WEST);
        boardGamePanel.add(buttonPanel, BorderLayout.SOUTH);

        //Mancala Frame
        mancalaFrame = new JFrame();
        mancalaFrame.add(boardGamePanel);
        mancalaFrame.setTitle("Mancala Game");
        mancalaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mancalaFrame.pack();
        mancalaFrame.setSize(800, 300);
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
    public JButton getMancalaA() {
        return mancalaA;
    }

    /**
     * Gets a player B score
     * @return
     */
    public JButton getMancalaB() {
        return mancalaB;
    }

    /**
     * Gets a select choice JPanel from the given string select
     * @param select
     * @return
     */
    public JPanel getPanel(String select) {
        if (select.equals("Pit")) {
            return pitPanel;
        } else if (select.equals("Select Style Board")) {
            return selectPanel;
        } else if (select.equals("Select Stone")) {
            return selectPanel;
        }else {
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
            return styleBoardButton;
       } else if (select.equals("Select Stone")) {
            return stoneButton;
        } else {
            return null;
        }
    }
}
