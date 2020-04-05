package com.sapp.plappy;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.audio.*;

public class bird
{
	public static final int MOVEMENT = 80;
	public static final int GRAVITY = -15;
	private Vector2 position;
	private Vector2 size;
	private Vector2 velocity;
	
	private Texture birdTexture;
	private Rectangle bounds;
	
	private Animation birdAnimation;
	
	private Sound flap;
	
	public bird(int x, int y){
		
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		
		birdTexture = new Texture(Gdx.files.internal("flappy.png"));
		birdAnimation = new Animation(new TextureRegion(birdTexture), 3, 0.5f);
		
		bounds = new Rectangle(x, y, birdTexture.getWidth() / 3, birdTexture.getHeight());
		
		flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
		flap.setVolume(1, 0.5f);
		
	}
	
	public void update(float dt){
		birdAnimation.update(dt);
		
		if (position.y > 0){
		velocity.add(0, GRAVITY);
		}
		velocity.scl(dt);
		
		position.add(MOVEMENT * dt, velocity.y);
		
		if (position.y < 0){
			position.y = 0;
		}
		
		velocity.scl(1/dt); 
		
		bounds.setPosition(position.x, position.y);
		
	}
	
	public void jump(){
		velocity.y = 200;
		flap.play();
	}
	
	public void dispose(){
		birdTexture.dispose();
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public TextureRegion getTexture()
	{
		return birdAnimation.getFrame();
	}

	public Vector2 getPosition()
	{
		return position;
	}
	
	public Vector2 getSize(){
		return size;
	}
	
}
