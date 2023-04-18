package src.Client;
//players data
public class Data {
    //constants
    private final int INITIAL_MONEY = 5000;
    //distribution
    private int balance;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Data() {
        balance = INITIAL_MONEY;
    }
}
