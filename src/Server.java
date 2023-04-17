package src;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;

public class Server extends JFrame implements Runnable {
    //Connection config constant
    private final int SOCKET_NUMBER = 9898;
    //JFrame config constant
    private final int WIDTH = 400;
    private final int HEIGHT = 300;


    public Server() {
        super("Server");
        //FRAME configuration
        //setPreferredSize(new Dimension(400, 300));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        try {
            ServerSocket serverSocket = new ServerSocket(SOCKET_NUMBER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
