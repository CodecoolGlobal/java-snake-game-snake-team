package com.codecool.snake.entities.snakes;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;



public class Spitju extends GameEntity implements Interactable, Animatable {


    private Point2D heading;
    private static final int RELATIVE_SPEED = 6;


    Spitju(Snake snake) {
        setImage(Globals.getInstance().getImage("Spitju"));
        setRotate(snake.getHead().getRotate());
        Vec2d startPos = snake.getHead().getPosition();
        heading = Utils.directionToVector(snake.getHead().getRotate(), snake.getSpeed() + RELATIVE_SPEED);
        setX(startPos.x+10);
        setY(startPos.y);
    }

    private void outOfBoundsHandler(){
        if(isOutOfBounds()) destroy();
    }


    @Override
    public void step(){
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        outOfBoundsHandler();
    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy) destroy();
    }
}
