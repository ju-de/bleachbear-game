package bleachbear;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Game extends Applet implements Runnable, KeyListener{
	private Player p;
	private Image image;
	private Graphics graphics;
	
	public void init(){
		
		setSize(640, 360);
		setFocusable(true);
		
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle("Bleach Bear");
		
		super.init();
	}
	
	public void start(){
		p = new Player();
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		super.stop();
	}
	
	public void destroy(){
		super.destroy();
	}
	
	public void run(){
		while(true){
			repaint();
			
			try{
				Thread.sleep(17);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break; 
		case KeyEvent.VK_LEFT:
			break; 
		case KeyEvent.VK_RIGHT:
			break; 
		}
		
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	/*public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
		}
		
		paint(graphics);

		g.drawImage(image, 0, 0, this);

	}

	public void paint(Graphics g) {
		
	}*/
}
