package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Position> body;
    private Directions direction;

    public Snake() {
        body = new LinkedList<>();
        body.add(new Position(10, 10));
        direction = Directions.RIGHT;
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

    public void move() {
        Position head = body.getFirst();
        Position newHead = null;

        switch (direction) {
            case UP:
                newHead = new Position(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                newHead = new Position(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                newHead = new Position(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                newHead = new Position(head.getX() + 1, head.getY());
                break;
        }

        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow() {
        body.addLast(body.getLast());
    }

    public void draw(Graphics g, Color snakeColor) {
        g.setColor(snakeColor);
        for (Position position : body) {
            g.fillRect(position.getX() * 10, position.getY() * 10, 10, 10);
        }
    }
}
