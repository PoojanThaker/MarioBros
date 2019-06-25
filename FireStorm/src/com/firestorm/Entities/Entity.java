package com.firestorm.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.firestorm.States.GameState;
import com.firestorm.World.TileMap;
import com.firestorm.rendering.textures.Texture;


public abstract class Entity 
{
	protected double x,y;
	protected Texture texture;
	protected TileMap tileMap;
	public Entity(Texture texture, double x, double y,TileMap tileMap) {
		super();
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.tileMap=tileMap;
		this.tileMap.addEntity(this);
	}
	
	public abstract void tick();
	
	
	public void Render(Graphics2D g,int offsetx,int offsety)
	{
		texture.render(g, x+offsetx, y+offsety);
//		 
	}
	
     public double getX()
     {
    	 return x;
     }
     
     public double getY()
     {
    	 return y;
     }
	
	
}
