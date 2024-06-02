package Model;

/**
 * Represents an obstacle in the game.
 */
public class Obstacle {

    private Position position;

    /**
     * Constructs an Obstacle at the specified coordinates.
     *
     * @param x the x-coordinate of the obstacle
     * @param y the y-coordinate of the obstacle
     */
    public Obstacle(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }
}
