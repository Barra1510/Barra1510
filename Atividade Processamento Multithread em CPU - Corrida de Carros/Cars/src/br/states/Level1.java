package br.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import br.Game;
import br.ThreadProcessor;

public class Level1 implements State {

	int size = 5;
	private Rectangle car[] = new Rectangle[size];
	/*
	private Rectangle car2 = new Rectangle(Game.width/25, Game.height/3, 50, 50);
	private Rectangle car3 = new Rectangle(Game.width/25, Game.height -100, 50, 50);
	private Rectangle car4 = new Rectangle(Game.width/25, Game.height/6, 50, 50);
	private Rectangle car5 = new Rectangle(Game.width/25, Game.height -200, 50, 50);*/
	
	private Rectangle linha = new Rectangle(Game.width -100, 0, 50, 600);
	private Random random = new Random();
	
	private int r[] = new int[size];
	
	private boolean move;
	
	@Override
	public void init() {
		car[0] = new Rectangle(Game.width/25, Game.height/2, 50, 50);
		car[1] = new Rectangle(Game.width/25, Game.height/3, 50, 50);
		car[2] = new Rectangle(Game.width/25, Game.height -100, 50, 50);
		car[3] = new Rectangle(Game.width/25, Game.height/6, 50, 50);
		car[4] = new Rectangle(Game.width/25, Game.height -200, 50, 50);
	}

	@Override
	public void update() {
		int nThreads = 5;
		int elemThread = size / nThreads;
		
		
		ThreadProcessor threads[] = new ThreadProcessor[nThreads];
		
		if(move)
		{	
			for(int i = 0; i < nThreads; i++)
			{					
				threads[i] = new ThreadProcessor(car, r, 0, 5);
				threads[i].start();
			}
			
			for(int i = 0; i < nThreads; i++)
			{
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		Log();
	}

	@Override
	public void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.width, Game.height);	
		
		g.setColor(Color.WHITE);
		g.fillRect(car[0].x, car[0].y, car[0].width, car[0].height);
		
		g.setColor(Color.YELLOW);
		g.fillRect(car[1].x, car[1].y, car[1].width, car[1].height);
		
		g.setColor(Color.PINK);
		g.fillRect(car[2].x, car[2].y, car[2].width, car[2].height);
		
		g.setColor(Color.BLUE);
		g.fillRect(car[3].x, car[3].y, car[3].width, car[3].height);
		
		g.setColor(Color.GREEN);
		g.fillRect(car[4].x, car[4].y, car[4].width, car[4].height);
		
		g.setColor(Color.RED);
		g.fillRect(linha.x, linha.y, linha.width, linha.height);
		
		
	}

	private void Log() {
		
		for(int i = 0; i < 5; i++)
		{
			int inicio = car[i].x - 56;
			int chegada = linha.x - car[i].x;
			if(car[i].x < linha.x)
				System.out.println("O Carro "+ i + " andou " + (inicio) + "m e faltam " + (chegada) + "m");
			if(car[i].x >= linha.x)
				System.out.println("O Carro "+ i + " alcançou a linha de chegada");
		}
	}
	@Override
	public void KeyPressed(int cod) {
		
		//randomInt = random.nextInt(6) + 1;
		move = false;
		if(cod == KeyEvent.VK_A)
		{
			for(int i = 0; i < 5; i++)
			{
				r[i] = random.nextInt(2) + 1;		
				System.out.println(r[i]);
			}
			
		}
	}

	@Override
	public void KeyReleased(int cod) {
		
		if(cod == KeyEvent.VK_A)
		{			
			move = true;			
		}

	}

}
