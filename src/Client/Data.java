package src.Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//players data
public class Data {
    //constants
    private final int INITIAL_MONEY = 5000;
    //distribution
    private int balance;
    private int playerNumber;
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;


    public int getBalance() {
        return balance;
    }
    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void activate() {
        balance = INITIAL_MONEY;
    }

    public Data() {
        balance = -999;
        playerNumber = -1;
    }
}
