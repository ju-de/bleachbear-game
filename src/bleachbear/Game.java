package bleachbear;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class Game extends Applet implements Runnable, KeyListener{
	private Player p;
	private Image image, pSprite;
	private Graphics bg;
	private URL assets;
	
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
			bg = image.getGraphics();
		}
		
		//bg.fillRect(0, 0, getWidth(), getHeight());
		paint(bg);

		g.drawImage(image, 0, 0, this);
	}

	public void paint(Graphics g) {
		g.drawImage(pSprite, p.getX()-61, p.getY()-61, this);
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
}
