package bleachbear;

class Loot {
	private int x, y, item, bob;
	private boolean bobbed;
	
	Loot(){
		x = spawnX();
		y = spawnY();
		bob = 0;
		bobbed = false;
		
		item = spawnItem();	//generate random loot item
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
	
	public void scroll(int dx){
		x += dx;
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
	
	public int spawnItem(){
		return (int)(Math.random()*4);
	}
	
	public int spawnX(){
		return (int)(Math.random()*400/2+100);
	}
	
	public int spawnY(){
		return 232;
	}
	
	public void destroy(){
		item = spawnItem();
		x = spawnX();
		y = spawnY();
	}
}