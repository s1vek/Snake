package UnitTest;

import Model.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class TestsTest {
    private Snake snake;
    private Game game;
    private PowerUp speedPowerUp;

    /**
     * Setup method to initialize objects before each test.
     */
    @Before
    public void setUp() {
        snake = new Snake();
        game = new Game("easy");
        speedPowerUp = new PowerUp(new Position(5, 5), PowerUpType.SPEED);
    }

    /**
     * Test to verify the creation of a Position object.
     */
    @Test
    public void testPositionCreation() {
        Position position = new Position(5, 10);
        assertEquals(5, position.getX());
        assertEquals(10, position.getY());
    }

    /**
     * Test to verify the initial body length of the snake.
     */
    @Test
    public void testInitialSnakeBody() {
        LinkedList<Position> body = snake.getBody();
        assertEquals(5, body.size());
    }

    /**
     * Test to verify the movement of the snake.
     */
    @Test
    public void testSnakeMovement() {
        snake.setDirection(Directions.RIGHT);
        Position initialHead = snake.getBody().getFirst();
        snake.move();
        Position newHead = snake.getBody().getFirst();
        assertEquals(new Position(initialHead.getX() + 1, initialHead.getY()), newHead);
    }

    /**
     * Test to verify the initial state of the game.
     */
    @Test
    public void testInitialGameState() {
        assertNotNull(game.getSnake());
        assertFalse(game.isGameOver());
        assertEquals(0, game.getScore());
    }

    /**
     * Test to verify the spawning of fruits in the game.
     */
    @Test
    public void testSpawnFruit() {
        int initialFruitCount = game.getFruits().size();
        game.spawnFruit();
        assertEquals(initialFruitCount + 1, game.getFruits().size());
    }

    /**
     * Test to verify the type of power-up.
     */
    @Test
    public void testPowerUpType() {
        assertEquals(PowerUpType.SPEED, speedPowerUp.getType());
    }

    /**
     * Test to verify the shrinking of the snake.
     */
    @Test
    public void testSnakeShrink() {
        int initialSize = snake.getBody().size();
        snake.shrink();
        assertEquals(initialSize - 1, snake.getBody().size());
    }

    /**
     * Test to verify the creation of an obstacle.
     */
    @Test
    public void testObstacleCreation() {
        Obstacle obstacle = new Obstacle(5, 10);
        assertNotNull(obstacle.getPosition());
        assertEquals(5, obstacle.getPosition().getX());
        assertEquals(10, obstacle.getPosition().getY());
    }
}