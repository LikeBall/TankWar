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
	
	boolean bu = false, bd = false, bl = false, br = false;
	
	private Parameter.Direction dir = Parameter.Direction.STOP;
	private Parameter.Direction gunDir = Parameter.Direction.U;
	private Missile ml = null;
	
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
		drawGun(g);
		g.setColor(c);
		if(ml != null) {
			ml.draw(g);
		}
		move();
	}
	
	void drawGun(Graphics g) {
		switch(gunDir) {
		case U:
			y -= Parameter.Y_SPEED;
			break;
		case D:
			y += Parameter.Y_SPEED;
			break;
		case L:
			x -= Parameter.X_SPEED;
			break;
		case R:
			x += Parameter.Y_SPEED;
			break;
		case LU:
			x -= Parameter.X_SPEED;
			y -= Parameter.Y_SPEED;
			break;
		case RU:
			x += Parameter.X_SPEED;
			y -= Parameter.Y_SPEED;
			break;
		case LD:
			x -= Parameter.X_SPEED;
			y += Parameter.Y_SPEED;
			break;
		case RD:
			x += Parameter.X_SPEED;
			y += Parameter.Y_SPEED;
			break;
		default:
			break;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
			
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
		case KeyEvent.VK_J :
			ml = fire();
			break;
		default : ;
		}
		dir = location();
		gunDirLocation();
	}
	
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		switch(keyCode) {
		case KeyEvent.VK_W : 
			bu = false;
			break;
		case KeyEvent.VK_S : 
			bd = false;
			break;
		case KeyEvent.VK_A : 
			bl = false;
			break;
		case KeyEvent.VK_D : 
			br = false;
			break;
		default : ;
		}
		dir = location();
		gunDirLocation();
	}
	
	Parameter.Direction location() {
		Parameter.Direction d = Parameter.Direction.STOP;
		if(bu && !bd && !bl && !br) {
			d = Parameter.Direction.U;
		}
		else if(!bu && bd && !bl && !br) {
			d = Parameter.Direction.D;
		}
		else if(!bu && !bd && bl && !br) {
			d = Parameter.Direction.L;
		}
		else if(!bu && !bd && !bl && br) {
			d = Parameter.Direction.R;
		}
		else if(bu && !bd && bl && !br) {
			d = Parameter.Direction.LU;
		}
		else if(bu && !bd && !bl && br) {
			d = Parameter.Direction.RU;
		}
		else if(!bu && bd && bl && !br) {
			d = Parameter.Direction.LD;
		}
		else if(!bu && bd && !bl && br) {
			d = Parameter.Direction.RD;
		}
		return d;
	}
	
	void move() {
		switch(dir) {
		case U:
			y -= Parameter.Y_SPEED;
			break;
		case D:
			y += Parameter.Y_SPEED;
			break;
		case L:
			x -= Parameter.X_SPEED;
			break;
		case R:
			x += Parameter.Y_SPEED;
			break;
		case LU:
			x -= Parameter.X_SPEED;
			y -= Parameter.Y_SPEED;
			break;
		case RU:
			x += Parameter.X_SPEED;
			y -= Parameter.Y_SPEED;
			break;
		case LD:
			x -= Parameter.X_SPEED;
			y += Parameter.Y_SPEED;
			break;
		case RD:
			x += Parameter.X_SPEED;
			y += Parameter.Y_SPEED;
			break;
		case STOP:
			break;
		default:
			
		}
	}

	Missile fire() {
		return new Missile(x+width/2-Parameter.M_WIDTH/2, y+heigth/2-Parameter.M_HEIGTH/2, Parameter.M_WIDTH, Parameter.M_HEIGTH, gunDir);
	}

	void gunDirLocation() {
		if(dir != Parameter.Direction.STOP) {
			if(dir != gunDir) {
				gunDir = dir;
			}
		}
	}

}