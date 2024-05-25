package Model;

public class Obstacle {
    private Position position;

    public Obstacle(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }
}
