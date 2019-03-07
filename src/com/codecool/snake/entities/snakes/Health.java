package com.codecool.snake.entities.snakes;


import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.text.Text;


public class Health extends GameEntity {
    public Health(Snake snake, int snakeNum) {
        setImage(Globals.getInstance().getImage("HealthIcon"));
        setPosition(new Vec2d(0, (snakeNum -1) * 30));
        Text text = new Text();
        text.setText(Integer.toString(snake.getHealth()));
        text.setX(30);
        text.setY((snakeNum -1) * 30);
    }
}
