package com.codecool.snake.entities.snakes;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;



public class Spitju extends GameEntity implements Interactable {




    @Override
    public void apply(GameEntity entity){}


    @Override
    public String getMessage(){
        return "SPITJU-SPITJU!";
    }


}
