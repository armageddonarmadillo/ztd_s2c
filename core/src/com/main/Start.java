package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Start extends Scene {
    mButton m1, m2;

    Start() {
        m1 = new mButton("Start", 1024 / 2 - mButton.bw / 2, 325, Color.NAVY);
        m2 = new mButton("About", 1024 / 2 - mButton.bw / 2, 200, Color.NAVY);
        font = new BitmapFont(Gdx.files.internal("./fonts/squid.fnt"));
        font.setColor(Color.GREEN);
        font.getData().setScale(1.5f);
        layout.setText(font, "Zombie Tower Defense");
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.create(Color.FIREBRICK), 0, 0, 1024, 600);
        font.draw(batch, layout, (float)1024 / 2 - (float)layout.width / 2, 575);
        m1.draw(batch);
        m2.draw(batch);
    }


    void tap(int x, int y){
        if(m1.hitbox().contains(x, y)) {
            Main.started = true;
            Main.game = new Game();
        }
        if(m2.hitbox().contains(x, y)) Main.info = true;
    }
}
