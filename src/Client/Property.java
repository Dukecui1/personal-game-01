package src.Client;

import java.awt.*;
import java.util.*;
import java.util.List;

//property of client
public class Property {
    public int owner;
    public int x, y; //position of the property
    public List<Integer> players; //contains player number on this property
    public Property(int owner, int x, int y) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.players = new ArrayList<Integer>();
    }

    public void playerArrived(int playerNumber) {
        players.add(playerNumber);
    }
    public void playerLeft(int playerNumber) {
        int target = -1;
        for (int i = 0 ; i < players.size(); i++) {
            if (players.get(i) == playerNumber) {
                target = i;
                break;
            }
        }
        if (target != -1) {
            players.remove(target);
        }
    }
    public void draw(Graphics g) {
        //draw property
        g.setColor(new Color(255, 100 , 100));
        g.fillRect(x, y, 90, 90);
        g.setColor(new Color(100, 100 , 100));
        g.drawRect(x, y, 90, 90);
        //draw player
        if (!players.isEmpty()) { //TODO different players will have problem in future update
            g.setColor(new Color(0,0,250));
            g.fillOval(x + 30, y + 30, 30, 30);
        }

    }
}
