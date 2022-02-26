package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Random;

/*
	- CLOSEST ZOMBIE 												--- DONE
	- SPENDING AND GAINING MONEY									--- DONE
	- DROPS (?) -> Zombies drop coins, tap on them to pick them up	--> MAYBE FOR LAST CLASS
	- BALANCE & POLISH & FINISH										--> NEXT CLASS
	- SPECIALS (Board wipe, Saw blade)
 */

public class Main extends ApplicationAdapter {
	// Game Variables
	OrthographicCamera camera;
	SpriteBatch batch;
	public static Game game;
	Start start;
	About about;
	GameOver gameover;

	public static boolean started = false, info = false, lose = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 600);
		game = new Game();
		start = new Start();
		about = new About();
		gameover = new GameOver();
	}

	@Override
	public void render () {
		tap();
		ScreenUtils.clear(1, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if(started) if(lose) gameover.draw(batch); else game.draw(batch);
		else if(info) about.draw(batch);
		else start.draw(batch);
		batch.end();
	}

	//potentially add MOUSE hover event over button to display tooltip
	void tap(){
		if(Gdx.input.justTouched()){
			Vector3 t = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(t);
			int x = (int)t.x, y = (int)t.y;
			if(started) if(lose) gameover.tap(x, y); else game.tap(x, y);
			else if(info) about.tap(x, y);
			else start.tap(x, y);
		}
	}

	// TODO: Don't go below this line
	@Override
	public void dispose () {
		batch.dispose();
	}
}
