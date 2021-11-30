package br;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ThreadProcessor extends Thread{
	
	private Rectangle car[] = new Rectangle[5];	
	int r[] = new int[5];
	int start, end;
	Graphics g;
	
	public ThreadProcessor(Rectangle car[], int r[], int s, int e)
	{
		this.car = car;	
		this.r = r;
		start = s;
		end = e;		
		
	}
	@Override
	public void run() 
	{
		for(int i = start; i < end; i++)
		{
			car[i].x += r[i];
		}
			
		
	} 

}
