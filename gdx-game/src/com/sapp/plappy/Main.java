package com.sapp.plappy;

import com.badlogic.gdx.*;
import com.badlogic.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.audio.*;

public class Main extends ApplicationAdapter
{
	public static int WIDTH;
	public static int HEIGHT;
	
	public static final String TITLE = "FLAPPY";
	
	private gameStateManager gsm;
	private SpriteBatch batch;
	private Music backsound;
	
	
	@Override
	public void create()
	{	
		batch = new SpriteBatch();
		gsm = new gameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		gsm.Push(new menuState(gsm));
		backsound = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		backsound.setLooping(true);
		backsound.setVolume(0.3f);
		backsound.play();
	}

	@Override
	public void render()
	{	
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.Update(Gdx.graphics.getDeltaTime());
		gsm.Render(batch);
	}

	@Override
	public void dispose()
	{
		backsound.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
		
	}

	@Override
	public void resume()
	{
		
	}

	@Override
	public void pause()
	{
		
	}
	
	
}
