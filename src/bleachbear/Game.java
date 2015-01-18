package bleachbear;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class Game extends Applet implements Runnable, KeyListener{
	Player p;
	Enemy m, r;
	Loot i;
	ArrayList<Bullet> pew = new ArrayList<Bullet>();
	int ammo=0;
	Image image, pSprite, eMelee, eRange, lootThing, projectile;
	int pWidth=64, pHeight=80, pRow=0, pCol=0,
		mWidth=56, mHeight=64, mRow=0, mCol=0,
		rWidth=56, rHeight=64, rRow=0, rCol=0,
		iWidth=40, iHeight=40,
		bWidth=5, bHeight=5;
	Rectangle pBound, mBound, rBound, iBound, bBound;
	Graphics panel;
	URL assets;
	
	public void init(){	
		setSize(600, 360);
		setFocusable(true);
		addKeyListener(this);
		
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle("Bleach Bear");
		
		p = new Player();
		m = new Enemy();
		r = new Enemy();
		i = new Loot();
		for(int i=0; i<8; i++)
			pew.add(new Bullet());
		
		try {
			assets = getDocumentBase();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		projectile = getImage(assets, "bullet.png");
		pSprite = getImage(assets, "player.png");
		eMelee = getImage(assets, "melee.png");
		eRange = getImage(assets, "ranged.png");
		lootThing = getImage(assets, i.getPath()+".png");
	}
	
	public void start(){
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
			i.bobbing();
			
			//projectile
			for(int i=0; i<8; i++)
				if(pew.get(i).shot())
					pew.get(i).shoot();
			
			//item handling
			if(iCollision()){
				switch(i.getItem()){
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				}
				i.destroy();
				lootThing = getImage(assets, i.getPath()+".png");
			}
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
		
		for(int i=0; i<8; i++)
			g.drawImage(projectile, pew.get(i).getX(), pew.get(i).getY(), bWidth, bHeight, this); 
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
				if(ammo == 7)
					ammo = 0;
				
				if(!(pew.get(ammo).shot())){
					pew.get(ammo).trigger(p.getX()+2, p.getY(), pRow);
					ammo++;
				}
				
				System.out.println(ammo);
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
	
	public boolean iCollision(){	//touch object
		pBound = new Rectangle(p.getX(), p.getY(), pWidth-40, pHeight-10);
		iBound = new Rectangle(i.getX(), i.getY(), iWidth-12, iHeight-10);
		
		if(pBound.intersects(iBound))
			return true;
		
		return false;
	}
	
	/*public boolean bCollision(int i){	//touch bullet
		bBound = new Rectangle(pew.get(i).getX(), pew.get(i).getY(), bWidth-2, bHeight-2);
		eBound
		
		if(bBound.intersects(eBound))
			return true;
		
		return false;
	}*/
}