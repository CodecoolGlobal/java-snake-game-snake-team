package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;

public class AdvancedEnemy extends Enemy implements Animatable, Interactable {

    /*This enemy will move on a rectangular pattern. If the snake connects with it, the snake is slowed down.
    * It will never be destroyed.*/

    private Point2D heading;
    private int dirX = 1;
    private int dirY = 1;



    public AdvancedEnemy(){
        super(10);
        System.out.println("I AM REBORN!");
        setImage(Globals.getInstance().getImage("AdvancedEnemy"));
        setX((Globals.WINDOW_WIDTH / 4));
        setY((Globals.WINDOW_HEIGHT /4));
        double direction = 90;
            int speed = 2;
        heading = Utils.directionToVector(direction, speed);
    }


    @Override
    public void apply(GameEntity entity) {

    }

    @Override
    public String getMessage() {
        return "running";
    }

    @Override
    public void step() {
        if (getX() < 750 && getY() == 175) {
            dirX = 1;
            dirY = 0;
        } else if (getX() == 750 && getY() < 525) {
            dirX = 0;
            dirY = 1;
        } else if (getX() > 250 && getY() == 525) {
            dirX = -1;
            dirY = 0;
        } else if (getX() == 250 && getY() > 175) {
            dirX = 0;
            dirY = -1;
        }
        setX(getX() + dirX);
        setY(getY() + dirY);


    }
}
