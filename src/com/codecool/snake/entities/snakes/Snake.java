package com.codecool.snake.entities.snakes;
import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;
import java.util.List;



public class Snake implements Animatable {


    private static final int STARTING_BODYPARTS = 4;


    private static int instanceCounter = 0;
    private boolean alive = true;
    private KeyCode turnLeftKey, turnRightKey, spitjuKey;
    private double speed = 2;
    private int health = 50;
    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    private double spitjuTimeWindow;


    public Snake(Vec2d position) {
        ++instanceCounter;
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        addPart(STARTING_BODYPARTS);
        if(instanceCounter == 1){
            turnLeftKey = KeyCode.LEFT; turnRightKey = KeyCode.RIGHT;
            spitjuKey = KeyCode.SPACE;
        } else {
            turnLeftKey = KeyCode.A; turnRightKey = KeyCode.D;
            spitjuKey = KeyCode.W;
        }
    }


    public void step() {
        if(alive) {
            SnakeControl turnDir = getUserInput();
            head.updateRotation(turnDir, speed);
            updateSnakeBodyHistory();
            checkForGameOverConditions();
            body.doPendingModifications();
            spitjuTimeWindow -= (spitjuTimeWindow > 0) ? 1 : 0;
        }
    }


    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(spitjuKey)){
                if(spitjuTimeWindow ==0) {
                new Spitju(this);
                spitjuTimeWindow = 10;
            }
        }
        if(InputHandler.getInstance().isKeyPressed(turnLeftKey)){
            turnDir = SnakeControl.TURN_LEFT;
        }
        if(InputHandler.getInstance().isKeyPressed(turnRightKey)){
            turnDir = SnakeControl.TURN_RIGHT;
        }
        return turnDir;
    }


    void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();
        Snake snake = head.getSnake();
        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position, snake);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }


    void moveFaster() {
        speed += 0.25;
    }


    void moveSlower() {
        if (speed > 1) {
            speed -=1;
        } else {
            speed = (float) 0.8;
        }
    }


    void fillHealth() {
        health += 10;
    }


    void changeHealth(int diff) {
        health += diff;
    }


    double getSpeed(){
        return speed;
    }


    SnakeHead getHead(){
        return head;
    }


    private void snakeDie(){
        List<GameEntity> bodyParts = body.getList();
        bodyParts.forEach(GameEntity::destroy);
        head.destroy();
        alive = false;
        --instanceCounter;
    }



    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            snakeDie();
            if(instanceCounter == 0){
                System.out.println("Game Over");
                Globals.getInstance().stopGame();
            }
        }
    }


    public boolean isAlive(){
        return alive;
    }


    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }


    private GameEntity getLastPart() {
        GameEntity result = body.getLast();
        if(result != null) return result;
        return head;
    }
}
