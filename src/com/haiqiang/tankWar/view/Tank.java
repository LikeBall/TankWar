package com.haiqiang.tankWar.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.haiqiang.tankWar.param.Parameter;

public class Tank {
	private int x;
	private int y;
	private int width;
	private int heigth;
	
	private enum Direction {U, D, L, R, LU, RU, LD, RD, STOP};
	
	private Direction dir = Direction.STOP;
	
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
		move();
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		boolean bu = false, bd = false, bl = false, br = false;
	
		switch(keyCode) {
		case KeyEvent.VK_W : 
			bu = true;
			break;
		case KeyEvent.VK_S : 
			bd = true;
			break;
		case KeyEvent.VK_A : 
			bl = true;
			break;
		case KeyEvent.VK_D : 
			br = true;
			break;
		default : ;
		}
		dir = location(bu, bd, bl, br);
	}
	Direction location(boolean bu, boolean bd, boolean bl, boolean br) {
		Direction d = Direction.STOP;
		if(bu && !bd && !bl && !br) {
			d = Direction.U;
		}
		else if(!bu && bd && !bl && !br) {
			d = Direction.D;
		}
		else if(!bu && !bd && bl && !br) {
			d = Direction.L;
		}
		else if(!bu && !bd && !bl && br) {
			d = Direction.R;
		}
		else if(bu && !bd && bl && !br) {
			d = Direction.LU;
		}
		else if(bu && !bd && !bl && br) {
			d = Direction.RU;
		}
		else if(!bu && bd && bl && !br) {
			d = Direction.LD;
		}
		else if(!bu && bd && !bl && br) {
			d = Direction.RD;
		}
		return d;
	}
	void move() {
		switch(dir) {
		case U:
			y -= Parameter.YSPEED;
			break;
		case D:
			y += Parameter.YSPEED;
			break;
		case L:
			x -= Parameter.XSPEED;
			break;
		case R:
			x += Parameter.YSPEED;
			break;
		case LU:
			x -= Parameter.XSPEED;
			y -= Parameter.YSPEED;
			break;
		case RU:
			x += Parameter.XSPEED;
			y -= Parameter.YSPEED;
			break;
		case LD:
			x -= Parameter.XSPEED;
			y += Parameter.YSPEED;
			break;
		case RD:
			x += Parameter.XSPEED;
			y += Parameter.YSPEED;
			break;
		case STOP:
			break;
		default:
			
		}
	}
}
