package br.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StateManager{
	
	public static final int numberStates = 1;
	public static State[] states = new State[numberStates];
	public static int currentState = 0;
	public static final int FPS = 0;
	public static final int LEVEL1 = 1;
	
	public static void SetState(int state)
	{
		currentState = state;
		states[currentState].init();
	}
	
	public StateManager()
	{
		states[0] = new Building();
		//states[1] = new Level1();
	}
	
	public void Init()
	{
		states[currentState].init();
	}
	
	public void Update()
	{
		states[currentState].update();
	}
	
	public void Draw(Graphics g)
	{
		states[currentState].Draw(g);
	}
	
	public static State getState()
	{
		return states[currentState];
	}
	
/*
	@Override
	public void keyPressed(KeyEvent e) {
		states[currentState].KeyPressed(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		states[currentState].KeyReleased(e.getKeyCode());
		
	}*/

}
