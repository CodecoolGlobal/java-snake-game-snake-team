package com.codecool.snake;
import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;



public class Globals {


    private static Globals instance = null;
    public static final double WINDOW_WIDTH = 1700;
    public static final double WINDOW_HEIGHT =1000;
    public Display display;
    private GameLoop gameLoop;
    private Resources resources;
    Game game;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }


    void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }


    void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("PowerUpRedbull", new Image("powerup_redbull.png"));
        resources.addImage("PowerUpHeart", new Image("powerup_heart.png"));
        resources.addImage("AdvancedEnemy", new Image("advanced_enemy.png"));
        resources.addImage("HealthIcon", new Image("health.png"));
        resources.addImage("Spitju", new Image("bullet.png"));
        resources.addImage("explosion", new Image("explosion1.png"));
    }


    public Image getImage(String name) { return resources.getImage(name); }


    void startGame() { gameLoop.start(); }


    public void stopGame() { gameLoop.stop(); }


    private Globals() {}
}
