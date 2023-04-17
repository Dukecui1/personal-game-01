package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends JFrame implements Runnable {

    public Client() {
        super("Client");
        //FRAME Configuration
        setPreferredSize(new Dimension(1400, 900));
        setVisible(true);
        setResizable(false);
        //COMPONENT Configuration
        createMenu();
    }

    private void createMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu actionMenu = new JMenu("Action");
        JMenuItem connectItem = new JMenuItem("Connect");
        connectItem.addActionListener(new OpenConnectionListener());
    }
    class OpenConnectionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO

        }
    }

    @Override
    public void run() {

    }

}
