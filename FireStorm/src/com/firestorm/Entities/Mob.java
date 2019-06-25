package com.firestorm.Entities;

import java.awt.Graphics2D;
import java.awt.image.renderable.RenderableImage;

import com.firestorm.States.GameState;
import com.firestorm.World.Tile;
import com.firestorm.World.TileMap;
import com.firestorm.rendering.textures.Animation;
import com.firestorm.rendering.textures.Texture;

public abstract class Mob extends Entity{
	

	protected double dx,dy=5;
	
	protected double gravity;
	protected double jumpac;
	protected boolean canjump;
	protected boolean falling=true;
	protected double maxdy;
	protected Animation walkleft;
	protected Animation walkback;
	boolean movingleft=false;
	boolean movingright=false;
	public Mob(Texture sprite, double x, double y,TileMap tileMap,Animation walkleft,Animation walkback,Animation statf,Animation statb) {
		super(sprite, x, y,tileMap);
		falling=true;
		gravity=0.5;
		maxdy=7;
		jumpac=25;
	    this.walkleft=walkleft;
	    this.walkback=walkback;
	}
	
	
	
	
	@Override
	public void tick() {
		
		
		 if(dy>0)falling=true;
		 else if(dy<0)
			 falling=false;
			
		move();	
		fall();
	
		if(dx>0)movingleft=true;
		else
			movingleft=false;
		
		if(dx<0)movingright=true;
		else
			movingright=false;
		
		if(movingleft) 
			{
			
			walkleft.run();
			}
		if(movingright)
		{
			walkback.run();
		}
		
	}
	
	@Override
	public void Render(Graphics2D g,int offsetx,int offsety) {
		if(!movingleft&&!movingright)
		super.Render(g,offsetx,offsety);
		else
			if(movingleft)
			walkleft.render(g,x+offsetx,y+offsety);
			else
			{
				walkback.render(g,x+offsetx,y+offsety);
			}
	}
	public void move()
	{
		boolean horiz = tileMap.getTileCollision(texture.getWidth(),x,y,x+dx,y,false);
		boolean vert = tileMap.getTileCollision(texture.getHeight(),x,y,x,y+dy,true);
		if(!horiz)x+=dx;
		if(!vert)y+=dy;
	}
	
	public boolean isFalling()
	{
		return falling;
	}
	
	public void setVelocity(double dy)
	{
		this.dy=dy;
	}
	
	protected void fall()
	{
		
	
			dy+=gravity;
			if(dy>maxdy)
				dy=maxdy;
		
	}
	public void setFalling(boolean b) {
		falling=b;
		
	} 
	void jump()
	{
		if(canjump)
		{
			dy-=jumpac;
		}
		canjump=false;
	}
}
