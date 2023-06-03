import java.awt.Container;
import javax.swing.*;

public class Dice {
    private int diceNumber;
    private int diceValue;
    private JLabel diceLabel;

    Icon[] scoreIcons = {   
        new ImageIcon("images/dice1.png"),
        new ImageIcon("images/dice2.png"),
        new ImageIcon("images/dice3.png"),
        new ImageIcon("images/dice4.png"),
        new ImageIcon("images/dice5.png"),
        new ImageIcon("images/dice6.png")
    };

    public Dice() {
        this.diceValue = 1;
        this.diceNumber = 0;
        this.diceLabel = new JLabel();
    }

    public void selectIcon() {
        int value = getDiceValue();
        this.diceLabel.setVisible(true);
        this.diceLabel.setIcon(scoreIcons[value - 1]);
    }

    public void hideIcon() {
        this.diceLabel.setVisible(false);
    }

    public int getDiceValue() {
        return this.diceValue;
    }

    public void setDiceValue(int value) {
        this.diceValue = value;
    }

    public void addDiceLabelToContainer(Container container) {
        container.add(this.diceLabel);
    }
}
