package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;

public class BerryPowerUp extends SimplePowerUp {
    public BerryPowerUp() {
        setImage(Globals.getInstance().getImage("PowerUpBerry"));
    }
}
