package bleachbear;

public class Enemy{
	private int x, y, dx, dy, hp, dmg, speed; 
	
	Enemy(){
		x = 320;
		y = 207;
		dx = 0;
		dy = 0;
		hp = 5;
		dmg = 1;
		speed = 2;
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