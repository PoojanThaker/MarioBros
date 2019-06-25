package com.firestorm.rendering.textures;

import java.awt.Graphics2D;

public class Animation {
	private int count=0;
	private int index=0;
	private int speed=5;
	private int numframes;
	private Texture currentFrame;
	private Texture[] frames;
	
	public Animation(int speed,Texture... frames)
	{
		this.speed=speed;
		this.frames = frames;
		this.numframes = frames.length;
	}
	
	private void nextFrame()
	{
		currentFrame = frames[count++];
		if(count>=numframes)
			count=0;
	}
	
	public void run()
	{
		index++;
		if(index>speed)
		{
			index=0;
		    nextFrame();
		}
	}
	
	public void render(Graphics2D g,double x,double y)
	{
		if(currentFrame != null)
			currentFrame.render(g, x, y);
	}
}
