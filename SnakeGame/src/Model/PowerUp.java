package Model;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a power-up in the game.
 */
public class PowerUp {
    private Position position;
    private PowerUpType type;

    /**
     * Constructs a PowerUp with the specified position and type.
     *
     * @param position the position of the power-up
     * @param type the type of the power-up
     */
    public PowerUp(Position position, PowerUpType type) {
        this.position = position;
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public PowerUpType getType() {
        return type;
    }

    /**
     * Draws the power-up on the game board.
     *
     * @param g the graphics context
     */
    public void draw(Graphics g) {
        switch (type) {
            case SPEED:
                g.setColor(Color.BLUE);
                break;
            case DOUBLE_SCORE:
                g.setColor(Color.GREEN);
                break;
            case EXTRA_FRUITS:
                g.setColor(Color.YELLOW);
                break;
        }
        g.fillOval(position.getX() * 10, position.getY() * 10, 10, 10);
    }

    /**
     * Applies the effect of the power-up to the game.
     *
     * @param game the game instance
     */
    public void applyEffect(Game game) {
        switch (type) {
            case SPEED:
                game.getSnake().increaseSpeed();
                break;
            case DOUBLE_SCORE:
                game.setDoubleScore(true);
                break;
            case EXTRA_FRUITS:
                game.spawnAdditionalFruits();
                break;
        }
    }
}
