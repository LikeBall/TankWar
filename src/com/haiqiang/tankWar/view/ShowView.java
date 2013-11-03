package com.haiqiang.tankWar.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ArrayList;


import com.haiqiang.tankWar.param.*;

public class ShowView extends Frame{
	
	private static final long serialVersionUID = 1L;
	
	private Image offScreenImage = null;
	private Tank tk = new Tank((Parameter.FRAME_WIDTH-20)/2, Parameter.FRAME_HEIGHT-30, 20, 20, this, true);
//	private Tank tkEnemy = new Tank((Parameter.FRAME_WIDTH-20)/2, Parameter.FRAME_HEIGHT-30, 20, 20, this, true);;
	public List<Tank> tkEnemys = new ArrayList<Tank>();
	public List<Missile> mls = new ArrayList<Missile>();
	public void paint(Graphics g) {
		g.drawString("MissileNum:" + Integer.toString(mls.size()), 50, 50);
		tk.draw(g);
		for(int i=0; i<tkEnemys.size(); i++) {
			tkEnemys.get(i).draw(g);
		}
		for(int i=0; i<mls.size(); i++) {
			for(int j=0; j<tkEnemys.size(); j++) {
				mls.get(i).hitTank(tkEnemys.get(j));
			}
			mls.get(i).draw(g);
		}
	}
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Parameter.FRAME_WIDTH, Parameter.FRAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, Parameter.FRAME_WIDTH, Parameter.FRAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	public ShowView(String s) {
		super(s);
	}
	
	public void launch() {
		this.setBounds(100, 100, Parameter.FRAME_WIDTH, Parameter.FRAME_HEIGHT);
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		this.setVisible(true);
		tkEnemys.add(newEnemy());
		Thread t = new Thread(new PaintThread());
		t.start();
	}
	
	Tank newEnemy() {
		return new Tank(50, 50, 20, 20, this, false, Parameter.Direction.R);
	}
	
	class PaintThread implements Runnable {
		
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
		
	}
	
	class KeyMonitor extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
				tk.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			tk.keyReleased(e);
		}
	}
}
