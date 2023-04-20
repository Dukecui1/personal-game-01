package src.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
        DicePanel dicePanel = new DicePanel();
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
    }

    class OpenConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket socket = new Socket("localhost", 9898);
                System.out.println("connected");
                data.activate();

                //TODO receive client number
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

}
