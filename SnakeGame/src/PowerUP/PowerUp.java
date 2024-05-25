package PowerUP;

import Model.Position;
import java.awt.Color;
import java.awt.Graphics;

public abstract class PowerUp {
    protected Position position;

    public PowerUp(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public abstract void applyEffect();

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(position.getX() * 10, position.getY() * 10, 10, 10);
    }
}
