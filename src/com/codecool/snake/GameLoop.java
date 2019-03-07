package com.codecool.snake;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import java.util.List;



class GameLoop {

    private List<Snake> snakes;
    private boolean running = false;


    GameLoop(List<Snake> snakes) {
        this.snakes = snakes;
    }


    void start() {
        running = true;
    }


    void stop() {
        running = false;
    }


    void step() {
        if (running) {
            for (Snake snake : snakes) {
                if(snake.isAlive()) snake.step();
                for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                    if (gameObject instanceof Animatable) {
                        ((Animatable) gameObject).step();
                    }
                }
                checkCollisions();
            }
        }
        Globals.getInstance().display.frameFinished();
}


    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
