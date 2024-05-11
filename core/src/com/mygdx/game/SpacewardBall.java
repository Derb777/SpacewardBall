package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class SpacewardBall extends Game {
	// глобальные константы
	public static final float WORLD_WIDTH = 9, WORLD_HEIGHT = 16;
	public static final float SCR_WIDTH = 900, SCR_HEIGHT = 1600;
	public static final float WALL_SIZE = 0.1f;
	public static final int TYPE_BOARD = 0, TYPE_BOX1 = 1, TYPE_BOX2 = 2;

	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	BitmapFont fontLarge, fontSmall;

	ScreenMenu screenMenu;
	ScreenSettings screenSettings;
	ScreenGame screenGame;
	ScreenAbout screenAbout;

	String playerName = "Player";
	boolean isSoundOn = true;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();
		fontLarge = new BitmapFont(Gdx.files.internal("crystalfont.fnt"));
		fontSmall = new BitmapFont(Gdx.files.internal("crystalsmall.fnt"));

		screenMenu = new ScreenMenu(this);
		screenSettings = new ScreenSettings(this);
		screenGame = new ScreenGame(this);
		screenAbout = new ScreenAbout(this);
		setScreen(screenGame);
	}

	@Override
	public void dispose () {
		batch.dispose();
		fontLarge.dispose();
		fontSmall.dispose();
	}
}
