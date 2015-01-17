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
	Enemy m, r;
	Loot i = new Loot(124, 232);
	Image image, pSprite, eMelee, eRange, lootThing;
	int pWidth=64, pHeight=80, pRow=0, pCol=0,
		mWidth=56, mHeight=64, mRow=0, mCol=0,
		rWidth=56, rHeight=64, rRow=0, rCol=0,
		iWidth=40, iHeight=40;
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
		eMelee = getImage(assets, "melee.png");
		eRange = getImage(assets, "ranged.png");
		lootThing = getImage(assets, "bee.png");
		lootThing = getImage(assets, i.getPath()+".png");
	}
	
	public void start(){
		p = new Player();
		m = new Enemy();
		r = new Enemy();
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
		g.drawImage(pSprite, p.getX(), p.getY(), p.getX()+pWidth, p.getY()+pHeight,
				pWidth*pCol, pHeight*pRow, pWidth+pWidth*pCol, pHeight+pHeight*pRow, this);
				//image, size, part of image, listener
		g.drawImage(eMelee, m.getX(), m.getY(), m.getX()+mWidth, m.getY()+mHeight,
				mWidth*mCol, mHeight*mRow, mWidth+mWidth*mCol, mHeight+mHeight*mRow, this);
		g.drawImage(eRange, r.getX()+80, r.getY(), r.getX()+rWidth+80, r.getY()+rHeight,
				rWidth*rCol, rHeight*rRow, rWidth+rWidth*rCol, rHeight+rHeight*rRow, this);
		
		g.drawImage(lootThing, i.getX(), i.getY(), iWidth, iHeight, this);
	}
		
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				p.jump();
				break;
			case KeyEvent.VK_LEFT:
				p.setDx(-1*p.getSpeed());
				pRow=4;
				break;
			case KeyEvent.VK_RIGHT:
				p.setDx(p.getSpeed());
				pRow=0;
				break;
			case KeyEvent.VK_SPACE:
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
			case KeyEvent.VK_SPACE:
				break;
		}
	}
	
	public void keyTyped(KeyEvent e){}
}