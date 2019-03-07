package com.codecool.snake.entities.enemies;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;
import com.codecool.snake.entities.snakes.Spitju;
import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable, Interactable {


    private Point2D heading;
    private static Random rnd = new Random();
    private int dirX = 1, dirY = 1;
    private boolean alive;


    public SimpleEnemy() {
        super(10);
        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        updateMovementPattern();
        alive = true;
    }


    private void updateMovementPattern() {
        System.out.println(getMessage());
        Random randbool = new Random();
        if (randbool.nextBoolean()) {
            if (randbool.nextBoolean()) {
                setX(Globals.WINDOW_WIDTH-10);
            } else {
                setX(10);
            }
            setY(rnd.nextInt((int) Globals.WINDOW_HEIGHT));
        } else {
            if (randbool.nextBoolean()) {
                setY(Globals.WINDOW_HEIGHT -10);
            } else {
                setY(10);
            }
            setX(rnd.nextInt((int) Globals.WINDOW_WIDTH));
        }
        double direction = rnd.nextDouble() * 360;
        int speed = 1;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }


    @Override
    public void step() {
        if(alive) {
            if (getX() <= 1 || getX() >= Globals.WINDOW_WIDTH - 1) {
                this.dirX *= -1;
            }
            if (getY() <= 1 || getY() >= Globals.WINDOW_HEIGHT - 1) {
                this.dirY *= -1;
            }
            setX(getX() + heading.getX() * dirX);
            setY(getY() + heading.getY() * dirY);
        }
    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead || entity instanceof SnakeBody  || entity instanceof Spitju){
            setImage(Globals.getInstance().getImage("SimpleEnemy"));
            updateMovementPattern();
        }

    }


    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
