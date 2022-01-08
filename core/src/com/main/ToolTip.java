package com.main;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ToolTip {
    int x, y, w, h;
    boolean hidden = true;
    String type;
    BitmapFont font = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();

    ToolTip(String type, Button p){
        w = 200;
        h = 100;
        x = (p.x + p.w / 2) - w / 2;
        y = p.y - h - 2;
    }

    void draw(SpriteBatch batch){
        if(hidden) return;
        batch.draw(Resources.tooltip_bg, x, y, w, h);
        //Draw text after background resources
        String[] words = "Fires damage damage bullets at a delay rate of fire.".split(" ");
        int rtx = 25, rty = 3;
        for (String s : words) {
            if(rtx + layout.width > w - 25){
                rtx = 25;
                rty += layout.height + 3;
            }

            font.setColor(Color.GRAY);
            font.draw(batch, s, x + rtx + 1, y + h - rty - 1);
            font.setColor(Color.MAROON);
            font.draw(batch, s, x + rtx, y + h - rty);
            layout.setText(font, " " + s);
            rtx += layout.width;
        }
        font.setColor(Color.GOLD);
        font.getData().setScale(2.0f);
        font.draw(batch, "Unlock: 5555", x + 12, y + 50);
        font.getData().setScale(1.0f);
        font.setColor(Color.BLACK);
        font.draw(batch, "(tap again to unlock)", x + 37, y + 15);

    }

    Rectangle hitbox() { return new Rectangle(x, y, w, h); }
}
