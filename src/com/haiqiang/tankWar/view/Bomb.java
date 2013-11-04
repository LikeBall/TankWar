package com.haiqiang.tankWar.view;

import java.awt.Color;
import java.awt.Graphics;

public class Bomb {
	
	private static final int MIN_DIA = 10;
	private static final int MAX_DIA = 30;
	private static final int BOMB_SPEED = 4;
	
	private int x;
	private int y;
	private int dia;
	private ShowView sv;
	
	boolean live = true;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		this.dia = MIN_DIA;
	}
	
	public Bomb(int x, int y, ShowView sv) {
		this(x, y);
		this.sv = sv;
	}
	
	public Bomb(int x, int y, int dia) {
		this.x = x;
		this.y = y;
		this.dia = dia;
	}
	
	public Bomb(int x, int y, int dia, ShowView sv) {
		this(x, y, dia);
		this.sv = sv;
	}
	
	public void draw(Graphics g) {
//		System.out.println(live);
		if(!isLive()) {
			sv.bombs.remove(this);
		}
		else{
			Color c = g.getColor();
			g.setColor(Color.ORANGE);
			g.fillOval(x-dia/2, y-dia/2, dia, dia);
			g.setColor(c);
			dia += BOMB_SPEED;
		}
	}
	
	boolean isLive() {
//		System.out.println(dia);
		if(dia > MAX_DIA) {
			live = false;
		}
		return live;
	}

}
