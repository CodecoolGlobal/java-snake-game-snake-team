package com.codecool.snake.entities.snakes;
import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import java.util.List;



public class Snake implements Animatable {


    private static final int STARTING_BODYPARTS = 14;
    private static final double SLOW_SPEED = 0.8;
    private static int instanceCounter = 0;


    private boolean alive = true;
    private double speed = 2;
    private int health = 50;
    private SnakeHead head;
    private KeyCode turnLeftKey, turnRightKey, spitjuKey;
    private double spitjuTimeWindow;
    private int slowTimeWindow;
    private Text text;
    private DelayedModificationList<GameEntity> body;



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
        text = new Text();
        text.setX(250); text.setY(250);
    }


    private void rotateBody(){
        List<GameEntity> bodyParts = body.getList();
        for(GameEntity part : bodyParts){
            part.setRotate(head.getRotate());
        }
    }


    private void move(){
        SnakeControl turnDir = getUserInput();
        if(slowTimeWindow>0) {
            head.updateRotation(turnDir, SLOW_SPEED);
            --slowTimeWindow;
        } else {
            head.updateRotation(turnDir, speed);
        }
    }


    public void step() {
        if(alive) {
            move();
            rotateBody();
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
                spitjuTimeWindow = 20;
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
        slowTimeWindow = 300;
    }



    void fillHealth() {
        health += 10;
    }


    void changeHealth(int damage) {
        health -= damage;
        text.setText(Integer.toString(health));
    }


    double getSpeed(){
        return speed;
    }


    SnakeHead getHead(){
        return head;
    }


    int getHealth(){
        return health;
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
