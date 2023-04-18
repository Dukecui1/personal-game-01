package src.Client;

import java.awt.*;

//property of client
public class Property {
    int owner;
    int x, y; //position of the property
    public Property(int owner, int x, int y) {
        this.owner = owner;
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(255, 100 , 100));
        g.fillRect(x, y, 90, 90);
        g.setColor(new Color(100, 100 , 100));
        g.drawRect(x, y, 90, 90);
    }
}
