package com.firestorm.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import com.firestorm.rendering.textures.Texture;

public class Tile {
	
	private static final Texture terrain = new Texture("Sprite_sheet_terrain");
	private static final Map<Integer,Tile> tileMap = new HashMap<Integer,Tile>();
	protected double x,y;
	protected Texture sprite;
	protected boolean solid;
	protected int id;
	
	public static final Tile title1 = new Tile(0xFFFFFFFF,new Texture(terrain,1,1,64,64),true);
	public static final Tile title2 = new Tile(0xFF00FF00,new Texture(terrain,2,1,64,64),false);
	public static final Tile title3 = new Tile(0xFFFF0000,new Texture(terrain,1,2,64,64),false);
	public static final Tile title4 = new Tile(0xFF00FFFF,new Texture(terrain,2,2,64,64),false);
//	public static final Tile title2 = new Tile(0xFF000000,new Sprite(terrain,1,2));
	
	private Tile(int id,Texture sprite,boolean solid)
	{
		this.id=id;
		this.sprite=sprite;
		this.solid=solid;
		tileMap.put(id,this);
	}
	public Tile(int id,float x, float y) {
		this.sprite=getFromId(id).sprite;
		this.x = x*sprite.getWidth();
		this.y = y*sprite.getHeight();
		this.solid = true;
	}
	
	public void render(Graphics2D g,int x,int y)
	{
		sprite.render(g, x, y);
 		
	}
	


	public static Tile getFromId(int id)
	{
		return tileMap.get(id);
	}
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return solid;
	}
	public int getId()
	{
		return id;
	}
}
