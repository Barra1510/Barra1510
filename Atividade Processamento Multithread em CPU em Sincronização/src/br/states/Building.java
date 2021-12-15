package br.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
//import java.awt.event.KeyEvent;
import java.util.Random;

import br.Elevador;
import br.Game;
import br.Passageiro;
//import br.Passageiro;
//import br.ThreadProcessor;

public class Building implements State {

	
	int size = Game.nAndares * 2;
	public int nPassageiro = 5;
	private Rectangle plataforma[] = new Rectangle[size];	
	private Rectangle passageiro[] = new Rectangle[nPassageiro];	
	
	private Random random = new Random();
	
	private int andares[] = new int[Game.nAndares];
	
	private boolean move, elevadorOcupado, elevadorParou, moveLeft = false;
	
	private Elevador elevador = new Elevador();
	
	private int andarDesejado[] = new int[Game.nAndares];
	
	int randomico;
	float speed = 2f;
	
	///
	
	Passageiro[] passageiros = new Passageiro[nPassageiro];
	
	@Override
	public void init() {
		
		randomico = random.nextInt(Game.nAndares-1)+1;	
		
		
		for(int i = 0; i < Game.nAndares - 1; i++)
		{
			plataforma[i] = new Rectangle(0, Game.height - (i+1) * 100, 700, 50);			
		}
		
		for(int i = 0; i < nPassageiro; i++)
		{
			passageiro[i] = new Rectangle(100, (Game.height - 25) - (i+1) * 100 , 50, 50);
			andarDesejado[i] = random.nextInt(Game.nAndares - i);			
			//pas[i].andarDesejado = random.nextInt(Game.nAndares);
			System.out.println(andarDesejado[i]);
		}
		
		for(int i = 0; i < Game.nAndares; i++)
		{
			andares[i] = Game.height - (i+1) * 100;
		}
		
		///
		
		for(int i = 0; i < passageiros.length; i++)
		{
			passageiros[i] = new Passageiro(elevador, i, nPassageiro);
			passageiros[i].start();
		}
		
		
	}

	@Override
	public void update() {		
		
		if(!elevadorOcupado)
		{
			elevador.VisitarAndar(andares[randomico]);
			if(elevador.elevador.y >= andares[randomico])
			{
				for(int i = 0; i < nPassageiro; i++)
				{
					if(elevador.elevador.y < passageiro[i].y && 
							elevador.elevador.y + elevador.elevador.height > passageiro[i].y)
					{
						passageiro[i].x += speed;
						elevadorParou = true;
						if(elevador.elevador.x < passageiro[i].x && 
								elevador.elevador.x + elevador.elevador.width > passageiro[i].x)
						{
							elevadorParou = false;
							speed = 0;
							elevadorOcupado = true;
						}
					}
				}
			}
		}
		
		if(elevadorOcupado)
		{
			for(int i = 0; i < nPassageiro; i++)
			{	
				if(elevador.elevador.x < passageiro[i].x && 
						elevador.elevador.x + elevador.elevador.width > passageiro[i].x)
				{
					elevador.VisitarAndar(andares[andarDesejado[i]]);
					passageiro[i].y = elevador.elevador.y;					
					
				}
				
				if(elevador.elevador.y <= andares[andarDesejado[i]]
						&& elevador.elevador.y + elevador.elevador.height >= andares[andarDesejado[i]])
				{
					//passageiro[i].y = passageiro[i].y;
					elevadorParou = true;
				}
				
			}
		}		
		
		System.out.println(elevadorParou);
		
		if(elevadorParou && elevadorOcupado)
		{
			
			for(int i = 0; i < nPassageiro; i++)
			{	
				if(elevador.elevador.x < passageiro[i].x + passageiro[i].width && 
						elevador.elevador.x + elevador.elevador.width > passageiro[i].x + passageiro[i].width)
				{
					speed = 2f;					
					moveLeft = true;
					Move(i);					
				}
				elevadorOcupado = false;
			}
		}
		
		if(elevadorParou)
		{
			elevador.AbrirPorta();
		}
		
		else 
		{
			elevador.FecharPorta();
		}		
		
		
	}	
	
	void Move(int i)
	{
		if (moveLeft)
		passageiro[i].x -= speed;
	}

	@Override
	public void Draw(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, Game.width, Game.height);	
		
		for(int i = 0; i < Game.nAndares -1; i++)
		{
			g.setColor(Color.WHITE);
			g.fillRect(plataforma[i].x, plataforma[i].y, plataforma[i].width, plataforma[i].height/2);	
		}
		
		for(int i = 0; i < nPassageiro; i++)
		{
			g.setColor(Color.GREEN);
			g.fillRect(passageiro[i].x, passageiro[i].y, passageiro[i].width/2, passageiro[i].height/2);	
		}
		
		elevador.Draw(g);	
	}	

}
