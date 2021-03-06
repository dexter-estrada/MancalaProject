/**
 * Launches the game and its gui representation
 * @author Legendary: Thanh Le (thanh.le01@sjsu.edu), Samuel Lam (samuel.lam@sjsu.edu), Dexter Estrada (dexter.estrada@sjsu.edu)
 */
public class Main {
    public static void main(String[] args) {
       DataModel dataModel = new DataModel();
       MancalaFrame mancalaFrame  = new MancalaFrame(dataModel);
       dataModel.addChangeListener(mancalaFrame);
    }
}
