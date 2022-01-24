package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.org.apache.xml.internal.utils.res.XResourceBundle;

import java.util.ArrayList;
import java.util.Random;

/*
	- button logic (?)
 */

public class Main extends ApplicationAdapter {
	// Game Variables
	SpriteBatch batch;
	Game game;
	Start start;

	@Override
	public void create () {
		batch = new SpriteBatch();
		game = new Game();
		start = new Start();
	}

	@Override
	public void render () {
		tap();
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		game.draw(batch);
		start.draw(batch);
		batch.end();
	}

	//potentially add MOUSE hover event over button to display tooltip
	void tap(){
		if(Gdx.input.justTouched()){
			int x = Gdx.input.getX(), y = Gdx.graphics.getHeight() - Gdx.input.getY();
			game.tap(x, y);
			start.tap(x, y);
		}
	}

	// TODO: Don't go below this line
	@Override
	public void dispose () {
		batch.dispose();
	}
}
