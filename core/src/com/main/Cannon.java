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
        float zx = Game.zombies.get(0).x + (float)Game.zombies.get(0).w / 2;
        float zy = Game.zombies.get(0).y + (float)Game.zombies.get(0).h / 2;
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
