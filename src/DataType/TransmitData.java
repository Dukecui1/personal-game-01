package src.DataType;

import java.io.Serializable;

public class TransmitData implements Serializable {
    /* 1 : roll dice
       2 : return dice results
    to be continued
    * */
    private int messageType;
    public int dice1;
    public int dice2;
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
    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
}
