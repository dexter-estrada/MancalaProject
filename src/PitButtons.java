import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Description: Class PitButtons holds name and keeps track of index of pit button
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class PitButtons extends JButton implements ChangeListener {
    private static final int PIT_WIDTH = 100;
    private static final int PIT_HEIGHT = 100;
    private int numStones;
    private  int pitNumber;
    private char pitSide;
    private DataModel dataModel;
    private int indexPitButton;
    private String namePitButton;
    private String namePit;

    /**
     * Construct the PitButtons class with no parameter
     */
    public PitButtons() {

    }

    public PitButtons (DataModel dataModel, char pitSide, int pitNumber) {
        this.dataModel = dataModel;
        this.pitSide = pitSide;
        this.pitNumber = pitNumber;

        numStones = dataModel.getPitValue(pitSide, pitNumber);

    }


    /**
     * Construct the PitButtons class with the indexPit given
     * @param name - The name
     */
    public PitButtons(String name) {
        namePit = name;
    }

    /**
     * Construct the PitButtons class with the indexPit given
     * @param namePit - The name of the pit
     * @param index - The index of the pit
     */
    public PitButtons(String namePit, int index) {
        //super(namePitButton);
        namePitButton = namePit;
        indexPitButton = index;
    }

    /**
     * Get the index Pit's button
     * @return - index pit
     */
    public int getIndexPit() {
        return indexPitButton;
    }

    /**
     * Get name pit's Button
     * @return - name of Pit's Button
     */
    public String getNamePitButton() {
        return namePitButton;
    }

    /**
     * Get preference name and index of pit Button
     * @return - name Pit's button and index of Pit's Button
     */
    public String toString() {
        return namePitButton + "-" + indexPitButton;
    }


    /**
     * Paint the shapes of stones inside the PitButtons
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GRAY);

        for (Shape s: getPitStoneIcon(numStones)) {
            g2.draw(s);
            g2.fill(s);
        }

    }


    /**
     * Returns the list of stones's shape
     * @param numStones
     * @return
     */
    public Shape [] getPitStoneIcon(int numStones) {
        if (numStones == 0) {
            Rectangle2D stoneIcon = new Rectangle2D.Double(0, 0, 0, 0);

            return new Shape[] {stoneIcon};
        }

        ArrayList<Rectangle2D.Double> iconList = new ArrayList<Rectangle2D.Double>();
        int dimension = (int) (Math.sqrt(numStones) + 1);
        int width = PIT_WIDTH/(dimension) - 10;
        int height = PIT_HEIGHT/(dimension) - 10;

        for (int i=0; i<numStones; i++) {
            int xModulo = i%dimension;
            int yModulo = i%dimension;

            int x = xModulo*(width+4) + 10;
            int y = yModulo*(height+4) + 5;

            iconList.add(new Rectangle2D.Double(x, y, width, height));
        }

        Rectangle2D.Double [] rectangelList = new Rectangle2D.Double[iconList.size()];
        rectangelList = iconList.toArray(rectangelList);

        return rectangelList;

    }


    public void stateChanged(ChangeEvent e) {
        numStones = dataModel.getPitValue(pitSide, pitNumber);
    }





}





















