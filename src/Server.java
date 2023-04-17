package src;

import javax.swing.*;
import java.awt.*;

public class Server extends JFrame implements Runnable {

    public Server() {
        super("Server");
        //FRAME configuration
        setPreferredSize(new Dimension(400, 300));
        setVisible(true);
        setResizable(false);
        //COMPONENT configuration
        createJText();
    }

    private void createJText() {
        JTextArea ta = new JTextArea();
        this.add(ta);
    }

    @Override
    public void run() {

    }
}
