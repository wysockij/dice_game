import java.util.ArrayList;
import java.util.Random;

public class GameController implements Runnable {
    Random random;
    private final int numberOfDices;
    private Model gameModel;
    public ArrayList<Dice> Dices;

    public GameController(int numberOfDices) {
        this.numberOfDices = numberOfDices;
        this.random = new Random();
    }

    @Override
    public void run() {
        int currentPlayerID = gameModel.getCurrentPlayerID();
        for (Dice dice : Dices) {
            int currentScore = random.nextInt(6) + 1;
            dice.setDiceValue(currentScore);
            dice.selectIcon();
        }
        gameModel.setPlayerScoreList(Dices, currentPlayerID);
        gameModel.setPlayerSummScore(currentPlayerID);
    }

    public void rollAndSum() {
        run();
    }

    public void setModel(Model model) {
        this.gameModel = model;
    }

    private int getNumberOfDices() {
        return numberOfDices;
    }

    public void makeDices() {
        Dices = new ArrayList<Dice>();
        for (int i = 0; i < getNumberOfDices(); i++) {
            Dice dice = new Dice();
            Dices.add(dice);
        }
    }
}
