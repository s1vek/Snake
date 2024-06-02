Third year project - Snake

Welcome to my implementation of game Snake, a simple yet fun implementation of the classic Snake game using Java. 

- ### Functions
### Game Modes

- **Easy**: Fewer obstacles and slower snake speed.
- **Medium**: Moderate number of obstacles and snake speed.
- **Hard**: Many obstacles and faster snake speed.
- **Custom**: Create and play on your custom maps.

### Power-Ups

- **Speed**: Temporarily increases the snake's speed.
- **Extra Life**: Grants an additional life.
- **Shrink**: Temporarily reduces the snake's length.

- ## Classes

### Model

- **Directions**: Enum representing possible movement directions for the snake (UP, DOWN, LEFT, RIGHT).
- **Fruit**: Represents a fruit in the game. The snake collects these to grow longer and increase the score.
- **Game**: Manages the overall game state, including the snake, fruits, power-ups, obstacles, and score.
- **Obstacle**: Represents obstacles on the game board that the snake must avoid.
- **Position**: Represents coordinates on the game board.
- **PowerUp**: Represents power-ups that the snake can collect for special effects.
- **PowerUpManager**: Manages the collection and application of power-ups.
- **PowerUpType**: Enum representing different types of power-ups (SPEED, EXTRA_LIFE, SHRINK).
- **Snake**: Represents the snake itself, including its body segments, direction, speed, and lives.

### UI

- **DifficultySelectionPanel**: UI panel for selecting the game's difficulty level.
- **GamePanel**: Main UI panel that displays the game and handles user interactions.
- **Main**: The main class that launches the game.
- **MainFrame**: The main window frame that holds different UI panels and manages transitions between them.
- **MainMenuPanel**: UI panel for the main menu of the game.
- **MapEditorPanel**: UI panel that allows users to create and save custom maps.
- **Settings**: Manages game settings such as snake color and background color.
- **SettingsPanel**: UI panel for changing the game's settings.

## Unit Tests

The project includes unit tests written using JUnit. These tests ensure that the core functionality of the game works as expected. The tests cover the following:

- **Position Tests**: Verify the creation and equality of `Position` objects.
- **Snake Tests**: Check the initial state, movement, growth, and shrinking of the snake.
- **Game Tests**: Validate the initial game state, spawning of fruits, handling of power-ups, and game-over conditions.
- **PowerUp Tests**: Ensure the correct application of different power-ups.
