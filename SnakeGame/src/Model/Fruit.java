package Model;

import java.awt.Color;
import java.awt.Graphics;

public class Fruit {
    private Position position;

    public Fruit() {
        this.position = new Position(10, 10); // Default position
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.getX() * 10, position.getY() * 10, 10, 10);
    }
}
