package br.states;

import java.awt.Graphics;

public interface State {
	void init();
	void update();
	void Draw(Graphics g);
	void KeyPressed(int cod);
	void KeyReleased(int cod);
}
