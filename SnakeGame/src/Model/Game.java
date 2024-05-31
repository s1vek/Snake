package Model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Represents the game logic and state.
 */
public class Game {
    private Snake snake;
    private List<PowerUp> powerUps;
    private List<Fruit> fruits;
    private boolean gameOver;
    private List<Obstacle> obstacles;
    private String difficulty;
    private int score;
    private Random random;
    private int fruitCounter;
    private String mapFilePath;
    private boolean doubleScore;
    private int highScore;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 80;
    private static final int UI_MARGIN = 5;

    /**
     * Constructs a Game instance with a specified difficulty.
     *
     * @param difficulty the difficulty level of the game
     */
    public Game(String difficulty) {
        this.difficulty = difficulty;
        snake = new Snake();
        powerUps = new ArrayList<>();
        fruits = new ArrayList<>();
        gameOver = false;
        obstacles = new ArrayList<>();
        random = new Random();
        score = 0;
        fruitCounter = 0;
        doubleScore = false; // Initialize double score mode as false
        generateObstacles();
        highScore = loadHighScore();
        spawnFruit();
    }

    /**
     * Constructs a Game instance with a specified difficulty and map file path.
     *
     * @param difficulty the difficulty level of the game
     * @param mapFilePath the file path of the map
     */
    public Game(String difficulty, String mapFilePath) {
        this(difficulty);
        this.mapFilePath = mapFilePath;
        loadMap(mapFilePath);
    }

    public Snake getSnake() {
        return snake;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHighScore() {
        return highScore;
    }

    /**
     * Updates the game state.
     */
    public void update() {
        if (!gameOver) {
            snake.move();
            checkCollisions();
            checkFruits();
            applyPowerUps();
            if (score > highScore) {
                highScore = score;
                saveHighScore();
            }
        }
    }

    /**
     * Generates obstacles based on the difficulty level.
     */
    private void generateObstacles() {
        int obstacleCount = 0;
        switch (difficulty) {
            case "easy":
                obstacleCount = 50;
                break;
            case "medium":
                obstacleCount = 65;
                break;
            case "hard":
                obstacleCount = 90;
                break;
        }
        for (int i = 0; i < obstacleCount; i++) {
            Position position = generateValidPosition();
            obstacles.add(new Obstacle(position.getX(), position.getY()));
        }
    }

    /**
     * Generates a valid position that does not collide with obstacles or the snake.
     *
     * @return a valid position
     */
    private Position generateValidPosition() {
        Position newPos;
        boolean validPosition;
        do {
            validPosition = true;
            int x = random.nextInt(WIDTH - 2 * UI_MARGIN) + UI_MARGIN;
            int y = random.nextInt(HEIGHT - 2 * UI_MARGIN) + UI_MARGIN;
            newPos = new Position(x, y);

            for (Obstacle obstacle : obstacles) {
                if (obstacle.getPosition().equals(newPos)) {
                    validPosition = false;
                    break;
                }
            }

            for (Position pos : snake.getBody()) {
                if (pos.equals(newPos)) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);
        return newPos;
    }

    /**
     * Spawns a new fruit at a valid position.
     */
    private void spawnFruit() {
        Position position = generateValidPosition();
        fruits.add(new Fruit(position));
        if (++fruitCounter % 3 == 0) {
            spawnPowerUp();
        }
    }

    /**
     * Spawns additional fruits at valid positions.
     */
    public void spawnAdditionalFruits() {
        for (int i = 0; i < 3; i++) {
            spawnFruit();
        }
    }

    /**
     * Spawns a power-up at a valid position.
     */
    private void spawnPowerUp() {
        Position position = generateValidPosition();
        PowerUpType[] types = PowerUpType.values();
        PowerUpType type = types[random.nextInt(types.length)];
        PowerUp powerUp = new PowerUp(position, type);
        powerUps.add(powerUp);
    }

    /**
     * Checks for collisions between the snake and obstacles or the game boundaries.
     */
    private void checkCollisions() {
        Position head = snake.getBody().getFirst();
        boolean collisionDetected = false;

        if (head.getX() < 0 || head.getX() >= WIDTH || head.getY() < 0 || head.getY() >= HEIGHT) {
            collisionDetected = true;
        }

        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.getX() == snake.getBody().get(i).getX() && head.getY() == snake.getBody().get(i).getY()) {
                collisionDetected = true;
            }
        }

        for (Obstacle obstacle : obstacles) {
            if (head.getX() == obstacle.getPosition().getX() && head.getY() == obstacle.getPosition().getY()) {
                collisionDetected = true;
            }
        }

        if (collisionDetected) {
            if (!snake.isInvincible()) {
                snake.setLives(snake.getLives() - 1);
                if (snake.getLives() <= 0) {
                    gameOver = true;
                }
            }
        }
    }

