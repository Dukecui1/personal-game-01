package src.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel{
    Property[] properties;
    private Data data;

    public MapPanel(Data data) {
        super();
        this.data = data;
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
                properties[i] = new Property(i % 5, 90 * temp, 0, 400 + 20 * i);
            } else if (i <= 17) {
                properties[i] = new Property(i % 5, 810, 90 * temp, 300 + 20 * i);
            } else if (i <= 26) {
                properties[i] = new Property(i % 5, 810 - 90 * temp, 810, 500 + 20 * i);
            } else {
                properties[i] = new Property(i % 5, 0, 810 - 90 * temp, 200 + 20 * i);
            }
        }
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

        this.setBackground(Color.orange);
        //property painting-------------------
        for (Property p : properties) {
            p.draw(g);
        }
    }
}
