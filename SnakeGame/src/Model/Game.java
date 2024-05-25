package Model;

import PowerUP.PowerUp;
import PowerUP.PowerUpManager;

import java.awt.Graphics;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents the game logic for the Snake game.
 */
public class Game {
    private Snake snake;
    private PowerUpManager powerUpManager;
    private Fruit fruit;
    private boolean gameOver;
    private List<Obstacle> obstacles;
    private String difficulty;
    private int score;
    private Random random;

    private static final int WIDTH = 100;  // Updated width to fit larger window
    private static final int HEIGHT = 80;  // Updated height to fit larger window
    private static final int UI_MARGIN = 5; // Adjust this value as needed

    /**
     * Constructor to initialize the game with the specified difficulty.
     *
     * @param difficulty The difficulty level of the game.
     */
    public Game(String difficulty) {
        this.difficulty = difficulty;
        snake = new Snake();
        powerUpManager = new PowerUpManager();
        fruit = new Fruit();
        gameOver = false;
        obstacles = new ArrayList<>();
        random = new Random();
        score = 0;
        generateObstacles();
        spawnFruit();
    }

    /**
     * Constructor to initialize the game with the specified difficulty and map file path.
     *
     * @param difficulty The difficulty level of the game.
     * @param mapFilePath The file path of the custom map.
     */
    public Game(String difficulty, String mapFilePath) {
        this(difficulty);
        loadMap(mapFilePath);
    }

    public Snake getSnake() {
        return snake;
    }

    public PowerUpManager getPowerUpManager() {
        return powerUpManager;
    }

    public Fruit getFruit() {
        return fruit;
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

    /**
     * Updates the game state.
     */
    public void update() {
        if (!gameOver) {
            snake.move();
            checkCollisions();
            checkFruit();
            applyPowerUps();
        }
    }

    /**
     * Generates obstacles on the game field based on the difficulty level.
     */
    private void generateObstacles() {
        int obstacleCount = 0;
        switch (difficulty) {
            case "easy":
                obstacleCount = 20;
                break;
            case "medium":
                obstacleCount = 40;
                break;
            case "hard":
                obstacleCount = 60;
                break;
        }
        for (int i = 0; i < obstacleCount; i++) {
            Position position = generateValidPosition();
            obstacles.add(new Obstacle(position.getX(), position.getY()));
        }
    }

    /**
     * Generates a valid position for placing an object on the game field.
     *
     * @return A valid position.
     */
    private Position generateValidPosition() {
        Position newPos;
        boolean validPosition;
        do {
            validPosition = true;
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            newPos = new Position(x, y);

            if (x < UI_MARGIN && y < UI_MARGIN) {
                validPosition = false;
                continue;
            }

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
     * Spawns a fruit at a random valid position on the game field.
     */
    private void spawnFruit() {
        Position position = generateValidPosition();
        fruit.setPosition(position);
    }

    /**
     * Checks for collisions between the snake and other objects.
     */
    private void checkCollisions() {
        Position head = snake.getBody().getFirst();
        // Check collision with walls
        if (head.getX() < 0 || head.getX() >= WIDTH || head.getY() < 0 || head.getY() >= HEIGHT) {
            gameOver = true;
        }
        // Check collision with itself
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.getX() == snake.getBody().get(i).getX() && head.getY() == snake.getBody().get(i).getY()) {
                gameOver = true;
            }
        }
        // Check collision with obstacles
        for (Obstacle obstacle : obstacles) {
            if (head.getX() == obstacle.getPosition().getX() && head.getY() == obstacle.getPosition().getY()) {
                gameOver = true;
            }
        }
    }

    /**
     * Checks if the snake has eaten a fruit and updates the game state accordingly.
     */
    private void checkFruit() {
        Position head = snake.getBody().getFirst();
        if (head.getX() == fruit.getPosition().getX() && head.getY() == fruit.getPosition().getY()) {
            spawnFruit();
            snake.grow();
            score += 10; // Increment score by 10 for each fruit
        }
    }

    /**
     * Applies power-ups if the snake has collided with them.
     */
    private void applyPowerUps() {
        Position head = snake.getBody().getFirst();
        for (PowerUp powerUp : powerUpManager.getPowerUps()) {
            if (head.getX() == powerUp.getPosition().getX() && head.getY() == powerUp.getPosition().getY()) {
                powerUp.applyEffect();
                powerUpManager.removePowerUp(powerUp);
                snake.grow();
                break;
            }
        }
    }

    /**
     * Resets the game to its initial state.
     */
    public void reset() {
        score += (snake.getBody().size() - 1) * 10; // Add points for the length of the snake
        snake = new Snake();
        powerUpManager = new PowerUpManager();
        fruit = new Fruit();
        gameOver = false;
        obstacles.clear();
        generateObstacles();
        spawnFruit();
    }

    /**
     * Draws the obstacles on the game field.
     *
     * @param g The graphics context.
     */
    public void drawObstacles(Graphics g) {
        g.setColor(Color.GRAY);
        for (Obstacle obstacle : obstacles) {
            g.fillRect(obstacle.getPosition().getX() * 10, obstacle.getPosition().getY() * 10, 10, 10);
        }
    }

    /**
     * Loads a map from a file.
     *
     * @param mapFilePath The file path of the map.
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
}
