package bleachbear;

import java.awt.Graphics;

public class Player{
	private int x, y, dx, dy, speed, jumpHeight;
	private boolean jumping;
	
	Player(){
		x = 100;
		y = 100;
		jumping = false;
		
		speed = 5;
		jumpHeight = 10;
	}
	
	public void move(){
		
		if(dx>200 || dx<200	)	//bounding box
			x+=dx;
		else if(dx!=0){}	//pan background
		
		if(y+dy<=100)	//above ground
			y+=dy;
		
		if(jumping){
			dy++;
			
			if(y+dy>=100){
				y=100;
				dy=0;
				jumping=false;
			}
		}
		
	}

	public void jump(){
		if(!(jumping)){	//not already jumping
			dy = jumpHeight;
			jumping = true;
		}
	}
	
	
}