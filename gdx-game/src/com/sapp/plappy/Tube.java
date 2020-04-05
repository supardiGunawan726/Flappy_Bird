package com.sapp.plappy;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.badlogic.gdx.*;

public class Tube
{
	public static final int TUBE_WIDTH = 52;
	private static final int FLUCTUATION = 200;
	private static final int TUBE_GAP = 50;
	private static final int LOWEST_OPENING = 90;
	
	private Texture tubeDown, tubeUp;
	private Vector2 posTubeDown, posTubeUp;
	
	private Random rand;
	
	private Rectangle boundsUp, boundsDown;
	
	public Tube(float x){
		
		tubeDown = new Texture(Gdx.files.internal("tubeDown.png"));
		tubeUp = new Texture(Gdx.files.internal("tubeUp.png"));
		
		rand = new Random();
		
		posTubeUp = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
		posTubeDown = new Vector2(x, posTubeUp.y - TUBE_GAP - tubeDown.getHeight());
		
		boundsUp = new Rectangle(posTubeUp.x , posTubeUp.y, tubeUp.getWidth(), tubeUp.getHeight());
		boundsDown = new Rectangle(posTubeDown.x , posTubeDown.y, tubeDown.getWidth(), tubeDown.getHeight());
		
		
		
	}
	
	public void reposition(float x){
		posTubeUp.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
		posTubeDown.set(x, posTubeUp.y - TUBE_GAP - tubeDown.getHeight());
		
		boundsUp.setPosition(posTubeUp.x, posTubeUp.y);
		boundsDown.setPosition(posTubeDown.x, posTubeDown.y);
		
	}
	
	public boolean collides(Rectangle player){
		return player.overlaps(boundsUp) || player.overlaps(boundsDown);
	}
	
	public void dispose(){
		tubeUp.dispose();
		tubeDown.dispose();
	}

	public void setPosTubeDown(Vector2 posTubeDown)
	{
		this.posTubeDown = posTubeDown;
	}

	public Vector2 getPosTubeDown()
	{
		return posTubeDown;
	}

	public void setPosTubeUp(Vector2 posTubeUp)
	{
		this.posTubeUp = posTubeUp;
	}

	public Vector2 getPosTubeUp()
	{
		return posTubeUp;
	}

	public void setTubeDown(Texture tubeDown)
	{
		this.tubeDown = tubeDown;
	}

	public Texture getTubeDown()
	{
		return tubeDown;
	}

	public void setTubeUp(Texture tubeUp)
	{
		this.tubeUp = tubeUp;
	}

	public Texture getTubeUp()
	{
		return tubeUp;
	}
}
