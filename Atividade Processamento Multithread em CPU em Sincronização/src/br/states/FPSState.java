package br.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import br.Game;

public class FPSState implements State{
	
	private long now, lastTime = System.nanoTime();
	private double timer = 0;
	private int tick = 0;
	private int t = 0;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		now = System.nanoTime();
		timer += now - lastTime;
		lastTime = now;
		tick++;
	}

	@Override
	public void Draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.width, Game.height);
		
		if(timer >= 1000000000)
		{
			t = tick;
			tick = 0;
			timer = 0;
		}
		
		g.setColor(Color.WHITE);
		Font font = new Font("Serif", Font.ITALIC, 28);
		g.setFont(font);
		
		String text = "FPS: " + t;
		g.drawString(text, g.getFontMetrics().stringWidth(text), g.getFontMetrics(font).getHeight());
	}

	@Override
	public void KeyPressed(int cod) {
		System.out.println("Pressed " + cod);
		
	}

	@Override
	public void KeyReleased(int cod) {
		System.out.println("Released " + cod);
		
	}

}
