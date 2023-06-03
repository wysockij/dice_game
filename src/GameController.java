import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class GameController {
    Random random;
    private final int numberOfDices;
    private Model gameModel;
    public ArrayList<Dice> Dices;

    public GameController(int numberOfDices) {
        this.numberOfDices = numberOfDices;
        this.random = new Random();
    }

    public void rollAndSum() {
        int currentPlayerID = gameModel.getCurrentPlayerID();
        for (Dice dice : Dices) {
            int currentScore = random.nextInt(6) + 1;
            dice.setDiceValue(currentScore);
            dice.selectIcon();
        }
        gameModel.setPlayerScoreList(Dices, currentPlayerID);
        gameModel.setPlayerSummScore(currentPlayerID);
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