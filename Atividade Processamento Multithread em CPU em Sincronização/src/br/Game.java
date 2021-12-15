package br;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import br.display.Tela;
import br.states.StateManager;

public class Game implements Runnable{
	
	private Tela tela;
	private Thread canvasThread;
	private boolean running = false;
	
	public static final int width = 1400;
	public static int height;
	
	public static int nAndares = 5;
	private StateManager sm;
	
	public Game()
	{
		height = nAndares * 100;
		tela = new Tela("Corrida", width, height);
		sm = new StateManager();
		//tela.SetKeyListener(sm);
	}

	@Override
	public void run() 
	{	
		Init();
		int FPS = 60;
		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1)
			{
				Update();
				Draw();
				//System.out.println(delta);
				delta--;
			}
		}
		Stop();
		
	}
	
	public void Init()
	{
		sm.Init();
		
	}
	
	public void Update() 
	{
		if(StateManager.getState() == null)
			return;
		sm.Update();
		
	}
	
	public void Draw() 
	{
		BufferStrategy buffer = tela.getBufferStrategy();
		if(buffer == null)
		{
			tela.createBufferStrategy();
			buffer = tela.getBufferStrategy();
		}
		
		Graphics g = buffer.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		if(StateManager.getState() != null)
			sm.Draw(g);
		
		g.dispose();
		buffer.show();        
		
		
	}
	public void Start()
	{
		if(canvasThread != null)
			return;
		canvasThread = new Thread(this);
		canvasThread.start();
		running = true;
	}
	
	public void Stop()
	{
		if(canvasThread == null)
			return;
		try {
			canvasThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}