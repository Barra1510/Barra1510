package br.states;

import java.awt.Color;
import java.awt.Graphics;

public interface State {
	void init();
	void update();
	void Draw(Graphics g, Color cor);
	/*void KeyPressed(int cod);
	void KeyReleased(int cod);*/
}
