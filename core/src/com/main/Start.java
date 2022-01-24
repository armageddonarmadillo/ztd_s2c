package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Start {

    Start() {

    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.create(Color.FIREBRICK), 0, 0, 1024, 600);
    }

    void tap(int x, int y){

    }
}
