package com.failed_coder.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

public class Flap extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture []birds;
	Texture gameover;
	BitmapFont font;
	//	ShapeRenderer shapeRenderer;
	Circle birdCircle;
	Rectangle[] top_tube;
	Rectangle[] bottom_tube;
	int flapstate = 0;
	int gamestate = 0;
	float velocity = 0;
	float gravity = 1.8f;
	float birdY = 0;
	Texture bottomtube;
	Texture uptube;
	float gap = 300;
	float maxoffset;
	Random random;
	int score = 0;
	int scorepanel = 0;
	int numberoftubes = 4;
	Texture back;
	float tubevelocity = 4;
	float []tubeX = new float[numberoftubes];
	float []tubeoffset = new float[numberoftubes];
	float distancebetweentubes;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background-night.png");
		birds = new Texture[2];
		gameover = new Texture("gameover.png");
//		shapeRenderer = new ShapeRenderer();
		birds[0] = new Texture("bird_1@2x.png");
		birds[1] = new Texture("bird_3@2x.png");
		birdCircle = new Circle();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);
		top_tube = new Rectangle[numberoftubes];
		bottom_tube = new Rectangle[numberoftubes];
		bottomtube = new Texture("pipe_bottom@2x.png");
		back = new Texture("message.png");
		uptube = new Texture("pipe_top@2x.png");
		maxoffset = Gdx.graphics.getHeight()/2 - gap/2 - 100;
		random = new Random();
		distancebetweentubes = Gdx.graphics.getWidth() *3 / 4;
		StartGame();
	}

	public void StartGame(){
		birdY = Gdx.graphics.getHeight()/2-birds[0].getHeight()/2;
		for(int i=0;i<numberoftubes;i++){
			tubeoffset[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-gap - 200);
			tubeX[i] = Gdx.graphics.getWidth()/2 - uptube.getWidth()/2 + Gdx.graphics.getWidth()+(i)*distancebetweentubes;
			top_tube[i] = new Rectangle();
			bottom_tube[i] = new Rectangle();
		}
	};

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		if(gamestate == 1){
			if(Gdx.input.justTouched()){
				velocity = -22;
				if(flapstate == 0){
					flapstate = 1;
				}else {
					flapstate = 0;
				}
			}
			if(tubeX[scorepanel] < Gdx.graphics.getWidth()/2){
				score ++;
				Gdx.app.log("score", String.valueOf(score));
				scorepanel = (scorepanel+1)%4;
			}
			for(int i=0;i<numberoftubes;i++) {
				if(tubeX[i] < -uptube.getWidth()){
					tubeoffset[i] = (random.nextFloat()-0.5f) * (Gdx.graphics.getHeight()-gap - 200);
					tubeX[i] += distancebetweentubes*numberoftubes;
				}else {
					tubeX[i] = tubeX[i] - tubevelocity;
				}
				batch.draw(uptube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeoffset[i]);
				batch.draw(bottomtube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeoffset[i]);
				top_tube[i].set(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeoffset[i],uptube.getWidth(),uptube.getHeight());
				bottom_tube[i].set(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeoffset[i],bottomtube.getWidth(),bottomtube.getHeight());
			}
			if(birdY > 0){
				velocity = velocity + gravity;
				birdY -= velocity;
			}else {
				gamestate = 2;
			}
		}else if(gamestate == 0){
			batch.draw(back,Gdx.graphics.getWidth()/2-back.getWidth()/2,Gdx.graphics.getHeight()/2-back.getWidth()/2);
			if(Gdx.input.justTouched()){
				gamestate = 1;
			}
		}else {
			batch.draw(gameover,Gdx.graphics.getWidth()/2-gameover.getWidth()/2,Gdx.graphics.getHeight()/2-gameover.getWidth()/2);
			if(Gdx.input.justTouched()){
				gamestate = 1;
				Gdx.app.log("k","lk");
				StartGame();
				score = 0;
				velocity = 0;
				scorepanel = 0;
			}
		}

//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		shapeRenderer.setColor(Color.RED);
		font.draw(batch,String.valueOf(score),100,200);
		batch.draw(birds[flapstate],Gdx.graphics.getWidth()/2 - birds[flapstate].getWidth()/2,birdY);
		batch.end();
		birdCircle.set(Gdx.graphics.getWidth()/2,birdY+birds[flapstate].getHeight()/2,birds[flapstate].getWidth()/2);
//		shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);
		for(int i=0;i<numberoftubes;i++){
//			shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeoffset[i],uptube.getWidth(),uptube.getHeight());
//			shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomtube.getHeight() + tubeoffset[i],bottomtube.getWidth(),bottomtube.getHeight());
			if(Intersector.overlaps(birdCircle,top_tube[i])||Intersector.overlaps(birdCircle,bottom_tube[i])){
				Gdx.app.log("Collision","Yes");
				gamestate = 2;
			}
		}
//		shapeRenderer.end();
	}

}

