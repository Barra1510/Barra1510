package br;

import java.awt.Rectangle;

public class Passageiro extends Thread {
	
	public int andarDesejado;
	public boolean estouNoElevador = false;
	public boolean chegueiNoMeuAndar = false;
	Elevador elevador;
	int index = 0;
	private Rectangle passageiro[];
	float speed;
	int andaDireita, andaEsquerda;
	int nPassageiro;
	
	public Passageiro(Elevador elevador, int index, int nPassageiro) 
	{
		this.elevador = elevador;
		this.index = index;
		this.nPassageiro = nPassageiro;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			Parado();
			Anda(andaDireita);
			Anda(andaEsquerda);
			EstaNoElevador();
		}
	}
	
	void Parado()
	{
		for(int i = 0; i < nPassageiro; i++)
		{
			speed = 0;
			passageiro[i].x += speed;
		}
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void Anda(int direction)
	{		
		for(int i = 0; i < nPassageiro; i++)
		{
			if(direction > 0)
			{
				speed = 2;
				passageiro[i].x += speed;
			}
			else
			{
				speed = 2;
				passageiro[i].x -= speed;
			}
		}
	}
	void EstaNoElevador()
	{
		for(int i = 0; i < nPassageiro; i++)
		{
			while (elevador.ElevadorCheio(passageiro[i]))
			{
				//TODO: INFORMAR O ELEVADOR QUE ESTÁ NELE
			}
		}
	}
}
