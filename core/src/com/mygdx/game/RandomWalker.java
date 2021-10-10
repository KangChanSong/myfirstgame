package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import jdk.jfr.BooleanFlag;

public class RandomWalker extends ApplicationAdapter {

    ShapeRenderer shapeRenderer;

    float x;
    float y;

    float xSpeed = 1;
    float ySpeed = 1;

    float xBefore;
    float yBefore;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
        xBefore = x;
        yBefore = y;
        System.out.println("created");
    }

    @Override
    public void render() {

        x += createRandom() * Gdx.graphics.getDeltaTime() * 100;
        y += createRandom() * Gdx.graphics.getDeltaTime() * 100;

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.circle(x, y, 100);
        shapeRenderer.end();

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();;
    }

    private int createRandom(){
        return MathUtils.random(0,1) == 0 ? -1 : 1;
    }
}
