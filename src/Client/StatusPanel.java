package src.Client;

import src.DataType.TransmitData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StatusPanel extends JPanel {
    private Data data;
    public Property[] properties;
    public StatusPanel(Data data, Property[] properties) {
        super();
        this.properties = properties;
        this.data = data;
        //JPanel config
        this.setLayout(null);
        this.setBounds(900, 540, 300, 360);
        this.setPreferredSize(new Dimension(300, 360));
        this.setBackground(new Color(150, 200, 150));
        JButton purchaseButton = new JButton("purchase property");
        purchaseButton.setPreferredSize(new Dimension(250, 90));
        purchaseButton.setBounds(20, 200, 250, 90);
        purchaseButton.addActionListener(new StatusPanel.BuyPropertyListener());
        add(purchaseButton);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawing
        g.setColor(Color.BLACK);
        g.setFont(new Font("time new roman", Font.BOLD, 20));
        g.drawString("PlayerNumber : " + data.getPlayerNumber(), 10, 30);
        g.drawString("Balance : " + data.getBalance(), 10, 50);
    }

    class BuyPropertyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (properties[data.getLocation()].owner == 0 && properties[data.getLocation()].price <= data.getBalance()) {
                try {
                    System.out.println("\t\tClient " + data.getPlayerNumber() + ": buy property request sending");
                    Thread.sleep(100);
                    data.outputStream.writeObject(TransmitData.propertyPurchase(data.getLocation()));
                    System.out.println("\t\tClient " + data.getPlayerNumber() + ": buy property request sent");
                    data.setBalance(data.getBalance() - properties[data.getLocation()].price);
                    properties[data.getLocation()].owner = data.getPlayerNumber();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            } else if(properties[data.getLocation()].owner == data.getPlayerNumber()){
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": you cannot buy your own property");
            } else if(properties[data.getLocation()].owner != data.getPlayerNumber()){
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": you cannot buy other's property");
            } else {
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": don't have enough money");
            }
        }
    }
}
