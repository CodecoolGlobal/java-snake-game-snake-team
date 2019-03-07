package com.codecool.snake;
import com.codecool.snake.entities.GameEntity;
import java.util.List;

import javafx.scene.layout.Pane;



public class Display {


    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();


    Display(Pane pane) {
        displayPane = pane;
    }


    public void add(GameEntity entity) {
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }


    public void remove(GameEntity entity) {
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }


    List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }


    void frameFinished() {
        gameObjects.doPendingModifications();
    }


    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        displayPane.getChildren().remove(snakeHead);
        displayPane.getChildren().add(snakeHead);
    }


    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }
}
