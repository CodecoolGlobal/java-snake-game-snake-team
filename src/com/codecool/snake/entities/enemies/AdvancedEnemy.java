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


    private int dirX = 1;
    private int dirY = 1;



    public AdvancedEnemy(){
        super(0);
        System.out.println("I AM REBORN!");
        setImage(Globals.getInstance().getImage("AdvancedEnemy"));
        setX((Globals.WINDOW_WIDTH / 4));
        setY((Globals.WINDOW_HEIGHT /4));
    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead || entity instanceof SnakeBody){
            System.out.println(getMessage());
        }

    }

    @Override
    public void step() {
        if (getX() < 750 && getY() == 175) {
            dirX = 1; dirY = 0;
        } else if (getX() == 750 && getY() < 525) {
            dirX = 0; dirY = 1;
        } else if (getX() > 250 && getY() == 525) {
            dirX = -1; dirY = 0;
        } else if (getX() == 250 && getY() > 175) {
            dirX = 0; dirY = -1;
        }
        setX(getX() + dirX);
        setY(getY() + dirY);
    }

    @Override
    public String getMessage() {
        return "BAAAARF\nSLOWED DOWN!";
    }
}
