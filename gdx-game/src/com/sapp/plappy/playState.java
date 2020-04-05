package com.sapp.plappy;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.audio.*;

public class playState extends State
{
	
	private static final int TUBE_SPACING = 125;
	private static final int TUBE_COUNT = 4;
	private static final int GROUND_OFFSET = -5;
	
	private bird bird;
	private Texture background;
	
	private Array<Tube> tubes;
	
	private Texture ground;
	private Vector2 groundpos1;
	private Vector2 groundpos2;
	
	private Sound hit;
	
	public playState(gameStateManager gsm){
		super(gsm);
		cam.setToOrtho(false, Main.WIDTH/4, Main.HEIGHT/4);
		bird = new bird((int)cam.viewportWidth / 4, (int)cam.viewportHeight / 2);
		background = new Texture(Gdx.files.internal("background.png"));
		
		tubes = new Array<Tube>();
		
		ground = new Texture(Gdx.files.internal("ground.png"));
		groundpos1 = new Vector2(cam.position.x - (cam.viewportWidth / 2), GROUND_OFFSET);
		groundpos2 = new Vector2(cam.position.x - (cam.viewportWidth / 2) + ground.getWidth(), GROUND_OFFSET);
		
		hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));
		
		for(int i = 1; i <= TUBE_COUNT; i++){
			tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
		}
	}

	@Override
	public void handleInput()
	{
		if (Gdx.input.justTouched()) {
			bird.jump();
		}
	}

	@Override
	public void update(float dt)
	{
		handleInput();
		updateGround();
		bird.update(dt);
		cam.position.x = bird.getPosition().x + 80;
		
		for (int i = 0; i < tubes.size; i++){
		Tube tube = tubes.get(i);
		
			if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTubeUp().x + tube.getTubeUp().getHeight() ){
				tube.reposition(tube.getPosTubeUp().x + ((tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
				}
			
			if (tube.collides(bird.getBounds())){
				hit.play(0.5f);
				gsm.Set(new playState(gsm));
				}
		}
		
		if(bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET){
			hit.play(0.5f);
			gsm.Set(new playState(gsm));
		}
		cam.update();
		
	}

	@Override
	public void render(SpriteBatch sb)
	{
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0, cam.viewportWidth, cam.viewportHeight);
	
		for (Tube tube : tubes){
			sb.draw(tube.getTubeUp(), tube.getPosTubeUp().x, tube.getPosTubeUp().y);
			sb.draw(tube.getTubeDown(), tube.getPosTubeDown().x, tube.getPosTubeDown().y);
			}
			
		sb.draw(ground, groundpos1.x, groundpos1.y);
		sb.draw(ground, groundpos2.x, groundpos2.y);
		
		sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
		sb.end();
	}

	@Override
	public void dispose()
	{
		background.dispose();
		bird.dispose();
		ground.dispose();
		for(Tube tube : tubes){
			tube.dispose();
			System.out.println("PlayState Disposed");
		}
	}
	
	public void updateGround(){
		if(cam.position.x - (cam.viewportWidth / 2) > groundpos1.x + ground.getWidth()){
			groundpos1.add(ground.getWidth() * 2, 0);
		}
		if(cam.position.x - (cam.viewportWidth / 2) > groundpos2.x + ground.getWidth()){
			groundpos2.add(ground.getWidth() * 2, 0);
		}
	}
	
}
