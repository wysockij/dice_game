import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends View implements ActionListener {
    private GameController GameController;
    private Model gameModel;
    private JLabel score1, score2;
    private JPanel buttonsPanel, scorePanel;
    private JButton rollButton, nextPlayer;
    private JPanel gameViewPanel;

    public boolean isNextPlayerAvailable = false;
    public boolean isRollingAvailable = true;
    public boolean isWinnerDeterminated = false;

    public GameView() {

        super();
        getFrame().setTitle("Dice Game");
        
        // 1'st Panel
        score1 = new JLabel();
        score2 = new JLabel();
        score1.setFont(new Font(null, Font.PLAIN, 20));
        score2.setFont(new Font(null, Font.PLAIN, 20));
        scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 100));
        scorePanel.add(score1);
        scorePanel.add(score2);

        // 2'st Panel
        gameViewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 70));
        gameViewPanel.setBackground(Color.WHITE);

        // 3'st Panel
        rollButton = new JButton("Roll dices");
        rollButton.addActionListener(this);
        nextPlayer = new JButton("Next player");
        nextPlayer.addActionListener(this);
        buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 100));
        buttonsPanel.setBackground(Color.lightGray);
        buttonsPanel.add(rollButton);
        buttonsPanel.add(nextPlayer);

        getFrame().setLayout(new GridLayout(3, 1));
        getFrame().add(buttonsPanel);
        getFrame().add(gameViewPanel);
        getFrame().add(scorePanel);
        getFrame().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rollButton && isRollingAvailable) {
            GameController.rollAndSum();
            score1.setText(
                "TURN: " + gameModel.getTurn() + 
                "    PLAYER: " + gameModel.getCurrentPlayerID() +
                "    Dices:" + gameModel.getPlayerScoreList(gameModel.getCurrentPlayerID()));
            score2.setText("Sum:" + gameModel.getPlayerSummScore(gameModel.getCurrentPlayerID()));
            isRollingAvailable = false;
            isNextPlayerAvailable = true;
            gameModel.saveScoreToFile();
            gameModel.determinateWinners();
            if (gameModel.getNumberOfPlayers() - 1 == gameModel.getCurrentPlayerID()) {
                isWinnerDeterminated = true;
                isNextPlayerAvailable = false;
                isRollingAvailable = false;
            }
        }
        if (isNextPlayerAvailable && e.getSource() == nextPlayer) {
            score1.setText("TURN: " + "    PLAYER: " + "    Dices: ");
            score2.setText("Sum: ");
            gameModel.changePlayer();
        }
        if (!isRollingAvailable && !isNextPlayerAvailable && isWinnerDeterminated && e.getSource() == nextPlayer) {
            score1.setText(
                    "The winners are players with numbers: " + gameModel.getWinners() +
                    " with the sum " + gameModel.getHighestScore()
            );
            score2.setText(null);
            hideScoreImages();
            gameModel.clearPlayersSumsArray();
            isWinnerDeterminated = false;
            isNextPlayerAvailable = true;
        }
    }

    public void setModel(Model gameModel) {
        this.gameModel = gameModel;
    }

    public void setController(GameController controller) {
        this.GameController = controller;
        GameController.makeDices();
        for (Dice dice : GameController.Dices) {
            dice.addDiceLabelToContainer(gameViewPanel);
        }
    }

    public void showScoreImages() {
        for (Dice dice : GameController.Dices) {
            dice.selectIcon();
        }
    }

    public void hideScoreImages() {
        for (Dice dice : GameController.Dices) {
            dice.hideIcon();
        }
    }
}