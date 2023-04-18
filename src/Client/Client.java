package src.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Client extends JFrame implements Runnable {
    //JFrame config constant
    private final int WIDTH = 900;
    private final int HEIGHT = 950;

    public Client() {
        super("Client");
        //COMPONENT Configuration
        createMenu();
        createMapPanel();
        //FRAME Configuration
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    private void createMapPanel() {
        MapPanel mapPanel = new MapPanel();
        add(mapPanel);
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
    class OpenConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket socket = new Socket("localhost", 9898);
                System.out.println("connected");
                //TODO receive client number
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    @Override
    public void run() {
        //TODO receive and send message, add sleep() to avoid over polling
    }

}
