package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyFirstGame extends ApplicationAdapter {

	ShapeRenderer renderer;

	@Override
	public void create() {
		renderer = new ShapeRenderer();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(0, 1, 0, 1);
		renderer.circle(200, 100, 75);
		renderer.end();;
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}
}
