package com.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    // Control Variables
    static boolean paused = false;
    static String current_type = "ghastly";
    static Random r = new Random();

    // Game Lists
    static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    static ArrayList<Cannon> cannons = new ArrayList<Cannon>();
    static ArrayList<Button> buttons = new ArrayList<Button>();
    static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    static ArrayList<Effect> effects = new ArrayList<Effect>();
    static ArrayList<Wall> walls = new ArrayList<Wall>();

    Game(){
        setup();
    }

    void update(){
        //calls
        spawn_zombies();

        //loops
        if(!paused){
            for(Zombie z : zombies) z.update();
            for(Cannon c : cannons) c.update();
            for(Button b : buttons) b.update();
            for(Bullet b : bullets) b.update();
            for(Wall w : walls) w.update();
        }

        //remove inactive objects
        housekeeping();
    }

    void draw(SpriteBatch batch){
        update();
        batch.draw(Resources.bg, 0, 0);
        UI.draw(batch);
        for(Wall w : walls) w.draw(batch);
        for(Zombie z : zombies) z.draw(batch);
        for(Cannon c : cannons) c.draw(batch);
        for(Button b : buttons) b.draw(batch);
        for(Bullet b : bullets) b.draw(batch);
        for(Effect e : effects) e.draw(batch);
    }

    void tap(int x, int y){
        for(Button b: buttons){
            if(!b.t.hidden && b.t.hitbox().contains(x, y)) return;
            if(b.gethitbox().contains(x, y)){
                if(b.locked) {
                    if(b.t.hidden) { hidett(); b.t.hidden = false; }
                    else { hidett(); b.locked = false; }
                }
                else {
                    if(b.type.equals("wall") || b.type.equals("mounted")){
                        if(walls.size() < 5)walls.add(new Wall(walls.size() * Resources.wall.getWidth(), 0, b.type.equals("mounted")));
                        return;
                    }
                    if(b.type.equals("pause") || b.type.equals("play")){
                        paused = !paused;
                        b.type = paused ? "play" : "pause";
                        return;
                    }
                    deselect();
                    hidett();
                    b.selected = true;
                    current_type = b.type;
                }
                return;
            }
            if(!b.t.hidden && !b.t.hitbox().contains(x, y) && !anybutton(x, y)) { b.t.hidden = true; return; }
        }

        //leave cannons last
        for(Cannon c : cannons) if(c.hitbox().contains(x, y)) return;
        if(buildable(x, y)) cannons.add(new Cannon(current_type, x, y));
    }

    void housekeeping(){
        for(Zombie z : zombies) if(!z.active) { effects.add(new Effect("splatter", z.x + z.w / 2, z.y + z.h / 2)); zombies.remove(z); break; }
        for(Effect e : effects) if(!e.active) { effects.remove(e); break; }
        for(Wall w : walls) if(!w.active) { walls.remove(w); break; }
    }

    boolean anybutton(int x, int y) {
        for(Button b : buttons) if(b.gethitbox().contains(x, y)) return true;
        return false;
    }

    boolean buildable(int x, int y){
        return ((y > 0 && y < 200) || (y > 300 && y < 500)) && x < 1000;
    }

    void deselect(){
        for(Button b : buttons) b.selected = false;
    }

    void hidett() { for(Button b : buttons) b.t.hidden = true; }

    void setup(){
        Tables.init();
        current_type = "ghastly";
        buttons.add(new Button("cannon", 225 + buttons.size() * 75, 525));
        buttons.get(buttons.size() - 1).locked = false;
        buttons.get(buttons.size() - 1).selected = true;
        buttons.add(new Button("fire", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("super", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("double", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("laser", 225 + buttons.size() * 75, 525));
        buttons.add(new Button("wall", 225 + buttons.size() * 75, 525));
        buttons.get(buttons.size() - 1).locked = false;
        buttons.get(buttons.size() - 1).selected = false;
        buttons.add(new Button("mounted", 225 + buttons.size() * 75, 525));

        buttons.add(new Button("pause", 1024 - 75, 525));
        buttons.get(buttons.size() - 1).locked = false;
        buttons.get(buttons.size() - 1).selected = false;
    }

    void spawn_zombies() {
        if(!zombies.isEmpty()) return;
        UI.wave++;
		/*
		* String[] quick_types = { "zzz", "zzz", "zzz", "riot" };
		for(int i = 0; i < UI.wave * 3; i++) {
			zombies.add(new Zombie("wizard_red", 1055 + i * 90, r.nextInt(450), 5));
		}*/

        for(int i = 0; i < 2 * UI.wave; i++){
            zombies.add(new Zombie("zzz", 1055 + i * 90, r.nextInt(450), 5));
        }
        if(UI.wave >= 10){
            for(int i = 0; i < UI.wave; i++){
                zombies.add(new Zombie("riot", 1055 + i * 90, r.nextInt(450), 5));
            }
        }
    }
}
