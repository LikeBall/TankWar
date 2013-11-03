package com.haiqiang.tankWar.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.haiqiang.tankWar.param.Parameter;

public class Tank {
		
	public static final int GUN_LENGTH = 10;
	private int x;
	private int y;
	private int width;
	private int heigth;
	
	boolean bu = false, bd = false, bl = false, br = false;
	
	private Parameter.Direction dir = Parameter.Direction.STOP;
	private Parameter.Direction gunDir = Parameter.Direction.U;

	private boolean live = true;
	private ShowView sv;
	private boolean good = false;
	
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
	
	Tank(int x, int y, int width, int heigth, ShowView sv, boolean good) {
		this(x, y, width, heigth, sv);
		this.good = good;
	}
	
	Tank(int x, int y, int width, int heigth, ShowView sv, boolean good, Parameter.Direction dir) {
		this(x, y, width, heigth, sv, good);
		this.dir = dir;
	}
	
	public void draw(Graphics g) {
		if(!live) {
			sv.tkEnemys.remove(this);
		}
		else{
			Color c = g.getColor();
			if(good){
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLUE);
			}
			g.fillOval(x, y, width, heigth);
			drawGun(g);
			g.setColor(c);
			move();
		}
	}
	
	void drawGun(Graphics g) {
		gunDirLocation();
		System.out.println("gunDir=" + gunDir);
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
		System.out.println("dir="+dir);
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
//		gunDirLocation();
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
		System.out.println("in move dir = " + dir);
		
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
		x = Math.max(0, x);
		y = Math.max(30, y);
		x = Math.min(Parameter.FRAME_WIDTH - width, x);
		y = Math.min(Parameter.FRAME_HEIGHT - heigth, y);
//		System.out.println("x=" + x);
//		System.out.println("y=" + y);
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

	Rectangle getRect() {
		return new Rectangle(x, y, width, heigth);
	}
	
	boolean isLive() {
		return live;
	}
	;
	void setLive(boolean b) {
		live = b;
	}
}