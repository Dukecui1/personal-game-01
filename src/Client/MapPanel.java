package src.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel{
    public Property[] properties;
    private Data data;
    private boolean lost;

    public MapPanel(Data data) {
        super();
        this.data = data;
        lost = false;
        //Drawing configuration
        this.setBounds(0, 0, 900, 900);
        //this.setPreferredSize(new Dimension(900, 900));
        this.setBackground(new Color(150, 100, 50));
        //field initialization
        properties = new Property[36];
        //component drawing
        initializeProperties();
    }

    private void initializeProperties() {
        for (int i = 0; i < properties.length; i++) {
            int temp = i % 9;
            if (i <= 8) {
                properties[i] = new Property(90 * temp, 0, 400 + 20 * i);
            } else if (i <= 17) {
                properties[i] = new Property(810, 90 * temp, 300 + 20 * i);
            } else if (i <= 26) {
                properties[i] = new Property(810 - 90 * temp, 810, 500 + 20 * i);
            } else {
                properties[i] = new Property(0, 810 - 90 * temp, 200 + 20 * i);
            }
        }
    }
    public void loserNotice() {
        lost = true;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //data update----------------------
        //remove the player from previous location(property)
        if (data.locationUpdate) {
            if (data.getPreviousLocation() != -1) {
                properties[data.getPreviousLocation()].playerLeft(data.getPlayerNumber());
                System.out.println("\t\t\t Local Client " + data.getPlayerNumber() + ": left " + data.getPreviousLocation());
            }
            //add player at next location(property)
            if (data.getLocation() != -1) {
                properties[data.getLocation()].playerArrived(data.getPlayerNumber());
                System.out.println("\t\t\t Local Client " + data.getPlayerNumber() + ": arrive at " + data.getLocation());
            }
            data.locationUpdate = false;
        }
        if (data.otherLocationUpdate) {
            if (data.getOtherPreviousLocation() != -1) {
                properties[data.getOtherPreviousLocation()].playerLeft(3 - data.getPlayerNumber());
                System.out.println("\t\t\t Local Client " + data.getPlayerNumber() + ":other player left " + data.getOtherPreviousLocation());
            }
            if (data.getOtherLocation() != -1) {
                properties[data.getOtherLocation()].playerArrived(3 - data.getPlayerNumber());
                System.out.println("\t\t\t Local Client " + data.getPlayerNumber() + ": other player arrives at " + data.getOtherLocation());
            }
            data.otherLocationUpdate = false;
        }

        this.setBackground(Color.orange);
        //property painting-------------------
        for (Property p : properties) {
            p.draw(g);
        }
        //Lost detection
        if (lost) {
            g.setColor(Color.RED);
            this.setBackground(Color.black);
            g.setFont(new Font("time new roman", Font.BOLD, 100));
            g.drawString("you lose", 300, 300);
        }
    }
}
