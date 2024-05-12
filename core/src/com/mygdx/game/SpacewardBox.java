package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.World;

public class SpacewardBox {
    int lives;
    StaticBody box;

    public SpacewardBox(int lives, World world, float x, float y, float width, float height) {
        this.lives = lives;
        box = new StaticBody(world, x, y, width, height);
    }

    float getX() {
        return box.getX();
    }

    float getY() {
        return box.getY();
    }

    public float getWidth() {
        return box.getWidth();
    }

    public float getHeight() {
        return box.getHeight();
    }
}
