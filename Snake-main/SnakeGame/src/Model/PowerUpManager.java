package Model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of PowerUp objects in the game.
 */
public class PowerUpManager {

    private List<PowerUp> powerUps;

    /**
     * Constructs a PowerUpManager with an empty list of power-ups.
     */
    public PowerUpManager() {
        powerUps = new ArrayList<>();
    }

    /**
     * Adds a power-up to the manager.
     * @param powerUp the power-up to add
     */
    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }

    /**
     * Removes a power-up from the manager.
     * @param powerUp the power-up to remove
     */
    public void removePowerUp(PowerUp powerUp) {
        powerUps.remove(powerUp);
    }

    /**
     * Returns the list of power-ups managed by this manager.
     * @return the list of power-ups
     */
    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    /**
     * Draws all the power-ups using the provided Graphics context.
     * @param g the Graphics context to use for drawing
     */
    public void draw(Graphics g) {
        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g);
        }
    }
}