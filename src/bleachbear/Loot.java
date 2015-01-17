package bleachbear;

class Loot {
	private int x, y, item, bob;
	private boolean takeable, bobbed;
	
	Loot(int spawnX, int spawnY){
		x = spawnX;
		y = spawnY;
		bob = 0;
		takeable = true;
		bobbed = false;
		
		item = (int)(Math.random()*4);	//generate random loot item
	}
	
	public void bobbing(){
		if(bobbed)
			bob++;
		else
			bob--;
		
		y+=bob/4;
		
		if(bob==-8)
			bobbed=true;
		else if(bob==8)
			bobbed=false;
	}
	
	public int getItem(){
		return item;
	}
	
	public String getPath(){
		switch(item){
		case 0:
			return "bee";
		case 1:
			return "bleach";
		case 2:
			return "bomb";
		case 3:
			return "boots";
		}
		return "";
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean getStatus(){
		return takeable;
	}
	
	public void destroy(){
		takeable = false;
		//regenerate loot item
		//spawn another on map
	}
}