package PowerUP;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class PowerUpManager {
    private List<PowerUp> powerUps;

    public PowerUpManager() {
        powerUps = new ArrayList<>();
    }

    public void addPowerUp(PowerUp powerUp) {
        powerUps.add(powerUp);
    }

    public void removePowerUp(PowerUp powerUp) {
        powerUps.remove(powerUp);
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void draw(Graphics g) {
        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g);
        }
    }
}
