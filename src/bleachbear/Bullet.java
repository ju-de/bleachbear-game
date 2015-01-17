package bleachbear;

public class Bullet {
	private int ammo, dmg, knockback; 
	
	Bullet(){
		ammo = 8;
		dmg = 1;
		knockback = 0;
	}
	
	public void boost(int steroid){
		knockback += steroid;
	}
	
	public boolean heavy(){
		if(knockback>0)
			return false;
		return true;
	}
	
	public void shoot(){
		ammo--;
	}
	
	public void reload(){
		ammo = 8;
	}
}
