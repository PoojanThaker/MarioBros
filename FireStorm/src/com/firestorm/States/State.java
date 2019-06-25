package com.firestorm.States;

import java.awt.Graphics;
import java.awt.Graphics2D;

public interface State 
{
	public void init();
	public void enter();
	public void tick(StateManager sm);
	public void render(Graphics2D g);
	public void exit();
	public String getName();
}
