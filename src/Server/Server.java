package src.Server;

import src.DataType.TransmitData;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    //IO list
    //store different players outputStream for broadcast clientNum : OutputStream
    private Map<Integer, ObjectOutputStream> playerOutputs;


    public Server() {
        super("Server");
        //Constant config
        clientNum = 0;
        //field config
        playerOutputs = new HashMap<>();
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
        private ObjectOutputStream outputStream;
        private ObjectInputStream inputStream;
        public HandleAClient(Socket socket, int clientNum) {
            this.socket = socket;
            this.clientNum = clientNum;
            try {
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                playerOutputs.put(clientNum, outputStream);
                System.out.println("Server : output stream for client number" + clientNum + " have been added " + playerOutputs.size());
                Thread.sleep(100);
                System.out.println("Server : player " + clientNum + " have been connected");
                outputStream.writeObject(clientNum);
                inputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        public void run() {
            //TODO calculate and communicate with clients
            while (true) {
                try {
                    System.out.println("Server : waiting for messages");
                    TransmitData td = (TransmitData) inputStream.readObject();
                    System.out.println("Server : messages have been received from " + clientNum);
                    ReadCommand(td);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        private void ReadCommand(TransmitData td) {
            //TODO read command
            int messageType = td.getMessageType();
            System.out.println("Server : messageType " + messageType);
            if (messageType == 1) {
                int roll1 = (int) (Math.random() * 6) + 1;
                int roll2 = (int) (Math.random() * 6) + 1;
                try {
                    Thread.sleep(100);
                    outputStream.writeObject(TransmitData.returnDiceResult(roll1, roll2));
                    System.out.println("Server : Dice result returned " + roll1 + " & " + roll2);
                    for (Map.Entry<Integer, ObjectOutputStream> e : playerOutputs.entrySet()) {
                        if (clientNum != e.getKey()) {
                            e.getValue().writeObject(TransmitData.locationUpdate(clientNum, roll1, roll2));
                            System.out.println("Server : movement returned " + (roll1 + roll2) + " clientNum " + clientNum);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if(messageType == 4){ //purchase broadcast
                try {
                    Thread.sleep(100);
                    System.out.println("Server : broadcast purchase for " + clientNum);
                    for (Map.Entry<Integer, ObjectOutputStream> e : playerOutputs.entrySet()) {
                        if (clientNum != e.getKey()) {
                            e.getValue().writeObject(TransmitData.propertyUpdate(clientNum, td.location));
                            System.out.println("Server : broadcast done " + " clientNum " + clientNum);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
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
