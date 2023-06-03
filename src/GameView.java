import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.lang.reflect.Array;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class GameView extends View implements ActionListener {
    private GameController GameController;
    private Model gameModel;
    private JLabel score1, score2, score3;
    private JPanel buttonsPanel, scorePanel;
    private JButton rollButton, nextPlayer;
    public JPanel gameViewPanel;

    public boolean isNextPlayerAvailable = false;
    public boolean isRollingAvailable = true;
    public boolean isWinnerDeterminated = false;

    public GameView() {

        super();
        getFrame().setTitle("Dice Game");
        score1 = new JLabel();
        score2 = new JLabel();
        score3 = new JLabel();
        score1.setFont(new Font(null, Font.PLAIN, 20));
        score2.setFont(new Font(null, Font.PLAIN, 20));
        score3.setFont(new Font(null, Font.PLAIN, 20));
        scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 100));
        scorePanel.add(score1);
        scorePanel.add(score2);
        scorePanel.add(score3);

        gameViewPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 70));
        gameViewPanel.setBackground(Color.WHITE);

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
        if (e.getSource() == rollButton && isRollingAvailable == true) {
            GameController.rollAndSum();
            score1.setText("TURN: " + gameModel.getTurn() + "    PLAYER: " + gameModel.getCurrentPlayerID() +
                            "    Dices:" + gameModel.getPlayerScoreList(gameModel.getCurrentPlayerID()));
            score2.setText("Sum:" + gameModel.getPlayerSummScore(gameModel.getCurrentPlayerID()));
            score3.setText(null);
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
        if (isNextPlayerAvailable == true && e.getSource() == nextPlayer) {
            score1.setText("TURN: " + "    PLAYER: " + "    Dices: ");
            score2.setText("Sum: ");
            score3.setText(null);
            gameModel.changePlayer();
        }
        if (isRollingAvailable == false && isNextPlayerAvailable == false && isWinnerDeterminated == true && e.getSource() == nextPlayer) {
//            System.out.println(gameModel.getWinners());
            score1.setText(null);
            score2.setText(null);
            score3.setText("The winners are players with numbers: " + gameModel.getWinners());
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