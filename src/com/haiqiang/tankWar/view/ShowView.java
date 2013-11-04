package com.haiqiang.tankWar.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;

import com.haiqiang.tankWar.param.*;

public class ShowView extends Frame{
	
	private static final long serialVersionUID = 1L;
	
	private Image offScreenImage = null;
	private Tank tk = new Tank((Parameter.FRAME_WIDTH-20)/2, Parameter.FRAME_HEIGHT-30, 20, 20, this);
	public List<Missile> mls = new ArrayList<Missile>();
	
	public void paint(Graphics g) {
		drawMlsNum(g);
		tk.draw(g);
		for(Missile ml : mls) {
				ml.draw(g);
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
		this.setBounds(Parameter.FRAME_X_ORIGIN, Parameter.FRAME_Y_ORIGIN, Parameter.FRAME_WIDTH, Parameter.FRAME_HEIGHT);
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}			
		});
		this.setVisible(true);
		Thread t = new Thread(new PaintThread());
		t.start();
	}
		
	class PaintThread implements Runnable {
		
		public void run() {
			while(true) {
				repaint(1);
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

	private void drawMlsNum(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.drawString("Missile Number:" + Integer.toString(mls.size()), 50, 50);
		g.setColor(c);
	}
}
