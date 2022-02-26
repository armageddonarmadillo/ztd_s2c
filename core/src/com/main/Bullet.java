package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    int x, y, w, h;
    int speed, dt = 0, md;
    boolean active = true;
    String type;
    float angle = 0f;

    Bullet(String type, int x, int y){
        this.type = type;
        this.x = x;
        this.y = y;
        w = Resources.bullet.getWidth();
        h = Resources.bullet.getHeight();
        speed = 10;
        angle = calcangle();
    }

    void update(){
        x += Math.cos(angle) * speed;
        y += Math.sin(angle) * speed;
        checkcollisions();
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
        return (float)(Math.atan((y - zy) / (x - zx)) + (x >= zx ? Math.PI : 0));
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.bullet, x, y);
    }

    void checkcollisions(){
        for (Zombie z : Game.zombies)
            if(z.hitbox().contains(hitbox())){
                z.hp--;
                active = false;
            }
    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}
