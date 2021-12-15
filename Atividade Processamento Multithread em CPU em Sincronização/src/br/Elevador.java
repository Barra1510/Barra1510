package br;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.Semaphore;

import br.states.Building;

public class Elevador {

	public Rectangle elevador = new Rectangle(Game.width/2, 0,100,75);
	
	private Random random = new Random();
	
	private Color cor = Color.RED;
	
	float speed = 1.2f;
	
	Semaphore andares[] = new Semaphore[Game.nAndares];
	
	//Building building = new Building();
	
	public Elevador()
	{
		for(int i = 0; i < Game.nAndares; i++)
		{
			//andares[i] = Game.height - (i+1) * 100;
			andares[i] = new Semaphore(1);
		}
	}
	
	public void Draw(Graphics g) 
	{
		g.setColor(cor);
		g.fillRect(elevador.x, elevador.y, elevador.width, elevador.height);
	}
	
	public void AbrirPorta()
	{
		cor = Color.BLUE;
	}
	
	public void FecharPorta()	
	{
		cor = Color.RED;
	}
	
	public void VisitarAndar(int i)
	{
		if(elevador.y <= i + 50)
			elevador.y += speed;
		if(elevador.y >= i + 50)
			elevador.y -= speed;		
	}
	
	public boolean ElevadorCheio(Rectangle passageiro)
	{
		/*for(int i = 0; i < building.nPassageiro; i++)
		{
			if(elevador.contains(passageiro.x, passageiro.y))
			{
				return true;
			}		
				
		}*/
		return false;
	}
	
}
