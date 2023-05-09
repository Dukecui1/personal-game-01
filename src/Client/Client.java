package src.Client;

import src.DataType.TransmitData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame implements Runnable, ActionListener {
    //JFrame config constant
    private final int WIDTH = 1200;
    private final int HEIGHT = 950;
    //player's data
    private Data data;
    //Component
    MapPanel mapPanel;
    DicePanel dicePanel;

    public Client() {
        super("Client");
        //DATA Configuration
        data = new Data();
        //COMPONENT Configuration
        createMenu();
        createMapPanel();
        createStatusPanel();
        createDicePanel();
        //FRAME Configuration
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        //main game thread
        Timer timer = new Timer(100, this);
        timer.start();
        Thread t = new Thread(this);
        t.start();
    }

    private void createMapPanel() {
        mapPanel = new MapPanel(data);
        add(mapPanel);
    }
    private void createStatusPanel() {
        StatusPanel statusPanel = new StatusPanel(data);
        add(statusPanel);
    }
    private void createDicePanel() {
        dicePanel = new DicePanel(data);
        add(dicePanel);
    }

    private void createMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu actionMenu = new JMenu("Action");
        JMenuItem connectItem = new JMenuItem("Connect");
        connectItem.addActionListener(new OpenConnectionListener());
        actionMenu.add(connectItem);
        jMenuBar.add(actionMenu);
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (data.isConnected) break;
        }
        while (data.isConnected) {
            try {
                System.out.println("\t\tClient: " + data.getPlayerNumber() + " waiting for messages");
                TransmitData td = (TransmitData) data.inputStream.readObject();
                System.out.println("\t\tClient: " + data.getPlayerNumber() + " messages received");
                readCommand(td);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void readCommand(TransmitData td) {
        switch (td.getMessageType()) {
            case 2 :
                dicePanel.setDicePics(td.dice1, td.dice2);
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": dice result received " + td.dice1 + " & " + td.dice2);
                data.setLocation((data.getLocation() + td.dice1 + td.dice2) % 36);
                System.out.println("\t\t\t Local Client " + data.getPlayerNumber() + ": now arrived at " + data.getLocation());
                break;
            case 3 :
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": dice result received from" + td.playerNum + " "+ td.dice1 + " & " + td.dice2);
                data.setOtherLocation((data.getOtherLocation() + td.dice1 + td.dice2) % 36);
                System.out.println("\t\t\t Local Client " + data.getPlayerNumber() + ": player " + td.playerNum +" now arrived at " + data.getLocation());
                break;
            default :
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    class OpenConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Socket socket;
            try {
                socket = new Socket("localhost", 9898);
                //store input and output stream into data
                data.inputStream = new ObjectInputStream(socket.getInputStream());
                data.outputStream = new ObjectOutputStream(socket.getOutputStream());
                data.setPlayerNumber((int)data.inputStream.readObject());
                System.out.println("\t\tClient " + data.getPlayerNumber() + ": connected");
                data.activate();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

}
