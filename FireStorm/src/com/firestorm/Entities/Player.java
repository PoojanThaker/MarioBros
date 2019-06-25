package com.firestorm.Entities;

import java.awt.event.KeyEvent;

import com.firestorm.States.GameState;
import com.firestorm.World.TileMap;
import com.firestorm.input.KeyInput;
import com.firestorm.rendering.textures.Animation;
import com.firestorm.rendering.textures.Texture;

public class Player extends Mob {

	public Player(double x, double y,TileMap tileMap) {
		super(new Texture(new Texture("playersprite"),1,1,64), x, y,tileMap,
				new Animation(3,new Texture(new Texture("playersprite"),1,1,64),new Texture(new Texture("playersprite"),2,1,64),new Texture(new Texture("playersprite"),3,1,64),new Texture(new Texture("playersprite"),4,1,64)),
				new Animation(3,new Texture(new Texture("walkback"),1,1,64),new Texture(new Texture("walkback"),2,1,64),new Texture(new Texture("walkback"),3,1,64),new Texture(new Texture("walkback"),4,1,64)),
				new Animation(1,new Texture(new Texture("playersprite"),1,1,64)),new Animation(1,new Texture(new Texture("walkback"),1,1,64)));
		// TODO Auto-generated constructor stub
	}
	
	boolean wasmovingforward=true;
	
	
	@Override
	public void tick()
	{
		
		if(KeyInput.iskeydown(KeyEvent.VK_UP))
		{
			jump();
		}
//		if(KeyInput.iskeydown(KeyEvent.VK_DOWN))
//		{
//			dy=+2;
//		}
		if(KeyInput.iskeydown(KeyEvent.VK_RIGHT))
		{
			dx=+5;
			wasmovingforward=true;
		}
		if(KeyInput.iskeydown(KeyEvent.VK_LEFT))
		{
			dx=-5;
			wasmovingforward=false;
		}
		
//		if(KeyInput.wasReleased(KeyEvent.VK_UP))
//		{
//			dy=0;
//		}
		if(KeyInput.wasReleased(KeyEvent.VK_DOWN))
		{
			dy=0;
		}
		if(KeyInput.wasReleased(KeyEvent.VK_LEFT))
		{
			dx=0;
		}
		if(KeyInput.wasReleased(KeyEvent.VK_RIGHT))
		{
			dx=0;
		}
		
		super.tick();
	}


	public void setY(int i) {
		// TODO Auto-generated method stubthis
		this.y=i;
	}


	public void setcanJump(boolean b) {
		// TODO Auto-generated method stub
		canjump=b;
	}


	


	
}
