package com.firestorm;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.firestorm.States.GameOver;
import com.firestorm.States.GameState;
import com.firestorm.States.MenuState;
import com.firestorm.States.StateManager;
import com.firestorm.input.KeyInput;
import com.firestorm.input.MouseInput;
import com.firestorm.rendering.textures.Texture;

public class Game extends Canvas implements Runnable {

	public static final String TITLE = "FireStorm";
	public static final int WIDTH = 640;
	public static final int HEIGHT = (WIDTH*3)/(4) ;
	private static boolean running;
	private BufferedImage background;
	private StateManager statemanager;
	public static int SCORE=0;
	
	 
	public Game()
	{
		 
		MouseInput mi = new MouseInput();
		addMouseListener(mi);
		addMouseMotionListener(mi);
		addKeyListener(new KeyInput());
		statemanager = new StateManager();
		statemanager.addState(new MenuState());
		statemanager.addState(new GameState());
		statemanager.addState(new GameOver());
		try {
			background = ImageIO.read(new File("./resources/textures/background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected void start()
	{
		if(running) return;
		running=true;
		new Thread(this,"Main").start();
		
	}
	public static void stop()
	{
		if(!running)return;
			running = false;
	}
	 
	private void tick()
	{
		
		 statemanager.tick();
		
	}
	
	private void render()
	{
		BufferStrategy bs = getBufferStrategy(); //get buffer comes from canvas
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();  //need to send graphics context
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(-6,-28);
		g2d.setColor(Color.BLACK);
	
		g2d.drawImage(background,0,0,null);
		statemanager.render(g2d);
	
		g2d.dispose();
		bs.show();
	}
	
	
	@Override
	public void run() {
		
		double target= 60;
		double nsptick = 1000000000/target;
		long lastTime = System.nanoTime();
		long lastTimeMilli = System.currentTimeMillis();
		int fps=0;
		int tps=0;
		boolean canRender = false;
		long elapsedtime=0;
		while(running)
		{
 
			
			long now = System.nanoTime();
			elapsedtime+=now-lastTime;
			lastTime=now;
			
			if(elapsedtime >= nsptick)
			{
				
				tick();
				KeyInput.update();
				MouseInput.update();
				tps++;
				canRender=true;
				elapsedtime=0;
			}
			else
			{
				canRender=false;
			}
 
			if(canRender)
			{
				render();
				fps++;
			}
			
//			if(System.currentTimeMillis()-lastTimeMilli>1000)
//			{
//				System.out.println(fps);
//				System.out.println(tps);
//				fps=0;
//				tps=0;
//				lastTimeMilli = System.currentTimeMillis();
//			}
			
		}
		System.exit(0);
		
	}
	
	
	
	
	

	 

}
