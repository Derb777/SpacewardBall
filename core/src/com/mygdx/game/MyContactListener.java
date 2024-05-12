package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {
    ScreenGame main;

    public MyContactListener(ScreenGame main) {
        this.main = main;
    }

    @Override
    public void beginContact(Contact contact) {
    // Обработка начала контакта
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();

        // Проверка, что это столкновение между DynamicBody
        if (bodyA.getType() == BodyDef.BodyType.DynamicBody && bodyB.getType() == BodyDef.BodyType.DynamicBody) {
            // Момент начала столкновения
            //main.sndKnock.play();
        }

        // Проверка, что это столкновение между DynamicBody
        if (bodyA.getType() == BodyDef.BodyType.DynamicBody && bodyB.getType() == BodyDef.BodyType.StaticBody){
            bodyB.setUserData((Integer)bodyB.getUserData()-1);
        }
        if(bodyA.getType() == BodyDef.BodyType.StaticBody && bodyB.getType() == BodyDef.BodyType.DynamicBody) {
            bodyA.setUserData((Integer)bodyA.getUserData()-1);
            // Момент начала столкновения
            //main.sndChpok.play();
        }
    }

    @Override
    public void endContact(Contact contact) {
// Обработка окончания контакта
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();

        // Проверка, что это столкновение между DynamicBody
        if (bodyA.getType() == BodyDef.BodyType.DynamicBody && bodyB.getType() == BodyDef.BodyType.DynamicBody) {
            // Момент окончания столкновения
            if(bodyA.getUserData().equals("cue")) fixtureA.setSensor(true);
            if(bodyB.getUserData().equals("cue")) fixtureB.setSensor(true);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
