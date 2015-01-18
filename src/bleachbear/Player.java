package bleachbear;

public class Player{
	private int x, y, dx, dy, speed, jumpHeight, hp;
	private boolean airborne, within;
	
	Player(){
		x = 64;
		y = 192;
		dx = 0;
		dy = 0;
		hp = 3;
		airborne = false;
		within = true;
		
		speed = 3;
		jumpHeight = 10;
	}
	
	public void move(){
		//x
		if(x+dx < 64){ //out of bounding box
			x = 64;
			within = false;
		}
		else if(x+dx > 600-320){
			x = 600-320;
			within = false;
		}
		else{
			x += dx;
			within = true;
		}
		
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

	public void speedBoost(){
		if(speed<7)
			speed ++;
	}
	
	public void fillHP(){
		if(hp<5)
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
	
	public boolean inBox(){
		return within;
	}
}