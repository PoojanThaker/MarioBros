package com.firestorm;
import javax.swing.JFrame;

public class FireStorm {
	public static void main(String[] args)
	{
		Game game = new Game();
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.setSize(game.WIDTH,game.HEIGHT);  //setsize before visible
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}
}
