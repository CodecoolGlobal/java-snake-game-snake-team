package com.codecool.snake.entities.snakes;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.AdvancedEnemy;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.BerryPowerUp;
import com.codecool.snake.entities.powerups.HeartPowerUp;
import com.codecool.snake.entities.powerups.RedbullPowerUp;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;



public class SnakeHead extends GameEntity implements Interactable {


    private static final float turnRate = 2;
    private static final int NEW_BODYPARTS = 3;
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
    public void apply(GameEntity entity){
        if(entity instanceof SimpleEnemy && ((SimpleEnemy) entity).isAlive()){
            snake.changeHealth(((SimpleEnemy) entity).getDamage());
            System.out.println(snake.getHealth());
        }
        if(entity instanceof BerryPowerUp){
            snake.addPart(NEW_BODYPARTS);
        }
        if(entity instanceof RedbullPowerUp){
            snake.moveFaster();
        }
        if(entity instanceof HeartPowerUp){
            snake.fillHealth();
        }
        if (entity instanceof AdvancedEnemy) {
            snake.moveSlower();
        }
    }
}
