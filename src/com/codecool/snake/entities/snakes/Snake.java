package com.codecool.snake.entities.snakes;
import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class Snake implements Animatable {


    private static int instanceCounter = 0;
    private boolean alive;
    private KeyCode turnLeftKey, turnRightKey, spitjuKey;
    private double speed = 2;
    private int health = 50;
    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    private double spitjuTimeWindow;
    private List<Spitju> spitjus;



    public Snake(Vec2d position) {
        ++instanceCounter;
        alive = true;
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        addPart(4);
        if(instanceCounter ==1){
            turnLeftKey = KeyCode.LEFT; turnRightKey = KeyCode.RIGHT;
            spitjuKey = KeyCode.SPACE;
        } else {
            turnLeftKey = KeyCode.A; turnRightKey = KeyCode.D;
            spitjuKey = KeyCode.W;
        }
        spitjus = new LinkedList<>();
    }


    private void updateSpitju(){
        Iterator<Spitju> iter = spitjus.iterator();
        try {
            while (iter.hasNext()) {
                iter.next().updateSpitjuPosition();
            }
        }catch (Exception error){
            System.out.println(spitjus.size());
        }

    }


    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForGameOverConditions(); List<Spitju> spitjus;
        body.doPendingModifications();
        spitjuTimeWindow -= (spitjuTimeWindow >0) ? 1 : 0;
        updateSpitju();
    }


    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(spitjuKey)){
            if(spitjuTimeWindow ==0) {
                spitjus.add(new Spitju(this));
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


    List<Spitju> getSpitjus(){
        return spitjus;
    }


    void moveFaster() {
        speed += 1;
    }

    public void moveSlower() {
        if (speed > 1) {
            speed -=1;
        } else {
            speed = (float) 0.8;
        }
    }

    public void fillHealth() {
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

    void destroySpitju(Spitju spitju){
        spitjus.remove(spitju);
    }

    private void checkForGameOverConditions() {
        if (head.isOutOfBounds() || health <= 0) {
            List<GameEntity> bodyParts = body.getList();
            bodyParts.forEach(GameEntity::destroy);
            head.destroy();
            alive = false;
            --instanceCounter;
            System.out.println(instanceCounter);
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
