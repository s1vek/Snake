package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Represents the snake in the game.
 */
public class Snake {
    private LinkedList<Position> body;
    private Directions direction;
    private int speed;
    private boolean invincible;
    private int lives;

    /**
     * Constructs a Snake with an initial body and direction.
     */
    public Snake() {
        body = new LinkedList<>();
        body.add(new Position(10, 10));
        body.add(new Position(9, 10));
        body.add(new Position(8, 10));
        body.add(new Position(7, 10));
        body.add(new Position(6, 10));
        direction = Directions.RIGHT;
        speed = 1;
        invincible = false;
        lives = 1;
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    /**
     * Moves the snake in its current direction.
     */
    public void move() {
        Position head = body.getFirst();
        Position newHead = null;

        switch (direction) {
            case UP:
                newHead = new Position(head.getX(), head.getY() - speed);
                break;
            case DOWN:
                newHead = new Position(head.getX(), head.getY() + speed);
                break;
            case LEFT:
                newHead = new Position(head.getX() - speed, head.getY());
                break;
            case RIGHT:
                newHead = new Position(head.getX() + speed, head.getY());
                break;
        }

        body.addFirst(newHead);
        body.removeLast();
    }

    /**
     * Grows the snake by adding a new segment to its body.
     */
    public void grow() {
        body.addLast(body.getLast());
    }

    /**
     * Shrinks the snake by removing the last segment of its body.
     */
    public void shrink() {
        if (body.size() > 1) {
            body.removeLast();
        }
    }

    /**
     * Increases the snake's speed.
     */
    public void increaseSpeed() {
        speed++;
    }

    /**
     * Adds a life to the snake.
     */
    public void addLife() {
        lives++;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * Draws the snake on the game board.
     *
     * @param g the graphics context
     * @param snakeColor the color of the snake
     */
    public void draw(Graphics g, Color snakeColor) {
        g.setColor(snakeColor);
        for (Position position : body) {
            g.fillRect(position.getX() * 10, position.getY() * 10, 10, 10);
        }
    }
}
