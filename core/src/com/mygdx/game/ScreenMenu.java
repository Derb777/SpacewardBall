package com.mygdx.game;

import static com.mygdx.game.SpacewardBall.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenMenu implements Screen {
    SpacewardBall main;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont font;

    SpaceButton btnPlay;
    SpaceButton btnSettings;
    SpaceButton btnAbout;
    SpaceButton btnExit;

    Texture imgBackGround;

    public ScreenMenu(SpacewardBall main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.fontLarge;

        imgBackGround = new Texture("srt4.png");

        btnPlay = new SpaceButton("Play", 100, 1000, font);
        btnSettings = new SpaceButton("Settings", 100, 800, font);
        btnAbout = new SpaceButton("About", 100, 600, font);
        btnExit = new SpaceButton("Exit", 100, 400, font);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // касания
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if(btnPlay.hit(touch.x, touch.y)){
                main.setScreen(main.screenGame);
            }
            if(btnSettings.hit(touch.x, touch.y)){
                main.setScreen(main.screenSettings);
            }
            if(btnAbout.hit(touch.x, touch.y)){
                main.setScreen(main.screenAbout);
            }
            if(btnExit.hit(touch.x, touch.y)){
                Gdx.app.exit();
            }
        }

        // события

        // отрисовка
        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch, btnPlay.text, btnPlay.x, btnPlay.y);
        font.draw(batch, btnSettings.text, btnSettings.x, btnSettings.y);
        font.draw(batch, btnAbout.text, btnAbout.x, btnAbout.y);
        font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        imgBackGround.dispose();
    }
}
