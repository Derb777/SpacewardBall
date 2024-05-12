package com.mygdx.game;

import static com.mygdx.game.SpacewardBall.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ScreenGame implements Screen {
    SpacewardBall main;
    SpriteBatch batchText;
    OrthographicCamera cameraText;
    Vector3 touchText;
    BitmapFont fontSmall, fontLarge;

    // системные объекты
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    World world;
    Box2DDebugRenderer debugRenderer;

    Texture imgBackGround;
    Texture imgball1;
    Texture imgball2;
    Texture imgball3;
    Texture imgball4;
    Texture imgball5;

    Texture[] imgBrick = new Texture[11];

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

    SpaceButton btnBack;
    Player[] players = new Player[11];

    // наши объекты и переменные
    StaticBody wallLeft, wallRight;
    StaticBody roof;
    KinematicBody platform;
    DynamicBody ball;
    Array<StaticBody> boxes = new Array<>();

    public ScreenGame(SpacewardBall main) {
        this.main = main;
        batchText = main.batch;
        cameraText = main.camera;
        touchText = main.touch;
        fontLarge = main.fontLarge;
        fontSmall = main.fontSmall;

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        touch = new Vector3();
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(new MyContactListener(this));
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.setDrawVelocities(true);
        debugRenderer.setDrawJoints(true);

        imgBackGround = new Texture("stars0.png");
        imgball1 = new Texture("ball1.png");
        imgball2 = new Texture("ball2.png");
        imgball3 = new Texture("ball3.png");
        imgball4 = new Texture("ball4.png");
        imgball5 = new Texture("ball5.png");

        for (int i = 0; i < imgBrick.length; i++) {
            imgBrick[i] = new Texture("Brick"+i+".png");
        }

        imgBricks1 = new Texture("Bricks1.png");
        imgBricks2 = new Texture("Bricks2.png");
        imgBricks3 = new Texture("Bricks3.png");
        imgBricks4 = new Texture("Bricks4.png");
        imgBricks5 = new Texture("Bricks5.png");
        imgBricks6 = new Texture("Bricks6.png");
        imgBricks7 = new Texture("Bricks7.png");
        imgBricks8 = new Texture("Bricks8.png");

        imgss1 = new Texture("ss1.png");
        imgss2 = new Texture("ss2.png");
        imgss3 = new Texture("ss3.png");

        imgsrt1 = new Texture("srt1.png");
        imgsrt2 = new Texture("srt2.png");
        imgsrt3 = new Texture("srt3.png");
        imgsrt4 = new Texture("srt4.png");


        btnBack = new SpaceButton("back", SCR_HEIGHT/5-100, fontSmall, Align.center);
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("Noname", 0);
        }
        loadRecords();

        wallLeft = new StaticBody(world, 0.05f, 8, WALL_SIZE, 16);
        wallRight = new StaticBody(world, 8.95f, 8, WALL_SIZE, 16);
        roof = new StaticBody(world, 4.5f, 15.95f, 9, WALL_SIZE);

        platform = new KinematicBody(world, 4.5f, 0.5f, 2.5f, 0.5f);
        ball = new DynamicBody(world, 4.5f, 8, 0.3f, "ball0");
        ball.setImpulse(new Vector2(0, -1.5f));

        boxes.add(new StaticBody(world, 1.4f, 10, 1, 0.5f));
        boxes.add(new StaticBody(world, 3.6f, 10, 1, 0.5f));
        boxes.add(new StaticBody(world, 5.6f, 10, 1, 0.5f));
        boxes.add(new StaticBody(world, 7.7f, 10, 1, 0.5f));

        boxes.add(new StaticBody(world, 1.4f, 14, 1, 0.5f));
        boxes.add(new StaticBody(world, 3.6f, 14, 1, 0.5f));
        boxes.add(new StaticBody(world, 5.6f, 14, 1, 0.5f));
        boxes.add(new StaticBody(world, 7.7f, 14, 1, 0.5f));

        boxes.add(new StaticBody(world, 1.4f, 12, 1, 0.5f));
        boxes.add(new StaticBody(world, 3.6f, 12, 1, 0.5f));
        boxes.add(new StaticBody(world, 5.6f, 12, 1, 0.5f));
        boxes.add(new StaticBody(world, 7.7f, 12, 1, 0.5f));


        //Gdx.input.setInputProcessor(new MyInputProcessor(this));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.step(1/60f, 6, 2);
        // касания
        if(Gdx.input.justTouched()){
            touchText.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cameraText.unproject(touchText);

            if(btnBack.hit(touchText.x, touchText.y)){
                main.setScreen(main.screenMenu);
            }
        }
        if(Gdx.input.isTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            platform.vx = (touch.x-platform.body.getPosition().x)*5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            platform.vx = -0.5f;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            platform.vx = 0.5f;
        }

        // события
        platform.move();
        ball.move();
        for (int i = 0; i < boxes.size; i++) {
            if((Integer) boxes.get(i).getBody().getUserData() == -1){
                for (Fixture fixture : boxes.get(i).getBody().getFixtureList()) {
                    boxes.get(i).getBody().destroyFixture(fixture);
                }
                world.destroyBody(boxes.get(i).getBody());
                boxes.removeIndex(i);
            }
        }

        // отрисовка
        ScreenUtils.clear(0.5f, 0, 0.5f, 1);
        // картинки в Box2D
        debugRenderer.render(world, camera.combined);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < boxes.size; i++) {
            batch.draw(imgBrick[(Integer) boxes.get(i).getBody().getUserData()], boxes.get(i).getX(), boxes.get(i).getY(), boxes.get(i).getWidth(), boxes.get(i).getHeight());
        }
        batch.draw(imgss3, platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());
        batch.draw(imgball1, ball.getX(), ball.getY(), ball.getWidth()/2, ball.getHeight()/2,
                ball.getWidth(), ball.getHeight(), 1, 1, ball.getAngle(), 0, 0, 71, 71, false, false);
        batch.end();

        // тексты
        batchText.setProjectionMatrix(cameraText.combined);
        batchText.begin();
        //batchText.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        //fontLarge.draw(batchText, "Game!", 200, 1000);
        btnBack.font.draw(batchText, btnBack.text, btnBack.x, btnBack.y);
        batchText.end();
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

   /* void gameOver(){
        players[players.length-1].name = satSpaceArcade.playerName;
        players[players.length-1].score = kills;
        isGameOver = true;
        sortRecords2();
        saveRecords();
        saveRecordToDB();
        sortRecords1();
    }

    void gameStart(){
        fragments.clear();
        enemies.clear();
        shots.clear();
        ship.lives = shipLives;
        ship.isAlive = true;
        respawnShip();
        isGameOver = false;
        isShowGlobalRecords = false;
        kills = 0;
    }*/

    void sortRecords2() {
        class Cmp implements Comparator<Player>{
            @Override
            public int compare(Player p1, Player p2) {
                return p2.score-p1.score;
            }
        }
        Arrays.sort(players, new Cmp());
    }

    void saveRecords() {
        Preferences prefs = Gdx.app.getPreferences("SatArcadeRecords");
        for (int i = 0; i < players.length; i++) {
            prefs.putString("name"+i, players[i].name);
            prefs.putInteger("score"+i, players[i].score);
        }
        prefs.flush();
    }

    void loadRecords() {
        Preferences prefs = Gdx.app.getPreferences("SatArcadeRecords");
        for (int i = 0; i < players.length; i++) {
            if(prefs.contains("name"+i)) players[i].name = prefs.getString("name"+i);
            if(prefs.contains("score"+i)) players[i].score = prefs.getInteger("score"+i);
        }
    }

    void clearRecords() {
        for (int i = 0; i < players.length; i++) {
            players[i].name = "Noname";
            players[i].score = 0;
        }
    }
}
