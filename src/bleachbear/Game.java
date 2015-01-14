package bleachbear;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class Game extends Applet implements Runnable, KeyListener{
	Player p;
	Image image, pSprite;
	int pWidth=64, pHeight=80, row=0, col=0;
	Graphics panel;
	URL assets;
	
	public void init(){
		setSize(600, 360);
		setFocusable(true);
		addKeyListener(this);
		
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle("Bleach Bear");
		
		try {
			assets = getDocumentBase();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		pSprite = getImage(assets, "player.png");
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
			p.move();
			repaint();
			
			try{
				Thread.sleep(17);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			panel = image.getGraphics();
		}
		
		panel.fillRect(0, 0, getWidth(), getHeight());
		paint(panel);

		g.drawImage(image, 0, 0, this);
	}

	public void paint(Graphics g) {
		g.drawImage(pSprite, p.getX(), p.getY(), p.getX()+pWidth, p.getY()+pHeight, pWidth*col, pHeight*row, pWidth+pWidth*col, pHeight+pHeight*row, this);	//image, size, part of image, listener
	}
		
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				p.jump();
				break;
			case KeyEvent.VK_LEFT:
				p.setDx(-1*p.getSpeed());
				row=4;
				break;
			case KeyEvent.VK_RIGHT:
				p.setDx(p.getSpeed());
				row=0;
				break;
		}	
	}
	
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				break;
			case KeyEvent.VK_LEFT:
				p.setDx(0);
				break;
			case KeyEvent.VK_RIGHT:
				p.setDx(0);
				break;
		}
	}
	public void keyTyped(KeyEvent e){}
}