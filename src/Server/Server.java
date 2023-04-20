package src.Server;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends JFrame implements Runnable {
    //Connection config constant
    private final int SOCKET_NUMBER = 9898;
    //JFrame config constant
    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    //Component
    private JTextArea ta;
    //Gameplay constant
    private int clientNum;


    public Server() {
        super("Server");
        //Constant config
        clientNum = 0;
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
    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket
        private int clientNum;
        public HandleAClient(Socket socket, int clientNum) {
            this.socket = socket;
            this.clientNum = clientNum;
            try {
                DataOutputStream outputFromClient = new DataOutputStream(socket.getOutputStream());
                outputFromClient.writeInt(clientNum);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void run() {
            //TODO calculate and communicate with clients

        }
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(SOCKET_NUMBER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ta.append("ChatServer started at " + new Date() + '\n');
        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            clientNum++;
            ta.append("Starting thread for client " + clientNum + " at " + new Date() + '\n');
            new Thread(new HandleAClient(socket, clientNum)).start();
        }
    }
}
