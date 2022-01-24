package com.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Resources {
    //UI Elements
    static Texture bg = new Texture(Gdx.files.internal("DungeonBackground.png"));
    static Texture button_cannon = new Texture(Gdx.files.internal("CannonIcon.png"));
    static Texture button_cannon_fire = new Texture(Gdx.files.internal("FireCannonIcon.png"));
    static Texture button_cannon_super = new Texture(Gdx.files.internal("SuperCannonIcon.png"));
    static Texture button_cannon_double = new Texture(Gdx.files.internal("doubleCannonIcon.png"));
    static Texture button_cannon_laser = new Texture(Gdx.files.internal("laserCannonIcon.png"));
    static Texture button_cannon_mounted = new Texture(Gdx.files.internal("mountedCannonIcon.png"));
    static Texture button_wall = new Texture(Gdx.files.internal("WallIcon.png"));
    static Texture play = new Texture(Gdx.files.internal("play.png"));
    static Texture pause = new Texture(Gdx.files.internal("pause.png"));
    static Texture wall = new Texture(Gdx.files.internal("Wall.png"));
    static Texture locked = new Texture(Gdx.files.internal("locked.png"));
    static Texture selected = new Texture(Gdx.files.internal("border.png"));
    static Texture red_bar = new Texture(Gdx.files.internal("red_bar.png"));
    static Texture green_bar = new Texture(Gdx.files.internal("green_bar.png"));
    static Texture tooltip_bg = new Texture(Gdx.files.internal("ttbg.png"));
    static Texture button_close = new Texture(Gdx.files.internal("x.png"));

    //Cannons
    static Texture cannon = new Texture(Gdx.files.internal("Cannon.png"));
    static Texture cannon_fire = new Texture(Gdx.files.internal("Firecannon.png"));
    static Texture cannon_super = new Texture(Gdx.files.internal("SuperCannon.png"));
    static Texture cannon_double = new Texture(Gdx.files.internal("doubleCannon.png"));
    static Texture cannon_laser = new Texture(Gdx.files.internal("laserCannon.png"));
    static Texture cannon_mounted = new Texture(Gdx.files.internal("mountedCannon.png"));
    static Texture ghastly = new Texture(Gdx.files.internal("ghastly.png"));

    //Zombies
    static Texture test_zombie = new Texture(Gdx.files.internal("Zombie.png"));
    static Texture zombie = new Texture(Gdx.files.internal("Zombies.png"));
    static Texture zombie_dif = new Texture(Gdx.files.internal("DifZombies.png"));
    static Texture zombie_speedy = new Texture(Gdx.files.internal("speedy_zombie.png"));
    static Texture zombie_riot = new Texture(Gdx.files.internal("riotzombieBIG.png"));
    static Texture zombie_fast = new Texture(Gdx.files.internal("Fastzombies.png"));

    //Wizards
    static Texture wizard_red = new Texture(Gdx.files.internal("wizard_red.png"));
    static Texture wizard_blue = new Texture(Gdx.files.internal("wizard_blue.png"));
    static Texture wizard_green = new Texture(Gdx.files.internal("wizard_green.png"));

    //Bullets
    static Texture bullet = new Texture(Gdx.files.internal("Bullet.png"));

    //Effects
    static Texture click = new Texture(Gdx.files.internal("click_effect.png"));
    static Texture boom = new Texture(Gdx.files.internal("boom.png"));
    static Texture splatter = new Texture(Gdx.files.internal("splatter.png"));

    //Sounds
    static Sound bullet_sound = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
    static Sound bullet_pew_sound = Gdx.audio.newSound(Gdx.files.internal("FireBullet.mp3"));

    //Methods
    static Texture create(Color c){
        Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        p.setColor(c);
        p.fillRectangle(0, 0, 1, 1);
        return new Texture(p);
    }

    static Color inverse(Color c){
        return new Color(1f - c.r, 1f - c.g, 1f - c.b, 1f - c.a);
    }
}
