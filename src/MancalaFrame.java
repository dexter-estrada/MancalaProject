import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Description: View portions: Pit buttons, undo button, style frame button, and mancala button of the player
 * on the game board frame.
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class MancalaFrame extends JFrame implements ChangeListener {
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
    private static UndoButton undoButton;
    private JButton stoneButton;
    private JTextField mancalaAScore;
    private JTextField mancalaBScore;
    private DataModel dataModel;
    StyleBoardGame styleBoardGame;

    final int MANCALA_FRAME_WIDTH = 850;
    final int MANCALA_FRAME_HEIGHT = 350;
    private boolean checkWinner;


    /**
     * Constructs the MancalaFrame to manage the position of PitButtons, undoButton,
     * * styleFrameButtons, and player score buttons.
     */
    public MancalaFrame(DataModel dataModel) {
        this.dataModel = dataModel;
        // Creating mancalaFrame
        mancalaFrame = new JFrame();
        // Create size of PitButtons
        pitButtons = new PitButtons[12];

        //Get score mancala A
        mancalaA = new JButton("Mancala-A: " + dataModel.getPlayerAMancala().getNumStones());
        mancalaA.setFont(new Font("Arial", Font.PLAIN, 10));
        mancalaA.setEnabled(false);
        mancalaA.setFocusable(false);
        mancalaA.setBackground(Color.BLUE);
        mancalaA.setForeground(Color.black);
        mancalaA.setBorder(new EmptyBorder(10, 40, 10, 40));

        //Get score mancala B
        mancalaB = new JButton("Mancala-B: " + dataModel.getPlayerBMancala().getNumStones());
        mancalaB.setFont(new Font("Arial", Font.PLAIN, 10));
        mancalaB.setEnabled(false);
        mancalaB.setFocusable(false);
        mancalaB.setBackground(Color.orange);
        mancalaB.setForeground(Color.WHITE);
        mancalaB.setBorder(new EmptyBorder(10, 40, 10, 40));

        //Undo Button
        undoButton = new UndoButton(dataModel);
        undoButton.setBackground(Color.PINK);
        undoButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Style Board Button
        styleBoardButton = new StyleBoardGame(mancalaFrame, pitButtons);
        styleBoardButton.setBackground(Color.RED);
        styleBoardButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Stone Button
        stoneButton = new StoneSelection(dataModel);
        stoneButton.setBackground(Color.CYAN);
        stoneButton.setBorder(new EmptyBorder(10, 10, 10, 10));


        //Set select JPanel for style board game Button and stone Button
        selectPanel = new JPanel();
        selectPanel.add(styleBoardButton, BorderLayout.NORTH);
        selectPanel.add(stoneButton, BorderLayout.SOUTH);


        //Create pitPanel to hold pits and mancala A and mancalaB
        pitPanel = new JPanel();
        pitPanel.setLayout(new GridLayout(2, 6));
        pitPanel.setBorder(new LineBorder(Color.BLACK));
        pitPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Creating pitButtons for player B
        for (int i = 11; i > 5; i--) {
            int finalI = i - 6;
            int index = finalI + 1;
            pitButtons[i] = new PitButtons("B", i);
            // JLabel sideBLabel = new JLabel(pitButtons[i].toString());
            pitButtons[i].setBackground(Color.LIGHT_GRAY);
            pitButtons[i].setText("B" + index + ": " + Integer.toString(dataModel.getPlayerBPits().get(finalI).getStoneAmount()));
            pitButtons[i].addActionListener(e -> dataModel.playerBMove(finalI));
            //pitPanel.add(sideBLabel);
            pitPanel.add(pitButtons[i]);
        }

        // Creating pitButtons for player A
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            int index = finalI + 1;
            pitButtons[i] = new PitButtons("A", i);
            // JLabel labelA = new JLabel("A");
            // JLabel sideALabel = new JLabel(pitButtons[i].toString());
            pitButtons[i].setBackground(Color.LIGHT_GRAY);
            pitButtons[i].setText("A" + index + ": " + Integer.toString(dataModel.getPlayerBPits().get(finalI).getStoneAmount()));
            pitButtons[i].addActionListener(e -> dataModel.playerAMove(finalI));
            // pitPanel.add(sideALabel);
            pitPanel.add(pitButtons[i]);
        }

        /*
        for (int i= pitButtons.length-1; i>=0; i--) {
            int finalI = i % 6; // Position of player A or B's pits
            if (i<6) {
                pitButtons[i] = new PitButtons("A", i);
               // JLabel labelA = new JLabel("A");
               // JLabel sideALabel = new JLabel(pitButtons[i].toString());
                pitButtons[i].setBackground(Color.LIGHT_GRAY);
                pitButtons[i].setText(Integer.toString(dataModel.getPlayerBPits().get(finalI)));
                pitButtons[i].addActionListener(e -> dataModel.playerAMove(finalI));
                // pitPanel.add(sideALabel);
            } else {
                pitButtons[i] = new PitButtons("B", i);
               // JLabel sideBLabel = new JLabel(pitButtons[i].toString());
                pitButtons[i].setBackground(Color.LIGHT_GRAY);
                pitButtons[i].setText(Integer.toString(dataModel.getPlayerBPits().get(finalI)));
                pitButtons[i].addActionListener(e -> dataModel.playerBMove(finalI));
                //pitPanel.add(sideBLabel);
            }
            pitPanel.add(pitButtons[i]);
        }
        */

        //Get mancala A score
        // JLabel mancalaALbl = new JLabel("Mancala-A Score");
        mancalaAScore = new JTextField("0");
        mancalaAScore.setText("A-Score: " + dataModel.getPlayerAMancala().getNumStones());
        mancalaAScore.setBackground(Color.WHITE);
        mancalaAScore.setForeground(Color.black);
        mancalaAScore.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Get mancala B score
        //  JLabel mancalaBLbl = new JLabel("Mancala-B Score");
        mancalaBScore = new JTextField("0");
        mancalaBScore.setText("B-Score: " + dataModel.getPlayerBMancala().getNumStones());
        mancalaBScore.setBackground(Color.white);
        mancalaBScore.setForeground(Color.black);
        mancalaBScore.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Set JPanel for undo button and quit button
        buttonPanel = new JPanel();
        buttonPanel.add(undoButton, BorderLayout.NORTH);
        buttonPanel.add(mancalaAScore, BorderLayout.EAST);
        //  buttonPanel.add(mancalaALbl);
        buttonPanel.add(mancalaBScore, BorderLayout.WEST);
        //buttonPanel.add(mancalaBLbl);

        //Board game panel
        boardGamePanel = new JPanel();
        boardGamePanel.setLayout(new BorderLayout());
        boardGamePanel.add(selectPanel, BorderLayout.NORTH);
        boardGamePanel.add(pitPanel, BorderLayout.CENTER);
        boardGamePanel.add(mancalaA, BorderLayout.EAST);
        boardGamePanel.add(mancalaB, BorderLayout.WEST);
        boardGamePanel.add(buttonPanel, BorderLayout.SOUTH);

        //Mancala Frame
        mancalaFrame.add(boardGamePanel);
        mancalaFrame.setTitle("Mancala Game");
        mancalaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mancalaFrame.pack();
        mancalaFrame.setSize(MANCALA_FRAME_WIDTH, MANCALA_FRAME_HEIGHT);
        mancalaFrame.setVisible(true);

    }

    /**
     * Gets pit Buttons array from the PitButtons class
     *
     * @return - pit buttons of each Mancala's side
     */
    public PitButtons[] getPitButtons() {
        return pitButtons;
    }

    /**
     * Gets a player A score
     *
     * @return - total stones of Mancala A
     */
    public JButton getMancalaA() {
        return mancalaA;
    }

    /**
     * Gets a player B score
     *
     * @return - total stones of Mancala B
     */
    public JButton getMancalaB() {
        return mancalaB;
    }

    /**
     * Gets Stone Selection Button
     *
     * @return - number stone selection
     */
    public JButton getStoneButton() {
        stoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        return stoneButton;
    }

    /**
     * Gets style board button
     *
     * @return - style board selection
     */
    public StyleBoardGame getStyleBoardButton() {
        return (StyleBoardGame) styleBoardButton;
    }

    /**
     * Gets undo button
     *
     * @return - undo choice
     */
    public static JButton getUndoButton() {
        return undoButton;
    }

    public static void resetUndoCounter() {
        undoButton.resetCounter();
    }

    /**
     * Gets a select choice JPanel from the given string select
     *
     * @param select - play select panel
     * @return - panel for selection
     */
    public JPanel getPanel(String select) {
        if (select.equals("Pit")) {
            return pitPanel;
        } else if (select.equals("Select Style Board")) {
            return selectPanel;
        } else if (select.equals("Select Stone")) {
            return selectPanel;
        } else {
            return null;
        }
    }


    /**
     * Gets a select choice JButton from the given string select
     *
     * @param select - player select button
     * @return button selection
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

    public void add(int numStones) {
        Pit pit = new Pit(numStones);
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    public void stateChanged(ChangeEvent e) {
        // Updating score
        mancalaA.setText("Mancala-A: " + dataModel.getPlayerAMancala().getNumStones());
        mancalaB.setText("Mancala-B: " + dataModel.getPlayerBMancala().getNumStones());
        mancalaAScore.setText("A-Score: " + dataModel.getPlayerAMancala().getNumStones());
        mancalaBScore.setText("B-Score: " + dataModel.getPlayerBMancala().getNumStones());
        styleBoardButton.addActionListener(event -> styleBoardButton.setBackground(Color.white));

        // Updating pits
        // Player A
        for (int i = 0; i < 6; i++) {
            pitButtons[i].setText("A" + i + ": " + Integer.toString(dataModel.getPlayerAPits().get(i).getStoneAmount()));
        }
        // Player B
        for (int i = 11; i > 5; i--) {
            int finalI = i - 6;
            pitButtons[i].setText("B" + finalI + ": " + Integer.toString(dataModel.getPlayerBPits().get(finalI).getStoneAmount()));
        }

        if (!checkWinner) {
            checkWinner = true;
            String playerWinner = dataModel.checkWinnerPlayer();
            if (playerWinner != null) {
                JFrame frame = new JFrame();
                if (playerWinner.equals("Player-A Win"))
                    JOptionPane.showMessageDialog(frame, "Player A Win!", "Winner of Mancala Game", JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "Player B Win!", "Winner of Mancala Game", JOptionPane.PLAIN_MESSAGE);
            }
            checkWinner = false;
        }
    }

    public void setBackground(Color yellow) {
        //mancalaFrame.setBackground(Color.WHITE);
    }

}
