package com.codecool.snake.entities.snakes;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;
import java.util.LinkedList;
import java.util.List;



public class Spitju extends GameEntity implements Interactable {

    private Snake snake;
    private Point2D heading;


    Spitju(Snake snake) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("bullet"));
        setRotate(snake.getHead().getRotate());
        Vec2d startPos = snake.getHead().getPosition();
        heading = Utils.directionToVector(snake.getHead().getRotate(), snake.getSpeed() + 5);
        setX(startPos.x+10);
        setY(startPos.y);
    }


    void updateSpitjuPosition(){
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        destroySpitju();
    }


    private void destroySpitju(){
        if(isOutOfBounds()){
            destroy();
            snake.getSpitjus().remove(this);
        }

    }


    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            snake.getSpitjus().remove(this);
            destroy();
        }

    }


    @Override
    public String getMessage(){
        return "SPITJU-SPITJU!";
    }
}
