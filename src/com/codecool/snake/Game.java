package com.codecool.snake;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.powerups.BerryPowerUp;
import com.codecool.snake.entities.powerups.RedbullPowerUp;
import com.codecool.snake.entities.powerups.HeartPowerUp;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;


public class Game extends Pane {
    private List<Snake> snakes;
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        snakes = new LinkedList<>();
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
    }

    public void init() {
        spawnSnake(200,500);
        spawnSnake(400,400);
        spawnEnemies(4);
        spawnPowerUps(2, 2, 2);
        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake(int xCor, int yCor) {
        snakes.add(new Snake(new Vec2d(xCor, yCor)));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    private void spawnPowerUps(int numberOfBerryPowerUps, int numberOfRedbullPowerUps, int numberOfHeartPowerUps) {
        for(int i = 0; i < numberOfBerryPowerUps; ++i) new BerryPowerUp();
        for(int i = 0; i < numberOfRedbullPowerUps; ++i) new RedbullPowerUp();
        for(int i = 0; i < numberOfHeartPowerUps; ++i) new HeartPowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
