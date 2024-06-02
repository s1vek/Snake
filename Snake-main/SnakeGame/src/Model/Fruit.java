package Model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a fruit in the game.
 */
public class Fruit {

    private Position position;

    /**
     * Constructs a Fruit with the specified position.
     *
     * @param position the position of the fruit
     */
    public Fruit(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Draws the fruit on the game board.
     *
     * @param g the graphics context
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(position.getX() * 10, position.getY() * 10, 10, 10);
    }
}
