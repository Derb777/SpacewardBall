package com.mygdx.game;

import static com.mygdx.game.SpacewardBall.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class ScreenSettings implements Screen {
    SpacewardBall main;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont fontLarge, fontSmall;

    SpaceButton btnName;
    SpaceButton btnSound;
    SpaceButton btnClearRecords;
    SpaceButton btnBack;

    Texture imgBackGround;
    boolean isUseInputKeyboard;
    InputKeyboard keyboard;

    public ScreenSettings(SpacewardBall main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        fontLarge = main.fontLarge;
        fontSmall = main.fontSmall;

        imgBackGround = new Texture("stars1.png");

        loadSettings();

        btnName = new SpaceButton("Name: "+main.playerName, 100, 1000, fontLarge);
        btnSound = new SpaceButton(main.isSoundOn ? "Sound On" : "Sound Off", 100, 800, fontLarge);
        btnClearRecords = new SpaceButton("Clear Records", 100, 600, fontLarge);
        btnBack = new SpaceButton("Back", 100, 400, fontLarge);
        keyboard = new InputKeyboard(fontSmall, SCR_WIDTH, SCR_HEIGHT/2, 8);

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
            if(isUseInputKeyboard){
                if (keyboard.endOfEdit(touch.x, touch.y)) {
                    main.playerName = keyboard.getText();
                    btnName.setText("Name: "+main.playerName);
                    isUseInputKeyboard = false;
                }
            } else {
                if (btnName.hit(touch.x, touch.y)) {
                    isUseInputKeyboard = true;
                }
                if (btnSound.hit(touch.x, touch.y)) {
                    main.isSoundOn = !main.isSoundOn;
                    btnSound.setText(main.isSoundOn ? "Sound On" : "Sound Off");
                }
                if (btnClearRecords.hit(touch.x, touch.y)) {
                    //main.screenGame.clearRecords();
                    //btnClearRecords.setText("Records Cleared");
                }
                if (btnBack.hit(touch.x, touch.y)) {
                    main.setScreen(main.screenMenu);
                }
            }
        }

        // события

        // отрисовка
        ScreenUtils.clear(0, 0.3f, 0, 1);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        fontLarge.draw(batch, btnName.text, btnName.x, btnName.y);
        fontLarge.draw(batch, btnSound.text, btnSound.x, btnSound.y);
        fontLarge.draw(batch, btnClearRecords.text, btnClearRecords.x, btnClearRecords.y);
        fontLarge.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        if(isUseInputKeyboard) {
            keyboard.draw(batch);
        }
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
        btnClearRecords.setText("Clear Records");
        saveSettings();
    }

    @Override
    public void dispose() {
        imgBackGround.dispose();
        keyboard.dispose();
    }

    void saveSettings() {
        Preferences prefs = Gdx.app.getPreferences("SatArcadeSettings");
        prefs.putString("name", main.playerName);
        prefs.putBoolean("sound", main.isSoundOn);
        prefs.flush();
    }

    void loadSettings() {
        Preferences prefs = Gdx.app.getPreferences("SatArcadeSettings");
        if(prefs.contains("name")) main.playerName = prefs.getString("name");
        if(prefs.contains("sound")) main.isSoundOn = prefs.getBoolean("sound");
    }
}
