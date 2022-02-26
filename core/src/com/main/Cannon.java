package com.main;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Cannon {
    int x, y, w, h;
    int counter = 0, delay = 50;
    boolean active = true;
    String type;
    Sprite sprite;

    //Animation Variables
    int rows = 1, cols = 4;
    Animation anim;
    TextureRegion[] frames;
    TextureRegion frame;
    float frame_time = 0.06f; //1 seconds = 1.0f

    Cannon(String type, int x, int y){
        this.type = type;
        cols = (Tables.values.get("columns_" + type) == null ? 1 : Tables.values.get("columns_" + type));
        w = (Tables.cannons.get(type) == null ? Resources.cannon : Tables.cannons.get(type)).getWidth() / cols;
        h = (Tables.cannons.get(type) == null ? Resources.cannon : Tables.cannons.get(type)).getHeight() / rows;
        this.x = gridlock(x - w / 2);
        this.y = gridlock(y - h / 2);
        init_animations();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        sprite = new Sprite(frame);
        sprite.setPosition(this.x, this.y);
    }

    void update(){
        counter++;
        if(counter > delay){
            if(!Game.zombies.isEmpty()) Game.bullets.add(new Bullet(type, x + w / 2, y + h / 2));
            counter = 0;
            Resources.bullet_sound.play(0.20f);
        }
        frame_time += Gdx.graphics.getDeltaTime();
        frame = (TextureRegion)anim.getKeyFrame(frame_time, true);
        sprite = new Sprite(frame);
        sprite.setPosition(this.x, this.y);
        sprite.setRotation(calcangle());
    }

    void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    int gridlock(int n){
        return ((int)((n + 25) / 50) * 50);
    }

    float calcangle(){
        Zombie closest = null;
        for(Zombie z : Game.zombies){
            if(closest == null) { closest = z; continue; }
            float ch = (float)Math.sqrt((x - closest.x) * (x - closest.x) + (y - closest.y) * (y - closest.y));
            float zh = (float)Math.sqrt((x - z.x) * (x - z.x) + (y - z.y) * (y - z.y));
            if (zh < ch) closest = z;
        }
        float zx = closest.x + (float)closest.w / 2, zy = closest.y + (float)closest.h / 2;
        return (float)Math.toDegrees(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }

    void init_animations(){
        //split texture into individual cells
        TextureRegion[][] sheet = TextureRegion.split((Tables.cannons.get(type) == null ? Resources.cannon : Tables.cannons.get(type)), w, h);

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
}
