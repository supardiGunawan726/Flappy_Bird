package com.sapp.plappy;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;

public class gameStateManager{
	private Stack<State> states;
	
	public gameStateManager(){
		
		states = new Stack<State>();
		
	}
	
	public void Push(State state){
		states.push(state);
	}
	
	public void Pop(){
		states.pop().dispose();
	}
	
	public void Set(State state){
		states.pop().dispose();
		states.push(state);
	}
	
	public void Update(float dt){
		states.peek().update(dt);
	}
	
	public void Render(SpriteBatch sb){
		states.peek().render(sb);
	}
}
