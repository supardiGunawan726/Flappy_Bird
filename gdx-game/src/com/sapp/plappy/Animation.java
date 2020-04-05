package com.sapp.plappy;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;

public class Animation
{
	private Array<TextureRegion> frames;
	private float maxFramesTimes;
	private float currentFrameTime;
	private int frameCount;
	private int frame;
	
	public Animation(TextureRegion region, int frameCount, float cycleTime){
		frames = new Array<TextureRegion>();
		int frameWidth = region.getRegionWidth() / frameCount;
		for (int i = 0; i < frameCount; i++){
			frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
		}
		this.frameCount = frameCount;
		maxFramesTimes = cycleTime / frameCount;
		frame = 0;
		
	}
	
	public void update(float dt){
		currentFrameTime += dt;
		if (currentFrameTime > maxFramesTimes){
			frame++;
			currentFrameTime = 0;
		}
		if (frame >= frameCount){
			frame = 0;
		}
	}
	
	public TextureRegion getFrame(){
		return frames.get(frame);
	}
}
