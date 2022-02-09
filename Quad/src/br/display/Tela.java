package br.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Tela {
	
	private JFrame jFrame;
	private Canvas canvas;
	
	public Tela(String title, int width, int height)
	{
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		jFrame = new JFrame(title);
		jFrame.add(canvas);
		jFrame.pack();
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
	
	public void createBufferStrategy()
	{
		canvas.createBufferStrategy(2);
	}
	
	public BufferStrategy getBufferStrategy()
	{
		return canvas.getBufferStrategy();
	}
	
	public void SetKeyListener(KeyListener kl)
	{
		jFrame.addKeyListener(kl);
	}

}
