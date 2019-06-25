package com.firestorm.World;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.firestorm.Game;
import com.firestorm.Entities.Coin;
import com.firestorm.Entities.Entity;
import com.firestorm.Entities.Player;
import com.firestorm.States.GameState;
import com.firestorm.rendering.textures.Animation;
import com.firestorm.rendering.textures.Texture;

public class TileMap 
{
	private static final int TILE_SIZE=64;
	private static int TILE_SIZE_BITS= 6;
	protected static int score=0;
	public int getScore() {
		return score;
	}



	private boolean gameover=false;
	
	private String name;
	private int width,height;
	private Tile[] tiles;
	private Coin[] coins;
	private Player player;
	private GameState gs;
	private ArrayList<Entity> entities;
	public TileMap(String name,GameState gs)
	{
		entities = new ArrayList<Entity>();
		this.gs=gs;
		load(name);
	}
	
	public Tile getTile(int x,int y)
	{
		if(x<0||x>=width||y<0||y>=height)return null;
		return tiles[x+y*width];
	}
	
	public void addEntity(Entity e)
	{
		if(!(e instanceof Player))entities.add(e);
	}
	public void removeEntity(Entity e)
	{
		if(!(e instanceof Player))entities.remove(e);
	}
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	public static int shrink(int pixels)
	{
		return pixels >> TILE_SIZE_BITS;
	}
	
	public static int enlarge(int tiles)
	{
		return tiles<<TILE_SIZE_BITS;
	}
	
	public void tick() {

		for(int i=0;i<entities.size();i++)
		{
			entities.get(i).tick();
		}
		player.tick();
		System.out.println(score);
		if(gameover)
		gs.setTimer(0);
	}
	
	public void render(Graphics2D g,int screenWidth,int screenHeight)
	{
		int mapWidth = enlarge(width);
		int mapHeight = enlarge(height);
		int offsetX = (int)((screenWidth/2)-(int)player.getX()-TILE_SIZE);
		int offsetY = (int)(screenHeight/2 - player.getY() - TILE_SIZE);
		offsetX = Math.min(offsetX,0);
		offsetX = Math.max(offsetX,screenWidth - mapWidth);
		offsetY = Math.min(offsetY,0);
		offsetY = Math.max(offsetY,screenHeight - mapHeight);
		int firstX = shrink(-offsetX);
		int lastX = firstX + shrink(screenWidth) + 1;
		int firstY = shrink(-offsetY);
		int lastY = firstY + shrink(screenHeight) + 1;
		
		for(int y = firstY;y<=lastY;y++)
		{
			for(int x=firstX;x<=lastX;x++)
			{
				Tile t = getTile(x,y);
				if(t!=null) t.render(g,enlarge(x)+offsetX,enlarge(y)+offsetY);
			}
		}
//		System.out.println("OffsetX: ");
//		System.out.println(offsetX);
//		System.out.println("OffsetY: ");
//		System.out.println(offsetY);
		
		for(int i=0;i<entities.size();i++)
			entities.get(i).Render(g,offsetX,offsetY);
		player.Render(g,offsetX,offsetY);
		
	}
	
	public boolean getTileCollision(int size,double oldX,double oldY,double newX,double newY,boolean vert)
	{
		double fromX = Math.min(oldX,newX);
		double fromY = Math.min(oldY,newY);
		double toX = Math.max(oldX,newX);
		double toY = Math.max(oldY,newY);
		 
		
		int fromTileX = shrink((int)fromX);
		int fromTileY = shrink((int)fromY);
		int toTileX = shrink((int)toX+size-1);
		int toTileY = shrink((int)toY + size-1);
		
		for(int y=fromTileY;y<=toTileY;y++)
		{
			for(int x = fromTileX;x<=toTileX;x++)
			{
				
				if(x<0||x>=width||(getTile(x,y)!=null&&getTile(x,y).isSolid()))
				{
					if(vert)
					{
						if(player.isFalling())
						{
							player.setY(enlarge(y)-size);
							player.setcanJump(true);
						}
						else
						{
							player.setY(enlarge(y+1));
							player.setVelocity(0);
						}
					}
					return true;
				}
				if(x<0||x>=width||(getTile(x,y)!=null&&getTile(x,y).getId()==0xFFFF0000))
				{
						
        			setTile(x, y,null);
        			Game.SCORE++;
        			
					
				}
				if(x<0||x>=width||(getTile(x,y)!=null&&getTile(x,y).getId()==0xFF00FFFF))
				{
						
        			gameover=true;
					
				}
				
				
			}
		}
		return false;
	}
	
	public void setTile(int x,int y,Tile tile)
	{
		tiles[x+y*width]=tile;
	}
	
	
	private void load(String name)
	{
		BufferedImage image=null;
		try {
			image = ImageIO.read(new File("./resources/textures/"+name+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.name=name;
		this.width=image.getWidth();
		this.height = image.getHeight();
		tiles = new Tile[width*height];
		int[] pixels=image.getRGB(0,0, width, height,null,0,width);
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				int id = pixels[x+y*width];
				if(id==0xFF0000FF)
				{
					player = new Player(enlarge(x),enlarge(y),this);
				}
			
				else if(Tile.getFromId(id)!=null)
				{
//					System.out.println("chnu");
					setTile(x,y,Tile.getFromId(id));
				}
				
			}
		}
	}


}
