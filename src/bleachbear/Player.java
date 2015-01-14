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
		jumpHeight = 10;
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
		if(y+dy >= 360-192)	//above ground
			y = 360-192;
		else
			y += dy;
		
		if(airborne){
			dy++;	//fall
			
			if(y+dy >= 192-jumpHeight){
				y = 192-jumpHeight;	//maxHeight reached
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