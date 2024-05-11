package com.mygdx.game;

import static com.mygdx.game.SpacewardBall.*;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicBody {
    private float x, y;
    private float width, height;
    private float r;
    Body body;
    public int type;

    public DynamicBody(World world, float x, float y, float r, String user) {
        this.x = x;
        this.y = y;
        this.r = r;
        width = height = 2*r;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearDamping = 0f;
        bodyDef.angularDamping = 0f;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);
        body.setUserData(user);

        CircleShape shape = new CircleShape();
        shape.setRadius(r);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public DynamicBody(World world, float x, float y, float width, float height, String user) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.linearDamping = 0.3f;
        bodyDef.angularDamping = 0.3f;
        bodyDef.position.set(x, y);

        body = world.createBody(bodyDef);
        body.setUserData(user);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2, height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.5f;

        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
    }

    public float getX() {
        return body.getPosition().x-width/2;
    }

    public float getY() {
        return body.getPosition().y-height/2;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getAngle() {
        return body.getAngle()* MathUtils.radiansToDegrees;
    }

    public void setPosition(float x, float y) {
        body.setTransform(x, y, body.getAngle());
    }

    public boolean hit(float tx, float ty) {
        for(Fixture f: body.getFixtureList()){
            if(f.testPoint(tx, ty)){
                return true;
            }
        }
        return false;
    }

    public void setImpulse(Vector2 p) {
        body.applyLinearImpulse(p, body.getWorldCenter(), true);
    }

    public void move() {
        if(Math.abs(body.getLinearVelocity().x) < Math.abs(0.001f)) {
            body.applyLinearImpulse(new Vector2(MathUtils.random(-0.2f, 0.2f), 0), body.getWorldCenter(), true);
        }
        if(Math.abs(body.getLinearVelocity().y) < Math.abs(0.001f)) {
            body.applyLinearImpulse(new Vector2(0, MathUtils.random(-0.5f, 0.5f)), body.getWorldCenter(), true);
        }
    }
}
