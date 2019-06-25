package com.firestorm.rendering.textures;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;



public class Texture {
	
	private static final Map<String,BufferedImage> texMap = new HashMap<String,BufferedImage>();
	private BufferedImage image;
	private String fileName;
	private int width,height;
	
	public Texture(String FileName)
	{
		this.fileName=FileName;
		BufferedImage oldTexture = texMap.get(FileName);
		 if(oldTexture!=null)
		 {
			 this.image=oldTexture;
		 }
		 else
		 {
			 try {
				image = ImageIO.read(new File("./resources/textures/"+ FileName+ ".png"));
				texMap.put(FileName, image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }	
		 this.width=image.getWidth();
		 this.height = image.getHeight();
	}
	
	public Texture(Texture spriteSheet,int x,int y,int width,int height)
	{
		this.width=width;
		this.height=height;
		String key = spriteSheet.fileName + "_" + x + "_" + y;
		BufferedImage old = texMap.get(key);
		if(old!=null)this.image=old;
		else
		this.image = spriteSheet.image.getSubimage(x*width-width,y*height-height, width, height);
	}
	
	public Texture(Texture spriteSheet,int x,int y,int size)
	{
		this(spriteSheet,x,y,size,size);
	}
	
	public void render(Graphics2D g,double x,double y)
	{
		g.drawImage(image,(int)x,(int)y,null);
		
	}

	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}

}
