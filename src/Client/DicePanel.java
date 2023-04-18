package src.Client;

import javax.swing.*;
import java.awt.*;

public class DicePanel extends JPanel {
    public DicePanel() {
        super();
        this.setBounds(90, 90, 720, 360);
        this.setPreferredSize(new Dimension(720, 360));
        this.setBackground(new Color(50, 200, 250));
    }
}
