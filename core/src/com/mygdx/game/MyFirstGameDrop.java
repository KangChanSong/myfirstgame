package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class MyFirstGameDrop extends ApplicationAdapter {

	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastDropTime;

	@Override
	public void create() {
		loadAssets();
		rainMusic.setLooping(true);
		rainMusic.play();
		loadCam();
		createBucketRectangle();
		createRainDropAndSpawnFirst();
	}

	@Override
	public void render() {
		// clear the screen with dark blue color.
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket
		// and all drops
		drawWithBatch();

		processUserInput();

		moveRainDrop();

	}


	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}

	private void loadAssets() {
		dropImage = new Texture(Gdx.files.internal("drop.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	}

	private void loadCam(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
	}

	private void createBucketRectangle() {
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 /2 ;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;
	}

	private void createRainDropAndSpawnFirst(){
		raindrops = new Array<Rectangle>();
		spawnRainDrop();
	}

	private void spawnRainDrop(){
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}

	private void drawWithBatch(){
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		for(Rectangle raindrop: raindrops){
			batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		batch.end();
	}

	private void processUserInput(){

		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = (int) (touchPos.x - 64 / 2);
		}

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200* Gdx.graphics.getDeltaTime();

		if(bucket.x < 0) bucket.x = 0;
		if(bucket.x > 800-64) bucket.x = 800 - 64;

	}


	private void moveRainDrop() {
		if(TimeUtils.nanoTime() - lastDropTime > 500000000) spawnRainDrop();

		for(Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext();){
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0) iter.remove();
			if(raindrop.overlaps(bucket)){
				dropSound.play();
				iter.remove();
			}
		}
	}
}
