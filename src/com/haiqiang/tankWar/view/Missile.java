package com.haiqiang.tankWar.view;

import java.awt.Color;
import java.awt.Graphics;

import com.haiqiang.tankWar.param.Parameter;

public class Missile {
	
	public static final int X_SPEED = 10;
	public static final int Y_SPEED = 10;
	
	private int x;
	private int y;
	private int width;
	private int heigth;
	private boolean bLive = false;
	
	private Parameter.Direction dir = Parameter.Direction.STOP;
	
	public Missile(int x, int y, int width, int heigth, Parameter.Direction dir) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
		this.dir = dir;
		bLive = true;
	}
		
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, heigth);
		g.setColor(c);
		move();
	}
	
	public boolean isLive() {
//		boolean bLive = true;
		if(x > Parameter.FRAME_WIDTH || x < 0 || y < 0 || y > Parameter.FRAME_HEIGHT) {
			bLive = false;
		}
		else {
			bLive = true;
		} 
		return bLive;
	}
	void move() {
		switch(dir) {
		case U:
			y -= Y_SPEED;
			break;
		case D:
			y += Y_SPEED;
			break;
		case L:
			x -= X_SPEED;
			break;
		case R:
			x += Y_SPEED;
			break;
		case LU:
			x -= X_SPEED;
			y -= Y_SPEED;
			break;
		case RU:
			x += X_SPEED;
			y -= Y_SPEED;
			break;
		case LD:
			x -= X_SPEED;
			y += Y_SPEED;
			break;
		case RD:
			x += X_SPEED;
			y += Y_SPEED;
			break;
		default:
			
		}
	}
		
}
