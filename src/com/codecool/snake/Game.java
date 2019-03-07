package com.codecool.snake;
import com.codecool.snake.entities.enemies.AdvancedEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.BerryPowerUp;
import com.codecool.snake.entities.powerups.HeartPowerUp;
import com.codecool.snake.entities.powerups.RedbullPowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.util.LinkedList;
import java.util.List;



class Game extends Pane {

    private List<Snake> snakes;
    private GameTimer gameTimer = new GameTimer();
    private static final int ENEMIES = 6, REDBULLS = 1, FOODS = 2, MEDIKITS = 2;
    private static final int STARTING_YCOR = 500;
    private static int STARTING_XCOR = 300;


    Game() {
        snakes = new LinkedList<>();
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
    }


    private void init() {
        spawnSnake(2);
        spawnEnemies();
        spawnPowerUps();
        GameLoop gameLoop = new GameLoop(snakes);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }


    void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }


    private void spawnSnake(int snake_number){
        for(int i=0; i<snake_number; ++i){
            snakes.add(new Snake(new Vec2d(STARTING_XCOR, STARTING_YCOR)));
            STARTING_XCOR += 200;
        }
    }

    private void spawnEnemies(){
        for(int i = 0; i < ENEMIES; ++i){
            new SimpleEnemy();
        }
        new AdvancedEnemy();
    }


    private void spawnPowerUps() {
        for(int i = 0; i < FOODS; ++i) new BerryPowerUp();
        for(int i = 0; i < REDBULLS; ++i) new RedbullPowerUp();
        for(int i = 0; i < MEDIKITS; ++i) new HeartPowerUp();
    }


    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
