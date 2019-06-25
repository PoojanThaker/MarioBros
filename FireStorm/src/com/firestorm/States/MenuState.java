package com.firestorm.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.firestorm.Game;
import com.firestorm.input.KeyInput;
import com.firestorm.input.MouseInput;
import com.firestorm.rendering.UI.Button;
import com.firestorm.utils.TextTool;

public class MenuState implements State
{
		private Button options[] ;
		private static int OptionNo=0;
		
		@Override
		public void init() {
			options = new Button[3];
			options[0] = new Button("PLAY",250+0*60,new Font("Calibri",Font.PLAIN,50),
					new Font("Calibri",Font.BOLD,55),Color.white,Color.yellow,3);
			options[1] = new Button("OPTIONS",250+1*60,new Font("Calibri",Font.PLAIN,50),
					new Font("Calibri",Font.BOLD,55),Color.white,Color.yellow,3);
			options[2] = new Button("EXIT",250+2*60,new Font("Calibri",Font.PLAIN,50),
					new Font("Calibri",Font.BOLD,55),Color.white,Color.yellow,3);
		}

		@Override
		public void enter() {
			// TODO Auto-generated method stub
			
		}
		
		public void tick(StateManager statemanager)
		{
			boolean clicked=false;
			for(int i=0;i<3;i++)
			{
				if(options[i].intersects(new Rectangle(MouseInput.getmx(),MouseInput.getmy(),1,1)))
				{
					OptionNo=i;
					if(MouseInput.waspressed(MouseEvent.BUTTON1))
						clicked=true;
				}
			}
			if(KeyInput.wasPressed(KeyEvent.VK_UP)||KeyInput.wasPressed(KeyEvent.VK_W))
			{
				OptionNo--;
				if(OptionNo<0)OptionNo=options.length-1;
			}
			if(KeyInput.wasPressed(KeyEvent.VK_DOWN)||KeyInput.wasPressed(KeyEvent.VK_S))
			{
				OptionNo++;
				if(OptionNo>=options.length)OptionNo=0;
			}
			if(clicked||KeyInput.wasPressed(KeyEvent.VK_ENTER))
				select(statemanager);
		}
		
		private void select(StateManager sm)
		{
			switch(OptionNo)
			{
				case 0:
				{
					sm.setState("level1");
					break;
				}
				case 1:
				{
					sm.setState("level2");
					break;
				}
				case 2:
				{
					System.out.println("exit");
					Game.stop();
				}
			}
		}
		
		
		
		
		public void render(Graphics2D g)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0, 0,Game.WIDTH,Game.HEIGHT);
			TextTool.write(g,Color.orange,new Font("Arial",Font.BOLD,50),"SUPER MARIO BROS");
			
			for(int i=0;i<options.length;i++)
			{
				if(i==OptionNo)
				{
					options[i].setSelected(true);
				}
				else
				{
					options[i].setSelected(false);
				}
				
				options[i].Render(g);
			}
		}

		

		@Override
		public void exit() {
			 
			
		}

		@Override
		public String getName() {
			
			return "menu";
		}
}
