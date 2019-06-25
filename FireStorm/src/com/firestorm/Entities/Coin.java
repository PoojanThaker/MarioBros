package com.firestorm.Entities;

import java.awt.Graphics2D;

import com.firestorm.World.TileMap;
import com.firestorm.rendering.textures.Animation;
import com.firestorm.rendering.textures.Texture;

public class Coin extends Entity {

	
	Animation anime;
	public Coin(double x,double y,TileMap tilemap,Animation anime)
	{
		super(null,x,y,tilemap);
		this.anime=anime;
		
	}
	

	void render(Graphics2D g)
	{
		anime.render(g, x, y);
	}


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}
