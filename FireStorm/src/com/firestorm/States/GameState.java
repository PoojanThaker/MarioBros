package com.firestorm.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.firestorm.Game;
import com.firestorm.Entities.Entity;
import com.firestorm.Entities.Player;
import com.firestorm.World.Tile;
import com.firestorm.World.TileMap;
import com.firestorm.rendering.textures.Texture;
import com.firestorm.utils.TextTool;

public class GameState implements State {

	
	private TileMap tileMap;
	private static int timer=500;
	
	public static void setTimer(int timer) {
		GameState.timer = timer;
	}


	@Override
	public void init() {
		this.timer=500;
		tileMap = new TileMap("level1",this);
	}
	
	
	@Override
	public void enter() {
		// TODO Auto-generated method stub
		this.timer=500;
		tileMap = new TileMap("level1",this);
		
	}

	int tickcount=0;
	@Override
	public void tick(StateManager sm) {

		tileMap.tick();
		tickcount++;
		if(tickcount==60)
		{
			tickcount=0;
			timer--;
		}
		if(timer==0)
		{
			sm.setState("Gameover");
		}
	}

	@Override
	public void render(Graphics2D g) {
		
		
		 tileMap.render(g,Game.WIDTH,Game.HEIGHT);
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,20),"Score :",20,65);
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,20),Integer.toString(Game.SCORE),100,65);
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,20),"Time :",470,65);
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,20),Integer.toString(timer),540,65);
		
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		tileMap=null;
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "level1";
	}
 
	
}
