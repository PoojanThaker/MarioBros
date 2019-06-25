package com.firestorm.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.firestorm.Game;

public class TextTool {
	
	public static void write(Graphics g,Color c,Font f,String s,int x,int y)
	{
		g.setColor(c);
		g.setFont(f);
		g.drawString(s, x, y);
	}
	
	public static void write(Graphics g,Color c,Font f,String s)
	{
		FontMetrics fm = g.getFontMetrics(f);
		int x = (Game.WIDTH-fm.stringWidth(s))/2;
		int y = (Game.HEIGHT-fm.getHeight())/2-fm.getAscent();
		write(g,c,f,s,x,y);
	}
	
	public static void write(Graphics g,Color c,Font f,String s,int y)
	{
		FontMetrics fm = g.getFontMetrics(f);
		int x = (Game.WIDTH-fm.stringWidth(s))/2;
		write(g,c,f,s,x,y);
	}
	
}
