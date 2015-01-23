package bleachbear;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
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
	Background bg;
	ArrayList<Bullet> pew = new ArrayList<Bullet>();
	int ammo = 0, maxAmmo = 6, tick = 0, direction = 0;
	Image image, pSprite, eMelee, eRange, lootThing, projectile, bDrop;
	int pWidth = 64, pHeight = 80, pRow = 0, pCol = 0,
		mWidth = 56, mHeight = 64, mRow = 0, mCol = 0,
		rWidth = 56, rHeight = 64, rRow = 0, rCol = 0,
		iWidth = 40, iHeight = 40,
		bWidth = 3,  bHeight = 3,  bCol = 0;
	Rectangle pBound, mBound, rBound, iBound, bBound;
	Graphics panel;
	URL assets;
	Font font;
	
	public void init(){	
		setSize(600, 360);
		setFocusable(true);
		addKeyListener(this);
		
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle("Bleach Bear");
		
		bg = new Background();
		p = new Player();
		m = new Enemy();
		r = new Enemy();
		i = new Loot();
		for(int i=0; i<maxAmmo; i++)
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
		bDrop = getImage(assets, "bg.png");
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
			m.move();
			r.move();
			i.bobbing();
				
			//item handling
			if(iCollision()){
				switch(i.getItem()){
				case 0:	//bee
					pew.get(0).boost(1);
					break;
				case 1:	//bleach
					p.fillHP();
					break;
				case 2:	//bomb
					ammo=0;
					break;
				case 3:	//boots
					p.speedBoost();
					break;
				}
				i.destroy();
				lootThing = getImage(assets, i.getPath()+".png");
			}
			
			//projectile
			for(int i=0; i<ammo; i++){
				if(bCollision()){
					pew.get(ammo).destroy();
					break;
				}
				else if(pew.get(i).shot()){	//move shot across screen
					bCol++;
					
					if(bCol == 2)
						bCol = 0;
					pew.get(i).shoot();
				}
			}
			
			//player animation
			tick++;
			if(tick%5 == 0){	//every 5 ticks
				switch(pRow){
				//idle
				case 0:
				case 4:
				//jump
				case 2:
				case 6:
				//shoot
				case 3:
				case 7:
					pCol++;
					repaint();
					if(pCol == 2)
						pCol = 0;
					break;
				//walk
				case 1:
				case 5:
					pCol++;
					repaint();
					if(pCol == 6)
						pCol = 0;
					break;
				}
			}
			
			//enemy animation
			if(tick%10 == 0){
				mCol++;
				if(mCol == 4)
					mCol = 0;
				
				rCol++;
				if(rCol == 4)
					rCol = 0;
			}
			repaint();
			
			try{
				Thread.sleep(17);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public void update(Graphics g){
		if(image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			panel = image.getGraphics();
		}
		panel.fillRect(0, 0, getWidth(), getHeight());
		paint(panel);

		g.drawImage(image, 0, 0, this);
	}

	public void paint(Graphics g){
		//g.drawImage(bDrop, bg.getX(), bg.getY(), 1600+bg.getX(), 270, this);
		if(ammo<maxAmmo)
			for(int i=0; i<maxAmmo; i++)
				g.drawImage(projectile, pew.get(i).getX(), pew.get(i).getY(), pew.get(i).getX()+bWidth, pew.get(i).getY()+bHeight,
				bWidth*bCol, 0, bWidth+bWidth*bCol, bHeight, this);
		
		if(ammo == 0)	//full ammo
			font = new Font("Dotum", Font.BOLD , 20);
		else
			font = new Font("Dotum", Font.PLAIN , 16);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(""+(maxAmmo-ammo), 600-40, 360-40);
		g.setColor(Color.BLACK);
		
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
				if(direction == 0)
					pRow = 2;
				else
					pRow = 6;
				p.jump();
				pCol = 0;
				bg.scroll(0, p.getJumpHeight()/2);
				break;
				
			case KeyEvent.VK_LEFT:
				p.setDx(-1*p.getSpeed());
				pRow = 5;
				direction = 1;
				if(!(p.inBox())){
					bg.scroll(p.getSpeed(), 0);
					m.scroll(p.getSpeed());
					r.scroll(p.getSpeed());
					i.scroll(p.getSpeed());
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				p.setDx(p.getSpeed());
				pRow = 1;
				direction = 0;
				if(!(p.inBox())){
					bg.scroll(-1*p.getSpeed(), 0);
					m.scroll(-1*p.getSpeed());
					r.scroll(-1*p.getSpeed());
					i.scroll(-1*p.getSpeed());
				}
				break;
				
			case KeyEvent.VK_SPACE:
				if(direction == 0){
					pRow = 3;
					pCol = 1;
				}
				else{
					pRow = 7;
					pCol = 0;
				}
				
				if(ammo<maxAmmo){
					pew.get(ammo).trigger(p.getX()+2, p.getY(), direction);
					ammo++;
				}		
				//System.out.println(ammo);
				break;
		}	
	}
	
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				if(direction == 0)
					pRow = 0;
				else
					pRow = 4;
				pCol = 0;
				break;
				
			case KeyEvent.VK_LEFT:
				p.setDx(0);
				pRow = 4;
				pCol = 0;
				break;
				
			case KeyEvent.VK_RIGHT:
				p.setDx(0);
				pRow = 0;
				pCol = 0;
				break;
				
			case KeyEvent.VK_SPACE:
				p.setDx(0);
				if(direction == 0){
					pRow = 0;
					pCol = 0;
				}
				else{
					pRow = 4;
					pCol = 1;
				}
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
	
	public boolean bCollision(){	//touch bullet
		if(ammo<maxAmmo){
			bBound = new Rectangle(pew.get(ammo).getX(), pew.get(ammo).getY(), bWidth, bHeight);
			mBound = new Rectangle(m.getX(), m.getY(), mWidth, mHeight);
			rBound = new Rectangle(r.getX(), r.getY(), rWidth, rHeight);
			
			if(bBound.intersects(mBound) || bBound.intersects(rBound))
				return true;
		}
		return false;
	}
}