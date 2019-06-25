package com.firestorm.rendering.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.firestorm.Game;
import com.firestorm.utils.TextTool;

public class Button extends Rectangle 
{
	private Font selectedfont;
	private Font unselectedfont;
	private Color unselectedcolor;
	private Color selectedcolor;
	private boolean selected;
	private String text;
	private int textY;
	private int padding;
	public Button(String text, int textY, Font unselectedfont, Font selectedfont, Color unselectedcolor,
			Color selectedcolor,int padding) {
		super();
		this.text = text;
		this.textY = textY;
		this.unselectedfont = unselectedfont;
		this.selectedfont = selectedfont;
		this.unselectedcolor = unselectedcolor;
		this.selectedcolor = selectedcolor;
		this.padding=padding;
	}
	
	public void setSelected(boolean selected)
	{
		this.selected=selected;
	}
	
	public void Render(Graphics2D g)
	{
		FontMetrics fm ;
		if(selected)
		{
			TextTool.write(g,selectedcolor,selectedfont,text,textY);
			fm = g.getFontMetrics(selectedfont);
		}
		else
		{
			TextTool.write(g,unselectedcolor,unselectedfont,text,textY);
			fm = g.getFontMetrics(unselectedfont);
		}
		
		this.x = (Game.WIDTH - fm.stringWidth(text))/2-padding;
		this.y = textY-fm.getMaxAscent()-padding;
		this.width = fm.stringWidth(text)+2*padding;
		this.height = fm.getMaxAscent()+2*padding;
	    g.drawRect(x, y,width,height);
		
	}
	
}
