package bleachbear;

public class Player{
	private int x, y, dx, dy, speed, jumpHeight, hp;
	private boolean airborne;
	
	Player(){
		x = 64;
		y = 192;
		dx = 0;
		dy = 0;
		hp = 3;
		airborne = false;
		
		speed = 3;
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
		dx = direction;
	}

	public void addJumpHeight(int height){
		jumpHeight += height;
	}
	
	public void addSpeed(int boost){
		speed += boost;
	}
	
	public void fillHP(){
		hp++;
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
	
	public boolean inAir(){
		return airborne;
	}
	
	public int getSpeed(){
		return speed;
	}
}