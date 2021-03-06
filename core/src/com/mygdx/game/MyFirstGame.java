package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyFirstGame extends ApplicationAdapter {

	ShapeRenderer shapeRenderer;

	float circleX = 200;
	float circleY = 100;

	float xSpeed = 1000;
	float ySpeed = 500;

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render() {

		circleX += xSpeed * Gdx.graphics.getDeltaTime();
		circleY += ySpeed * Gdx.graphics.getDeltaTime();

		if(circleX < 0 || circleX > Gdx.graphics.getWidth()) xSpeed *= -1;
		if(circleY < 0 || circleY > Gdx.graphics.getHeight()) ySpeed *= -1;

		Gdx.gl.glClearColor(.25f,.25f,.25f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.circle(circleX, circleY, 75);
		shapeRenderer.end();
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
}
