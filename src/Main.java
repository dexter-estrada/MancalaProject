/**
 * Description
 *
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class Main {
    public static void main(String[] args) {
        DataModel dataModel = new DataModel(4);     // Integer is only used to test if moves/undo works
        MancalaFrame mancalaFrame  = new MancalaFrame(dataModel);
        dataModel.addChangeListener(mancalaFrame);
        //UndoButton undoButton = new UndoButton();
        //StoneSelection stoneSelection = new StoneSelection();

    }

}
