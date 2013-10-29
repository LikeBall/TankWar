package com.haiqiang.tankWar.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Tank {
	private int x;
	private int y;
	private int width;
	private int heigth;
	
	Tank(int x, int y, int width, int heigth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
	}
		
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, width, heigth);
		g.setColor(c);
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_W : 
			y -= 5;
			break;
		case KeyEvent.VK_S : 
			y += 5;
			break;
		case KeyEvent.VK_A : 
			x -= 5;
			break;
		case KeyEvent.VK_D : 
			x += 5;
			break;
		default : ;
		}
	}
}
