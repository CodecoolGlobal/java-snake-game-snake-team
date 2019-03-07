package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();

    public SimplePowerUp() {
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            destroy();
            int randomNum = rnd.nextInt(10);
            if (randomNum < 3) {
                new HeartPowerUp();
            }
            if (randomNum < 5) {
                new RedbullPowerUp();
            } else {
                new BerryPowerUp();
            }
        }
    }
}
