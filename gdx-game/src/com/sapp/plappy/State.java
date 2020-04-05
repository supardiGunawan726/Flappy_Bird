package com.sapp.plappy;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;

public abstract class State
{
	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected gameStateManager gsm;
	
	protected State(gameStateManager gsm){
		
		this.gsm = gsm;
		cam = new OrthographicCamera();
		mouse = new Vector3();
		
	}
	
	protected abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	public abstract void dispose();
}
