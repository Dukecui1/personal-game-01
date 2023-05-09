package src.DataType;

import java.io.Serializable;

public class TransmitData implements Serializable {
    /* 1 : roll dice
       2 : return dice results
       3 : location update
       4 : property purchase
       5 : property update
    to be continued
    * */
    private int messageType;
    public int dice1;
    public int dice2;
    public int playerNum;
    public int location; //0 - 35
    public static TransmitData rollDice() {
        TransmitData td = new TransmitData();
        td.setMessageType(1);
        return td;
    }
    public static TransmitData returnDiceResult(int dice1, int dice2) {
        TransmitData td = new TransmitData();
        td.setMessageType(2);
        td.dice1 = dice1;
        td.dice2 = dice2;
        return td;
    }
    public static TransmitData locationUpdate(int playNum, int dice1, int dice2) {
        TransmitData td = new TransmitData();
        td.setMessageType(3);
        td.dice1 = dice1;
        td.dice2 = dice2;
        td.playerNum = playNum;
        return td;
    }
    public static TransmitData propertyPurchase(int location) {
        TransmitData td = new TransmitData();
        td.setMessageType(4);
        td.location = location;
        return td;
    }
    public static TransmitData propertyUpdate(int playerNum, int location) {
        TransmitData td = new TransmitData();
        td.setMessageType(5);
        td.location = location;
        td.playerNum = playerNum;
        return td;
    }
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
