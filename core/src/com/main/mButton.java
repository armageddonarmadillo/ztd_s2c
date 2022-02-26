package com.main;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class mButton {
    int x, y;
    String label = "button";
    BitmapFont font = new BitmapFont();
    GlyphLayout layout = new GlyphLayout();
    Color btn;
    static final int bw = 150;
    static final int bh = 75;

    mButton(String label, int x, int y, Color color){
        this.label = label;
        this.x = x;
        this.y = y;
        btn = color;

        font.setColor(Resources.inverse(color));
        while((layout.width < bw - 2 * ((float)bw / 10)) && (layout.height < bh - 2 * ((float)bh / 10))){
            font.getData().setScale(font.getData().scaleX + 0.1f);
            layout.setText(font, label);
        }
    }

    void draw(SpriteBatch batch){
        batch.draw(Resources.create(btn), x, y, bw, bh);
        font.draw(batch, layout, (x + (float)bw / 2) - (layout.width / 2), y + (float)bh / 2 + (layout.height / 2));
    }

    Rectangle hitbox() { return new Rectangle(x, y, bw, bh ); }

}
