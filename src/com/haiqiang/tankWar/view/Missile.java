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
	
	private Parameter.Direction dir = Parameter.Direction.STOP;
	private ShowView sv;
	private boolean bLive = false;
	
	public Missile(int x, int y, int width, int heigth, Parameter.Direction dir) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
		this.dir = dir;
		bLive = true;
	}
	
	public Missile(int x, int y, int width, int heigth, Parameter.Direction dir, ShowView sv) {
		this(x, y,  width, heigth, dir);
		this.sv = sv;
	}
	
	public void draw(Graphics g) {
		if(isLive()) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			g.fillOval(x, y, width, heigth);
			g.setColor(c);
			move();
		}
		else {
			sv.mls.remove(this);
		}
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

	boolean isLive() {
		if(x<=0 || y <=0 || x>=Parameter.FRAME_WIDTH || y>=Parameter.FRAME_HEIGHT) {
			bLive = false;
		}
		return bLive;
	}
	
}
