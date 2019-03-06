package com.codecool.snake.entities.snakes;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class Spitju extends GameEntity implements Interactable {


    private Point2D heading;

    public Spitju(Snake snake) {
        setImage(Globals.getInstance().getImage("bullet"));
        setRotate(snake.getHead().getRotate()-90);
        Vec2d startPos = snake.getHead().getPosition();
        heading = Utils.directionToVector(snake.getHead().getRotate(), snake.getSpeed() + 5);
        setX(startPos.x);
        setY(startPos.y);
    }


    public void updateBulletPosition(){
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }


    @Override
    public void apply(GameEntity entity){}


    @Override
    public String getMessage(){
        return "SPITJU-SPITJU!";
    }
}
