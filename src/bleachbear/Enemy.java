package bleachbear;

public class Enemy{
	private int x, y, dx, hp, dmg, speed; 
	
	Enemy(){
		x = 320;
		y = 207;
		dx = speed;
		hp = 5;
		dmg = 1;
		speed = 2;
	}
	
	public void move(){
		x += dx;
		
		if(x<20 || x>580)
			dx *= -1;	//change direction
	}
	
	public void turn(){
		dx = (int)(Math.random()*2-1)*speed;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void loseHP(int dmg){
		hp -= dmg;
	}
	
	public void knockedBack(int back){
		dx += back;
	}
	
	public void slow(int mud){
		if(speed>1)
			speed -= mud;
	}	
}