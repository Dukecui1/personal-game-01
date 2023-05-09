package src.Client;

import src.DataType.TransmitData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {
    //JFrame config constant
    private final int WIDTH = 1200;
    private final int HEIGHT = 950;
    //player's data
    private Data data;
    //Component
    MapPanel mapPanel;

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
        DicePanel dicePanel = new DicePanel(data);
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
    public void actionPerformed(ActionEvent e) {
        repaint();
        //TODO problemA
//        if (data.isConnected) {
//            try {
//                System.out.println("\t\tClient: " + data.getPlayerNumber() + " waiting for messages");
//                TransmitData td = (TransmitData) data.inputStream.readObject();
//                System.out.println("\t\tClient: " + data.getPlayerNumber() + " messages received");
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            } catch (ClassNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
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
