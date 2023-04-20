package src.Client;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private Data data;
    public StatusPanel(Data data) {
        super();
        this.data = data;
        //JPanel config
        this.setBounds(900, 540, 300, 360);
        this.setPreferredSize(new Dimension(300, 360));
        this.setBackground(new Color(150, 200, 150));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 20));
        g.drawString("Balance : " + data.getBalance(), 10, 30);
    }
}
