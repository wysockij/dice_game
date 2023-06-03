import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends View implements ActionListener {
    private ImageIcon image;
    private JLabel welcomeLabel, playersLabel, dicesLabel;
    private JPanel welcomePanel, buttonsPanel;
    private JButton startButton;
    private JComboBox<Integer> playersComboBox, dicesComboBox;
    private Integer[] numberOfPlayersArray = { 2, 4, 6 }, numberOfDicesArray = { 1, 2, 3 };

    public MenuView() {

        super();
        getFrame().setTitle("Dice Game");
        
        // 1'st Panel
        image = new ImageIcon("dices.png");

        welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome to dice game!");
        welcomeLabel.setIcon(image);
        welcomeLabel.setIconTextGap(60);
        welcomeLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomeLabel.setVerticalTextPosition(JLabel.TOP);
        welcomeLabel.setFont(new Font(null, Font.PLAIN, 50));
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setPreferredSize(new Dimension(50, 50));
        welcomePanel.add(welcomeLabel);

        // 2'nd Panel
        startButton = new JButton("Start game");
        startButton.addActionListener(this);

        playersComboBox = new JComboBox<Integer>(numberOfPlayersArray);
        dicesComboBox = new JComboBox<Integer>(numberOfDicesArray);

        playersLabel = new JLabel();
        playersLabel.setText("Numbers of players:");

        dicesLabel = new JLabel();
        dicesLabel.setText("Numbers of dices:");

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.lightGray);
        buttonsPanel.setBounds(0, 500, 1280, 320);
        buttonsPanel.add(playersLabel);
        buttonsPanel.add(playersComboBox);
        buttonsPanel.add(dicesLabel);
        buttonsPanel.add(dicesComboBox);
        buttonsPanel.add(startButton);

        getFrame().add(buttonsPanel);
        getFrame().add(welcomePanel);
        getFrame().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {

            Integer numberOfPlayers = playersComboBox.getItemAt(playersComboBox.getSelectedIndex());
            Integer numberOfDices = dicesComboBox.getItemAt(dicesComboBox.getSelectedIndex());
            welcomePanel.setVisible(false);
            getFrame().dispose();
            Model model = new Model(numberOfPlayers, numberOfDices);
            GameController gameController = new GameController(numberOfDices);
            GameView gameView = new GameView();

            model.setView(gameView);
            gameController.setModel(model);
            gameView.setModel(model);
            gameView.setController(gameController);
        }
    }
}
