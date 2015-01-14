package bleachbear;

import java.awt.Graphics;

public class Player{
	private int x, y, dx, dy, speed, jumpHeight;
	private boolean airborne;
	
	Player(){
		x = 64;
		y = 192;
		dx = 0;
		dy = 0;
		airborne = false;
		
		speed = 5;
		jumpHeight = 12;
	}
	
	public void move(){
		//x
		if(x+dx < 64) //out of bounding box
			x = 64;
		if(x+dx > 600-320)
			x = 600-320;
		else
			x += dx;
		
		//y

		y += dy;
		
		if(airborne){
			dy++;	//fall
			
			if(y+dy >= 192){
				y = 192;	//ground reached
				dy = 0;
				airborne = false;
			}
		}
	}
	
	public void setDx(int direction){
		dx=direction;
	}

	public void setJumpHeight(int height){
		jumpHeight+=height;
	}
	public void jump(){
		if(!(airborne)){	//not already jumping
			dy = -jumpHeight;
			airborne = true;
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	
}