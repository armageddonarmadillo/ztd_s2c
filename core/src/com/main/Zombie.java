package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Zombie {
    int x, y, w, h, speed;
    int hp, mhp;
    String type;
    boolean active = true;

    //Animation Variables
    int rows = 1, cols;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.2f; //1 seconds = 1.0f

    Zombie(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        this.speed = (Tables.values.get("speed_" + type) == null ? 1 : Tables.values.get("speed_" + type));
        hp = (Tables.values.get("health_" + type) == null ? 5 : Tables.values.get("health_" + type));
        mhp = hp;
        cols = (Tables.values.get("columns_" + type) == null ? 4 : Tables.values.get("columns_" + type));
        w = (Tables.zombies.get(type) == null ? Resources.zombie : Tables.zombies.get(type)).getWidth() / cols;
        h = (Tables.zombies.get(type) == null ? Resources.zombie : Tables.zombies.get(type)).getHeight() / rows;
        init_animations();
    }

    void draw(SpriteBatch b){
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        b.draw(frame, x, y);

        //healthbar
        b.draw(Resources.red_bar, x, y + h + 1, w, 5);
        b.draw(Resources.green_bar, x, y + h + 1, hp * ((float)w / (float)mhp), 5);
    }

    void update(){
        x -= speed;
        UI.money += hp > 0 ? 0 : 5;
        UI.score += hp > 0 ? 0 : 1;
        UI.life -= x > -50 ? 0 : Tables.values.get("damage_"+type) == null ? 1 : Tables.values.get("damage_"+type);
        active = x > -50 && hp > 0;
    }

    void init_animations(){
        //split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split((Tables.zombies.get(type) == null ? Resources.zombie : Tables.zombies.get(type)), w, h);

        //init number of frames to maximum number possible (all rows * all cols)
        frames = new TextureRegion[rows * cols];

        //loop through the sheet and fill frames array with cells (in order)
        int index = 0;
        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++)
                frames[index++] = sheet[r][c];

        //initialize animation
        anim = new Animation(frame_time, frames);
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}
