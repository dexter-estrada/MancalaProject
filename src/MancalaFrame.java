import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton stoneButton;
    private JTextField mancalaAScore;
    private JTextField mancalaBScore;
    private DataModel dataModel;
    final int MANCALA_FRAME_WIDTH = 850;
    final int MANCALA_FRAME_HEIGHT = 350;


    /**
     * Constructs the MancalaFrame to manage the position of PitButtons, undoButton,
     *  * styleFrameButtons, and player score buttons.
     */
    public MancalaFrame(DataModel dataModel) {
        this.dataModel = dataModel;
        //Get score mancala A
        mancalaA = new JButton("Mancala-A");
        mancalaA.setFont(new Font("Arial", Font.PLAIN, 10));
        mancalaA.setEnabled(false);
        mancalaA.setFocusable(false);
        mancalaA.setBackground(Color.BLUE);
        mancalaA.setForeground(Color.black);
        mancalaA.setBorder(new EmptyBorder(10, 40, 10, 40));

        //Get score mancala B
        mancalaB = new JButton("Mancala-B");
        mancalaB.setFont(new Font("Arial", Font.PLAIN, 10));
        mancalaB.setEnabled(false);
        mancalaB.setFocusable(false);
        mancalaB.setBackground(Color.orange);
        mancalaB.setForeground(Color.WHITE);
        mancalaB.setBorder(new EmptyBorder(10, 40, 10, 40));

        //Undo Button
        undoButton = new JButton("Undo");
        undoButton.setBackground(Color.PINK);
        undoButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Style Board Button
        styleBoardButton = new JButton("Style Board Button");
        styleBoardButton.setBackground(Color.RED);
        styleBoardButton.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Stone Button
        stoneButton = new JButton("Stone Button");
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

        //Create size of PitButtons
        pitButtons = new PitButtons[12];
        for (int i= pitButtons.length-1; i>=0; i--) {
            if (i<6) {
                pitButtons[i] = new PitButtons("A", i);
               // JLabel labelA = new JLabel("A");
               // JLabel sideALabel = new JLabel(pitButtons[i].toString());
                pitButtons[i].setBackground(Color.LIGHT_GRAY);
                pitButtons[i].setText("A");
                pitPanel.add(pitButtons[i]);
                dataModel.attach(pitButtons[i]);

               // pitPanel.add(sideALabel);


            } else {
                pitButtons[i] = new PitButtons("B", i);
               // JLabel sideBLabel = new JLabel(pitButtons[i].toString());
                pitButtons[i].setBackground(Color.LIGHT_GRAY);
                pitButtons[i].setText("B");
                pitPanel.add(pitButtons[i]);
                dataModel.attach(pitButtons[i]);
                //pitPanel.add(sideBLabel);
            }
        }

        //Get mancala A score
       // JLabel mancalaALbl = new JLabel("Mancala-A Score");
        mancalaAScore = new JTextField("0");
        mancalaAScore.setText("A-Score");
        mancalaAScore.setBackground(Color.WHITE);
        mancalaAScore.setForeground(Color.black);
        mancalaAScore.setBorder(new EmptyBorder(10, 10, 10, 10));

        //Get mancala B score
      //  JLabel mancalaBLbl = new JLabel("Mancala-B Score");
        mancalaBScore = new JTextField("0");
        mancalaBScore.setText("B-Score");
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
        mancalaFrame.setSize(MANCALA_FRAME_WIDTH, MANCALA_FRAME_HEIGHT);
        mancalaFrame.setVisible(true);

    }

    /**
     * Gets pit Buttons array from the PitButtons class
     * @return - pit buttons of each Mancala's side
     */
    public PitButtons[] getPitButtons() {
        return pitButtons;
    }

    /**
     * Gets a player A score
     * @return - total stones of Mancala A
     */
    public JButton getMancalaA() {
        return mancalaA;
    }

    /**
     * Gets a player B score
     * @return - total stones of Mancala B
     */
    public JButton getMancalaB() {
        return mancalaB;
    }

    /**
     * Gets Stone Selection Button
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
     * @return - style board selection
     */
    public JButton getStyleBoardButton() {
        return styleBoardButton;
    }

    /**
     * Gets undo button
     * @return - undo choice
     */
    public JButton getUndoButton() {
        return undoButton;
    }

    /**
     * Gets a select choice JPanel from the given string select
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
        }else {
            return null;
        }
    }


    /**
     * Gets a select choice JButton from the given string select
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
}
