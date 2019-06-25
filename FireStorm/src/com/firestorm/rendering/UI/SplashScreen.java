package com.firestorm.rendering.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.firestorm.rendering.textures.Texture;

public class SplashScreen extends JWindow
{
	private BorderLayout borderlayout;
	private JLabel imgLabel;
	private JPanel southpanel;
	private FlowLayout southflow;
	private JProgressBar progressbar;
	
	public SplashScreen(Texture texture)
	{
		borderlayout = new BorderLayout();
		imgLabel = new JLabel();
		southpanel = new JPanel();
		southflow = new FlowLayout();
		progressbar = new JProgressBar();
	}
	
	private void init()
	{
		getContentPane().setLayout(borderlayout);
		southpanel.setLayout(southflow);
		southpanel.setBackground(Color.BLACK);
		getContentPane().add(southpanel,BorderLayout.SOUTH);
		pack();
	}
	
	public void setMaxProgress(int maxProgress)
	{
		progressbar.setMaximum(maxProgress);
	}
	
	public void setProgress(int progress)
	{
		int percentage = (progress/progressbar.getMaximum())*100;
	
		SwingUtilities.invokeLater(new Runnable(){
		
			@Override
			public void run()
			{
				progressbar.setStringPainted(true);
				progressbar.setString("Loading : " + percentage + "%");
			}
		});
	}
}
