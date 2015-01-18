package bleachbear;

public class Bullet {
	private int dmg, knockback, x, y, direction;
	private boolean shooting;
	
	Bullet(){
		dmg = 1;
		knockback = 1;
		x = -100;
		y = 240;
		direction = 0;
		shooting = false;
	}
	
	public void shoot(){
		if(x>600)
			this.destroy();
		
		if(direction==0)
			x += 10;
		else
			x -= 10;
	}
	
	public void boost(int steroid){
		knockback += steroid;
	}
	
	public boolean heavy(){
		if(knockback>0)
			return false;
		return true;
	}
	
	public void trigger(int pX, int pY, int facing){
		x = pX;
		y = pY+50;
		shooting = true;
		direction = facing;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean shot(){
		return shooting;
	}
	
	public void destroy(){
		x = -100;
		shooting = false;
	}
		
}