package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {
    static int money = 10000, wave, life = 20, score;
    static BitmapFont font = new BitmapFont();

    static void draw(SpriteBatch batch){
        font.getData().setScale(1.5f);
        font.setColor(Color.GOLD); //money color
        font.draw(batch, "Money: " + money, 5, 595);
        font.setColor(Color.PINK); //wave color
        font.draw(batch, "Wave: " + wave, 5, 575);
        font.setColor(Color.LIME); //life color
        font.draw(batch, "Life: " + life, 5, 555);
        font.setColor(Color.CYAN); //score color
        font.draw(batch, "Score: " + score, 5, 535);
    }
}
