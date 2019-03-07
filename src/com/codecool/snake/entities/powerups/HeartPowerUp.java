package com.codecool.snake.entities.powerups;
import com.codecool.snake.Globals;


public class HeartPowerUp extends SimplePowerUp {
    public HeartPowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpHeart"));
    }
}
