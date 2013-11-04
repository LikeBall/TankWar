package com.haiqiang.tankWar.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
//import java.util.LinkedList;
//import java.util.List;

import com.haiqiang.tankWar.param.Parameter;

public class Tank {
		
	public static final int GUN_LENGTH = 10;
	private int x;
	private int y;
	private int width;
	private int heigth;
	
	boolean bu = false, bd = false, bl = false, br = false;
	
	private ShowView sv;
	private Parameter.Direction dir = Parameter.Direction.STOP;
	private Parameter.Direction gunDir = Parameter.Direction.U;
//	private List<Missile> mls = new LinkedList<Missile>();
	
	Tank(int x, int y, int width, int heigth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
	}
		
	Tank(int x, int y, int width, int heigth, ShowView sv) {
		this(x, y, width, heigth);
		this.sv = sv;
	}
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, width, heigth);
		drawGun(g);
		g.setColor(c);
		
		
		move();
	}
	
	void drawGun(Graphics g) {
		switch(gunDir) {
		case U:
			g.drawLine(x+width/2, y+heigth/2, x+width/2, y-GUN_LENGTH);;
			break;
		case D:
			g.drawLine(x+width/2, y+heigth/2, x+width/2, y+heigth+GUN_LENGTH);
			break;
		case L:
			g.drawLine(x+width/2, y+heigth/2, x-GUN_LENGTH, y+heigth/2);
			break;
		case R:
			g.drawLine(x+width/2, y+heigth/2, x+width+GUN_LENGTH, y+heigth/2);
			break;
		case LU:
			g.drawLine(x+width/2, y+heigth/2, x, y);
			break;
		case RU:
			g.drawLine(x+width/2, y+heigth/2, x+width, y);
			break;
		case LD:
			g.drawLine(x+width/2, y+heigth/2, x, y+heigth);
			break;
		case RD:
			g.drawLine(x+width/2, y+heigth/2, x+width, y+heigth);
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
		case KeyEvent.VK_J :
			sv.mls.add(fire());
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
		return new Missile(x+width/2-Parameter.M_WIDTH/2, y+heigth/2-Parameter.M_HEIGTH/2, Parameter.M_WIDTH, Parameter.M_HEIGTH, gunDir, sv);
	}

	void gunDirLocation() {
		if(dir != Parameter.Direction.STOP) {
			if(dir != gunDir) {
				gunDir = dir;
			}
		}
	}

}