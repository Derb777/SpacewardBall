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

	Texture imgball1;
	Texture imgball2;
	Texture imgball3;
	Texture imgball4;
	Texture imgball5;

	Texture imgBrick1;
	Texture imgBrick2;
	Texture imgBrick3;
	Texture imgBrick4;
	Texture imgBrick5;
	Texture imgBrick6;
	Texture imgBrick7;
	Texture imgBrick8;
	Texture imgBrick9;
	Texture imgBrick10;
	Texture imgBrick11;

	Texture imgBricks1;
	Texture imgBricks2;
	Texture imgBricks3;
	Texture imgBricks4;
	Texture imgBricks5;
	Texture imgBricks6;
	Texture imgBricks7;
	Texture imgBricks8;

	Texture imgss1;
	Texture imgss2;
	Texture imgss3;

	Texture imgsrt1;
	Texture imgsrt2;
	Texture imgsrt3;
	Texture imgsrt4;

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

		imgball1 = new Texture("ball1.png");
		imgball2 = new Texture("ball2.png");
		imgball3 = new Texture("ball3.png");
		imgball4 = new Texture("ball4.png");
		imgball5 = new Texture("ball5.png");

		imgBrick1 = new Texture("Brick1.png");
		imgBrick2 = new Texture("Brick2.png");
		imgBrick3 = new Texture("Brick3.png");
		imgBrick4 = new Texture("Brick4.png");
		imgBrick5 = new Texture("Brick5.png");
		imgBrick6 = new Texture("Brick6.png");
		imgBrick7 = new Texture("Brick7.png");
		imgBrick8 = new Texture("Brick8.png");
		imgBrick9 = new Texture("Brick9.png");
		imgBrick10 = new Texture("Brick10.png");
		imgBrick11 = new Texture("Brick11.png");

		imgBricks1 = new Texture("imgBricks1");
		imgBricks2 = new Texture("imgBricks2");
		imgBricks3 = new Texture("imgBricks3");
		imgBricks4 = new Texture("imgBricks4");
		imgBricks5 = new Texture("imgBricks5");
		imgBricks6 = new Texture("imgBricks6");
		imgBricks7 = new Texture("imgBricks7");
		imgBricks8 = new Texture("imgBricks8");

		imgss1 = new Texture("imgss1");
		imgss2 = new Texture("imgss2");
		imgss3 = new Texture("imgss3");

		imgsrt1 = new Texture("imgsrt1");
		imgsrt2 = new Texture("imgsr2");
		imgsrt3 = new Texture("imgsr3");
		imgsrt4 = new Texture("imgsrt4");




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
