package com.codecool.snake.entities.snakes;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.BerryPowerUp;
import com.codecool.snake.entities.powerups.HeartPowerUp;
import com.codecool.snake.entities.powerups.RedbullPowerUp;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;



public class SnakeHead extends GameEntity implements Interactable {


    private static final float turnRate = 2;
    private Snake snake;


    SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }


    protected Snake getSnake() {
        return snake;
    }


    void updateRotation(SnakeControl turnDirection, double speed) {
        double headRotation = getRotate();
        System.out.println(headRotation);
        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }



    @Override
    public Vec2d getPosition() {
        return super.getPosition();
    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage());
        }
        if(entity instanceof BerryPowerUp){
            System.out.println(getMessage());
            snake.addPart(4);
        }
        if(entity instanceof RedbullPowerUp){
            System.out.println(getMessage());
            snake.moveFaster();
        }
        if(entity instanceof HeartPowerUp){
            System.out.println(getMessage());
            snake.fillHealth();
        }
    }


    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
