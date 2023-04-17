package src;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

public class Server extends JFrame implements Runnable {
    //Connection config constant
    private final int SOCKET_NUMBER = 9898;
    //JFrame config constant
    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    //Component
    private JTextArea ta;


    public Server() {
        super("Server");
        //COMPONENT configuration
        createJText();
        //FRAME configuration
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setResizable(false);
        //Server thread
        Thread t = new Thread(this);
        t.start();
    }

    private void createJText() {
        ta = new JTextArea();
        this.add(ta);
        this.setSize(WIDTH,HEIGHT);
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(SOCKET_NUMBER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ta.append("ChatServer started at "
                + new Date() + '\n');
    }
}
