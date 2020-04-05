package com.sapp.plappy;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;

public class menuState extends State
{
	private Texture background;
	private Texture playButton;
	private Vector2 buttonSize;
	public menuState(gameStateManager gsm){
		super(gsm);
		background = new Texture(Gdx.files.internal("background.png"));
		playButton = new Texture(Gdx.files.internal("playButton.png"));
		buttonSize = new Vector2(playButton.getWidth() * 2, playButton.getHeight() * 2);
	}
	
	@Override
	public void handleInput()
	{
		if (Gdx.input.justTouched()){
			gsm.Push(new playState(gsm));
		}
	}

	@Override
	public void update(float dt)
	{
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb)
	{
		sb.begin();
		sb.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
		sb.draw(playButton, (Main.WIDTH / 2) - (buttonSize.x / 2), Main.HEIGHT / 2, buttonSize.x, buttonSize.y);
		sb.end();
	}

	@Override
	public void dispose()
	{
		background.dispose();
		playButton.dispose();
		System.out.println("Menu State Disposed");
	}
	
}
