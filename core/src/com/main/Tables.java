package com.main;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class Tables {
    static HashMap<String, Texture> cannons = new HashMap<String, Texture>();
    static HashMap<String, Texture> buttons = new HashMap<String, Texture>();
    static HashMap<String, Texture> zombies = new HashMap<String, Texture>();
    static HashMap<String, Texture> resources = new HashMap<String, Texture>();
    static HashMap<String, Integer> values = new HashMap<String, Integer>();

    static void init(){
        values.put("columns_boom", 7);
        values.put("columns_click", 4);
        values.put("columns_laser", 16);
        values.put("columns_speedy", 6);
        values.put("columns_wizard_red", 2);
        values.put("columns_wizard_blue", 2);
        values.put("columns_wizard_green", 2);
        values.put("columns_ghastly", 3);
        values.put("columns_splatter", 6);

        values.put("damage_speedy", 3);

        values.put("place_fire", 15);
        values.put("place_laser", 50);
        values.put("place_double", 10);
        values.put("place_mounted", 250);

        values.put("unlock_fire", 300);
        values.put("unlock_double", 150);
        values.put("unlock_super", 250);
        values.put("unlock_laser", 500);
        values.put("unlock_mounted", 1000);

        values.put("health_speedy", 2);
        values.put("health_riot", 50);
        values.put("health_dif", 8);
        values.put("health_fast", 6);
        values.put("health_wizard_red", 2022);
        values.put("health_wizard_green", 2022);
        values.put("health_wizard_blue", 2022);

        values.put("speed_speedy", 10);
        values.put("speed_fast", 5);
        values.put("speed_dif", 3);
        values.put("speed_riot", 2);
        values.put("speed_wizard_red", 6);
        values.put("speed_wizard_green", 6);
        values.put("speed_wizard_blue", 6);

        resources.put("effect_click", Resources.click);
        resources.put("effect_boom", Resources.boom);
        resources.put("effect_splatter", Resources.splatter);

        cannons.put("fire", Resources.cannon_fire);
        cannons.put("double", Resources.cannon_double);
        cannons.put("super", Resources.cannon_super);
        cannons.put("laser", Resources.cannon_laser);
        cannons.put("mounted", Resources.cannon_mounted);
        cannons.put("ghastly", Resources.ghastly);

        buttons.put("fire", Resources.button_cannon_fire);
        buttons.put("double", Resources.button_cannon_double);
        buttons.put("super", Resources.button_cannon_super);
        buttons.put("laser", Resources.button_cannon_laser);
        buttons.put("mounted", Resources.button_cannon_mounted);
        buttons.put("wall", Resources.button_wall);
        buttons.put("play", Resources.play);
        buttons.put("pause", Resources.pause);

        zombies.put("dif", Resources.zombie_dif);
        zombies.put("fast", Resources.zombie_fast);
        zombies.put("speedy", Resources.zombie_speedy);
        zombies.put("riot", Resources.zombie_riot);
        zombies.put("wizard_red", Resources.wizard_red);
        zombies.put("wizard_blue", Resources.wizard_blue);
        zombies.put("wizard_green", Resources.wizard_green);
    }
}
