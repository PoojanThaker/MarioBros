package com.firestorm.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.firestorm.Game;
import com.firestorm.input.KeyInput;
import com.firestorm.utils.TextTool;

public class GameOver implements State {

	
	int score;
	@Override
	public void init() {
	
			score=Game.SCORE;
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick(StateManager sm) {

         if(KeyInput.wasPressed(KeyEvent.VK_ENTER))
         {
        	 Game.SCORE=0;
        	 sm.setState("menu");
         }

	}

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,50),"Game Over");
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,20),"Your Score : ",240,280);
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,20),Integer.toString(Game.SCORE),370,280);
		 TextTool.write(g,Color.black,new Font("Arial",Font.BOLD,20),"Press Enter to Replay",220,330);

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Gameover";
	}

}
