package src.Client;

import src.DataType.TransmitData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DicePanel extends JPanel {
    //players data
    private Data data;
    //dice pictures
    ImageIcon[] dicePics;
    //components
    JLabel diceLabel1;
    JLabel diceLabel2;

    public DicePanel(Data data) {
        super();
        //pics config
        dicePics = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            String src = "src/Images/die" + (i + 1) + ".png";
            dicePics[i] = new ImageIcon(src);
        }
        //parameter config
        this.data = data;
        //Panel config
        this.setLayout(null);
        this.setBounds(900, 0, 300, 540);
        this.setPreferredSize(new Dimension(300, 540));
        this.setBackground(new Color(50, 200, 250));
        //component config
        diceLabel1 = new JLabel(new ImageIcon("src/Images/die1.png"));
        diceLabel2 = new JLabel(new ImageIcon("src/Images/die2.png"));
        diceLabel1.setBounds(50, 0, 200, 200);
        diceLabel2.setBounds(50, 200, 200, 200);
        add(diceLabel1);
        add(diceLabel2);
        JButton rollButton = new JButton("Roll Dice");
        rollButton.setPreferredSize(new Dimension(80, 30));
        rollButton.setBounds(120, 500, 80, 30);
        rollButton.addActionListener(new RollDiceListener());
        add(rollButton);

    }
    public void setDicePics(int d1, int d2) {
        diceLabel1.setIcon(dicePics[d1 - 1]);
        diceLabel2.setIcon(dicePics[d2 - 1]);
    }
    class RollDiceListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": roll dice request sending");
                Thread.sleep(100);
                data.outputStream.writeObject(TransmitData.rollDice());
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": roll dice request sent");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
