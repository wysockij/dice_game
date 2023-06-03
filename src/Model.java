import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Model extends Exception implements Runnable {
    private int numberOfPlayers;
    private int numberOfDices;
    public ArrayList<Integer> winners;
    public ArrayList<Player> players;
    GameView gameView;
    private int currentPlayerID = 0;
    private int turn = 1;

    public Model(int numberOfPlayers, int numberOfDices) {
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfDices = numberOfDices;
        players = new ArrayList<Player>();
        for (int i = 0; i < numberOfPlayers; i++) {
            Player PlayerName = new Player(i);
            players.add(PlayerName);
        }
    }

    public void setPlayerScoreList(ArrayList<Dice> dices, int playerID) {
        for (Dice dice : dices) {
            players.get(playerID).scoreList.add(dice.getDiceValue());
        }
    }

    public ArrayList getPlayerScoreList(int playerID) {
        return players.get(playerID).scoreList;
    }

    public void setPlayerSummScore(int playerID) {
        (players.get(playerID)).setSum();
    }

    public void clearPlayerScoreList(int playerID) {
        players.get(playerID).scoreList.clear();
    }

    public int getPlayerSummScore(int playerID) {
        return players.get(playerID).sum;
    }

    protected void setView(GameView gameView) {
        this.gameView = gameView;
    }

    public void changePlayer() {
        clearPlayerScoreList(currentPlayerID);
        gameView.hideScoreImages();

        ++currentPlayerID;
        if (currentPlayerID == numberOfPlayers) {
            ++turn;
            currentPlayerID = 0;
        }
        gameView.isNextPlayerAvailable = false;
        gameView.isRollingAvailable = true;
    }

    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public int getTurn() {
        return turn;
    }

    public int getNumberOfDices() {
        return numberOfDices;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void run() {
        while (true) {
            gameView.repaint();
        }
    }

    ArrayList<Integer> playersSums = new ArrayList<Integer>();
    
    public void determinateWinners() {
        winners = new ArrayList<Integer>();
        ArrayList<Integer> winners = new ArrayList<Integer>();
        playersSums.add(getPlayerSummScore(getCurrentPlayerID()));
        int hightsScore = playersSums.get(0);
        for (int i = 1; i < playersSums.size(); i++) {
            int tempElement = playersSums.get(i);
            if (tempElement > hightsScore) {
                hightsScore = tempElement;
            }
        }
        for (int i = 0; i < playersSums.size(); i++) {
            if (playersSums.get(i).equals(hightsScore)) {
                winners.add(i);
            }
        }
        this.winners = winners;
    }

    public ArrayList<Integer> getWinners() {
        return winners;
    }

    public void clearPlayersSumsArray() {
        playersSums.clear();
    }

    public void saveScoreToFile() {
        Runnable writeToFile = () -> {
            try {
                FileWriter fileWriter = new FileWriter("scores_log.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.write(
                    "\nTurn: " + getTurn() +
                    "\nPlayer: " + getCurrentPlayerID() +
                    "\nDices: " + getPlayerScoreList(getCurrentPlayerID()) +
                    "\nSum: " + getPlayerSummScore(getCurrentPlayerID()) + 
                    "\n");
                fileWriter.close();
                printWriter.close();
            }
            catch (IOException e) {
                System.out.println("Missing file!");
            }
        };
        Thread thread = new Thread(writeToFile);
        thread.start();
    }

    private static class Player {
        public int playerID;

        public int sum = 0;

        public ArrayList<Integer> scoreList;
        public Player(int playerID) {
            this.playerID = playerID;
            scoreList = new ArrayList<Integer>();
        }
        public void setSum() {
            this.sum = 0;
            for (Integer score : this.scoreList) {
                this.sum += score;
            }
        }
    }
}
