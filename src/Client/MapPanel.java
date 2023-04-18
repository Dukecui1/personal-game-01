package src.Client;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    Property[] properties;

    public MapPanel() {
        super();
        //Drawing configuration
        this.setLayout(null);
        this.setPreferredSize(new Dimension(900, 900));
        this.setBackground(new Color(150, 100, 50));
        //field initialization
        properties = new Property[36];

        //component drawing
        drawProperties();
        createDicePanel();
        createStatusPanel();
    }

    private void createStatusPanel() {
        StatusPanel statusPanel = new StatusPanel();
        add(statusPanel);
    }

    private void createDicePanel() {
        DicePanel dicePanel = new DicePanel();
        add(dicePanel);

    }

    private void drawProperties() {
        for (int i = 0; i < properties.length; i++) {
            int temp = i % 9;
            if (i <= 8) {
                properties[i] = new Property(i % 5, 90 * temp, 0);
            } else if (i <= 17) {
                properties[i] = new Property(i % 5, 810, 90 * temp);
            } else if (i <= 26) {
                properties[i] = new Property(i % 5, 810 - 90 * temp, 810);
            } else {
                properties[i] = new Property(i % 5, 0, 810 - 90 * temp);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        for (Property p : properties) {
            p.draw(g);
        }

    }
}