    /**
     * Checks if the snake's head has collided with any fruits and handles the collision.
     */
    private void checkFruits() {
        Position head = snake.getBody().getFirst();
        List<Fruit> fruitsCopy = new ArrayList<>(fruits); // Make a copy of the fruits list

        for (Fruit fruit : fruitsCopy) {
            if (head.getX() == fruit.getPosition().getX() && head.getY() == fruit.getPosition().getY()) {
                System.out.println("Fruit eaten at: " + fruit.getPosition().getX() + ", " + fruit.getPosition().getY());
                fruits.remove(fruit); // Remove from the original list
                snake.grow();
                score += doubleScore ? 20 : 10; // Double the score if doubleScore is true
                spawnFruit(); // Spawn a new fruit after eating one
            }
        }
    }

    /**
     * Applies the effects of any power-ups that the snake has collected.
     */
    private void applyPowerUps() {
        Position head = snake.getBody().getFirst();
        List<PowerUp> collectedPowerUps = new ArrayList<>();

        Iterator<PowerUp> iterator = powerUps.iterator();
        while (iterator.hasNext()) {
            PowerUp powerUp = iterator.next();
            if (head.getX() == powerUp.getPosition().getX() && head.getY() == powerUp.getPosition().getY()) {
                System.out.println("Power-up collected: " + powerUp.getType());
                if (powerUp.getType() == PowerUpType.EXTRA_FRUITS) {
                    spawnAdditionalFruits();
                } else {
                    powerUp.applyEffect(this);
                }
                collectedPowerUps.add(powerUp);
            }
        }

        // Remove collected power-ups
        powerUps.removeAll(collectedPowerUps);
    }

    /**
     * Resets the game state to start a new game.
     */
    public void reset() {
        score = 0; // Reset score to zero
        snake = new Snake();
        powerUps = new ArrayList<>();
        fruits = new ArrayList<>();
        gameOver = false;
        obstacles.clear();
        if (mapFilePath != null) {
            loadMap(mapFilePath);
        } else {
            generateObstacles();
        }
        spawnFruit();
    }

    /**
     * Draws the obstacles on the game board.
     *
     * @param g the graphics context
     */
    public void drawObstacles(Graphics g) {
        g.setColor(Color.GRAY);
        for (Obstacle obstacle : obstacles) {
            g.fillRect(obstacle.getPosition().getX() * 10, obstacle.getPosition().getY() * 10, 10, 10);
        }
    }

    /**
     * Draws the power-ups on the game board.
     *
     * @param g the graphics context
     */
    public void drawPowerUps(Graphics g) {
        for (PowerUp powerUp : powerUps) {
            powerUp.draw(g);
        }
    }

    /**
     * Draws the fruits on the game board.
     *
     * @param g the graphics context
     */
    public void drawFruits(Graphics g) {
        for (Fruit fruit : fruits) {
            fruit.draw(g);
        }
    }

    /**
     * Loads the map from a file.
     *
     * @param mapFilePath the file path of the map
     */
    private void loadMap(String mapFilePath) {
        obstacles.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(mapFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                obstacles.add(new Obstacle(x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleScore(boolean b) {

    }

    /**
     * Loads the high score from a file.
     *
     * @return the high score
     */
    private int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Saves the high score to a file.
     */
    private void saveHighScore() {
        try (FileWriter writer = new FileWriter("highscore.txt")) {
            writer.write(Integer.toString(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

