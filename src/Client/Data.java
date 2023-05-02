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
    public boolean locationUpdate;
    private int location;// from 0 to 35 represent which property the player is at
    private int previousLocation;
    //IO
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;

    public void setLocation(int location) {
        previousLocation = this.location;
        this.location = location;
        locationUpdate = true;
    }

    public int getLocation() {
        return location;
    }

    public int getPreviousLocation() {
        return previousLocation;
    }

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
        setLocation(0);
    }

    public Data() {
        balance = -999;
        playerNumber = -1;
        location = -1;
        locationUpdate = false;
    }
}
